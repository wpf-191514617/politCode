package com.zodiac.polit.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.minilive.library.BaseAdapterUtil;
import com.minilive.library.entity.EventData;
import com.minilive.library.network.callback.StringCallback;
import com.minilive.library.util.StringUtils;
import com.minilive.library.util.Trace;
import com.zodiac.polit.Constant;
import com.zodiac.polit.R;
import com.zodiac.polit.base.BaseActivity;
import com.zodiac.polit.bean.RequestBean;
import com.zodiac.polit.bean.ResponseBean;
import com.zodiac.polit.bean.SignupFamilyInfo;
import com.zodiac.polit.bean.SignupInfoBean;
import com.zodiac.polit.bean.SignupSchoolInfoBean;
import com.zodiac.polit.bean.SignupUserInfoBean;
import com.zodiac.polit.bean.request.SignupRequest;
import com.zodiac.polit.bean.response.AreaResponse;
import com.zodiac.polit.bean.response.CenterResponse;
import com.zodiac.polit.bean.response.OrderResponse;
import com.zodiac.polit.bean.response.SignInfoResult;
import com.zodiac.polit.bean.response.SignupInfoResponse;
import com.zodiac.polit.bean.response.TypeResponse;
import com.zodiac.polit.http.provider.OtherProvider;
import com.zodiac.polit.http.provider.UserProvider;
import com.zodiac.polit.ui.fragment.signup.Signup1;
import com.zodiac.polit.ui.fragment.signup.Signup2;
import com.zodiac.polit.ui.fragment.signup.Signup3;
import com.zodiac.polit.ui.fragment.signup.Signup4;
import com.zodiac.polit.util.AppHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by john on 2018/10/12.
 */

public class UpdateSignInfoActivity extends BaseActivity {

    public static final String KEY_ID = "signup.id";
    @BindView(R.id.btnSave)
    Button btnSave;
    /*@BindView(R.id.signUpInfo1)
    SignInfo1 signUpInfo1;*/

    private String mSignupId;

