package com.zodiac.polit.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;

import com.google.gson.Gson;
import com.minilive.library.entity.EventData;
import com.minilive.library.network.callback.StringCallback;
import com.minilive.library.util.StringUtils;
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
import com.zodiac.polit.bean.response.TypeResponse;
import com.zodiac.polit.http.provider.UserProvider;
import com.zodiac.polit.ui.fragment.signup.SignupFragment1;
import com.zodiac.polit.ui.fragment.signup.SignupFragment2;
import com.zodiac.polit.ui.fragment.signup.SignupFragment3;
import com.zodiac.polit.ui.fragment.signup.SignupFragment4;
import com.zodiac.polit.util.AppHelper;
import com.zodiac.polit.util.CacheHelper;
import com.zodiac.polit.view.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;
import okhttp3.Call;

/**
 * Created by john on 2018/10/5.
 */

public class SignupActivity extends BaseActivity {
    @BindView(R.id.pager)
    NoScrollViewPager pager;

    private List<Fragment> mList;
    private SignFragmentAdapter mSignFragmentAdapter;

    private int mCurrentIndex = 0;

    private SignupUserInfoBean signupUserInfoBean;
    private SignupInfoBean signupInfoBean;
    private SignupFamilyInfo signupFamilyInfo;
    private SignupSchoolInfoBean signupSchoolInfoBean;

    public void setSignupSchoolInfoBean(SignupSchoolInfoBean signupSchoolInfoBean) {
        this.signupSchoolInfoBean = signupSchoolInfoBean;
    }

    public void setSignupUserInfoBean(SignupUserInfoBean signupUserInfoBean) {
        this.signupUserInfoBean = signupUserInfoBean;
    }

    public void setSignupInfoBean(SignupInfoBean signupInfoBean) {
        this.signupInfoBean = signupInfoBean;
    }

    public void setSignupFamilyInfo(SignupFamilyInfo signupFamilyInfo) {
        this.signupFamilyInfo = signupFamilyInfo;
    }

    public String getStudentType(){
        if (signupInfoBean == null){
            return null;
        }
        return signupInfoBean.student.getValue();
    }

    @Override
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    protected void initViewAndData() {

        setTitle("招飞报名");
        mList = new ArrayList<>();
        SignupFragment1 signupFragment1 = new SignupFragment1();
        SignupFragment2 signupFragment2 = new SignupFragment2();
        SignupFragment3 signupFragment3 = new SignupFragment3();
        SignupFragment4 signupFragment4 = new SignupFragment4();
        signupFragment1.setParent(this);
        signupFragment2.setParent(this);
        signupFragment3.setParent(this);
        signupFragment4.setParent(this);
        mList.add(signupFragment2);
        mList.add(signupFragment1);
        mList.add(signupFragment3);
        mList.add(signupFragment4);
        mSignFragmentAdapter = new SignFragmentAdapter(getSupportFragmentManager());
        pager.setOffscreenPageLimit(mList.size());
        pager.setAdapter(mSignFragmentAdapter);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == mList.size() - 1){
                    EventData eventData = new EventData(Constant.CODE_SIGN);
                    EventBus.getDefault().post(eventData);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_signup;
    }

    public void onNext(){
        mCurrentIndex++;
        pager.setCurrentItem(mCurrentIndex);
    }

    public void onPrev(){
        mCurrentIndex--;
        pager.setCurrentItem(mCurrentIndex);
    }

    public void onSubmit(){
        SignupRequest signupRequest = new SignupRequest();
        RequestBean requestBean = AppHelper.getRequestBean();
        signupRequest.setSessionId(requestBean.sessionId);
        signupRequest.setTimestamp(requestBean.timestamp);
        signupRequest.setSignature(requestBean.signature);
        signupRequest.setId("");
        signupRequest.setApplayYear(signupInfoBean.year.getLabel());
        signupRequest.setMemberApplayTask(signupInfoBean.orderID);
        /*signupRequest.setStudentType(signupInfoBean.student.getValue());
        SignupRequest.OfficeBean officeBean = new SignupRequest.OfficeBean();
        officeBean.setId(signupInfoBean.centerID);*/
        signupRequest.setOffice(signupInfoBean.centerID);
        signupRequest.setApplayProvince(getResponseValue(signupInfoBean.province));
        /*SignupRequest.ApplayProvinceBean applayProvinceBean = new SignupRequest.ApplayProvinceBean();
        applayProvinceBean.setId(signupInfoBean.province.getValue());
        signupRequest.setApplayProvince(applayProvinceBean);*/
        signupRequest.setCardId(signupUserInfoBean.id);
        signupRequest.setPhone(signupUserInfoBean.phone);
        signupRequest.setRealName(signupUserInfoBean.realName);
        signupRequest.setBirthday(signupUserInfoBean.birthday);
        signupRequest.setSex(String.valueOf(signupUserInfoBean.sex));
        signupRequest.setArtsSciences(getResponseValue(signupSchoolInfoBean.arts));
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
        showLoadingDialog();
        UserProvider.doSignup(signupRequest, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
                showToast(e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                if (!StringUtils.isEmpty(response)){
                    ResponseBean responseBean = new Gson().fromJson(response , ResponseBean.class);
                    if (responseBean.getCode().equals("0")){
                        showToast("报名成功");
                        finish();
                    } else {
                        showToast(responseBean.getMessage());
                        if (responseBean.getCode().equals("400")){
                            onLogout();
                        }
                    }
                }
            }
        });
    }

    private String getAreaValue(AreaResponse areaResponse){
        return areaResponse == null ? "" : areaResponse.getId();
    }

    private String getResponseValue(TypeResponse typeResponse){
        return typeResponse == null ? "" : typeResponse.getValue();
    }

    @Override
    protected void onCustomBack() {
        if (mCurrentIndex == 0){
            onBackPressed();
        } else {
            onPrev();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
            onCustomBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    class SignFragmentAdapter extends FragmentStatePagerAdapter{

        public SignFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position);
        }

        @Override
        public int getCount() {
            return mList.size();
        }
    }

}
