package com.zodiac.polit.bean.request;

/**
 * Created by john on 2018/10/6.
 */

public class VisitorRequest {


    /**
     * title :
     * visitorIp :
     * visitorAddr :
     * visitorOffice :
     * user : {"id":"","name":""}
     */

    private String title;
    private String visitorIp;
    private String visitorAddr;
    private String visitorOffice;
    private UserBean user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVisitorIp() {
        return visitorIp;
    }

    public void setVisitorIp(String visitorIp) {
        this.visitorIp = visitorIp;
    }

    public String getVisitorAddr() {
        return visitorAddr;
    }

    public void setVisitorAddr(String visitorAddr) {
        this.visitorAddr = visitorAddr;
    }

    public String getVisitorOffice() {
        return visitorOffice;
    }

    public void setVisitorOffice(String visitorOffice) {
        this.visitorOffice = visitorOffice;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * id :
         * name :
         */

        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
