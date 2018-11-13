package com.zodiac.polit.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.minilive.library.util.ACache;
import com.minilive.library.util.StringUtils;
import com.zodiac.polit.Constant;
import com.zodiac.polit.MyApplication;
import com.zodiac.polit.bean.response.TypeResponse;
import com.zodiac.polit.bean.response.UserResponse;

import java.util.List;

/**
 * Created by john on 2018/9/29.
 */

public class CacheHelper {

    private static CacheHelper mInstance = null;

    private final String KEY_USER = "USER";
    private final String KEY_DIC = "app.DIC";

    private ACache mACache;

    private CacheHelper() {
        mACache = ACache.get(MyApplication.getContext(), Constant.CACHE_NAME);
    }

    public static CacheHelper getInstance() {
        if (mInstance == null) {
            synchronized (CacheHelper.class) {
                if (mInstance == null) {
                    mInstance = new CacheHelper();
                }
            }
        }
        return mInstance;
    }

    public void putCurrentUser(String userResponse){
        mACache.put(KEY_USER , userResponse);
    }

    public void putCurrentUser(UserResponse userResponse){
        mACache.put(KEY_USER , new Gson().toJson(userResponse));
    }

    public UserResponse getCurrentUser(){
        String result = mACache.getAsString(KEY_USER);
        return new Gson().fromJson(result , UserResponse.class);
    }

    public boolean isLogin(){
        return !StringUtils.isEmpty(mACache.getAsString(KEY_USER));
    }

    public void clear(){
        mACache.clear();
    }

    public void putDicData(String data){
        mACache.put(KEY_DIC , data);
    }

    public List<TypeResponse> getDicData(){
        String data = mACache.getAsString(KEY_DIC);
        if (StringUtils.isEmpty(data)){
            return null;
        }
        return new Gson().fromJson(data , new TypeToken<List<TypeResponse>>(){}.getType());
    }


}
