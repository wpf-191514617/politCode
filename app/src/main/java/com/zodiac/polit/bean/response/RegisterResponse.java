package com.zodiac.polit.bean.response;

/**
 * Created by john on 2018/9/29.
 */

public class RegisterResponse {


    /**
     * code : 0
     * message : 成功
     * content : {"id":"1000010","isNewRecord":false,"createDate":"2018-09-29 20:36:57","updateBy":{"id":"1000010","isNewRecord":false,"pageNo":0,"pageSize":0,"loginFlag":"1","admin":false,"roleNames":""},"updateDate":"2018-09-29 20:36:57","pageNo":0,"pageSize":0,"phone":"13890000000","realName":"嘉兴","cardId":"610323199112255508","userAgent":"PC","lastVisitTime":"2018-09-29 20:36:57","lastVisitIp":"1.25.110.199","password":"000000"}
     * sessionId : d7bc3cbad6d7497b9622b9b77ff63b3d
     */

    private String code;
    private String message;
    private ContentBean content;
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

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public static class ContentBean {
        /**
         * id : 1000010
         * isNewRecord : false
         * createDate : 2018-09-29 20:36:57
         * updateBy : {"id":"1000010","isNewRecord":false,"pageNo":0,"pageSize":0,"loginFlag":"1","admin":false,"roleNames":""}
         * updateDate : 2018-09-29 20:36:57
         * pageNo : 0
         * pageSize : 0
         * phone : 13890000000
         * realName : 嘉兴
         * cardId : 610323199112255508
         * userAgent : PC
         * lastVisitTime : 2018-09-29 20:36:57
         * lastVisitIp : 1.25.110.199
         * password : 000000
         */

        private String id;
        private boolean isNewRecord;
        private String createDate;
        private UpdateByBean updateBy;
        private String updateDate;
        private int pageNo;
        private int pageSize;
        private String phone;
        private String realName;
        private String cardId;
        private String userAgent;
        private String lastVisitTime;
        private String lastVisitIp;
        private String password;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isIsNewRecord() {
            return isNewRecord;
        }

        public void setIsNewRecord(boolean isNewRecord) {
            this.isNewRecord = isNewRecord;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }

        public UpdateByBean getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(UpdateByBean updateBy) {
            this.updateBy = updateBy;
        }

        public String getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(String updateDate) {
            this.updateDate = updateDate;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
        }

        public String getUserAgent() {
            return userAgent;
        }

        public void setUserAgent(String userAgent) {
            this.userAgent = userAgent;
        }

        public String getLastVisitTime() {
            return lastVisitTime;
        }

        public void setLastVisitTime(String lastVisitTime) {
            this.lastVisitTime = lastVisitTime;
        }

        public String getLastVisitIp() {
            return lastVisitIp;
        }

        public void setLastVisitIp(String lastVisitIp) {
            this.lastVisitIp = lastVisitIp;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public static class UpdateByBean {
            /**
             * id : 1000010
             * isNewRecord : false
             * pageNo : 0
             * pageSize : 0
             * loginFlag : 1
             * admin : false
             * roleNames :
             */

            private String id;
            private boolean isNewRecord;
            private int pageNo;
            private int pageSize;
            private String loginFlag;
            private boolean admin;
            private String roleNames;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public boolean isIsNewRecord() {
                return isNewRecord;
            }

            public void setIsNewRecord(boolean isNewRecord) {
                this.isNewRecord = isNewRecord;
            }

            public int getPageNo() {
                return pageNo;
            }

            public void setPageNo(int pageNo) {
                this.pageNo = pageNo;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public String getLoginFlag() {
                return loginFlag;
            }

            public void setLoginFlag(String loginFlag) {
                this.loginFlag = loginFlag;
            }

            public boolean isAdmin() {
                return admin;
            }

            public void setAdmin(boolean admin) {
                this.admin = admin;
            }

            public String getRoleNames() {
                return roleNames;
            }

            public void setRoleNames(String roleNames) {
                this.roleNames = roleNames;
            }
        }
    }
}
