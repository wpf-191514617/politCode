package com.zodiac.polit.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.minilive.library.BaseAdapterUtil;
import com.minilive.library.network.callback.StringCallback;
import com.minilive.library.util.StringUtils;
import com.zodiac.polit.bean.response.TypeResponse;
import com.zodiac.polit.http.provider.OtherProvider;

import java.util.List;

import okhttp3.Call;

/**
 * Created by john on 2018/10/12.
 */

public class DicHelper {

    private static DicHelper mInstance;

    private List<TypeResponse> mList;

    private DicHelper() {
    }

    public static DicHelper getInstance() {
        if (mInstance == null) {
            synchronized (DicHelper.class) {
                if (mInstance == null) {
                    mInstance = new DicHelper();
                }
            }
        }
        return mInstance;
    }

    public void getTypeResponseByValueAndType(String value , String type , OnTypeCallback onTypeCallback){
        if (!BaseAdapterUtil.isListNotEmpty(mList)){
            mList = CacheHelper.getInstance().getDicData();
            if (!BaseAdapterUtil.isListNotEmpty(mList)){
                getDicDataFromServer(value , type ,onTypeCallback);
            } else {
                filterData(value , type ,onTypeCallback);
            }
        } else {
            filterData(value , type ,onTypeCallback);
        }
    }

    private void getDicDataFromServer(final String value, final String type, final OnTypeCallback onTypeCallback) {
        OtherProvider.getConstantData(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }

            @Override
            public void onResponse(String response, int id) {
                if (!StringUtils.isEmpty(response)){
                    CacheHelper.getInstance().putDicData(response);
                    mList = new Gson().fromJson(response , new TypeToken<List<TypeResponse>>(){}.getType());
                    filterData(value , type ,onTypeCallback);
                }
            }
        });
    }

    private void filterData(String value , String type , OnTypeCallback onTypeCallback){
        for (TypeResponse typeResponse : mList){
            if (typeResponse.getValue().equals(value) && typeResponse.getType().equals(type)){
                onTypeCallback.callBack(typeResponse);
            }
        }
    }



    public interface OnTypeCallback{
        void callBack(TypeResponse typeResponse);
    }

}
