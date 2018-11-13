package com.zodiac.polit.bean.response;

/**
 * Created by john on 2018/9/24.
 */

public class ConnectUsResponse {


    /**
     * id : 2
     * isNewRecord : false
     * createDate : 2018-09-03 18:28:22
     * updateBy : {"id":"1","isNewRecord":false,"pageNo":0,"pageSize":0,"loginFlag":"1","admin":true,"roleNames":""}
     * updateDate : 2018-09-03 18:28:27
     * pageNo : 0
     * pageSize : 0
     * parentIds : 0,1
     * name : 招飞总局
     * sort : 10
     * area : {"id":"1","isNewRecord":false,"pageNo":0,"pageSize":0,"parentIds":"0","name":"中国区域","sort":30,"parentId":"0"}
     * code : 2001001
     * type : 2
     * grade : 1
     * address : 北京市海淀区闵行路25号
     * zipCode : 100195
     * phone : 010-66921803
     * email : a@163.com
     * useable : 1
     * province : ,
     * parentId : 1
     * remarks :
     * master : 东北
     * fax :
     * primaryPerson : {"id":"","isNewRecord":true,"pageNo":0,"pageSize":0,"loginFlag":"1","admin":false,"roleNames":""}
     * deputyPerson : {"id":"","isNewRecord":true,"pageNo":0,"pageSize":0,"loginFlag":"1","admin":false,"roleNames":""}
     */

    private String id;
    private boolean isNewRecord;
    private String createDate;
    private UpdateByBean updateBy;
    private String updateDate;
    private int pageNo;
    private int pageSize;
    private String parentIds;
    private String name;
    private int sort;
    private AreaBean area;
    private String code;
    private String type;
    private String grade;
    private String address;
    private String zipCode;
    private String phone;
    private String email;
    private String useable;
    private String province;
    private String parentId;
    private String remarks;
    private String master;
    private String fax;
    private PrimaryPersonBean primaryPerson;
    private DeputyPersonBean deputyPerson;

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

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
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

    public AreaBean getArea() {
        return area;
    }

    public void setArea(AreaBean area) {
        this.area = area;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUseable() {
        return useable;
    }

    public void setUseable(String useable) {
        this.useable = useable;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public PrimaryPersonBean getPrimaryPerson() {
        return primaryPerson;
    }

    public void setPrimaryPerson(PrimaryPersonBean primaryPerson) {
        this.primaryPerson = primaryPerson;
    }

    public DeputyPersonBean getDeputyPerson() {
        return deputyPerson;
    }

    public void setDeputyPerson(DeputyPersonBean deputyPerson) {
        this.deputyPerson = deputyPerson;
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

    public static class AreaBean {
        /**
         * id : 1
         * isNewRecord : false
         * pageNo : 0
         * pageSize : 0
         * parentIds : 0
         * name : 中国区域
         * sort : 30
         * parentId : 0
         */

        private String id;
        private boolean isNewRecord;
        private int pageNo;
        private int pageSize;
        private String parentIds;
        private String name;
        private int sort;
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

        public String getParentIds() {
            return parentIds;
        }

        public void setParentIds(String parentIds) {
            this.parentIds = parentIds;
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

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    }

    public static class PrimaryPersonBean {
        /**
         * id :
         * isNewRecord : true
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

    public static class DeputyPersonBean {
        /**
         * id :
         * isNewRecord : true
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
