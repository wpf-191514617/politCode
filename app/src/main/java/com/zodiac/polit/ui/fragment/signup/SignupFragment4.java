package com.zodiac.polit.ui.fragment.signup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.minilive.library.entity.EventData;
import com.minilive.library.util.StringUtils;
import com.zodiac.polit.Constant;
import com.zodiac.polit.R;
import com.zodiac.polit.TypeConstant;
import com.zodiac.polit.bean.SignupSchoolInfoBean;
import com.zodiac.polit.bean.response.AreaResponse;
import com.zodiac.polit.bean.response.TypeResponse;
import com.zodiac.polit.ui.activity.ListAreaActivity;
import com.zodiac.polit.ui.activity.ListSelectActivity;
import com.zodiac.polit.widget.InputLayout;

import java.nio.charset.MalformedInputException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by john on 2018/10/5.
 */

public class SignupFragment4 extends BaseSignupFragment {

    @BindView(R.id.inputSchoolProvice)
    InputLayout inputSchoolProvice;
    @BindView(R.id.inputSchoolCity)
    InputLayout inputSchoolCity;
    @BindView(R.id.inputSchoolArea)
    InputLayout inputSchoolArea;
    @BindView(R.id.inputSchoolName)
    InputLayout inputSchoolName;
    @BindView(R.id.inputSchoolName1)
    InputLayout inputSchoolName1;
    @BindView(R.id.inputInOrder)
    InputLayout inputInOrder;
    @BindView(R.id.inputScience)
    InputLayout inputScience;
    @BindView(R.id.inputMajor)
    InputLayout inputMajor;
    @BindView(R.id.inputTeamName)
    InputLayout inputTeamName;
    @BindView(R.id.inputTeamLeaderName)
    InputLayout inputTeamLeaderName;
    @BindView(R.id.inputTeamLeaderPhone)
    InputLayout inputTeamLeaderPhone;
    @BindView(R.id.inputEstimateHigh)
    InputLayout inputEstimateHigh;
    @BindView(R.id.inputEstimate1)
    InputLayout inputEstimate1;
    @BindView(R.id.btnCommit)
    Button btnCommit;
    Unbinder unbinder;

    private String mStudentType;

    private TypeResponse provinceTypeResponse , mEstimateMiddle , mArtsTypeResponse , mEstimateMiddleHigh , mInOrderTypeResponse;
    private AreaResponse mCityAreaResponse , mAreaResponse , mSchoolAreaResponse;