    private SignupInfoResponse mSignupInfoResponse;
    private Signup1 mSignup1;
    private Signup2 mSignup2;
    private Signup3 mSignup3;
    private Signup4 mSignup4;

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void initViewAndData() {
        setTitle("招飞信息");
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.menu_edit) {
                    // 查询该条是否可以编辑
                    checkIdEdit();
                    return true;
                }
                return false;
            }
        });

        mSignup1 = new Signup1();
        mSignup2 = new Signup2();
        mSignup3 = new Signup3();
        mSignup4 = new Signup4();
        getSupportFragmentManager().beginTransaction().add(R.id.flSignup1, mSignup2).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.flSignup2, mSignup1).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.flSignup3, mSignup3).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.flSignup4, mSignup4).commit();
        getSignInfo();
    }

    @Override
    protected void onEventComming(EventData data) {
        super.onEventComming(data);
        if (data.getCODE() == Constant.CODE_LOGIN)
            getSignInfo();
    }

    private void getSignInfo() {
        showLoadingDialog();
        UserProvider.getSignupInfo(mSignupId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
                showToast("获取数据失败");
                finish();
            }

            @Override
            public void onResponse(String response, int id) {
                Trace.d("res------" + response);
                dismissLoadingDialog();
                if (!StringUtils.isEmpty(response)) {
                    mSignupInfoResponse = new Gson().fromJson(response, SignupInfoResponse.class);
                    if (mSignupInfoResponse != null) {
                        if (mSignupInfoResponse.getCode().equals("0") && mSignupInfoResponse.getContent() != null) {
                            mSignup1.setData(mSignupInfoResponse.getContent());
                            mSignup1.setEditble(false);
                            mSignup2.setData(mSignupInfoResponse.getContent());
                            mSignup2.setEditble(false);
                            mSignup3.setData(mSignupInfoResponse.getContent());
                            mSignup3.setEditble(false);
                            mSignup4.setData(mSignupInfoResponse.getContent());
                            mSignup4.setEditble(false);
                        } else {
                            showToast(mSignupInfoResponse.getMessage());
                            if (mSignupInfoResponse.getCode().equals("400")){
                                onLogout();
                            }
                        }
                    }
                }
            }
        });
    }


    private void checkIdEdit() {
        showLoadingDialog();
        UserProvider.checkIsEdit(mSignupId, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
                showToast("获取信息失败");
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                if (!StringUtils.isEmpty(response)) {
                    List<SignInfoResult> signInfoResults = new Gson().fromJson(response,
                            new TypeToken<List<SignInfoResult>>() {
                            }.getType());
                    if (signInfoResults != null && signInfoResults.size() > 0) {
                        showToast("已进入审核阶段，不能修改");
                        return;
                    }
                    mSignup1.setEditble(true);
                    mSignup2.setEditble(true);
                    mSignup3.setEditble(true);
                    mSignup4.setEditble(true);
                    btnSave.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    @Override
    protected void getBundleExtras(Bundle extras) {
        mSignupId = extras.getString(KEY_ID);
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_updatesign;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private SignupUserInfoBean signupUserInfoBean;
    private SignupInfoBean signupInfoBean;
    private SignupFamilyInfo signupFamilyInfo;
    private SignupSchoolInfoBean signupSchoolInfoBean;

    @OnClick(R.id.btnSave)
    public void onViewClicked() {
        if (!mSignup1.checkData()) {
            return;
        }
        if (!mSignup2.checkData()) {
            return;
        }
        if (!mSignup3.checkData()) {
            return;
        }
        signupUserInfoBean = mSignup1.getData();
        signupInfoBean = mSignup2.getData();
        signupFamilyInfo = mSignup3.getData();
        signupSchoolInfoBean = mSignup4.getData();
        findCenter(signupInfoBean);
    }


    private void findCenter(final SignupInfoBean signupInfoBean) {
        showLoadingDialog();
        OtherProvider.findCenterByProvince(signupInfoBean.province.getValue(), signupInfoBean.student.getValue(),new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
                showToast("获取招飞中心失败");
            }

            @Override
            public void onResponse(String res, int id) {
                if (!StringUtils.isEmpty(res)) {
                    List<CenterResponse> responseList = new Gson().fromJson(res,
                            new TypeToken<List<CenterResponse>>() {
                            }.getType());
                    if (BaseAdapterUtil.isListNotEmpty(responseList)) {
                        signupInfoBean.centerID = responseList.get(0).getId();
                        findOrder(signupInfoBean, responseList.get(0).getId());
                    } else {
                        dismissLoadingDialog();
                        showToast("暂无招飞中心");
                    }
                }
            }
        });
    }

    private void findOrder(final SignupInfoBean signupInfoBean, String id) {
        OtherProvider.findOrder(signupInfoBean.student.getValue(), signupInfoBean.year.getLabel(), id, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
                showToast("获取招飞任务失败");
            }

            @Override
            public void onResponse(String response, int id) {
                if (!StringUtils.isEmpty(response)) {
                    List<OrderResponse> orderResponses = new Gson().fromJson(response,
                            new TypeToken<List<OrderResponse>>() {
                            }.getType());
                    if (BaseAdapterUtil.isListNotEmpty(orderResponses)) {
                        signupInfoBean.orderID = orderResponses.get(0).getId();
                        onSubmit();
                    } else {
                        dismissLoadingDialog();
                        showToast("暂无招飞任务");
                    }
                }
            }
        });
    }


    public void onSubmit() {
        SignupRequest signupRequest = new SignupRequest();
        RequestBean requestBean = AppHelper.getRequestBean();
        signupRequest.setSessionId(requestBean.sessionId);
        signupRequest.setTimestamp(requestBean.timestamp);
        signupRequest.setSignature(requestBean.signature);
        signupRequest.setId(mSignupInfoResponse.getContent().getId());
        signupRequest.setApplayYear(signupInfoBean.year.getLabel());
        signupRequest.setMemberApplayTask(signupInfoBean.orderID);
        signupRequest.setApplayProvince(getResponseValue(signupInfoBean.province));
       // signupRequest.setStudentType(signupInfoBean.student.getValue());
        signupRequest.setOffice(signupInfoBean.centerID);
       /* SignupRequest.ApplayProvinceBean applayProvinceBean = new SignupRequest.ApplayProvinceBean();
        applayProvinceBean.setId(signupInfoBean.province.getValue());
        signupRequest.setApplayProvince(applayProvinceBean);*/
        signupRequest.setCardId(signupUserInfoBean.id);
        signupRequest.setPhone(signupUserInfoBean.phone);
        signupRequest.setRealName(signupUserInfoBean.realName);
        signupRequest.setBirthday(signupUserInfoBean.birthday);
        signupRequest.setSex(String.valueOf(signupUserInfoBean.sex));
        signupRequest.setArtsSciences(getResponseValue(signupSchoolInfoBean.arts));
        /*signupRequest.setChannel("");
        signupRequest.setCity("");*/
        signupRequest.setClassName("");
        signupRequest.setDepartment(signupSchoolInfoBean.teamName);
        signupRequest.setEstimateHighSchool(getResponseValue(signupSchoolInfoBean.EstimateHigh));
        signupRequest.setEstimateMiddleSchool(getResponseValue(signupSchoolInfoBean.Estimate1));
        signupRequest.setFatherName(signupFamilyInfo.fatherName);
        signupRequest.setFatherPhone(signupFamilyInfo.fatherPhone);
        signupRequest.setHomeAddress(signupFamilyInfo.address);
        signupRequest.setHomeArea(getAreaValue(signupFamilyInfo.ares));
        signupRequest.setHomeCity(getAreaValue(signupFamilyInfo.city));
        signupRequest.setHomeProvince(getResponseValue(signupFamilyInfo.province));
        signupRequest.setHouseholdRegistration(getResponseValue(signupUserInfoBean.houseHold));
        signupRequest.setIsOneChild(getResponseValue(signupUserInfoBean.child));
        signupRequest.setMotherName(signupFamilyInfo.motherName);
        signupRequest.setMotherPhone(signupFamilyInfo.motherPhone);
        signupRequest.setNation(getResponseValue(signupUserInfoBean.nation));
        //signupRequest.setPic("");

        signupRequest.setPoliticsType(getResponseValue(signupUserInfoBean.politics));
        signupRequest.setPresentPrevious(getResponseValue(signupSchoolInfoBean.inOrder));
        signupRequest.setProfession(signupSchoolInfoBean.major);
        /*signupRequest.setProvince("");
        signupRequest.setPublicityName("招飞宣传人员1");
        signupRequest.setRemarks("");*/
        signupRequest.setSchool(getAreaValue(signupSchoolInfoBean.school));
        signupRequest.setSchoolArea(getAreaValue(signupSchoolInfoBean.area));
        signupRequest.setSchoolCity(getAreaValue(signupSchoolInfoBean.city));
        signupRequest.setSchoolName(signupSchoolInfoBean.schoolName);
        signupRequest.setSchoolProvince(getResponseValue(signupSchoolInfoBean.province));
        /*signupRequest.setStudentType(getResponseValue(signupInfoBean.student));
        signupRequest.setSysArea("");*/
        signupRequest.setTeacherName(signupSchoolInfoBean.teamLeaderName);
        signupRequest.setTeacherPhone(signupSchoolInfoBean.teamLeaderPhone);

        UserProvider.doSignup(signupRequest, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
                showToast(e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                if (!StringUtils.isEmpty(response)) {
                    ResponseBean responseBean = new Gson().fromJson(response, ResponseBean.class);
                    if (responseBean.getCode().equals("0")) {
                        showToast("保存成功");
                        finish();
                    } else {
                        showToast(responseBean.getMessage());
                        if (responseBean.getCode().equals("400"))
                            onLogout();
                    }
                }
            }
        });
    }

    private String getAreaValue(AreaResponse areaResponse) {
        return areaResponse == null ? "" : areaResponse.getId();
    }

    private String getResponseValue(TypeResponse typeResponse) {
        return typeResponse == null ? "" : typeResponse.getValue();
    }

}
