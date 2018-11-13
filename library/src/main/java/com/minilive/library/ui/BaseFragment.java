package com.minilive.library.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.minilive.library.entity.EventData;
import com.minilive.library.util.Trace;
import com.minilive.library.viewcontroller.VaryViewHelperController;

import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

/**
 * Created by Administrator on 2018/1/8.
 */

public abstract class BaseFragment extends Fragment {

    protected String TAG = getClass().getSimpleName();

    private boolean isFirstResume = true;
    private boolean isFirstVisible = true;
    private boolean isFirstInVisible = true;

    private boolean isPrepared;

    private VaryViewHelperController mVaryViewHelperController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isRegisterEventBus()) {
            EventBus.getDefault().register(this);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Trace.d("fragment" , "----------onCreateView");
        if (getContentViewLayoutID() != 0) {
            return inflater.inflate(getContentViewLayoutID(), container, false);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Trace.d("fragment" , "----------onViewCreated");
        if (null != getLoadingTargetView()) {
            mVaryViewHelperController = new VaryViewHelperController(getLoadingTargetView());
        }
        initViewAndData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isRegisterEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPrepare();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstResume) {
            isFirstResume = false;
        }
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            onUserInVisible();
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            } else {
                onUserVisible();
            }
        } else {
            if (isFirstInVisible) {
                isFirstInVisible = false;
                onFirstUserInVisible();
            } else {
                onUserInVisible();
            }
        }
    }


    /**
     * 是否注册EventBus
     *
     * @return
     */
    protected abstract boolean isRegisterEventBus();

    /**
     * 设置 layoutID
     *
     * @return
     */
    protected abstract int getContentViewLayoutID();

    /**
     * ViewController 根View
     *
     * @return
     */
    protected abstract View getLoadingTargetView();

    protected abstract void initViewAndData();

    /**
     * 当前fragment可见时调用， like    onResume
     */
    protected abstract void onUserVisible();

    /**
     * like onPause
     */
    protected abstract void onUserInVisible();

    /**
     * 第一次 当fragment是不可见的时候
     */
    protected abstract void onFirstUserInVisible();

    /**
     * fragment 第一次 可见的时候， 做初始化 或 数据的刷新（只调用一次）
     */
    protected abstract void onFirstUserVisible();


    protected abstract void onEventComming(EventData eventData);


    private synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
        } else {
            isPrepared = true;
        }
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
        Intent intent = new Intent(getActivity(), clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void jumpToForResult(Class<?> clazz, int requestCode) {
        jumpToForResult(clazz, requestCode, null);
    }

    protected void jumpToForResult(Class<?> clazz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
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
        getActivity().finish();
    }


    protected void showToast(int resourceId) {
        try {
            Toast.makeText(getActivity(), resourceId, Toast.LENGTH_SHORT).show();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    protected void showToast(String text) {
        try {
            Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }


    /**
     * toggle show loading
     *
     * @param toggle
     */
    protected void toggleShowLoading(boolean toggle, String msg) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewHelperController.showLoading(msg, this);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    protected void toggleShowDefaultView(View view){
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("必须传入一个目标视图加载控制器");
        }
        mVaryViewHelperController.showCustomDefaultView(view);
    }

    /**
     * toggle show empty
     *
     * @param toggle
     */
    protected void toggleShowEmpty(boolean toggle, String msg, View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewHelperController.showEmpty(msg, onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    protected void toggleShowEmpty(boolean toggle , String msg , int imgResourceId , View
            .OnClickListener onClickListener){
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("必须传入一个目标视图加载控制器");
        }
        if (toggle){
            mVaryViewHelperController.showEmpty(msg , imgResourceId , onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }


    /**
     * toggle show error
     *
     * @param toggle
     */
    protected void toggleShowError(boolean toggle, String msg, View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewHelperController.showError(msg, onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }


    /**
     * toggle show network error
     *
     * @param toggle
     */
    protected void toggleNetworkError(boolean toggle, View.OnClickListener onClickListener) {
        if (null == mVaryViewHelperController) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }

        if (toggle) {
            mVaryViewHelperController.showNetworkError(onClickListener);
        } else {
            mVaryViewHelperController.restore();
        }
    }

    protected void reStoreView() {
        if (null != mVaryViewHelperController) {
            mVaryViewHelperController.restore();
        }
    }

    @Subscribe
    public void onEventMainThread(EventData eventData) {
        if (null != eventData) {
            onEventComming(eventData);
        }
    }


}
