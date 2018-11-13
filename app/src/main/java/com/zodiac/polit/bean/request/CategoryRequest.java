package com.zodiac.polit.bean.request;

/**
 * Created by john on 2018/9/24.
 */

public class CategoryRequest {


    /**
     * category : {"id":75}
     */

    private CategoryBean category;

    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
    }

    public static class CategoryBean {
        /**
         * id : 75
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
