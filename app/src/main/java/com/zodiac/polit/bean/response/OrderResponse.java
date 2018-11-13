package com.zodiac.polit.bean.response;

/**
 * Created by john on 2018/10/13.
 */

public class OrderResponse {


    /**
     * id : 1
     * isNewRecord : false
     * remarks : zongju01test01
     * createDate : 2018-10-07 20:52:20
     * updateBy : {"id":"2","isNewRecord":false,"pageNo":0,"pageSize":0,"name":"zongju01","loginFlag":"1","admin":false,"roleNames":""}
     * updateDate : 2018-10-07 21:10:09
     * pageNo : 0
     * pageSize : 0
     * applayYear : 2018
     * studentType : 1
     * office : {"id":"4","isNewRecord":false,"pageNo":0,"pageSize":0,"name":"北京招飞中心","sort":30,"type":"2","province":"北京市,河北省,内蒙古自治区,山西省,天津市","parentId":"0"}
     * applayDateStart : 2018-10-08
     * applayDateEnd : 2018-10-30
     * applayDateResult : 2018-11-01
     * isEffective : 1
     */

    private String id;
    private boolean isNewRecord;
    private String remarks;
    private String createDate;
    private UpdateByBean updateBy;
    private String updateDate;
    private int pageNo;
    private int pageSize;
    private String applayYear;
    private String studentType;
    private OfficeBean office;
    private String applayDateStart;
    private String applayDateEnd;
    private String applayDateResult;
    private String isEffective;

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public String getApplayYear() {
        return applayYear;
    }

    public void setApplayYear(String applayYear) {
        this.applayYear = applayYear;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }

    public OfficeBean getOffice() {
        return office;
    }

    public void setOffice(OfficeBean office) {
        this.office = office;
    }

    public String getApplayDateStart() {
        return applayDateStart;
    }

    public void setApplayDateStart(String applayDateStart) {
        this.applayDateStart = applayDateStart;
    }

    public String getApplayDateEnd() {
        return applayDateEnd;
    }

    public void setApplayDateEnd(String applayDateEnd) {
        this.applayDateEnd = applayDateEnd;
    }

    public String getApplayDateResult() {
        return applayDateResult;
    }

    public void setApplayDateResult(String applayDateResult) {
        this.applayDateResult = applayDateResult;
    }

    public String getIsEffective() {
        return isEffective;
    }

    public void setIsEffective(String isEffective) {
        this.isEffective = isEffective;
    }

    public static class UpdateByBean {
        /**
         * id : 2
         * isNewRecord : false
         * pageNo : 0
         * pageSize : 0
         * name : zongju01
         * loginFlag : 1
         * admin : false
         * roleNames :
         */

        private String id;
        private boolean isNewRecord;
        private int pageNo;
        private int pageSize;
        private String name;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

    public static class OfficeBean {
        /**
         * id : 4
         * isNewRecord : false
         * pageNo : 0
         * pageSize : 0
         * name : 北京招飞中心
         * sort : 30
         * type : 2
         * province : 北京市,河北省,内蒙古自治区,山西省,天津市
         * parentId : 0
         */

        private String id;
        private boolean isNewRecord;
        private int pageNo;
        private int pageSize;
        private String name;
        private int sort;
        private String type;
        private String province;
        private String parentId;

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    }
}
