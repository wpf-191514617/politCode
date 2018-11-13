package com.minilive.library.util;

import android.util.Log;

/**
 * Created by Administrator on 2018/1/10.
 *
 *  Log 日志工具类
 *
 */

public class Trace {

    private static final String TAG = "miniLive";

    private static boolean isDebug = true;

    public static void e(String msg){
        e(TAG , msg);
    }

    public static void e(String tag , String msg){
        if (isDebug){
            Log.e(tag , msg);
        }
    }

    public static void i(String msg){
        i(TAG , msg);
    }

    public static void i(String tag , String msg){
        if (isDebug){
            Log.i(tag , msg);
        }
    }

    public static void d(String msg){
        d(TAG , msg);
    }

    public static void d(String tag , String msg){
        if (isDebug){
            Log.d(tag , msg);
        }
    }

}
