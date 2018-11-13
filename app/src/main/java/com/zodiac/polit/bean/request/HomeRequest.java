package com.zodiac.polit.bean.request;

/**
 * Created by john on 2018/9/24.
 */

public class HomeRequest {


    /**
     * category : {"id":1}
     * areaCode : {"areaCode":1}
     * pageNo : 1
     * pageSize : 10
     */

    private CategoryBean category;
    private AreaCodeBean areaCode;
    private int pageNo;
    private int pageSize;

    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
    }

    public AreaCodeBean getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(AreaCodeBean areaCode) {
        this.areaCode = areaCode;
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

    public static class CategoryBean {
        /**
         * id : 1
         */

        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    public static class AreaCodeBean {
        /**
         * areaCode : 1
         */

        private String areaCode;

        public String getAreaCode() {
            return areaCode;
        }

        public void setAreaCode(String areaCode) {
            this.areaCode = areaCode;
        }
    }
}