    private final int REQUEST_PROVINCE = 31;
    private final int REQUEST_CITY = 32;
    private final int REQUEST_AREA = 33;
    private final int REQUEST_SCHOOLNAME = 34;
    private final int REQUEST_ESTIMATE_MIDDLE = 35;
    private final int REQUEST_ATRS = 36;
    private final int REQUEST_ESTIMATE_MIDDLE_HIGH = 37;
    private final int REQUEST_INORDER = 38;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_signup4;
    }

    @Override
    protected void initViewAndData() {
        inputSchoolProvice.setSelectListener(new OnSelectListener("选择省份", TypeConstant.TYPE_PROVINCE, REQUEST_PROVINCE));
        inputSchoolCity.setSelectListener(new OnSelectListener("选择城市", TypeConstant.TYPE_PROVINCE, REQUEST_CITY));
        inputSchoolArea.setSelectListener(new OnSelectListener("选择区县", TypeConstant.TYPE_PROVINCE, REQUEST_AREA));
        inputSchoolName.setSelectListener(new OnSelectListener("选择学校", TypeConstant.TYPE_PROVINCE, REQUEST_SCHOOLNAME));
        inputScience.setSelectListener(new OnSelectListener("文理科" , TypeConstant.TYPE_ARTS ,REQUEST_ATRS));
        inputEstimateHigh.setSelectListener(new OnSelectListener("高考预估", TypeConstant.TYPE_ESTIMATE_HIGH, REQUEST_ESTIMATE_MIDDLE_HIGH));
        inputEstimate1.setSelectListener(new OnSelectListener("中考预估", TypeConstant.TYPE_ESTIMATE_MIDDLE, REQUEST_ESTIMATE_MIDDLE));
        inputInOrder.setSelectListener(new OnSelectListener("应往届" , TypeConstant.TYPE_FRESH , REQUEST_INORDER));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnCommit)
    public void onViewClicked() {
        SignupSchoolInfoBean schoolInfoBean = new SignupSchoolInfoBean();
        schoolInfoBean.province = provinceTypeResponse;
        schoolInfoBean.city = mCityAreaResponse;
        schoolInfoBean.area = mAreaResponse;
        schoolInfoBean.school = mSchoolAreaResponse;
        schoolInfoBean.schoolName = inputSchoolName1.getText();
        schoolInfoBean.inOrder = mInOrderTypeResponse;
        schoolInfoBean.arts = mArtsTypeResponse;
        schoolInfoBean.major = inputMajor.getText();
        schoolInfoBean.teamName = inputTeamName.getText();
        schoolInfoBean.teamLeaderName = inputTeamLeaderName.getText();
        schoolInfoBean.teamLeaderPhone = inputTeamLeaderName.getText();
        schoolInfoBean.EstimateHigh = mEstimateMiddleHigh;
        schoolInfoBean.Estimate1 = mEstimateMiddle;
        mParent.setSignupSchoolInfoBean(schoolInfoBean);
        mParent.onSubmit();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_PROVINCE:
                    provinceTypeResponse = data.getParcelableExtra("result");
                    if (provinceTypeResponse == null) {
                        return;
                    }
                    inputSchoolProvice.setText(provinceTypeResponse.getLabel());
                    break;
                case REQUEST_CITY:
                    mCityAreaResponse = data.getParcelableExtra("result");
                    if (mCityAreaResponse == null) {
                        return;
                    }
                    inputSchoolCity.setText(mCityAreaResponse.getName());
                    break;
                case REQUEST_AREA:
                    mAreaResponse = data.getParcelableExtra("result");
                    if (mAreaResponse == null) {
                        return;
                    }
                    inputSchoolArea.setText(mAreaResponse.getName());
                    break;
                case REQUEST_SCHOOLNAME:
                    mSchoolAreaResponse = data.getParcelableExtra("result");
                    if (mSchoolAreaResponse == null) {
                        return;
                    }
                    inputSchoolName.setText(mSchoolAreaResponse.getName());
                   /* inputSchoolName.setText(mSchoolAreaResponse.getName());
                    inputSchoolName1.setText(mSchoolAreaResponse.getName());*/
                    if (mSchoolAreaResponse.getId().equals("-1")){
                        inputSchoolName1.setText("");
                    } else {
                        inputSchoolName1.setText(mSchoolAreaResponse.getName());
                    }
                    break;
                case REQUEST_ESTIMATE_MIDDLE:
                    mEstimateMiddle = data.getParcelableExtra("result");
                    if (mEstimateMiddle == null) {
                        return;
                    }
                    inputEstimate1.setText(mEstimateMiddle.getLabel());
                    break;
                case REQUEST_ATRS:
                    mArtsTypeResponse = data.getParcelableExtra("result");
                    if (mArtsTypeResponse == null) {
                        return;
                    }
                    inputScience.setText(mArtsTypeResponse.getLabel());
                    break;
                case REQUEST_ESTIMATE_MIDDLE_HIGH:
                    mEstimateMiddleHigh = data.getParcelableExtra("result");
                    if (mEstimateMiddleHigh == null) {
                        return;
                    }
                    inputEstimateHigh.setText(mEstimateMiddleHigh.getLabel());
                    break;
                case REQUEST_INORDER:
                    mInOrderTypeResponse = data.getParcelableExtra("result");
                    if (mInOrderTypeResponse == null) {
                        return;
                    }
                    inputInOrder.setText(mInOrderTypeResponse.getLabel());
                    break;
            }
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void onEventComming(EventData eventData) {
        super.onEventComming(eventData);
        if (eventData.getCODE() == Constant.CODE_SIGN){
            mStudentType = mParent.getStudentType();
            if (mStudentType.equals("1") || mStudentType.equals("2")){
                inputTeamLeaderName.setTitle("班主任姓名");
                inputTeamLeaderPhone.setTitle("班主任手机号");
            }
            if (StringUtils.isEmpty(mStudentType)){
                return;
            }
            if (mStudentType.equals("1")){  //初三
                inputInOrder.setVisibility(View.GONE);
                inputScience.setVisibility(View.GONE);
                inputMajor.setVisibility(View.GONE);
                inputTeamName.setVisibility(View.GONE);
                inputTeamLeaderName.setVisibility(View.VISIBLE);
                inputTeamLeaderPhone.setVisibility(View.VISIBLE);
                inputEstimateHigh.setVisibility(View.GONE);
                inputEstimate1.setVisibility(View.VISIBLE);
            } else if (mStudentType.equals("2")){ //高三
                inputInOrder.setVisibility(View.GONE);
                inputScience.setVisibility(View.VISIBLE);
                inputMajor.setVisibility(View.GONE);
                inputTeamName.setVisibility(View.GONE);
                inputTeamLeaderName.setVisibility(View.VISIBLE);
                inputTeamLeaderPhone.setVisibility(View.VISIBLE);
                inputEstimateHigh.setVisibility(View.VISIBLE);
                inputEstimate1.setVisibility(View.GONE);
            } else if (mStudentType.equals("3")){ //军校
                inputInOrder.setVisibility(View.VISIBLE);
                inputScience.setVisibility(View.GONE);
                inputMajor.setVisibility(View.VISIBLE);
                inputTeamName.setVisibility(View.VISIBLE);
                inputTeamLeaderName.setVisibility(View.VISIBLE);
                inputTeamLeaderPhone.setVisibility(View.VISIBLE);
                inputEstimateHigh.setVisibility(View.GONE);
                inputEstimate1.setVisibility(View.GONE);
            }
        }
    }

    class OnSelectListener implements View.OnClickListener{

        private String title, type;
        private int requestCode;

        public OnSelectListener(String title, String type, int requestCode) {
            this.title = title;
            this.type = type;
            this.requestCode = requestCode;
        }

        @Override
        public void onClick(View view) {
            if (requestCode == REQUEST_CITY) { // 选择市
                // 判断省份是否为空
                if (provinceTypeResponse == null) {
                    showToast("请选择省份");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString(ListAreaActivity.KEY_TITLE, title);
                bundle.putString(ListAreaActivity.KEY_ID, provinceTypeResponse.getValue());
                jumpToForResult(ListAreaActivity.class, requestCode, bundle);
                return;
            }

            if (requestCode == REQUEST_AREA) {
                if (mCityAreaResponse == null) {
                    showToast("请选择城市");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString(ListAreaActivity.KEY_TITLE, title);
                bundle.putString(ListAreaActivity.KEY_ID, mCityAreaResponse.getId());
                jumpToForResult(ListAreaActivity.class, requestCode, bundle);
                return;
            }

            if (requestCode == REQUEST_SCHOOLNAME) {
                if (mAreaResponse == null) {
                    showToast("请选择区县");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString(ListAreaActivity.KEY_TITLE, title);
                bundle.putString(ListAreaActivity.KEY_ID, mAreaResponse.getId());
                bundle.putBoolean("isSchool" , true);
                jumpToForResult(ListAreaActivity.class, requestCode, bundle);
                return;
            }

            Bundle bundle = new Bundle();
            bundle.putString(ListSelectActivity.KEY_TITLE, title);
            bundle.putString(ListSelectActivity.KEY_VALUE, type);
            jumpToForResult(ListSelectActivity.class, requestCode, bundle);

        }
    }

}
