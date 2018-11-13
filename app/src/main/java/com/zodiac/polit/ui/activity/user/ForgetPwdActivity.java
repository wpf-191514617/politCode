package com.zodiac.polit.ui.activity.user;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.minilive.library.network.callback.StringCallback;
import com.minilive.library.util.StringUtils;
import com.zodiac.polit.R;
import com.zodiac.polit.base.BaseActivity;
import com.zodiac.polit.bean.ResponseBean;
import com.zodiac.polit.bean.response.SendSMSCodeResponse;
import com.zodiac.polit.http.provider.UserProvider;
import com.zodiac.polit.util.CountDownTimerTask;
import com.zodiac.polit.widget.InputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by john on 2018/10/13.
 */

public class ForgetPwdActivity extends BaseActivity {
    @BindView(R.id.inputIDCard)
    InputLayout inputIDCard;
    @BindView(R.id.inputPhone)
    InputLayout inputPhone;
    @BindView(R.id.inputCode)
    InputLayout inputCode;
    @BindView(R.id.tvSendCode)
    TextView tvSendCode;
    @BindView(R.id.btnCommit)
    Button btnCommit;

    private CountDownTimerTask mCountDownTimerTask;
    private SendSMSCodeResponse sendSMSCodeResponse;

    @Override
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    protected void initViewAndData() {
        setTitle("找回密码");
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_forgetpwd;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tvSendCode, R.id.btnCommit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvSendCode:
                String phone = inputPhone.getText();
                if (!StringUtils.isMobileNO(phone)){
                    showToast("请输入正确的手机号");
                    return;
                }
                tvSendCode.setClickable(false);
                sendSMSCode(phone);
                break;
            case R.id.btnCommit:
                String idCard = inputIDCard.getText();
                if (StringUtils.isEmpty(idCard)){
                    showToast("请输入身份证号");
                    return;
                }
                String phone1 = inputPhone.getText();
                if (!StringUtils.isMobileNO(phone1)){
                    showToast("请输入正确的手机号");
                    return;
                }

                if (sendSMSCodeResponse == null){
                    showToast("请先发送验证码");
                    return;
                }
                String code = inputCode.getText().toString();
                if (StringUtils.isEmpty(code)){
                    showToast("请输入验证码");
                    return;
                }
                submmit(idCard , phone1 , code);
                break;
        }
    }

    private void submmit(String idCard, String phone1, String code) {
        showLoadingDialog();
        UserProvider.forgetPwd(idCard, phone1, code, sendSMSCodeResponse.getSessionId(), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                showToast("操作失败");
                dismissLoadingDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                ResponseBean responseBean = new Gson().fromJson(response , ResponseBean.class);
                Toast.makeText(ForgetPwdActivity.this , responseBean.getMessage() , Toast.LENGTH_LONG).show();
                if(responseBean.getCode().equals("0")){
                    finish();
                }
            }
        });
    }

    private void sendSMSCode(String phone) {
        UserProvider.sendSMSCode(phone, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                tvSendCode.setClickable(true);
                showToast("发送验证码失败");
            }

            @Override
            public void onResponse(String response, int id) {
                if (!StringUtils.isEmpty(response)){
                    sendSMSCodeResponse = new Gson().fromJson(response , SendSMSCodeResponse.class);
                    if (sendSMSCodeResponse != null){
                        if (sendSMSCodeResponse.getCode().equals("0")){
                            countDown();
                        } else {
                            tvSendCode.setClickable(true);
                            showToast(sendSMSCodeResponse.getMessage());
                        }
                    }
                } else {
                    tvSendCode.setClickable(true);
                    showToast("发送验证码失败");
                }
            }
        });
    }

    private void countDown() {
        if(mCountDownTimerTask == null){
            mCountDownTimerTask = new CountDownTimerTask(new CountDownTimerTask.OnTimerCallback() {
                @Override
                public void onTick(long millisUntilFinished) {
                    tvSendCode.setTextColor(Color.parseColor("#666666"));
                    tvSendCode.setText(millisUntilFinished/1000 + "秒后重发");
                }

                @Override
                public void onFinish() {
                    tvSendCode.setTextColor(getResources().getColor(R.color.orange));
                    tvSendCode.setText("发送验证码");
                    tvSendCode.setClickable(true);
                }
            });
        }
        mCountDownTimerTask.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimerTask != null){
            mCountDownTimerTask.cancel();
        }
    }
}
