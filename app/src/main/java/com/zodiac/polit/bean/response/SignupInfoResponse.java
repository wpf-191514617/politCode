package com.zodiac.polit.bean.response;

/**
 * Created by john on 2018/10/8.
 */

public class SignupInfoResponse {


    /**
     * code : 0
     * message : 成功
     * content : {"id":"2","isNewRecord":false,"createDate":"2018-10-07 23:01:49","updateBy":{"id":"1000012","isNewRecord":false,"pageNo":0,"pageSize":0,"loginFlag":"1","admin":false,"roleNames":""},"updateDate":"2018-10-07 23:01:49","pageNo":0,"pageSize":0,"pic":"images/picture.png","nation":"1","politicsType":"2","isOneChild":"0","householdRegistration":"1","schoolProvince":"2808","schoolCity":"2856","schoolArea":"2857","schoolName":"渭南师范学院","school":"-1","homeProvince":"2808","homeCity":"2841","homeArea":"2843","homeAddress":"五泉镇","fatherName":"","fatherPhone":"","motherName":"","motherPhone":"","teacherName":"","teacherPhone":"","presentPrevious":"","profession":"","department":"","artsSciences":"","estimateHighSchool":"","estimateMiddleSchool":"","applayProvince":"464","memberApplayTask":{"id":"1","isNewRecord":false,"pageNo":0,"pageSize":0,"applayYear":"2018","studentType":"1","office":{"id":"3","isNewRecord":false,"pageNo":0,"pageSize":0,"name":"沈阳招飞中心","sort":30,"type":"2","province":"649,579,464","parentId":"0"},"applayDateStart":"2018-10-01","applayDateEnd":"2018-10-31","isEffective":"1"},"applayYear":"2018","studentType":"1","office":{"id":"3","isNewRecord":false,"pageNo":0,"pageSize":0,"name":"沈阳招飞中心","sort":30,"type":"2","province":"649,579,464","parentId":"0"},"cardId":"61040319900308201X","phone":"13892537712","realName":"高先生","birthday":"1990-03-08","sex":"男","provinceSum":0,"ageSUM":0,"schoolSum":0}
     */

    private String code;
    private String message;
    private ContentBean content;

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

    public static class ContentBean {
        /**
         * id : 2
         * isNewRecord : false
         * createDate : 2018-10-07 23:01:49
         * updateBy : {"id":"1000012","isNewRecord":false,"pageNo":0,"pageSize":0,"loginFlag":"1","admin":false,"roleNames":""}
         * updateDate : 2018-10-07 23:01:49
         * pageNo : 0
         * pageSize : 0
         * pic : images/picture.png
         * nation : 1
         * politicsType : 2
         * isOneChild : 0
         * householdRegistration : 1
         * schoolProvince : 2808
         * schoolCity : 2856
         * schoolArea : 2857
         * schoolName : 渭南师范学院
         * school : -1
         * homeProvince : 2808
         * homeCity : 2841
         * homeArea : 2843
         * homeAddress : 五泉镇
         * fatherName :
         * fatherPhone :
         * motherName :
         * motherPhone :
         * teacherName :
         * teacherPhone :
         * presentPrevious :
         * profession :
         * department :
         * artsSciences :
         * estimateHighSchool :
         * estimateMiddleSchool :
         * applayProvince : 464
         * memberApplayTask : {"id":"1","isNewRecord":false,"pageNo":0,"pageSize":0,"applayYear":"2018","studentType":"1","office":{"id":"3","isNewRecord":false,"pageNo":0,"pageSize":0,"name":"沈阳招飞中心","sort":30,"type":"2","province":"649,579,464","parentId":"0"},"applayDateStart":"2018-10-01","applayDateEnd":"2018-10-31","isEffective":"1"}
         * applayYear : 2018
         * studentType : 1
         * office : {"id":"3","isNewRecord":false,"pageNo":0,"pageSize":0,"name":"沈阳招飞中心","sort":30,"type":"2","province":"649,579,464","parentId":"0"}
         * cardId : 61040319900308201X
         * phone : 13892537712
         * realName : 高先生
         * birthday : 1990-03-08
         * sex : 男
         * provinceSum : 0
         * ageSUM : 0
         * schoolSum : 0
         */

        private String id;
        private boolean isNewRecord;
        private String createDate;
        private UpdateByBean updateBy;
        private String updateDate;
        private int pageNo;
        private int pageSize;
        private String pic;
        private String nation;
        private String politicsType;
        private String isOneChild;
        private String householdRegistration;
        private String schoolProvince;
        private String schoolCity;
        private String schoolArea;
        private String schoolName;
        private String school;
        private String homeProvince;
        private String homeCity;
        private String homeArea;
        private String homeAddress;
        private String fatherName;
        private String fatherPhone;
        private String motherName;
        private String motherPhone;
        private String teacherName;
        private String teacherPhone;
        private String presentPrevious;
        private String profession;
        private String department;
        private String artsSciences;
        private String estimateHighSchool;
        private String estimateMiddleSchool;
        private String applayProvince;
        private MemberApplayTaskBean memberApplayTask;
        private String applayYear;
        private String studentType;
        private OfficeBeanX office;
        private String cardId;
        private String phone;
        private String realName;
        private String birthday;
        private String sex;
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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getNation() {
            return nation;
        }

