package com.minilive.library.listener;

import android.view.View;

/**
 * Created by Administrator on 2018/1/2.
 * View双击事件的监听
 */

public abstract class OnDoubleClickListener implements View.OnClickListener {

    // 间隔时间
    private long mThrottleFirstTime = 500;
    // 用来记录上次点击时间
    private long mLastClickTime = 0;

    public OnDoubleClickListener(){

    }

    public OnDoubleClickListener(int throttleFirstTime){
        mThrottleFirstTime = throttleFirstTime;
    }


    @Override
    public void onClick(View view) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - mLastClickTime < mThrottleFirstTime){
            mLastClickTime = currentTime;
            onDoubleClick(view);
        }
    }

    public abstract void onDoubleClick(View view);

}
