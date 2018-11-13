package com.zodiac.polit.ui.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;

import com.google.gson.Gson;
import com.minilive.library.entity.EventData;
import com.minilive.library.network.callback.StringCallback;
import com.minilive.library.util.StringUtils;
import com.minilive.library.util.Trace;
import com.zodiac.polit.Constant;
import com.zodiac.polit.R;
import com.zodiac.polit.base.BaseActivity;
import com.zodiac.polit.bean.RequestBean;
import com.zodiac.polit.bean.ResponseBean;
import com.zodiac.polit.bean.request.UpdatePwdRequest;
import com.zodiac.polit.http.provider.UserProvider;
import com.zodiac.polit.ui.MainActivity;
import com.zodiac.polit.util.AppHelper;
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

public class UpdatePasswordActivity extends BaseActivity {

    @BindView(R.id.inputOldPassword)
    InputLayout inputOldPassword;
    @BindView(R.id.inputPassword)
    InputLayout inputPassword;
    @BindView(R.id.inputPassword1)
    InputLayout inputPassword1;
    @BindView(R.id.btnCommit)
    Button btnCommit;

    @Override
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    protected void initViewAndData() {
        setTitle("修改密码");
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_update_pwd;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnCommit)
    public void onViewClicked() {
        String old = inputOldPassword.getText();
        String password = inputPassword.getText();
        String password1 = inputPassword1.getText();

        if (StringUtils.isEmpty(old)){
            showToast("请输入旧密码");
            return;
        }

        if (StringUtils.isEmpty(password)){
            showToast("请输入新密码");
            return;
        }

        if (StringUtils.isEmpty(password1)){
            showToast("输入的确认密码不能为空");
            return;
        }

        if (!password.equals(password1)){
            showToast("俩次输入的密码不一致");
            return;
        }

        commit(old,password);

    }

    private void commit(String old, String password) {
        showLoadingDialog();
        UpdatePwdRequest updatePwdRequest = new UpdatePwdRequest();
        RequestBean requestBean = AppHelper.getRequestBean();
        updatePwdRequest.sessionId = requestBean.sessionId;
        updatePwdRequest.timestamp = requestBean.timestamp;
        updatePwdRequest.signature = requestBean.signature;
        updatePwdRequest.oldPassword = old;
        updatePwdRequest.newPassword = password;
        updatePwdRequest.confirmNewPassword = password;
        UserProvider.updatePwd(updatePwdRequest, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
                e.printStackTrace();
                onLogout();
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                if (!TextUtils.isEmpty(response)){
                    ResponseBean responseBean = new Gson().fromJson(response , ResponseBean.class);
                    if (responseBean.getCode().equals("0")){
                        showToast("操作成功");
                        sendEvent(new EventData(Constant.CODE_LOGOUT));
                        jumpToThenKill(LoginActivity.class);
                        return;
                    }
                    showToast(responseBean.getMessage());
                    if (responseBean.getCode().equals("400")) {
                        onLogout();
                    }
                    /*if (responseBean.getCode().equals("1")){
                        showToast(responseBean.getMessage());
                        CacheHelper.getInstance().clear();
                        Intent intent = new Intent(UpdatePasswordActivity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }*/
                }
            }
        });
    }
}