        public void setNation(String nation) {
            this.nation = nation;
        }

        public String getPoliticsType() {
            return politicsType;
        }

        public void setPoliticsType(String politicsType) {
            this.politicsType = politicsType;
        }

        public String getIsOneChild() {
            return isOneChild;
        }

        public void setIsOneChild(String isOneChild) {
            this.isOneChild = isOneChild;
        }

        public String getHouseholdRegistration() {
            return householdRegistration;
        }

        public void setHouseholdRegistration(String householdRegistration) {
            this.householdRegistration = householdRegistration;
        }

        public String getSchoolProvince() {
            return schoolProvince;
        }

        public void setSchoolProvince(String schoolProvince) {
            this.schoolProvince = schoolProvince;
        }

        public String getSchoolCity() {
            return schoolCity;
        }

        public void setSchoolCity(String schoolCity) {
            this.schoolCity = schoolCity;
        }

        public String getSchoolArea() {
            return schoolArea;
        }

        public void setSchoolArea(String schoolArea) {
            this.schoolArea = schoolArea;
        }

        public String getSchoolName() {
            return schoolName;
        }

        public void setSchoolName(String schoolName) {
            this.schoolName = schoolName;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getHomeProvince() {
            return homeProvince;
        }

        public void setHomeProvince(String homeProvince) {
            this.homeProvince = homeProvince;
        }

        public String getHomeCity() {
            return homeCity;
        }

        public void setHomeCity(String homeCity) {
            this.homeCity = homeCity;
        }

        public String getHomeArea() {
            return homeArea;
        }

        public void setHomeArea(String homeArea) {
            this.homeArea = homeArea;
        }

        public String getHomeAddress() {
            return homeAddress;
        }

        public void setHomeAddress(String homeAddress) {
            this.homeAddress = homeAddress;
        }

        public String getFatherName() {
            return fatherName;
        }

        public void setFatherName(String fatherName) {
            this.fatherName = fatherName;
        }

        public String getFatherPhone() {
            return fatherPhone;
        }

        public void setFatherPhone(String fatherPhone) {
            this.fatherPhone = fatherPhone;
        }

        public String getMotherName() {
            return motherName;
        }

        public void setMotherName(String motherName) {
            this.motherName = motherName;
        }

        public String getMotherPhone() {
            return motherPhone;
        }

        public void setMotherPhone(String motherPhone) {
            this.motherPhone = motherPhone;
        }

        public String getTeacherName() {
            return teacherName;
        }

        public void setTeacherName(String teacherName) {
            this.teacherName = teacherName;
        }

        public String getTeacherPhone() {
            return teacherPhone;
        }

        public void setTeacherPhone(String teacherPhone) {
            this.teacherPhone = teacherPhone;
        }

        public String getPresentPrevious() {
            return presentPrevious;
        }

        public void setPresentPrevious(String presentPrevious) {
            this.presentPrevious = presentPrevious;
        }

        public String getProfession() {
            return profession;
        }

        public void setProfession(String profession) {
            this.profession = profession;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getArtsSciences() {
            return artsSciences;
        }

        public void setArtsSciences(String artsSciences) {
            this.artsSciences = artsSciences;
        }

        public String getEstimateHighSchool() {
            return estimateHighSchool;
        }

        public void setEstimateHighSchool(String estimateHighSchool) {
            this.estimateHighSchool = estimateHighSchool;
        }

        public String getEstimateMiddleSchool() {
            return estimateMiddleSchool;
        }

        public void setEstimateMiddleSchool(String estimateMiddleSchool) {
            this.estimateMiddleSchool = estimateMiddleSchool;
        }

        public String getApplayProvince() {
            return applayProvince;
        }

        public void setApplayProvince(String applayProvince) {
            this.applayProvince = applayProvince;
        }

        public MemberApplayTaskBean getMemberApplayTask() {
            return memberApplayTask;
        }

        public void setMemberApplayTask(MemberApplayTaskBean memberApplayTask) {
            this.memberApplayTask = memberApplayTask;
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

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
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

        public static class UpdateByBean {
            /**
             * id : 1000012
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

        public static class MemberApplayTaskBean {
            /**
             * id : 1
             * isNewRecord : false
             * pageNo : 0
             * pageSize : 0
             * applayYear : 2018
             * studentType : 1
             * office : {"id":"3","isNewRecord":false,"pageNo":0,"pageSize":0,"name":"沈阳招飞中心","sort":30,"type":"2","province":"649,579,464","parentId":"0"}
             * applayDateStart : 2018-10-01
             * applayDateEnd : 2018-10-31
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
                 * province : 649,579,464
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

        public static class OfficeBeanX {
            /**
             * id : 3
             * isNewRecord : false
             * pageNo : 0
             * pageSize : 0
             * name : 沈阳招飞中心
             * sort : 30
             * type : 2
             * province : 649,579,464
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
}
