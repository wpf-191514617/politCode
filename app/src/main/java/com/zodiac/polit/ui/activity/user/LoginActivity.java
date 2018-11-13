package com.zodiac.polit.ui.activity.user;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.minilive.library.entity.EventData;
import com.minilive.library.network.callback.StringCallback;
import com.minilive.library.util.StringUtils;
import com.zodiac.polit.Constant;
import com.zodiac.polit.R;
import com.zodiac.polit.base.BaseActivity;
import com.zodiac.polit.bean.request.LoginRequest;
import com.zodiac.polit.bean.response.UserResponse;
import com.zodiac.polit.http.provider.UserProvider;
import com.zodiac.polit.util.CacheHelper;
import com.zodiac.polit.widget.InputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;
import okhttp3.Call;

/**
 * Created by john on 2018/9/29.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.inputPhone)
    InputLayout inputPhone;
    @BindView(R.id.inputPassword)
    InputLayout inputPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.tvRegister)
    TextView tvRegister;

    @Override
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    protected void initViewAndData() {
        setTitle("登录");
      //  inputPhone.getEditText().setInputType(InputType.TYPE_CLASS_PHONE);
        inputPassword.getEditText().setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnLogin, R.id.tvRegister,R.id.tvForget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvForget:
                jumpTo(ForgetPwdActivity.class);
                break;
            case R.id.btnLogin:
                String phone = inputPhone.getText();
                String password = inputPassword.getText();
                String id = "";
                if (phone.length() < 11){
                    showToast("请输入正确的用户名");
                    return;
                }

                if (phone.length()>11&& phone.length() < 18){
                    showToast("请输入正确的用户名");
                    return;
                }

                if (phone.length() > 18){
                    showToast("请输入正确的用户名");
                    return;
                }

                if (phone.length() == 11){
                    doLogin(phone ,  "", password);
                } else{
                    doLogin("" ,  phone, password);
                }
                break;
            case R.id.tvRegister:
                jumpTo(RegisterActivity.class);
                break;
        }
    }

    private void doLogin(String phone, String idCard, String password) {
        showLoadingDialog();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.cardId = idCard;
        loginRequest.phone = phone;
        loginRequest.password = password;
        UserProvider.doLogin(loginRequest, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
                showToast("操作失败");
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                if (!StringUtils.isEmpty(response)){
                    UserResponse userResponse = new Gson().fromJson(response , UserResponse.class);
                    if (userResponse.getCode().equals("0")){
                        CacheHelper.getInstance().putCurrentUser(response);
                        EventBus.getDefault().post(new EventData(Constant.CODE_LOGIN));
                        showToast("登录成功");
                        setResult(RESULT_OK);
                        finish();
                    } else {
                        showToast(userResponse.getMessage());
                    }
                }
            }
        });
    }
}
