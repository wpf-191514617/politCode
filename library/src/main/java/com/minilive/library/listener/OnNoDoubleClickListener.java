package com.minilive.library.listener;

import android.view.View;

/**
 * Created by Administrator on 2018/1/2.
 *
 *  view的单次点击事件监听
 *
 */

public abstract class OnNoDoubleClickListener implements View.OnClickListener {

    // 间隔时间
    private long mThrottleFirstTime = 1000;
    // 用来记录上次点击时间
    private long mLastClickTime = 0;

    public OnNoDoubleClickListener(){

    }

    public OnNoDoubleClickListener(int throttleFirstTime){
        mThrottleFirstTime = throttleFirstTime;
    }


    @Override
    public void onClick(View view) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - mLastClickTime > mThrottleFirstTime) {
            mLastClickTime = currentTime;
            onSingleClick(view);
        }
    }

    // 单次点击事件回调
    protected abstract void onSingleClick(View view);

}
