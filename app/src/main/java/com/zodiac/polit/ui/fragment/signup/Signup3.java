package com.zodiac.polit.ui.fragment.signup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.minilive.library.network.callback.StringCallback;
import com.minilive.library.util.StringUtils;
import com.zodiac.polit.R;
import com.zodiac.polit.TypeConstant;
import com.zodiac.polit.base.BaseFragment;
import com.zodiac.polit.bean.SignupFamilyInfo;
import com.zodiac.polit.bean.response.AreaResponse;
import com.zodiac.polit.bean.response.SignupInfoResponse;
import com.zodiac.polit.bean.response.TypeResponse;
import com.zodiac.polit.http.provider.OtherProvider;
import com.zodiac.polit.ui.activity.ListAreaActivity;
import com.zodiac.polit.ui.activity.ListSelectActivity;
import com.zodiac.polit.util.DicHelper;
import com.zodiac.polit.widget.InputLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by john on 2018/10/12.
 */

public class Signup3 extends BaseFragment {

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
    Unbinder unbinder;

    private TypeResponse provinceTypeResponse;
    private AreaResponse mCityAreaResponse, mAreaResponse;

    private final int REQUEST_PROVINCE = 21;
    private final int REQUEST_CITY = 22;
    private final int REQUEST_AREA = 23;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.layout_signup3;
    }

    @Override
    protected void initViewAndData() {
        inputProvice.setSelectListener(new OnSelectListener("选择省份", TypeConstant.TYPE_PROVINCE, REQUEST_PROVINCE));
        inputCity.setSelectListener(new OnSelectListener("选择城市", TypeConstant.TYPE_PROVINCE, REQUEST_CITY));
        inputArea.setSelectListener(new OnSelectListener("选择区县", TypeConstant.TYPE_PROVINCE, REQUEST_AREA));
    }

    public void setData(SignupInfoResponse.ContentBean contentBean) {
        DicHelper.getInstance().getTypeResponseByValueAndType(contentBean.getHomeProvince(), TypeConstant.TYPE_PROVINCE, onTypeCallback);
        inputAddress.setText(contentBean.getHomeAddress());
        inputFatherName.setText(contentBean.getFatherName());
        inputFatherPhone.setText(contentBean.getFatherPhone());
        inputMotherName.setText(contentBean.getMotherName());
        inputMotherPhone.setText(contentBean.getMotherPhone());
        mCityAreaResponse = new AreaResponse();
        mCityAreaResponse.setId(contentBean.getHomeCity());
        mAreaResponse = new AreaResponse();
        mAreaResponse.setId(contentBean.getHomeArea());
        OtherProvider.getChildByParentId(contentBean.getHomeProvince(), new AreaCallback(CODE_CITY , contentBean.getHomeCity()));
        OtherProvider.getChildByParentId(contentBean.getHomeCity(), new AreaCallback(CODE_AREA , contentBean.getHomeArea()));
    }

    private boolean isEdit = true;

    public void setEditble(boolean isEdit) {
        inputAddress.setEditble(isEdit);
        inputFatherName.setEditble(isEdit);
        inputFatherPhone.setEditble(isEdit);
        inputMotherName.setEditble(isEdit);
        inputMotherPhone.setEditble(isEdit);
        this.isEdit = isEdit;
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
            if (!isEdit) {
                return;
            }
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


    private DicHelper.OnTypeCallback onTypeCallback = new DicHelper.OnTypeCallback() {
        @Override
        public void callBack(TypeResponse typeResponse) {
            String type = typeResponse.getType();
            switch (type) {
                case TypeConstant.TYPE_PROVINCE:
                    provinceTypeResponse = typeResponse;
                    inputProvice.setText(typeResponse.getLabel());
                    break;
            }
        }
    };


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


    class AreaCallback extends StringCallback {

        public int CODE;
        public String mId;

        public AreaCallback(int CODE, String id) {
            this.CODE = CODE;
            this.mId = id;
        }

        @Override
        public void onError(Call call, Exception e, int id) {

        }

        @Override
        public void onResponse(String response, int id) {
            if (StringUtils.isEmpty(response)) {
                showToast("获取数据失败");
                return;
            }
            List<AreaResponse> areaResponseList = new Gson().fromJson(response, new TypeToken<List<AreaResponse>>() {
            }.getType());
            for (AreaResponse areaResponse : areaResponseList) {
                if (areaResponse.getId().equals(mId)) {
                    if (CODE == CODE_CITY) {
                        mCityAreaResponse = areaResponse;
                        inputCity.setText(areaResponse.getName());
                    }
                    if (CODE == CODE_AREA) {
                        mAreaResponse = areaResponse;
                        inputArea.setText(areaResponse.getName());
                    }
                }
            }
        }
    }

    private final int CODE_CITY = 81;
    private final int CODE_AREA = 82;

    public boolean checkData(){
        if (provinceTypeResponse == null || mCityAreaResponse == null || mAreaResponse == null || StringUtils.isEmpty(inputAddress.getText())) {
            showToast("请填写完整的家庭地址信息");
            return false;
        }
        /*if (StringUtils.isEmpty(inputFatherName.getText()) || !StringUtils.isMobileNO(inputFatherPhone.getText())
                ||StringUtils.isEmpty(inputMotherName.getText()) || !StringUtils.isMobileNO(inputMotherPhone.getText()))
        {
            showToast("请输入正确的信息");
            return false;
        }*/
        return true;
    }

    public SignupFamilyInfo getData(){
        SignupFamilyInfo familyInfo = new SignupFamilyInfo();
        familyInfo.province = provinceTypeResponse;
        familyInfo.city = mCityAreaResponse;
        familyInfo.ares = mAreaResponse;
        familyInfo.address = inputAddress.getText();
        familyInfo.fatherName = inputFatherName.getText();
        familyInfo.fatherPhone = inputFatherPhone.getText();
        familyInfo.motherName = inputMotherName.getText();
        familyInfo.motherPhone = inputMotherPhone.getText();
        return familyInfo;
    }

}
