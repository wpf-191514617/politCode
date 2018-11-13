package com.zodiac.polit.bean.request;

/**
 * Created by john on 2018/9/29.
 */

public class UpdatePwdRequest {

    public String sessionId;
    public String timestamp;
    public String signature;
    public String oldPassword;
    public String newPassword;
    public String confirmNewPassword;
}
