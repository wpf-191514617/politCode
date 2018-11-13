package com.zodiac.polit.bean;

import java.util.List;

public class CityBean {

    /**
     * code : 310000
     * Name : 上海市
     * children : [{"code":"310101","name":"上海市","children":[{"code":"310114","name":"嘉定区"},{"code":"310120","name":"奉贤区"},{"code":"310113","name":"宝山区"},{"code":"310104","name":"徐汇区"},{"code":"310107","name":"普陀区"},{"code":"310110","name":"杨浦区"},{"code":"310117","name":"松江区"},{"code":"310115","name":"浦东新区"},{"code":"310109","name":"虹口区"},{"code":"310116","name":"金山区"},{"code":"310105","name":"长宁区"},{"code":"310112","name":"闵行区"},{"code":"310108","name":"闸北区"},{"code":"310118","name":"青浦区"},{"code":"310106","name":"静安区"},{"code":"310101","name":"黄浦区"}]}]
     */

    private String code;
    private String name;
    private boolean isCheck;
    private List<ChildrenBeanX> children;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChildrenBeanX> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBeanX> children) {
        this.children = children;
    }

    public static class ChildrenBeanX {
        /**
         * code : 310100
         * Name : 上海市
         * children : [{"code":"310114","name":"嘉定区"},{"code":"310120","name":"奉贤区"},{"code":"310113","name":"宝山区"},{"code":"310104","name":"徐汇区"},{"code":"310107","name":"普陀区"},{"code":"310110","name":"杨浦区"},{"code":"310117","name":"松江区"},{"code":"310115","name":"浦东新区"},{"code":"310109","name":"虹口区"},{"code":"310116","name":"金山区"},{"code":"310105","name":"长宁区"},{"code":"310112","name":"闵行区"},{"code":"310108","name":"闸北区"},{"code":"310118","name":"青浦区"},{"code":"310106","name":"静安区"},{"code":"310101","name":"黄浦区"}]
         */

        private String code;
        private String name;
        private boolean isCheck;
        private List<ChildrenBean> children;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {
            /**
             * code : 310114
             * Name : 嘉定区
             */

            private String code;
            private String name;
            private boolean isCheck;

            public boolean isCheck() {
                return isCheck;
            }

            public void setCheck(boolean check) {
                isCheck = check;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
