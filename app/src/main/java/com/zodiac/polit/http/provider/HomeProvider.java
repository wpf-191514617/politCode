package com.zodiac.polit.http.provider;

import com.google.gson.Gson;
import com.minilive.library.network.OkHttpUtils;
import com.minilive.library.network.callback.StringCallback;
import com.minilive.library.util.StringUtils;
import com.minilive.library.util.Trace;
import com.zodiac.polit.Constant;
import com.zodiac.polit.bean.request.CategoryRequest;
import com.zodiac.polit.bean.request.ContentRequest;
import com.zodiac.polit.bean.request.HomeRequest;
import com.zodiac.polit.bean.request.NewsRequest;
import com.zodiac.polit.bean.request.QueryRequest;
import com.zodiac.polit.http.BaseProvider;

/**
 * Created by john on 2018/9/24.
 */

public class HomeProvider extends BaseProvider {

    public static void loadConnectUsData(StringCallback callback) {
        postJson("/airforce/rest/common/commonInfo/findCenterList", "{}", callback);
    }

    /**
     * 问题
     * @param page
     * @param callback
     */
    public static void loadQuestionData(int page, StringCallback callback) {
        loadAppArticleList("44", page, callback);
    }

    /**
     * 政策
     * @param page
     * @param callback
     */
    public static void loadPolicyData(int page, StringCallback callback) {
        loadAppArticleList("59", page, callback);
    }


    /**
     * 公告
     * @param page
     * @param callback
     */
    public static void loadNoticeData(int page, StringCallback callback) {
        loadAppArticleList("28", page, callback);
    }


    private static void loadAppArticleList(String cid, int page, StringCallback callback) {
        HomeRequest homeRequest = new HomeRequest();
        HomeRequest.CategoryBean categoryBean = new HomeRequest.CategoryBean();
        categoryBean.setId(cid);
        homeRequest.setCategory(categoryBean);

        HomeRequest.AreaCodeBean areaCodeBean = new HomeRequest.AreaCodeBean();
        if (StringUtils.isEmpty(Constant.cityCode)){
            areaCodeBean.setAreaCode("");
        } else {
            areaCodeBean.setAreaCode(Constant.cityCode);
        }
        homeRequest.setAreaCode(areaCodeBean);

        homeRequest.setPageNo(page);
        homeRequest.setPageSize(10);
        postJson("/airforce/rest/cms/app/getAppArticleList", new Gson().toJson(homeRequest), callback);
    }

    public static void loadBanner(StringCallback stringCallback){
        CategoryRequest categoryRequest = new CategoryRequest();
        CategoryRequest.CategoryBean categoryBean = new CategoryRequest.CategoryBean();
        categoryBean.setId("75");
        categoryRequest.setCategory(categoryBean);
        postJson("/airforce/rest/cms/article/findList" , new Gson().toJson(categoryRequest) , stringCallback);
    }


    /**
     * 首页数据
     * @param page
     * @param callback
     */
    public static void loadHomeNewsData(int page, StringCallback callback){
        //loadNewsData("28" , page , callback);
        loadNewsData("51,56",page,callback);
    }


    /**
     * 要讯数据
     * @param page
     * @param callback
     */
    public static void loadNewsDataById(String ids , int page, StringCallback callback){
        loadNewsData(ids , page , callback);
    }


    private static void loadNewsData(String ids,int page , StringCallback stringCallback){
        NewsRequest newsRequest = new NewsRequest();
        newsRequest.setIds(ids);
        newsRequest.setPageNo(page);
        newsRequest.setPageSize(LIMIT);
        NewsRequest.AreaCodeBean areaCodeBean = new NewsRequest.AreaCodeBean();
        if (!StringUtils.isEmpty(Constant.cityCode)){
            areaCodeBean.setAreaCode(Constant.cityCode);
        } else {
            areaCodeBean.setAreaCode("");
        }
        newsRequest.setAreaCode(areaCodeBean);
        postJson("/airforce/rest/cms/app/getAllContentList" , new Gson().toJson(newsRequest) , stringCallback);
    }


    public static void loadNewsDetail(String ctId , String contentID , StringCallback callback){
        ContentRequest contentRequest = new ContentRequest();
        contentRequest.setCategoryId(ctId);
        contentRequest.setContentId(contentID);
        patch("/airforce/rest/cms/article/getContent",new Gson().toJson(contentRequest) , callback);
    }

    public static void query(QueryRequest queryRequest, StringCallback stringCallback){
        postJson("/airforce/rest/common/commonInfo/resultList",new Gson().toJson(queryRequest) , stringCallback);
    }

}
