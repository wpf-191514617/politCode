package com.zodiac.polit.ui.activity.user;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.minilive.library.entity.EventData;
import com.minilive.library.network.callback.StringCallback;
import com.minilive.library.util.StringUtils;
import com.minilive.library.util.Trace;
import com.zodiac.polit.Constant;
import com.zodiac.polit.R;
import com.zodiac.polit.base.BaseActivity;
import com.zodiac.polit.bean.ResponseBean;
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
 * Created by john on 2018/10/11.
 */

public class UpdateUserInfoActivity extends BaseActivity {
    @BindView(R.id.inputRealName)
    InputLayout inputRealName;
    @BindView(R.id.inputPhone)
    InputLayout inputPhone;
    @BindView(R.id.inputIDCard)
    InputLayout inputIDCard;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView(R.id.tvName)
    TextView tvName;

    @Override
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    protected void initViewAndData() {
        setTitle("修改个人信息");
        inputIDCard.getEditText().setBackgroundResource(R.drawable.bg_et_dd);
        UserResponse userResponse = CacheHelper.getInstance().getCurrentUser();
        UserResponse.ContentBean contentBean = userResponse.getContent();
        if (contentBean != null) {
            setText(tvName , "您好，" + contentBean.getRealName());
            inputRealName.setText(contentBean.getRealName());
            inputPhone.setText(contentBean.getPhone());
            inputIDCard.setText(contentBean.getCardId());
        }
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_updateuserinfo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnSave)
    public void onViewClicked() {
        String realName = inputRealName.getText();
        String phone = inputPhone.getText();
        if (StringUtils.isEmpty(realName)){
            showToast("请输入真实姓名");
            return;
        }
        if (!StringUtils.isMobileNO(phone)){
            showToast("请输入正确的手机号码");
            return;
        }
        updateUserInfo(realName , phone);
    }

    private void updateUserInfo(final String realName, final String phone) {
        showLoadingDialog();
        UserProvider.updateUserInfo(realName, phone, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
                showToast("修改失败");
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                ResponseBean responseBean = new Gson().fromJson(response , ResponseBean.class);
                if (responseBean == null){
                    showToast("修改失败");
                    return;
                }
                if (!responseBean.getCode().equals("0")){
                    showToast(responseBean.getMessage());
                    if (responseBean.getCode().equals("400")) {
                        onLogout();
                    }
                  /*  showToast(responseBean.getMessage());
                    EventBus.getDefault().post(new EventData(Constant.CODE_INVALID));*/
                    return;
                }
                UserResponse userResponse = CacheHelper.getInstance().getCurrentUser();
                UserResponse.ContentBean contentBean = userResponse.getContent();
                contentBean.setRealName(realName);
                contentBean.setPhone(phone);
                userResponse.setContent(contentBean);
                CacheHelper.getInstance().putCurrentUser(userResponse);
                showToast("修改成功");
                EventBus.getDefault().post(new EventData(Constant.CODE_UPDATEINFO));
                finish();
            }
        });
    }


}
