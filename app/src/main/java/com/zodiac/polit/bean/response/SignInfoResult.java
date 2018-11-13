package com.zodiac.polit.bean.response;

/**
 * Created by john on 2018/10/12.
 */

public class SignInfoResult {


    /**
     * id : 1
     * isNewRecord : false
     * remarks :
     * createDate : 2018-10-08 00:07:56
     * updateBy : {"id":"1","isNewRecord":false,"pageNo":0,"pageSize":0,"name":"system","loginFlag":"1","admin":true,"roleNames":""}
     * updateDate : 2018-10-08 00:07:56
     * pageNo : 0
     * pageSize : 0
     * memberApplay : {"id":"2","isNewRecord":false,"pageNo":0,"pageSize":0,"memberApplayTask":{"id":"1","isNewRecord":false,"pageNo":0,"pageSize":0,"applayYear":"2018","studentType":"1","office":{"id":"3","isNewRecord":false,"pageNo":0,"pageSize":0,"name":"沈阳招飞中心","sort":30,"type":"2","parentId":"0"},"applayDateStart":"2018-10-01","applayDateEnd":"2018-10-31","applayDateResult":"2018-11-01","isEffective":"1"},"office":{"id":"3","isNewRecord":false,"pageNo":0,"pageSize":0,"name":"沈阳招飞中心","sort":30,"type":"2","parentId":"0"},"cardId":"61040319900308201X","phone":"13892537712","realName":"高先生","provinceSum":0,"ageSUM":0,"schoolSum":0}
     * realName : 高先生
     * cardId : 61040319900308201X
     * phone : 13892537712
     * applayYear : 2018
     * studentType : 1
     * office : {"id":"3","isNewRecord":false,"pageNo":0,"pageSize":0,"name":"沈阳招飞中心","sort":30,"type":"2","parentId":"0"}
     * phaseName : 初选
     * phaseStatus : 已通过
     * phaseMsg : 已通过初级审核
     * showed : active
     * currented : 1
     * sort : 0
     */

    private String id;
    private boolean isNewRecord;
    private String remarks;
    private String createDate;
    private UpdateByBean updateBy;
    private String updateDate;
    private int pageNo;
    private int pageSize;
    private MemberApplayBean memberApplay;
    private String realName;
    private String cardId;
    private String phone;
    private String applayYear;
    private String studentType;
    private OfficeBeanXX office;
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

    public MemberApplayBean getMemberApplay() {
        return memberApplay;
    }

    public void setMemberApplay(MemberApplayBean memberApplay) {
        this.memberApplay = memberApplay;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public OfficeBeanXX getOffice() {
        return office;
    }

    public void setOffice(OfficeBeanXX office) {
        this.office = office;
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
         * name : system
         * loginFlag : 1
         * admin : true
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

    public static class MemberApplayBean {
        /**
         * id : 2
         * isNewRecord : false
         * pageNo : 0
         * pageSize : 0
         * memberApplayTask : {"id":"1","isNewRecord":false,"pageNo":0,"pageSize":0,"applayYear":"2018","studentType":"1","office":{"id":"3","isNewRecord":false,"pageNo":0,"pageSize":0,"name":"沈阳招飞中心","sort":30,"type":"2","parentId":"0"},"applayDateStart":"2018-10-01","applayDateEnd":"2018-10-31","applayDateResult":"2018-11-01","isEffective":"1"}
         * office : {"id":"3","isNewRecord":false,"pageNo":0,"pageSize":0,"name":"沈阳招飞中心","sort":30,"type":"2","parentId":"0"}
         * cardId : 61040319900308201X
         * phone : 13892537712
         * realName : 高先生
         * provinceSum : 0
         * ageSUM : 0
         * schoolSum : 0
         */

        private String id;
        private boolean isNewRecord;
        private int pageNo;
        private int pageSize;
        private MemberApplayTaskBean memberApplayTask;
        private OfficeBeanX office;
        private String cardId;
        private String phone;
        private String realName;
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

        public MemberApplayTaskBean getMemberApplayTask() {
            return memberApplayTask;
        }

        public void setMemberApplayTask(MemberApplayTaskBean memberApplayTask) {
            this.memberApplayTask = memberApplayTask;
        }

        public OfficeBeanX getOffice() {
            return office;
        }

        public void setOffice(OfficeBeanX office) {
            this.office = office;
        }

        public String getCardId() {
            return cardId;
        }

        public void setCardId(String cardId) {
            this.cardId = cardId;
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

        public static class MemberApplayTaskBean {
            /**
             * id : 1
             * isNewRecord : false
             * pageNo : 0
             * pageSize : 0
             * applayYear : 2018
             * studentType : 1
             * office : {"id":"3","isNewRecord":false,"pageNo":0,"pageSize":0,"name":"沈阳招飞中心","sort":30,"type":"2","parentId":"0"}
             * applayDateStart : 2018-10-01
             * applayDateEnd : 2018-10-31
             * applayDateResult : 2018-11-01
             * isEffective : 1
             */

            private String id;
            private boolean isNewRecord;
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

            public static class OfficeBean {
                /**
                 * id : 3
                 * isNewRecord : false
                 * pageNo : 0
                 * pageSize : 0
                 * name : 沈阳招飞中心
                 * sort : 30
                 * type : 2
                 * parentId : 0
                 */

                private String id;
                private boolean isNewRecord;
                private int pageNo;
                private int pageSize;
                private String name;
                private int sort;
                private String type;
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

                public String getParentId() {
                    return parentId;
                }

                public void setParentId(String parentId) {
                    this.parentId = parentId;
                }
            }
        }

        public static class OfficeBeanX {
            /**
             * id : 3
             * isNewRecord : false
             * pageNo : 0
             * pageSize : 0
             * name : 沈阳招飞中心
             * sort : 30
             * type : 2
             * parentId : 0
             */

            private String id;
            private boolean isNewRecord;
            private int pageNo;
            private int pageSize;
            private String name;
            private int sort;
            private String type;
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

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }
        }
    }

    public static class OfficeBeanXX {
        /**
         * id : 3
         * isNewRecord : false
         * pageNo : 0
         * pageSize : 0
         * name : 沈阳招飞中心
         * sort : 30
         * type : 2
         * parentId : 0
         */

        private String id;
        private boolean isNewRecord;
        private int pageNo;
        private int pageSize;
        private String name;
        private int sort;
        private String type;
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

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    }
}
