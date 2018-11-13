package com.zodiac.polit.ui.activity.user;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.minilive.library.network.callback.StringCallback;
import com.minilive.library.util.StringUtils;
import com.zodiac.polit.R;
import com.zodiac.polit.base.BaseActivity;
import com.zodiac.polit.bean.request.RegisterRequest;
import com.zodiac.polit.bean.response.RegisterResponse;
import com.zodiac.polit.http.provider.UserProvider;
import com.zodiac.polit.widget.InputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by john on 2018/9/29.
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.inputName)
    InputLayout inputName;
    @BindView(R.id.inputPhone)
    InputLayout inputPhone;
    @BindView(R.id.inputID)
    InputLayout inputID;
    @BindView(R.id.inputPassword)
    InputLayout inputPassword;
    @BindView(R.id.inputPassword1)
    InputLayout inputPassword1;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.tvLogin)
    TextView tvLogin;
    @Override
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    protected void initViewAndData() {
        setTitle("注册");
        inputPhone.getEditText().setInputType(InputType.TYPE_CLASS_PHONE);
        inputPassword.getEditText().setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        inputPassword1.getEditText().setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnRegister, R.id.tvLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                String name = inputName.getText();
                String phone = inputPhone.getText();
                String idCard = inputID.getText();
                String password = inputPassword.getText();
                String password1 = inputPassword1.getText();

                if (StringUtils.isEmpty(name))
                {
                    showToast("请输入姓名");
                    return;
                }
                if (!StringUtils.isMobileNO(phone)){
                    showToast("请输入正确的手机号");
                    return;
                }

               /* if (StringUtils.isEmpty(idCard) || idCard.length() != 18){
                    showToast("请输入正确的身份证号码");
                    return;
                }*/

                if (StringUtils.isEmpty(password)){
                    showToast("请输入登录密码");
                    return;
                }

                if (!password.equals(password1)) {
                    showToast("俩次输入的密码不一致");
                    return;
                }

                register(name ,phone , idCard , password);

                break;
            case R.id.tvLogin:
                finish();
                break;
        }
    }

    private void register(String name, String phone, String idCard, String password) {
        showLoadingDialog();
        RegisterRequest registerRequest= new RegisterRequest();
        registerRequest.cardId = idCard;
        registerRequest.password = password;
        registerRequest.phone = phone;
        registerRequest.realName = name;
        UserProvider.register(registerRequest, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
                showToast("操作失败");
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                RegisterResponse registerResponse = new Gson().fromJson(response , RegisterResponse.class);
                if (registerResponse != null){
                    showToast("注册成功");
                    finish();
                }
            }
        });
    }
}
