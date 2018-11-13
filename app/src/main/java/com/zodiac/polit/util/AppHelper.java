package com.zodiac.polit.util;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.zodiac.polit.bean.RequestBean;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by john on 2018/9/29.
 */

public class AppHelper {

    public static RequestBean getRequestBean() {
        String sessionId = CacheHelper.getInstance().getCurrentUser().getSessionId();
        String time = String.valueOf(System.currentTimeMillis());
        String signature = MD5(sessionId+time);
        RequestBean requestBean = new RequestBean();
        requestBean.sessionId = sessionId;
        requestBean.timestamp = time;
        requestBean.signature = signature;
        return requestBean;
    }



    /**
     * 32位MD5加密的大写字符串
     *
     * @param s
     * @return
     */
    private final static String MD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
