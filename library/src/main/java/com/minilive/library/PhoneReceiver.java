package com.minilive.library;

import android.app.Activity;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.util.SimpleArrayMap;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import com.minilive.library.net.NetChangeObserver;

public class PhoneReceiver extends BroadcastReceiver {

    private static SimpleArrayMap<Activity, PhoneChangeObserver> mPhoneChangeObservers;
    private static BroadcastReceiver broadcastReceiver;
    private boolean isCallPhone = false;
    private int phoneState;
    public final int CALL_PHONE_STATE = 3;

    public static BroadcastReceiver getReceive() {
        if (broadcastReceiver == null) {
            broadcastReceiver = new PhoneReceiver();
        }
        return broadcastReceiver;
    }

    public static void registerPhoneStateReceiver(Activity activity) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
        filter.addAction("android.intent.action.PHONE_STATE");
        activity.registerReceiver(getReceive(), filter);
    }

    public static void unRegisterPhoneStateReceiver(Activity activity) {
        try {
            if (broadcastReceiver != null) {
                activity.unregisterReceiver(broadcastReceiver);
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (TextUtils.equals(intent.getAction(), Intent.ACTION_NEW_OUTGOING_CALL)) {
            phoneState = CALL_PHONE_STATE;
            isCallPhone = true;
        } else {
            //查了下android文档，貌似没有专门用于接收来电的action,所以，非去电即来电.
            //如果我们想要监听电话的拨打状况，需要这么几步 :
            /** 第一：获取电话服务管理器TelephonyManager manager = this.getSystemService(TELEPHONY_SERVICE);
             * 第二：通过TelephonyManager注册我们要监听的电话状态改变事件。manager.listen(new MyPhoneStateListener(),
             * PhoneStateListener.LISTEN_CALL_STATE);这里的PhoneStateListener.LISTEN_CALL_STATE就是我们想要
             * 监听的状态改变事件，初次之外，还有很多其他事件哦。
             * 第三步：通过extends PhoneStateListener来定制自己的规则。将其对象传递给第二步作为参数。
             * 第四步：这一步很重要，那就是给应用添加权限。android.permission.READ_PHONE_STATE
             */
            isCallPhone = false;
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
            tm.listen(listener, PhoneStateListener.LISTEN_CALL_STATE);

        }
        notifyObserver((Activity) context);
    }

    PhoneStateListener listener = new PhoneStateListener() {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            //注意，方法必须写在super方法后面，否则incomingNumber无法获取到值。
            super.onCallStateChanged(state, incomingNumber);
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    System.out.println("挂断");
                    phoneState = TelephonyManager.CALL_STATE_IDLE;
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    System.out.println("接听");
                    phoneState = TelephonyManager.CALL_STATE_OFFHOOK;
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    System.out.println("响铃:来电号码" + incomingNumber);
                    phoneState = TelephonyManager.CALL_STATE_RINGING;
                    //输出来电号码
                    break;
            }
        }
    };

    private void notifyObserver(Activity activity) {
        if (mPhoneChangeObservers.isEmpty()) {
            return;
        }
        PhoneChangeObserver phoneChangeObserver = mPhoneChangeObservers.get(activity);
        if (phoneChangeObserver == null) {
            return;
        }
        if (isCallPhone) {
            phoneChangeObserver.callPhoneState();
        } else {
            phoneChangeObserver.phoneStateChange(phoneState);
        }
    }

    public static void registerObserver(Activity activity, PhoneChangeObserver observer) {
        if (mPhoneChangeObservers == null) {
            mPhoneChangeObservers = new SimpleArrayMap<>();
        }
        mPhoneChangeObservers.put(activity, observer);
    }

    public static void removeRegisterObserver(Activity activity, PhoneChangeObserver observer) {
        if (mPhoneChangeObservers != null) {
            if (mPhoneChangeObservers.containsValue(observer)) {
                mPhoneChangeObservers.remove(activity);
            }
        }
    }

}
