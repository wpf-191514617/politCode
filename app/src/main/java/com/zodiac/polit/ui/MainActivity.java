package com.zodiac.polit.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.minilive.library.entity.EventData;
import com.minilive.library.network.callback.StringCallback;
import com.minilive.library.util.BaseAppManager;
import com.minilive.library.util.StringUtils;
import com.minilive.library.util.Trace;
import com.minilive.library.view.MainNavigateTabBar;
import com.zodiac.polit.Constant;
import com.zodiac.polit.R;
import com.zodiac.polit.http.provider.OtherProvider;
import com.zodiac.polit.ui.activity.SignupActivity;
import com.zodiac.polit.ui.activity.user.LoginActivity;
import com.zodiac.polit.ui.fragment.HomeFragment;
import com.zodiac.polit.ui.fragment.MineFragment;
import com.zodiac.polit.ui.fragment.PolicyFragment;
import com.zodiac.polit.ui.fragment.QuestionChildFragment;
import com.zodiac.polit.ui.fragment.QuestionFragment;
import com.zodiac.polit.ui.fragment.SignupFragment;
import com.zodiac.polit.ui.fragment.home.QueryFragment;
import com.zodiac.polit.util.CacheHelper;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;
import okhttp3.Call;

public class MainActivity extends AppCompatActivity {

    private FrameLayout main_container;
    private MainNavigateTabBar navigateTabBar;

    private final int REQUEST_LOGIN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BaseAppManager.getInstance().addActivity(this);
        EventBus.getDefault().register(this);
        main_container = findViewById(R.id.main_container);
        navigateTabBar = findViewById(R.id.navigateTabBar);
        navigateTabBar.setFragmentActivity(this);
        navigateTabBar.onRestoreInstanceState(savedInstanceState);
        navigateTabBar.addTab(HomeFragment.class, new MainNavigateTabBar.TabParam(R.drawable
                .foot_home, R.drawable.foot_home_1, R.string.tab_home));

        navigateTabBar.addTab(QueryFragment.class, new MainNavigateTabBar.TabParam(R.drawable
                .query1, R.drawable.query, R.string.query));

        navigateTabBar.addTab(SignupFragment.class, new MainNavigateTabBar.TabParam(R.drawable
                .foot_baoming, R.drawable.foot_baoming_1, R.string.signup));

        navigateTabBar.addTab(QuestionFragment.class, new MainNavigateTabBar.TabParam(R.drawable
                .foot_wenti, R.drawable.foot_wenti_1, R.string.question));

        navigateTabBar.addTab(MineFragment.class, new MainNavigateTabBar.TabParam(R.drawable
                .foot_user, R.drawable.foot_user_1, R.string.mine));

        navigateTabBar.setTabSelectListener(new MainNavigateTabBar.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(MainNavigateTabBar.ViewHolder holder) {


                mCurrentIndex = holder.tabIndex;

                if (mCurrentIndex == 2) {
                    if (!CacheHelper.getInstance().isLogin()) {
                        startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), REQUEST_LOGIN);
                    } else {
                        startActivity(new Intent(MainActivity.this, SignupActivity.class));
                    }
                    return false;
                }

                if (mCurrentIndex == 4){
                    if (!CacheHelper.getInstance().isLogin()) {
                        startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), REQUEST_LOGIN);
                    }
                    return true;
                }

                if (mCurrentIndex == 0) {
                    EventBus.getDefault().post(new EventData(Constant.CODE_SELECTED));
                }


                /*if (holder.tabIndex == 2 || holder.tabIndex == 4) {
                    if (!CacheHelper.getInstance().isLogin())
                        startActivityForResult(new Intent(MainActivity.this, LoginActivity.class), REQUEST_LOGIN);

                }

                if (mCurrentIndex == 2) {
                    return false;
                }*/

                /*if (mCurrentIndex == 0) {
                    EventBus.getDefault().post(new EventData(Constant.CODE_SELECTED));
                }
                if (holder.tabIndex == 2 || holder.tabIndex == 4) {
                    if (!CacheHelper.getInstance().isLogin()) {
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                return true;
            }
                    if (holder.tabIndex == 2) {
                startActivity(new Intent(MainActivity.this, SignupActivity.class));
                return false;
            }
        }*/
                return true;
            }
        });

    }

    private int mCurrentIndex = 0;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //保存当前选中的选项状态
        navigateTabBar.onSaveInstanceState(outState);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LOGIN) {
            if (resultCode == RESULT_OK) {
                if (mCurrentIndex == 2) {
                    startActivity(new Intent(MainActivity.this, SignupActivity.class));
                    return;
                }
                navigateTabBar.setCurrentSelectedTab(mCurrentIndex);
            } else {
                mCurrentIndex = 0;
                navigateTabBar.setCurrentSelectedTab(0);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseAppManager.getInstance().removeActivity(this);
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onEventMainThread(EventData data) {
        if (data.getCODE() == Constant.CODE_LOGOUT) {
            CacheHelper.getInstance().clear();
            if (data.getData().toString().equals("0")) {
                mCurrentIndex = 0;
                navigateTabBar.setCurrentSelectedTab(0);
            }
            /*mCurrentIndex = 0;
            navigateTabBar.setCurrentSelectedTab(0);*/

        } else if (data.getCODE() == Constant.CODE_LOGIN) {
            // 获取数据字典
            getConstantData();
            // navigateTabBar.setCurrentSelectedTab(mCurrentIndex);
        } else if (data.getCODE() == Constant.CODE_INVALID) {
            mCurrentIndex = 0;
            navigateTabBar.setCurrentSelectedTab(0);
            CacheHelper.getInstance().clear();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }

    private void getConstantData() {
        OtherProvider.getConstantData(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                Trace.d("res------");
            }

            @Override
            public void onResponse(String response, int id) {
                if (!StringUtils.isEmpty(response)) {
                    CacheHelper.getInstance().putDicData(response);
                }
            }
        });
    }

    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
