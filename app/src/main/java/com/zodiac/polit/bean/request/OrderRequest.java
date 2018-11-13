package com.zodiac.polit.bean.request;

/**
 * Created by john on 2018/10/13.
 */

public class OrderRequest {


    /**
     * applayYear : 2018
     * studentType : 1
     * office : {"id":"4"}
     */

    private String applayYear;
    private String studentType;
    private OfficeBean office;

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

    public static class OfficeBean {
        /**
         * id : 4
         */

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
