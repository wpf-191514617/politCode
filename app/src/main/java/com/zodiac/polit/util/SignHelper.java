package com.zodiac.polit.util;

import com.zodiac.polit.TypeConstant;
import com.zodiac.polit.bean.response.TypeResponse;

import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by john on 2018/10/6.
 */

public class SignHelper {

    public static List<TypeResponse> getTypeListByTag(String tag) {
        /*if (tag.equals(TypeConstant.TYPE_CUSTOM_YEAR)) {
            return getYearList();
        }*/
        if (tag.equals(TypeConstant.TYPE_CUSTOM_YEAR_TEN)){
            return getTenYearList();
        }
        return null;
    }

    private static List<TypeResponse> getTenYearList() {
        int yaear = Calendar.getInstance().get(Calendar.YEAR);
        List<TypeResponse> typeResponseList = new ArrayList<>();
        for (int i = yaear;i > yaear-10;i-- ){
            TypeResponse typeResponse = new TypeResponse();
            typeResponse.setLabel(String.valueOf(i));
            typeResponseList.add(typeResponse);
        }
        return typeResponseList;
    }



    private static List<TypeResponse> getYearList() {
        int yaear = Calendar.getInstance().get(Calendar.YEAR);
        List<TypeResponse> typeResponseList = new ArrayList<>();
        TypeResponse typeResponse = new TypeResponse();
        typeResponse.setLabel(String.valueOf(yaear));
        typeResponseList.add(typeResponse);
        typeResponse = new TypeResponse();
        typeResponse.setLabel(String.valueOf(yaear+1));
        typeResponseList.add(typeResponse);
        return typeResponseList;
    }


}
