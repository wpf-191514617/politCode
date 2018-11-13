package com.zodiac.polit.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.minilive.library.network.callback.StringCallback;
import com.minilive.library.util.StringUtils;
import com.zodiac.polit.R;
import com.zodiac.polit.TypeConstant;
import com.zodiac.polit.base.BaseActivity;
import com.zodiac.polit.bean.response.ApplyStatusResponse;
import com.zodiac.polit.bean.response.TypeResponse;
import com.zodiac.polit.http.provider.UserProvider;
import com.zodiac.polit.util.DicHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * Created by john on 2018/10/8.
 */

public class SignInfoActivity extends BaseActivity {
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvMechanism)
    TextView tvMechanism;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvStatus)
    TextView tvStatus;
    @BindView(R.id.tvMake)
    TextView tvMake;
    @BindView(R.id.tvSign1)
    TextView tvSign1;
    @BindView(R.id.tvMsg1)
    TextView tvMsg1;
    @BindView(R.id.tvSignTime1)
    TextView tvSignTime1;
    @BindView(R.id.tvStatus1)
    TextView tvStatus1;
    @BindView(R.id.tvSign2)
    TextView tvSign2;
    @BindView(R.id.tvMsg2)
    TextView tvMsg2;
    @BindView(R.id.tvSignTime2)
    TextView tvSignTime2;
    @BindView(R.id.tvStatus2)
    TextView tvStatus2;
    @BindView(R.id.tvSign3)
    TextView tvSign3;
    @BindView(R.id.tvMsg3)
    TextView tvMsg3;
    @BindView(R.id.tvSignTime3)
    TextView tvSignTime3;
    @BindView(R.id.tvStatus3)
    TextView tvStatus3;
    @BindView(R.id.tvSign4)
    TextView tvSign4;
    @BindView(R.id.tvMsg4)
    TextView tvMsg4;
    @BindView(R.id.tvSignTime4)
    TextView tvSignTime4;
    @BindView(R.id.tvStatus4)
    TextView tvStatus4;

    public static final String KEY_ID = "key.id";
    public static final String KEY_NAME = "name";
    public static final String KEY_TIME = "time";
    public static final String KEY_YEAR = "year";
    public static final String KEY_STUDENT_TYPE = "student_type";
    @BindView(R.id.lineView1)
    View lineView1;
    @BindView(R.id.lineView2)
    View lineView2;
    @BindView(R.id.lineView3)
    View lineView3;
    private String id = "";

    @Override
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    protected void initViewAndData() {
        setTitle("招飞进展");
        if (StringUtils.isEmpty(id)) {
            showToast("参数错误");
            return;
        }
        tvStatus.setText("报名详情");
        showLoadingDialog();
        UserProvider.getApplyStatus(id, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
                showToast(e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                if (response == null) {
                    showToast("获取数据失败");
                    return;
                }

                List<ApplyStatusResponse> statusResponses = new Gson().fromJson(response, new TypeToken<List<ApplyStatusResponse>>() {
                }.getType());

                if (statusResponses == null || statusResponses.size() < 3) {
                    showToast("获取数据失败");
                    return;
                }

                setText(tvMechanism, name);
                setText(tvTime, "报名时间：" + time);

                DicHelper.getInstance().getTypeResponseByValueAndType(type, TypeConstant.TYPE_STUDENT, new DicHelper.OnTypeCallback() {
                    @Override
                    public void callBack(TypeResponse typeResponse) {
                        setText(tvName, year + "年" + typeResponse.getLabel() + "报名");
                    }
                });


                /*setText(tvMsg1, statusResponses.get(0).getPhaseStatus());
                setText(tvSignTime1, statusResponses.get(0).getUpdateDate());
                setText(tvMsg2, statusResponses.get(1).getPhaseStatus());
                setText(tvSignTime2, statusResponses.get(1).getUpdateDate());
                setText(tvMsg3, statusResponses.get(2).getPhaseStatus());
                setText(tvSignTime3, statusResponses.get(2).getUpdateDate());
                setText(tvMsg4, statusResponses.get(3).getPhaseStatus());
                setText(tvSignTime4, statusResponses.get(3).getUpdateDate());*/
                int textColor = getResources().getColor(R.color.white);
                int bgResource = R.drawable.bg_signup_status;
                for (int i = 0; i < statusResponses.size(); i++) {
                    ApplyStatusResponse statusResponse = statusResponses.get(i);
                    if (statusResponse.getShowed().equals("active")) {
                        if (i == 0) {
                            setText(tvMsg1, statusResponses.get(0).getPhaseStatus());
                            setText(tvSignTime1, statusResponses.get(0).getUpdateDate().substring(0, 10));
                            tvSign1.setBackgroundResource(bgResource);
                            tvSign1.setTextColor(textColor);
                            lineView1.setBackgroundColor(getResources().getColor(R.color.orange));
                        }
                        if (i == 1) {
                            setText(tvMsg2, statusResponses.get(i).getPhaseStatus());
                            setText(tvSignTime2, statusResponses.get(i).getUpdateDate().substring(0, 10));
                            tvSign2.setBackgroundResource(bgResource);
                            tvSign2.setTextColor(textColor);
                            lineView2.setBackgroundColor(getResources().getColor(R.color.orange));
                           /* tvSign3.setTextColor(textColor);
                            tvSign4.setTextColor(textColor);
                            tvSign3.setBackgroundResource(R.drawable.bg_signup_status_n);
                            tvSign4.setBackgroundResource(R.drawable.bg_signup_status_n);
                            setText(tvSignTime3, "");
                            setText(tvSignTime4, "");
                            setText(tvMsg3, "");
                            setText(tvMsg4, "");*/
                        }
                        if (i == 2) {
                            setText(tvMsg3, statusResponses.get(i).getPhaseStatus());
                            setText(tvSignTime3, statusResponses.get(i).getUpdateDate().substring(0, 10));
                            tvSign3.setBackgroundResource(bgResource);
                            tvSign3.setTextColor(textColor);
                            lineView3.setBackgroundColor(getResources().getColor(R.color.orange));
                            /*tvSign4.setTextColor(textColor);
                            tvSign4.setBackgroundResource(R.drawable.bg_signup_status_n);
                            setText(tvSignTime4, "");
                            setText(tvMsg4, "");*/
                        }

                        if (i == 3) {
                            setText(tvMsg4, statusResponses.get(i).getPhaseStatus());
                            setText(tvSignTime4, statusResponses.get(i).getUpdateDate().substring(0, 10));
                            tvSign4.setBackgroundResource(bgResource);
                            tvSign4.setTextColor(textColor);

                            /*tvSign4.setTextColor(textColor);
                            tvSign4.setBackgroundResource(R.drawable.bg_signup_status_n);
                            setText(tvSignTime4, "");
                            setText(tvMsg4, "");*/
                        }

                    }
                }
            }
        });
    }

    private String name, time, year, type;

    @Override
    protected void getBundleExtras(Bundle extras) {
        id = extras.getString(KEY_ID);
        name = extras.getString(KEY_NAME);
        time = extras.getString(KEY_TIME);
        year = extras.getString(KEY_YEAR);
        type = extras.getString(KEY_STUDENT_TYPE);
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_signupinfo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tvStatus)
    public void onViewClicked() {
        Bundle bundle = new Bundle();
        bundle.putString(UpdateSignInfoActivity.KEY_ID, id);
        jumpTo(UpdateSignInfoActivity.class, bundle);
    }
}
