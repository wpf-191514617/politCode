package com.zodiac.polit;

import com.baidu.location.BDLocation;

/**
 * Created by john on 2018/9/23.
 */

public class Constant {

    public static final String CACHE_NAME = "polit.cache";
    public static String cityCode = "110000";
    public static BDLocation location;

    //public static String BASEURL = "http://service.kjzfw.mil.cn";

    public static String BASEURL = "http://211.166.16.69:80";

    public static int CODE_SIGN = 0xA02;
    public static int CODE_LOGOUT = 0xA03;
    public static final int CODE_UPDATEINFO = 0xA04;
    public static final int CODE_LOGIN = 0xA05;
    public static final int CODE_INVALID = 0xA06;
    public static final int CODE_CHANGED = 0xA07;
    public static final int CODE_SELECTED = 0xA08;
    public static final int CODE_CITY = 0xA09;
    public static final int CODE_CITY1 = 0xA10;
}
