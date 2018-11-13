package com.zodiac.polit.bean.response;

/**
 * Created by john on 2018/10/13.
 */

public class SendSMSCodeResponse {


    /**
     * code : 0
     * message : 验证码已发送成功
     * sessionId : 944652c8397546cd9c0b78e609b10d04
     */

    private String code;
    private String message;
    private String sessionId;

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

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
