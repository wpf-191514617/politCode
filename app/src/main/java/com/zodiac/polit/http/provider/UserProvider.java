package com.zodiac.polit.http.provider;

import com.google.gson.Gson;
import com.minilive.library.network.callback.StringCallback;
import com.zodiac.polit.bean.RequestBean;
import com.zodiac.polit.bean.ResponseBean;
import com.zodiac.polit.bean.request.ApplyStatus;
import com.zodiac.polit.bean.request.ForgetPwdRequest;
import com.zodiac.polit.bean.request.IDRequest;
import com.zodiac.polit.bean.request.LoginRequest;
import com.zodiac.polit.bean.request.NoticeRequest;
import com.zodiac.polit.bean.request.PhoneRequest;
import com.zodiac.polit.bean.request.RegisterRequest;
import com.zodiac.polit.bean.request.SignupInfoRequest;
import com.zodiac.polit.bean.request.SignupRequest;
import com.zodiac.polit.bean.request.UpdatePwdRequest;
import com.zodiac.polit.bean.request.UserInfoRequest;
import com.zodiac.polit.http.BaseProvider;
import com.zodiac.polit.util.AppHelper;
import com.zodiac.polit.util.CacheHelper;

/**
 * Created by john on 2018/9/29.
 */

public class UserProvider extends BaseProvider{

    public static void register(RegisterRequest registerRequest , StringCallback callback){
        postJson("/airforce/rest/member/memberInfo/register" , new Gson().toJson(registerRequest) , callback);
    }

    public static void doLogin(LoginRequest loginRequest , StringCallback stringCallback){
        postJson("/airforce/rest/member/memberInfo/login" , new Gson().toJson(loginRequest) , stringCallback);
    }

    public static void updatePwd(UpdatePwdRequest updatePwdRequest , StringCallback stringCallback){
        postJson("/airforce/rest/member/memberInfo/modifyPassword" , new Gson().toJson(updatePwdRequest) , stringCallback);
    }

    public static void getNoticeList(int page , StringCallback stringCallback){
        NoticeRequest noticeRequest = new NoticeRequest();
        noticeRequest.id = CacheHelper.getInstance().getCurrentUser().getContent().getId();
        noticeRequest.pageNo = page;
        noticeRequest.pageSize = LIMIT;
        postJson("/airforce/rest/cms/article/findLoginArticle" , new Gson().toJson(noticeRequest),stringCallback);
    }


    public static void getSignupHistoryList(StringCallback stringCallback){
        RequestBean requestBean = AppHelper.getRequestBean();
        //String url = "airforce/rest/member/memberApplay/list";
        //"airforce/rest/cms/app/appList"
        postJson("/airforce/rest/member/memberApplay/list" , new Gson().toJson(requestBean) , stringCallback);
    }

    public static void getSignupInfo(String id , StringCallback stringCallback){
        SignupInfoRequest signupInfoRequest = new SignupInfoRequest();
        signupInfoRequest.id = id;
        RequestBean requestBean = AppHelper.getRequestBean();
        signupInfoRequest.sessionId = requestBean.sessionId;
        signupInfoRequest.signature = requestBean.signature;
        signupInfoRequest.timestamp = requestBean.timestamp;
        postJson("/airforce/rest/member/memberApplay/get" , new Gson().toJson(signupInfoRequest) , stringCallback);
    }

    public static void doSignup(SignupRequest signupRequest , StringCallback stringCallback){
        postJson("/airforce/rest/member/memberApplay/save" , new Gson().toJson(signupRequest) , stringCallback);
    }

    public static void getApplyStatus(String applyId , StringCallback stringCallback){
        ApplyStatus.MemberApplayBean memberApplayBean = new ApplyStatus.MemberApplayBean();
        memberApplayBean.setId(applyId);
        ApplyStatus applyStatus = new ApplyStatus();
        applyStatus.setMemberApplay(memberApplayBean);
        postJson("/airforce/rest/member/memberAuditHistory/list" , new Gson().toJson(applyStatus) , stringCallback);
    }

    public static void updateUserInfo(String realName, String phone , StringCallback stringCallback){
        UserInfoRequest userInfoRequest = new UserInfoRequest();
        userInfoRequest.id = CacheHelper.getInstance().getCurrentUser().getContent().getId();
        userInfoRequest.phone = phone;
        userInfoRequest.realName = realName;
        RequestBean requestBean = AppHelper.getRequestBean();
        userInfoRequest.sessionId = requestBean.sessionId;
        userInfoRequest.signature = requestBean.signature;
        userInfoRequest.timestamp = requestBean.timestamp;
        postJson("/airforce/rest/member/memberInfo/modify" , new Gson().toJson(userInfoRequest) , stringCallback);
    }

    public static void checkIsEdit(String applyId , StringCallback stringCallback){
        ApplyStatus.MemberApplayBean memberApplayBean = new ApplyStatus.MemberApplayBean();
        memberApplayBean.setId(applyId);
        ApplyStatus applyStatus = new ApplyStatus();
        applyStatus.setMemberApplay(memberApplayBean);
        postJson("/airforce/rest/member/memberAudit/list" , new Gson().toJson(applyStatus) , stringCallback);
    }


    public static void sendSMSCode(String phone , StringCallback stringCallback){
        PhoneRequest phoneRequest = new PhoneRequest();
        phoneRequest.phone = phone;
        postJson("/airforce/rest/member/memberInfo/getSmsCode" , new Gson().toJson(phoneRequest) , stringCallback);
    }

    public static void forgetPwd(String id , String phone , String code ,String sessionId, StringCallback stringCallback){
        ForgetPwdRequest forgetPwdRequest = new ForgetPwdRequest();
        forgetPwdRequest.cardId = id;
        forgetPwdRequest.phone = phone;
        forgetPwdRequest.smsCode = code;
        forgetPwdRequest.sessionId = sessionId;
        postJson("/airforce/rest/member/memberInfo/resetPassword" , new Gson().toJson(forgetPwdRequest) , stringCallback);
    }


    public static void judgeCanDelete(String id , StringCallback stringCallback){
        SignupInfoRequest signupInfoRequest = new SignupInfoRequest();
        signupInfoRequest.id = id;
        RequestBean requestBean = AppHelper.getRequestBean();
        signupInfoRequest.sessionId = requestBean.sessionId;
        signupInfoRequest.signature = requestBean.signature;
        signupInfoRequest.timestamp = requestBean.timestamp;
        postJson("/airforce/rest/member/memberApplay/judgeCanDelete" ,
                new Gson().toJson(signupInfoRequest) , stringCallback);
    }


    public static void delete(String id , StringCallback stringCallback){
        SignupInfoRequest signupInfoRequest = new SignupInfoRequest();
        signupInfoRequest.id = id;
        RequestBean requestBean = AppHelper.getRequestBean();
        signupInfoRequest.sessionId = requestBean.sessionId;
        signupInfoRequest.signature = requestBean.signature;
        signupInfoRequest.timestamp = requestBean.timestamp;
        postJson("/airforce/rest/member/memberApplay/delete" ,
                new Gson().toJson(signupInfoRequest) , stringCallback);
    }

}
