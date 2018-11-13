package com.zodiac.polit.ui.fragment.signup;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.minilive.library.util.StringUtils;
import com.minilive.library.util.Trace;
import com.zodiac.polit.R;
import com.zodiac.polit.TypeConstant;
import com.zodiac.polit.base.BaseFragment;
import com.zodiac.polit.bean.SignupUserInfoBean;
import com.zodiac.polit.bean.response.SignupInfoResponse;
import com.zodiac.polit.bean.response.TypeResponse;
import com.zodiac.polit.ui.activity.ListSelectActivity;
import com.zodiac.polit.util.DicHelper;
import com.zodiac.polit.util.IDCardUtil;
import com.zodiac.polit.widget.InputLayout;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by john on 2018/10/12.
 */

public class Signup1 extends BaseFragment {

    @BindView(R.id.inputRealName)
    InputLayout inputRealName;
    @BindView(R.id.inputID)
    InputLayout inputID;
    @BindView(R.id.inputPhone)
    InputLayout inputPhone;
    @BindView(R.id.inputBirthday)
    InputLayout inputBirthday;
    @BindView(R.id.inputSex)
    InputLayout inputSex;
    @BindView(R.id.inputNation)
    InputLayout inputNation;
    @BindView(R.id.inputPolitical)
    InputLayout inputPolitical;
    @BindView(R.id.inputHousehold)
    InputLayout inputHousehold;
    @BindView(R.id.inputChild)
    InputLayout inputChild;
    Unbinder unbinder;

    private final int REQUEST_NATION = 1;
    private final int REQUEST_POLITICS = 2;
    private final int REQUEST_HOUSEHOLD = 3;
    private final int REQUEST_CHILD = 4;

    private TypeResponse nationTypeResponse, politicsTypeResponse, houseHoldTypeResponse , childTypeResponse;
    private AlertDialog.Builder mSexBuilder;

