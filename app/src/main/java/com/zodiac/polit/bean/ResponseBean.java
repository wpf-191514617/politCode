package com.zodiac.polit.bean;

/**
 * Created by john on 2018/9/30.
 */

public class ResponseBean {


    /**
     * code : 1
     * message : 会话超时，请重新登录！
     */

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
