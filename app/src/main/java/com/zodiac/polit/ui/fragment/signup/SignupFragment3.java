package com.zodiac.polit.ui.fragment.signup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.minilive.library.util.StringUtils;
import com.zodiac.polit.R;
import com.zodiac.polit.TypeConstant;
import com.zodiac.polit.bean.SignupFamilyInfo;
import com.zodiac.polit.bean.response.AreaResponse;
import com.zodiac.polit.bean.response.TypeResponse;
import com.zodiac.polit.ui.activity.ListAreaActivity;
import com.zodiac.polit.ui.activity.ListSelectActivity;
import com.zodiac.polit.widget.InputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by john on 2018/10/5.
 */

public class SignupFragment3 extends BaseSignupFragment {

    @BindView(R.id.inputProvice)
    InputLayout inputProvice;
    @BindView(R.id.inputCity)
    InputLayout inputCity;
    @BindView(R.id.inputArea)
    InputLayout inputArea;
    @BindView(R.id.inputAddress)
    InputLayout inputAddress;
    @BindView(R.id.inputFatherName)
    InputLayout inputFatherName;
    @BindView(R.id.inputFatherPhone)
    InputLayout inputFatherPhone;
    @BindView(R.id.inputMotherName)
    InputLayout inputMotherName;
    @BindView(R.id.inputMotherPhone)
    InputLayout inputMotherPhone;
    @BindView(R.id.btnNext)
    Button btnNext;
    Unbinder unbinder;

    private TypeResponse provinceTypeResponse;
    private AreaResponse mCityAreaResponse, mAreaResponse;

    private final int REQUEST_PROVINCE = 21;
    private final int REQUEST_CITY = 22;
    private final int REQUEST_AREA = 23;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_signup_familyinfo;
    }

    @Override
    protected void initViewAndData() {
        inputProvice.setSelectListener(new OnSelectListener("选择省份", TypeConstant.TYPE_PROVINCE, REQUEST_PROVINCE));
        inputCity.setSelectListener(new OnSelectListener("选择城市", TypeConstant.TYPE_PROVINCE, REQUEST_CITY));
        inputArea.setSelectListener(new OnSelectListener("选择区县", TypeConstant.TYPE_PROVINCE, REQUEST_AREA));
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

    @OnClick(R.id.btnNext)
    public void onViewClicked() {
        if (provinceTypeResponse == null || mCityAreaResponse == null || mAreaResponse == null || StringUtils.isEmpty(inputAddress.getText())) {
            showToast("请填写完整的家庭地址信息");
            return;
        }
        /*if (StringUtils.isEmpty(inputFatherName.getText()) || !StringUtils.isMobileNO(inputFatherPhone.getText())
                ||StringUtils.isEmpty(inputMotherName.getText()) || !StringUtils.isMobileNO(inputMotherPhone.getText()))
        {
            showToast("请输入正确的信息");
            return;
        }*/
        SignupFamilyInfo familyInfo = new SignupFamilyInfo();
        familyInfo.province = provinceTypeResponse;
        familyInfo.city = mCityAreaResponse;
        familyInfo.ares = mAreaResponse;
        familyInfo.address = inputAddress.getText();
        familyInfo.fatherName = inputFatherName.getText();
        familyInfo.fatherPhone = inputFatherPhone.getText();
        familyInfo.motherName = inputMotherName.getText();
        familyInfo.motherPhone = inputMotherPhone.getText();
        mParent.setSignupFamilyInfo(familyInfo);
        mParent.onNext();
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
                    inputProvice.setText(provinceTypeResponse.getLabel());
                    break;
                case REQUEST_CITY:
                    mCityAreaResponse = data.getParcelableExtra("result");
                    if (mCityAreaResponse == null) {
                        return;
                    }
                    inputCity.setText(mCityAreaResponse.getName());
                    break;
                case REQUEST_AREA:
                    mAreaResponse = data.getParcelableExtra("result");
                    if (mAreaResponse == null) {
                        return;
                    }
                    inputArea.setText(mAreaResponse.getName());
                    break;
            }
        }
    }

    class OnSelectListener implements View.OnClickListener {

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
                    showToast("请选择省份");
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString(ListAreaActivity.KEY_TITLE, title);
                bundle.putString(ListAreaActivity.KEY_ID, mCityAreaResponse.getId());
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