    private DatePickerDialog mDatePickerDialog;
    private int mSex = -1;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.layout_sign1;
    }

    @Override
    protected void initViewAndData() {
        inputNation.setSelectListener(new SelectListener("民族", TypeConstant.TYPE_NATION, REQUEST_NATION));
        inputPolitical.setSelectListener(new SelectListener("政治面貌", TypeConstant.TYPE_POLITICS, REQUEST_POLITICS));
        inputHousehold.setSelectListener(new SelectListener("户口类型", TypeConstant.TYPE_HOUSEHOLD, REQUEST_HOUSEHOLD));
        inputChild.setSelectListener(new SelectListener("独生子女" , TypeConstant.TYPE_CHILD , REQUEST_CHILD));

        inputID.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() > 14) {
                    String year = String.valueOf(IDCardUtil.getYearByIdCard(charSequence.toString()));
                    String month = String.valueOf(IDCardUtil.getMonthByIdCard(charSequence.toString()));
                    String day = String.valueOf(IDCardUtil.getDateByIdCard(charSequence.toString()));
                    inputBirthday.setText(year + "-" + month + "-" + day);
                }

                if (charSequence.toString().length() > 16){
                    mSex = Integer.parseInt(IDCardUtil.getGenderByIdCard(charSequence.toString()));

                    if (mSex == 1)
                        inputSex.setText("男");
                    else
                        inputSex.setText("女");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void showDateDialog() {
        if (mDatePickerDialog == null) {
            final Calendar calendar = Calendar.getInstance();
            mDatePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    if (calendar.get(Calendar.YEAR) <= year) {
                        showToast("时间有误，请从新选择");
                        return;
                    }

                    inputBirthday.setText(year + "-" + month + "-" + day);
                }
            }, calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH));
        }
        mDatePickerDialog.show();
    }

    private void showSexDialog() {
        if (mSexBuilder == null) {
            mSexBuilder = new AlertDialog.Builder(getContext());
            mSexBuilder.setTitle("性别");
        }
        mSexBuilder.setSingleChoiceItems(R.array.sex, mSex-1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                if (i == 0) {
                    mSex = 1;
                    inputSex.setText("男");
                } else {
                    mSex = 2;
                    inputSex.setText("女");
                }
            }
        });
        mSexBuilder.create().show();
    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == Activity.RESULT_OK) {
            TypeResponse typeResponse = data.getParcelableExtra("result");
            if (typeResponse == null) {
                return;
            }
            switch (requestCode) {
                case REQUEST_NATION:
                    nationTypeResponse = typeResponse;
                    inputNation.setText(nationTypeResponse.getLabel());
                    break;
                case REQUEST_POLITICS:
                    politicsTypeResponse = typeResponse;
                    inputPolitical.setText(politicsTypeResponse.getLabel());
                    break;
                case REQUEST_HOUSEHOLD:
                    houseHoldTypeResponse = typeResponse;
                    inputHousehold.setText(houseHoldTypeResponse.getLabel());
                    break;
                case REQUEST_CHILD:
                    childTypeResponse = typeResponse;
                    inputChild.setText(childTypeResponse.getLabel());
                    break;
            }
        }
    }

    class SelectListener implements View.OnClickListener {

        private String title, type;
        private int requestCode;

        public SelectListener(String title, String type, int requestCode) {
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

    private boolean isEdit = true;

    public void setEditble(boolean isEdit){
        inputRealName.setEditble(isEdit);
        inputPhone.setEditble(isEdit);
        inputBirthday.setEditble(isEdit);
        inputSex.setEditble(isEdit);
        this.isEdit = isEdit;
    }


    public void setData(SignupInfoResponse.ContentBean contentBean){
        inputRealName.setText(contentBean.getRealName());
        inputID.setText(contentBean.getCardId());
        inputPhone.setText(contentBean.getPhone());
        inputBirthday.setText(contentBean.getBirthday());
        /*if (contentBean.getSex().equals("1")){
            inputSex.setText("男");
        } else {
            inputSex.setText("女");
        }*/
        inputSex.setOnlyRead();
        inputBirthday.setOnlyRead();
        DicHelper.getInstance().getTypeResponseByValueAndType(contentBean.getNation() , TypeConstant.TYPE_NATION , onTypeCallback);
        DicHelper.getInstance().getTypeResponseByValueAndType(contentBean.getPoliticsType() , TypeConstant.TYPE_POLITICS , onTypeCallback);
        DicHelper.getInstance().getTypeResponseByValueAndType(contentBean.getHouseholdRegistration() , TypeConstant.TYPE_HOUSEHOLD , onTypeCallback);
        DicHelper.getInstance().getTypeResponseByValueAndType(contentBean.getIsOneChild() , TypeConstant.TYPE_CHILD , onTypeCallback);
    }


    private DicHelper.OnTypeCallback onTypeCallback = new DicHelper.OnTypeCallback() {
        @Override
        public void callBack(TypeResponse typeResponse) {
            String type = typeResponse.getType();
            switch (type){
                case TypeConstant.TYPE_NATION:
                    nationTypeResponse = typeResponse;
                    inputNation.setText(typeResponse.getLabel());
                    break;
                case TypeConstant.TYPE_POLITICS:
                    politicsTypeResponse = typeResponse;
                    inputPolitical.setText(typeResponse.getLabel());
                    break;
                case TypeConstant.TYPE_HOUSEHOLD:
                    houseHoldTypeResponse = typeResponse;
                    inputHousehold.setText(typeResponse.getLabel());
                    break;
                case TypeConstant.TYPE_CHILD:
                    childTypeResponse = typeResponse;
                    inputChild.setText(typeResponse.getLabel());
                    break;
            }
        }
    };

    public boolean checkData(){
        if (StringUtils.isEmpty(inputRealName.getText())){
            showToast("请输入真实姓名");
            return false;
        }
        if (!IDCardUtil.checkIdentityCode(inputID.getText())){
       // if (StringUtils.isEmpty(inputID.getText())){
            showToast("请输入正确的身份证号码");
            return false;
        }
        if (!StringUtils.isMobileNO(inputPhone.getText())){
            showToast("请输入正确的手机号");
            return false;
        }
        if (StringUtils.isEmpty(inputBirthday.getText())){
            showToast("请输入出生日期");
            return false;
        }
        if (mSex == -1){
            showToast("请选择性别");
            return false;
        }
        if (nationTypeResponse == null){
            showToast("请选择民族");
            return false;
        }
        if (politicsTypeResponse == null){
            showToast("请选择政治面貌");
            return false;
        }
        if (houseHoldTypeResponse == null){
            showToast("请选择户口类型");
            return false;
        }
        if (childTypeResponse == null){
            showToast("请选择是否独生子女");
            return false;
        }
        return true;
    }


    public SignupUserInfoBean getData(){
        SignupUserInfoBean signupUserInfoBean = new SignupUserInfoBean();
        signupUserInfoBean.realName = inputRealName.getText().toString();
        signupUserInfoBean.id = inputID.getText().toString();
        signupUserInfoBean.phone = inputPhone.getText().toString();
        signupUserInfoBean.birthday = inputBirthday.getText().toString();
        signupUserInfoBean.sex = mSex;
        signupUserInfoBean.nation = nationTypeResponse;
        signupUserInfoBean.politics = politicsTypeResponse;
        signupUserInfoBean.houseHold = houseHoldTypeResponse;
        signupUserInfoBean.child = childTypeResponse;
        return signupUserInfoBean;
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
}
