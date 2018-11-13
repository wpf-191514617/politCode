package com.zodiac.polit.ui.fragment.signup;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.minilive.library.BaseAdapterUtil;
import com.minilive.library.network.callback.StringCallback;
import com.minilive.library.util.StringUtils;
import com.zodiac.polit.R;
import com.zodiac.polit.TypeConstant;
import com.zodiac.polit.bean.SignupInfoBean;
import com.zodiac.polit.bean.response.CenterResponse;
import com.zodiac.polit.bean.response.OrderResponse;
import com.zodiac.polit.bean.response.SignupInfoResponse;
import com.zodiac.polit.bean.response.TypeResponse;
import com.zodiac.polit.http.provider.OtherProvider;
import com.zodiac.polit.ui.activity.ListSelectActivity;
import com.zodiac.polit.widget.InputLayout;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by john on 2018/10/5.
 */

public class SignupFragment2 extends BaseSignupFragment {


    @BindView(R.id.inputSignupYear)
    InputLayout inputSignupYear;
    @BindView(R.id.inputStudentType)
    InputLayout inputStudentType;
    @BindView(R.id.inputProvince)
    InputLayout inputProvince;
    @BindView(R.id.btnNext)
    Button btnNext;
    Unbinder unbinder;

    private TypeResponse yearTypeResponse , studentTypeResponse, provinceTypeResponse;

    private final int REQUEST_YEAR = 11;
    private final int REQUEST_STUDENT = 12;
    private final int REQUEST_PROVIMCE = 13;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_signup_info;
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

    @OnClick(R.id.btnNext)
    public void onViewClicked() {

        if (yearTypeResponse == null){
            showToast("请选择年份");
            return;
        }

        if (studentTypeResponse == null){
            showToast("请选择学生类型");
            return;
        }

        if (provinceTypeResponse == null){
            showToast("请选择省份");
            return;
        }

        SignupInfoBean signupInfoBean = new SignupInfoBean();
        signupInfoBean.year = yearTypeResponse;
        signupInfoBean.student = studentTypeResponse;
        signupInfoBean.province = provinceTypeResponse;

        findCenter(signupInfoBean);

    }

    private void findCenter(final SignupInfoBean signupInfoBean) {
        OtherProvider.findCenterByProvince(signupInfoBean.province.getValue(), signupInfoBean.student.getValue(),new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                showToast("获取招飞中心失败");
            }

            @Override
            public void onResponse(String res, int id) {
                if (!StringUtils.isEmpty(res)){
                    List<CenterResponse> responseList = new Gson().fromJson(res ,
                            new TypeToken<List<CenterResponse>>(){}.getType());
                    if (BaseAdapterUtil.isListNotEmpty(responseList)){
                        signupInfoBean.centerID = responseList.get(0).getId();
                        findOrder(signupInfoBean ,responseList.get(0).getId());
                    } else {
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
                showToast("获取招飞任务失败");
            }

            @Override
            public void onResponse(String response, int id) {
                if (!StringUtils.isEmpty(response)){
                    List<OrderResponse> orderResponses = new Gson().fromJson(response,
                            new TypeToken<List<OrderResponse>>(){}.getType());
                    if (BaseAdapterUtil.isListNotEmpty(orderResponses)){
                        signupInfoBean.orderID = orderResponses.get(0).getId();
                        mParent.setSignupInfoBean(signupInfoBean);
                        mParent.onNext();
                    } else {
                        showToast("暂无招飞任务");
                    }
                }
            }
        });
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
                case REQUEST_YEAR:
                    yearTypeResponse = typeResponse;
                    inputSignupYear.setText(yearTypeResponse.getLabel());
                    break;
                case REQUEST_STUDENT:
                    studentTypeResponse = typeResponse;
                    inputStudentType.setText(studentTypeResponse.getLabel());
                    break;
                case REQUEST_PROVIMCE:
                    provinceTypeResponse = typeResponse;
                    inputProvince.setText(provinceTypeResponse.getLabel());
                    break;
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
            Bundle bundle = new Bundle();
            bundle.putString(ListSelectActivity.KEY_TITLE, title);
            bundle.putString(ListSelectActivity.KEY_VALUE, type);
            jumpToForResult(ListSelectActivity.class, requestCode, bundle);
        }
    }

}
