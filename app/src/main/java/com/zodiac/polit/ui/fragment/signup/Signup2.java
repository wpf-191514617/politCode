package com.zodiac.polit.ui.fragment.signup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minilive.library.entity.EventData;
import com.zodiac.polit.Constant;
import com.zodiac.polit.R;
import com.zodiac.polit.TypeConstant;
import com.zodiac.polit.base.BaseFragment;
import com.zodiac.polit.bean.SignupInfoBean;
import com.zodiac.polit.bean.response.SignupInfoResponse;
import com.zodiac.polit.bean.response.TypeResponse;
import com.zodiac.polit.ui.activity.ListSelectActivity;
import com.zodiac.polit.util.DicHelper;
import com.zodiac.polit.widget.InputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

/**
 * Created by john on 2018/10/12.
 */

public class Signup2 extends BaseFragment {
    @BindView(R.id.inputSignupYear)
    InputLayout inputSignupYear;
    @BindView(R.id.inputStudentType)
    InputLayout inputStudentType;
    @BindView(R.id.inputProvince)
    InputLayout inputProvince;
    Unbinder unbinder;

    private TypeResponse yearTypeResponse , studentTypeResponse, provinceTypeResponse;

    private final int REQUEST_YEAR = 11;
    private final int REQUEST_STUDENT = 12;
    private final int REQUEST_PROVIMCE = 13;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.layout_signup2;
    }

    @Override
    protected void initViewAndData() {
        inputSignupYear.setSelectListener(new OnSelectListener("招飞年份" , TypeConstant.TYPE_CUSTOM_YEAR , REQUEST_YEAR));
        inputStudentType.setSelectListener(new OnSelectListener("学生类型" , TypeConstant.TYPE_STUDENT , REQUEST_STUDENT));
        inputProvince.setSelectListener(new OnSelectListener("省份" , TypeConstant.TYPE_PROVINCE , REQUEST_PROVIMCE));
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

    public void setData(SignupInfoResponse.ContentBean contentBean){
        yearTypeResponse = new TypeResponse();
        yearTypeResponse.setLabel(contentBean.getApplayYear());
        inputSignupYear.setText(contentBean.getApplayYear());
        DicHelper.getInstance().getTypeResponseByValueAndType(contentBean.getStudentType() , TypeConstant.TYPE_STUDENT, onTypeCallback);
        DicHelper.getInstance().getTypeResponseByValueAndType(contentBean.getApplayProvince() , TypeConstant.TYPE_PROVINCE, onTypeCallback);
    }

    private DicHelper.OnTypeCallback onTypeCallback = new DicHelper.OnTypeCallback() {
        @Override
        public void callBack(TypeResponse typeResponse) {
            String type = typeResponse.getType();
            switch (type){
                case TypeConstant.TYPE_STUDENT:
                    studentTypeResponse = typeResponse;
                    inputStudentType.setText(typeResponse.getLabel());
                    break;
                case TypeConstant.TYPE_PROVINCE:
                    provinceTypeResponse = typeResponse;
                    inputProvince.setText(typeResponse.getLabel());
                    break;
            }
        }
    };



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == Activity.RESULT_OK) {
            TypeResponse typeResponse = data.getParcelableExtra("result");
            if (typeResponse == null) {
                return;
            }
            switch (requestCode) {
                case REQUEST_YEAR:
                    yearTypeResponse = typeResponse;
                    inputSignupYear.setText(yearTypeResponse.getLabel());
                    break;
                case REQUEST_STUDENT:
                    studentTypeResponse = typeResponse;
                    inputStudentType.setText(studentTypeResponse.getLabel());
                    EventBus.getDefault().post(new EventData(Constant.CODE_CHANGED,studentTypeResponse.getValue()));
                    break;
                case REQUEST_PROVIMCE:
                    provinceTypeResponse = typeResponse;
                    inputProvince.setText(provinceTypeResponse.getLabel());
                    break;
            }
        }
    }

    private boolean isEdit = true;

    public void setEditble(boolean isEdit){
        this.isEdit = isEdit;
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
            if (!isEdit){
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString(ListSelectActivity.KEY_TITLE, title);
            bundle.putString(ListSelectActivity.KEY_VALUE, type);
            jumpToForResult(ListSelectActivity.class, requestCode, bundle);
        }
    }

    public boolean checkData(){
        if (yearTypeResponse == null){
            showToast("请选择年份");
            return false;
        }

        if (studentTypeResponse == null){
            showToast("请选择学生类型");
            return false;
        }

        if (provinceTypeResponse == null){
            showToast("请选择省份");
            return false;
        }
        return true;
    }

    public SignupInfoBean getData(){
        SignupInfoBean signupInfoBean = new SignupInfoBean();
        signupInfoBean.year = yearTypeResponse;
        signupInfoBean.student = studentTypeResponse;
        signupInfoBean.province = provinceTypeResponse;
        return  signupInfoBean;
    }


}
