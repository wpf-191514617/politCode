package com.zodiac.polit.bean.response;

/**
 * Created by john on 2018/10/10.
 */

public class ApplyStatusResponse {


    /**
     * id : 1
     * isNewRecord : false
     * createDate : 2018-10-08 00:07:56
     * updateBy : {"id":"1","isNewRecord":false,"pageNo":0,"pageSize":0,"loginFlag":"1","admin":true,"roleNames":""}
     * updateDate : 2018-10-08 00:07:56
     * pageNo : 0
     * pageSize : 0
     * memberApplay : {"id":"2","isNewRecord":false,"pageNo":0,"pageSize":0,"provinceSum":0,"ageSUM":0,"schoolSum":0}
     * phaseName : 初选
     * phaseStatus : 已通过
     * phaseMsg : 已通过初级审核
     * showed : active
     * currented : 1
     * sort : 0
     */

    private String id;
    private boolean isNewRecord;
    private String createDate;
    private UpdateByBean updateBy;
    private String updateDate;
    private int pageNo;
    private int pageSize;
    private MemberApplayBean memberApplay;
    private String phaseName;
    private String phaseStatus;
    private String phaseMsg;
    private String showed;
    private String currented;
    private int sort;

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

    public MemberApplayBean getMemberApplay() {
        return memberApplay;
    }

    public void setMemberApplay(MemberApplayBean memberApplay) {
        this.memberApplay = memberApplay;
    }

    public String getPhaseName() {
        return phaseName;
    }

    public void setPhaseName(String phaseName) {
        this.phaseName = phaseName;
    }

    public String getPhaseStatus() {
        return phaseStatus;
    }

    public void setPhaseStatus(String phaseStatus) {
        this.phaseStatus = phaseStatus;
    }

    public String getPhaseMsg() {
        return phaseMsg;
    }

    public void setPhaseMsg(String phaseMsg) {
        this.phaseMsg = phaseMsg;
    }

    public String getShowed() {
        return showed;
    }

    public void setShowed(String showed) {
        this.showed = showed;
    }

    public String getCurrented() {
        return currented;
    }

    public void setCurrented(String currented) {
        this.currented = currented;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public static class UpdateByBean {
        /**
         * id : 1
         * isNewRecord : false
         * pageNo : 0
         * pageSize : 0
         * loginFlag : 1
         * admin : true
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

    public static class MemberApplayBean {
        /**
         * id : 2
         * isNewRecord : false
         * pageNo : 0
         * pageSize : 0
         * provinceSum : 0
         * ageSUM : 0
         * schoolSum : 0
         */

        private String id;
        private boolean isNewRecord;
        private int pageNo;
        private int pageSize;
        private int provinceSum;
        private int ageSUM;
        private int schoolSum;

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

        public int getProvinceSum() {
            return provinceSum;
        }

        public void setProvinceSum(int provinceSum) {
            this.provinceSum = provinceSum;
        }

        public int getAgeSUM() {
            return ageSUM;
        }

        public void setAgeSUM(int ageSUM) {
            this.ageSUM = ageSUM;
        }

        public int getSchoolSum() {
            return schoolSum;
        }

        public void setSchoolSum(int schoolSum) {
            this.schoolSum = schoolSum;
        }
    }
}
