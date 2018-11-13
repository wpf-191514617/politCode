package com.zodiac.polit.bean.response;

import java.util.List;

/**
 * Created by john on 2018/9/24.
 */

public class BannerResponse {


    /**
     * id : 89
     * isNewRecord : false
     * createDate : 2018-09-22 17:14:16
     * updateBy : {"id":"1","isNewRecord":false,"pageNo":0,"pageSize":0,"loginFlag":"1","admin":true,"roleNames":""}
     * updateDate : 2018-09-22 17:14:16
     * pageNo : 0
     * pageSize : 0
     * category : {"id":"75","isNewRecord":false,"pageNo":0,"pageSize":0,"name":"手机-广告图","sort":30,"module":"","inMenu":"0","inList":"1","showModes":"0","allowComment":"0","isAudit":"0","hits":0,"url":"/airforce/front/list-75.html","ids":"75","root":false,"parentId":"0"}
     * title : 广告图3
     * link :
     * color :
     * image :
     * keywords : 广告图3
     * description : 广告图3
     * weight : 0
     * hits : 0
     * posid : ,1,2,
     * file : /airforce/userfiles/1/files/cms/article/2018/09/banner.jpg
     * releaseObject : 1,2
     * user : {"isNewRecord":true,"pageNo":0,"pageSize":0,"name":"system","loginFlag":"1","admin":false,"roleNames":""}
     * url : /airforce/front/view-75-89.html
     * posidList : ["1","2"]
     * imageSrc :
     * filesSrc : /airforce/userfiles/1/files/cms/article/2018/09/banner.jpg
     */

    private String id;
    private boolean isNewRecord;
    private String createDate;
    private UpdateByBean updateBy;
    private String updateDate;
    private int pageNo;
    private int pageSize;
    private CategoryBean category;
    private String title;
    private String link;
    private String color;
    private String image;
    private String keywords;
    private String description;
    private int weight;
    private int hits;
    private String posid;
    private String file;
    private String releaseObject;
    private UserBean user;
    private String url;
    private String imageSrc;
    private String filesSrc;
    private List<String> posidList;

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

    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public String getPosid() {
        return posid;
    }

    public void setPosid(String posid) {
        this.posid = posid;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getReleaseObject() {
        return releaseObject;
    }

    public void setReleaseObject(String releaseObject) {
        this.releaseObject = releaseObject;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getFilesSrc() {
        return filesSrc;
    }

    public void setFilesSrc(String filesSrc) {
        this.filesSrc = filesSrc;
    }

    public List<String> getPosidList() {
        return posidList;
    }

    public void setPosidList(List<String> posidList) {
        this.posidList = posidList;
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

    public static class CategoryBean {
        /**
         * id : 75
         * isNewRecord : false
         * pageNo : 0
         * pageSize : 0
         * name : 手机-广告图
         * sort : 30
         * module :
         * inMenu : 0
         * inList : 1
         * showModes : 0
         * allowComment : 0
         * isAudit : 0
         * hits : 0
         * url : /airforce/front/list-75.html
         * ids : 75
         * root : false
         * parentId : 0
         */

        private String id;
        private boolean isNewRecord;
        private int pageNo;
        private int pageSize;
        private String name;
        private int sort;
        private String module;
        private String inMenu;
        private String inList;
        private String showModes;
        private String allowComment;
        private String isAudit;
        private int hits;
        private String url;
        private String ids;
        private boolean root;
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

        public String getModule() {
            return module;
        }

        public void setModule(String module) {
            this.module = module;
        }

        public String getInMenu() {
            return inMenu;
        }

        public void setInMenu(String inMenu) {
            this.inMenu = inMenu;
        }

        public String getInList() {
            return inList;
        }

        public void setInList(String inList) {
            this.inList = inList;
        }

        public String getShowModes() {
            return showModes;
        }

        public void setShowModes(String showModes) {
            this.showModes = showModes;
        }

        public String getAllowComment() {
            return allowComment;
        }

        public void setAllowComment(String allowComment) {
            this.allowComment = allowComment;
        }

        public String getIsAudit() {
            return isAudit;
        }

        public void setIsAudit(String isAudit) {
            this.isAudit = isAudit;
        }

        public int getHits() {
            return hits;
        }

        public void setHits(int hits) {
            this.hits = hits;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIds() {
            return ids;
        }

        public void setIds(String ids) {
            this.ids = ids;
        }

        public boolean isRoot() {
            return root;
        }

        public void setRoot(boolean root) {
            this.root = root;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }
    }

    public static class UserBean {
        /**
         * isNewRecord : true
         * pageNo : 0
         * pageSize : 0
         * name : system
         * loginFlag : 1
         * admin : false
         * roleNames :
         */

        private boolean isNewRecord;
        private int pageNo;
        private int pageSize;
        private String name;
        private String loginFlag;
        private boolean admin;
        private String roleNames;

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
}
