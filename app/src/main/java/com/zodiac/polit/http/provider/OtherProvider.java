package com.zodiac.polit.http.provider;

import com.google.gson.Gson;
import com.minilive.library.network.callback.StringCallback;
import com.zodiac.polit.TypeConstant;
import com.zodiac.polit.bean.request.CheckSignupRequest;
import com.zodiac.polit.bean.request.FindCenterRequest;
import com.zodiac.polit.bean.request.IDRequest;
import com.zodiac.polit.bean.request.OrderRequest;
import com.zodiac.polit.bean.request.TypeRequest;
import com.zodiac.polit.bean.request.VisitorRequest;
import com.zodiac.polit.http.BaseProvider;

import java.util.Calendar;

import okhttp3.Call;

/**
 * Created by john on 2018/10/5.
 */

public class OtherProvider extends BaseProvider {

    public static void getListData(String type, StringCallback stringCallback) {
        TypeRequest typeRequest = new TypeRequest();
        typeRequest.type = type;
        postJson("/airforce/rest/common/commonInfo/getDictList", new Gson().toJson(typeRequest), stringCallback);
    }

    public static void saveVisitorData(String title) {
        VisitorRequest visitorRequest = new VisitorRequest();
        VisitorRequest.UserBean userBean = new VisitorRequest.UserBean();
        visitorRequest.setTitle(title);
        // TODO    添加其他字段信息
        postJson("/airforce/rest/cms/article/saveVisitorData", new Gson().toJson(visitorRequest), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {

            }
        });
    }


    public static void getChildByParentId(String id, StringCallback stringCallback) {
        IDRequest idRequest = new IDRequest();
        idRequest.id = id;
        postJson("/airforce/rest/common/commonInfo/findChildsByParentId", new Gson().toJson(idRequest), stringCallback);
    }

    public static void getConstantData(StringCallback stringCallback) {
        TypeRequest typeRequest = new TypeRequest();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(TypeConstant.TYPE_PROVINCE).append(",")
                .append(TypeConstant.TYPE_HOUSEHOLD).append(",")
                .append(TypeConstant.TYPE_NATION).append(",")
                .append(TypeConstant.TYPE_POLITICS).append(",")
                .append(TypeConstant.TYPE_STUDENT).append(",")
                .append(TypeConstant.TYPE_FRESH).append(",")
                .append(TypeConstant.TYPE_ARTS).append(",")
                .append(TypeConstant.TYPE_ESTIMATE_HIGH).append(",")
                .append(TypeConstant.TYPE_CHILD).append(",")
                .append(TypeConstant.TYPE_ESTIMATE_MIDDLE).append(",")
                .append(TypeConstant.TYPE_PHASE_NAME).append(",")
                .append(TypeConstant.TYPE_PHASE_STATUS);
        typeRequest.type = stringBuilder.toString();
        postJson("/airforce/rest/common/commonInfo/getDictList", new Gson().toJson(typeRequest), stringCallback);
    }


    public static void findCenterByProvince(String provinceId,String studentType, StringCallback stringCallback) {
        /*IDRequest idRequest = new IDRequest();
        idRequest.id = provinceId;*/
        FindCenterRequest idRequest = new FindCenterRequest();
        idRequest.id = provinceId;
        idRequest.studentType = studentType;
        //"/airforce/rest/common/commonInfo/findCenterByProvince"
        String url = "/airforce/rest/common/commonInfo/findCenterByProvinceAndStudentType";
        postJson(url, new Gson().toJson(idRequest), stringCallback);
    }


    public static void findOrder(String studentType, String year, String id, StringCallback stringCallback) {
        OrderRequest orderRequest = new OrderRequest();
        OrderRequest.OfficeBean officeBean = new OrderRequest.OfficeBean();
        officeBean.setId(id);
        orderRequest.setOffice(officeBean);
        orderRequest.setApplayYear(year);
        orderRequest.setStudentType(studentType);
        postJson("/airforce/rest/member/memberApplayTask/list", new Gson().toJson(orderRequest), stringCallback);
    }


    public static void checkThisUserIsSign(String cardId, StringCallback stringCallback) {
        CheckSignupRequest checkSignupRequest = new CheckSignupRequest();
        checkSignupRequest.setCardId(cardId);
        checkSignupRequest.setApplayYear(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
        postJson("/airforce/rest/member/memberApplay/judgeApplayed", new Gson().toJson(checkSignupRequest), stringCallback);
    }

}
