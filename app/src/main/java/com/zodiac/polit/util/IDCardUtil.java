package com.zodiac.polit.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by john on 2018/10/13.
 */

public class IDCardUtil {

    /** 中国公民身份证号码最小长度。 */
    public  final int CHINA_ID_MIN_LENGTH = 15;

    /** 中国公民身份证号码最大长度。 */
    public  final int CHINA_ID_MAX_LENGTH = 18;
    /**
     * 根据身份编号获取年龄
     *
     * @param idCard
     *            身份编号
     * @return 年龄
     */
    public static int getAgeByIdCard(String idCard) {
        int iAge = 0;
        Calendar cal = Calendar.getInstance();
        String year = idCard.substring(6, 10);
        int iCurrYear = cal.get(Calendar.YEAR);
        iAge = iCurrYear - Integer.valueOf(year);
        return iAge;
    }

    /**
     * 根据身份编号获取生日
     *
     * @param idCard 身份编号
     * @return 生日(yyyyMMdd)
     */
    public static String getBirthByIdCard(String idCard) {
        return idCard.substring(6, 14);
    }

    /**
     * 根据身份编号获取生日年
     *
     * @param idCard 身份编号
     * @return 生日(yyyy)
     */
    public static Short getYearByIdCard(String idCard) {
        return Short.valueOf(idCard.substring(6, 10));
    }

    /**
     * 根据身份编号获取生日月
     *
     * @param idCard
     *            身份编号
     * @return 生日(MM)
     */
    public static String getMonthByIdCard(String idCard) {
        return idCard.substring(10, 12);
    }

    /**
     * 根据身份编号获取生日天
     *
     * @param idCard
     *            身份编号
     * @return 生日(dd)
     */
    public static String getDateByIdCard(String idCard) {
        return idCard.substring(12, 14);
    }

    /**
     * 根据身份编号获取性别
     *
     * @param idCard 身份编号
     * @return 性别(M-男，F-女，N-未知)
     */
    public static String getGenderByIdCard(String idCard) {
        String sGender = "未知";

        String sCardNum = idCard.substring(16, 17);
        if (Integer.parseInt(sCardNum) % 2 != 0) {
            sGender = "1";//男
        } else {
            sGender = "2";//女
        }
        return sGender;
    }


    /**
     * 校验18位身份证号
     *
     * @param identityCode
     *
     * 返回true则表示校验通过
     */
    public static boolean checkIdentityCode(String identityCode) {
        // 校验身份证位数为18位
        if (!identityCode.matches("\\d{17}(\\d|x|X)$")) {
            return false;
        }
        Date d = new Date();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        int year = Integer.parseInt(df.format(d));
        if (Integer.parseInt(identityCode.substring(6, 10)) < 1900
                || Integer.parseInt(identityCode.substring(6, 10)) > year) {// 7-10位是出生年份，范围应该在1900-当前年份之间
            return false;
        }
        if (Integer.parseInt(identityCode.substring(10, 12)) < 1
                || Integer.parseInt(identityCode.substring(10, 12)) > 12) {// 11-12位代表出生月份，范围应该在01-12之间
            return false;
        }
        if (Integer.parseInt(identityCode.substring(12, 14)) < 1
                || Integer.parseInt(identityCode.substring(12, 14)) > 31) {// 13-14位是出生日期，范围应该在01-31之间
            return false;
        }
        // 校验第18位
        // S = Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和
        // Ai:表示第i位置上的身份证号码数字值
        // Wi:表示第i位置上的加权因子
        // Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
        String[] tempA = identityCode.split("|");
        int[] a = new int[18];
        for (int i = 0; i < tempA.length - 2; i++) {
            a[i] = Integer.parseInt(tempA[i + 1]);
        }
        int[] w = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 }; // 加权因子
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum = sum + a[i] * w[i];
        }
        // Y = mod(S, 11)
        // 通过模得到对应的校验码
        // Y: 0 1 2 3 4 5 6 7 8 9 10
        // 校验码: 1 0 X 9 8 7 6 5 4 3 2
        String[] v = { "1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2" }; // 校验码
        int y = sum % 11;
        if (!v[y].equalsIgnoreCase(identityCode.substring(17))) {// 第18位校验码错误
            return false;
        }
        return true;
    }


}
