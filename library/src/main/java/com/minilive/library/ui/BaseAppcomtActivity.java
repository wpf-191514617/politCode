package com.minilive.library.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.minilive.library.PhoneChangeObserver;
import com.minilive.library.PhoneReceiver;
import com.minilive.library.entity.EventData;
import com.minilive.library.net.NetChangeObserver;
import com.minilive.library.net.NetStateReceiver;
import com.minilive.library.net.NetType;
import com.minilive.library.util.BaseAppManager;
import com.minilive.library.util.NavigationBarUtil;
import com.minilive.library.viewcontroller.VaryViewHelperController;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by Administrator on 2018/1/8.
 */

public abstract class BaseAppcomtActivity extends AppCompatActivity {

    /**
     * 网络状态
     */
    protected NetChangeObserver mNetChangeObserver = null;
    protected PhoneChangeObserver mPhoneChangeObserver = null;
    private final int INIT = 1985;

    /**
     * view controller
     */
    private VaryViewHelperController mVaryViewHelperController = null;
    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case INIT:
                    initConfig();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContentViewLayoutId() != 0) {
            setContentView(getContentViewLayoutId());
        }
        handler.sendEmptyMessage(INIT);
    }

    private void initConfig() {
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
        if (isRegisterEventBus()) {
            EventBus.getDefault().register(this);
        }

        BaseAppManager.getInstance().addActivity(this);
        mNetChangeObserver = new NetChangeObserver() {

            @Override
            public void onNetConnected(NetType type) {
                super.onNetConnected(type);
                onNetworkConnected(type);
            }

            @Override
            public void onNetDisConnect() {
                super.onNetDisConnect();
                onNetworkDisConnected();
            }
        };
        mPhoneChangeObserver = new PhoneChangeObserver() {
            @Override
            public void phoneStateChange(int state) {
                onAnswerPhone(state);
            }

            @Override
            public void callPhoneState() {
                onCallPhone();
            }
        };
        NetStateReceiver.registerObserver(this, mNetChangeObserver);
        PhoneReceiver.registerObserver(this, mPhoneChangeObserver);
        initViewAndData();
    }

    /**
     * 是否注册 EventBus
     *
     * @return
     */
    protected abstract boolean isRegisterEventBus();

    /**
     * 初始化View和数据
     */
    protected abstract void initViewAndData();

    /**
     * Bundle 传递数据
     *
     * @param extras
     */
    protected abstract void getBundleExtras(Bundle extras);

    /**
     * setContentView(R.layout...)
     *
     * @return
     */
    protected abstract int getContentViewLayoutId();


    /**
     * 当前没有网络连接
     */
    protected void onNetworkDisConnected() {
    }

    /**
     * 当前网络连接类型
     *
     * @param type
     */
    protected void onNetworkConnected(NetType type) {
    }

    /**
     * 当前是用户拨打电话
     */
    protected void onCallPhone() {
    }

    /**
     * 当前用户正在接听电话
     *
     * @param phoneState
     */
    protected void onAnswerPhone(int phoneState) {
    }

    /**
     * 切换根View   findViewById(R.id...);
     *
     * @return
     */
    protected abstract View getLoadingTargetView();


    /**
     * EventBus
     *
     * @param data
     */
    protected abstract void onEventComming(EventData data);


    @Override
    public void setContentView(int layoutResId) {
        super.setContentView(layoutResId);
        if (null != getLoadingTargetView()) {
            mVaryViewHelperController = new VaryViewHelperController(getLoadingTargetView());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        NetStateReceiver.removeRegisterObserver(this, mNetChangeObserver);
        PhoneReceiver.removeRegisterObserver(this, mPhoneChangeObserver);
        if (isRegisterEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        BaseAppManager.getInstance().removeActivity(this);
    }

    /**
     * startActivity
     *
     * @param clazz
     */
    protected void jumpTo(Class<?> clazz) {
        jumpTo(clazz, null);
    }

    protected void jumpTo(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void jumpToForResult(Class<?> clazz, int requestCode) {
        jumpToForResult(clazz, requestCode, null);
    }

    protected void jumpToForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    protected void jumpToThenKill(Class<?> clazz) {
        jumpToThenKill(clazz, null);
    }

    protected void jumpToThenKill(Class<?> clazz, Bundle bundle) {
        jumpTo(clazz, bundle);
        this.finish();
    }

    protected void showToast(int resourceId) {
        showToast(getString(resourceId));
    }

    protected void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    protected void reStoreView() {
        if (null != mVaryViewHelperController) {
            mVaryViewHelperController.restore();
        }
    }

    /**
     * 显示  正在加载界面
     *
     * @param toggle
     * @param msg
     */
    protected void toggleShowLoading(boolean toggle, String msg) {

        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("必须传入一个目标视图加载控制器");
        }

        if (toggle) {
            mVaryViewHelperController.showLoading(msg, this);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    /**
     * 显示返回内容为空界面
     *
     * @param toggle
     * @param msg
     * @param onClickListener
     */
    protected void toggleShowEmpty(boolean toggle, String msg, View.OnClickListener
            onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("必须传入一个目标视图加载控制器");
        }

        if (toggle) {
            mVaryViewHelperController.showEmpty(msg, onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    /**
     * 显示加载失败页面
     *
     * @param toggle
     * @param msg
     * @param onClickListener
     */
    protected void toggleShowError(boolean toggle, String msg, View.OnClickListener
            onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("必须传入一个目标视图加载控制器");
        }

        if (toggle) {
            mVaryViewHelperController.showError(msg, onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    /**
     * 无网络连接时   显示当前界面
     *
     * @param toggle
     * @param onClickListener
     */
    protected void toggleNetworkError(boolean toggle, View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("必须传入一个目标视图加载控制器");
        }

        if (toggle) {
            mVaryViewHelperController.showNetworkError(onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }

    }

    protected void toggleShowEmpty(boolean toggle, String msg, int imgResourceId, View
            .OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("必须传入一个目标视图加载控制器");
        }
        if (toggle) {
            mVaryViewHelperController.showEmpty(msg, imgResourceId, onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }


    protected void toggleShowDefaultView(View view) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("必须传入一个目标视图加载控制器");
        }
        mVaryViewHelperController.showCustomDefaultView(view);
    }

    @Subscribe
    public void onEventMainThread(EventData data) {

        if (null != data) {
            onEventComming(data);
        }

    }


    /**
     * set status bar translucency
     *
     * @param on
     */
    protected void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

}
