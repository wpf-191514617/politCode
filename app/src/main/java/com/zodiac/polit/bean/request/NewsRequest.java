package com.zodiac.polit.bean.request;

/**
 * Created by john on 2018/9/24.
 */

public class NewsRequest {


    /**
     * ids :
     * areaCode : {"areaCode":"areaCode"}
     * pageNo : 1
     * pageSize : 1
     */

    private String ids;
    private AreaCodeBean areaCode;
    private int pageNo;
    private int pageSize;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
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

    public static class AreaCodeBean {
        /**
         * areaCode : areaCode
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
