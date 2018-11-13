package com.zodiac.polit.ui.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.minilive.library.entity.EventData;
import com.zodiac.polit.Constant;
import com.zodiac.polit.R;
import com.zodiac.polit.base.BaseFragment;
import com.zodiac.polit.bean.request.IDRequest;
import com.zodiac.polit.bean.response.UserResponse;
import com.zodiac.polit.ui.activity.SignupActivity;
import com.zodiac.polit.ui.activity.user.NoticeListActivity;
import com.zodiac.polit.ui.activity.user.SignupHistoryListActivity;
import com.zodiac.polit.ui.activity.user.UpdatePasswordActivity;
import com.zodiac.polit.ui.activity.user.UpdateUserInfoActivity;
import com.zodiac.polit.util.CacheHelper;
import com.zodiac.polit.widget.RowView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

/**
 * Created by john on 2018/9/22.
 */

public class MineFragment extends BaseFragment {

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.rvUserInfo)
    RowView rvUserInfo;
    @BindView(R.id.rvUpdatePwd)
    RowView rvUpdatePwd;
    @BindView(R.id.rvNotice)
    RowView rvNotice;
    @BindView(R.id.rvPolit)
    RowView rvPolit;
    @BindView(R.id.rvHistory)
    RowView rvHistory;
    @BindView(R.id.btnLogout)
    Button btnLogout;
    Unbinder unbinder;

    private UserResponse mUserResponse;

    private AlertDialog mBuilder;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initViewAndData() {
        setUserInfo();
    }

    private void setUserInfo() {
        if (CacheHelper.getInstance().getCurrentUser() != null) {
            mUserResponse = CacheHelper.getInstance().getCurrentUser();
            tvName.setText("您好，" + mUserResponse.getContent().getRealName());
        } else {
            tvName.setText("");
        }
    }

    @Override
    protected void onEventComming(EventData eventData) {
        super.onEventComming(eventData);
        if (eventData.getCODE() == Constant.CODE_UPDATEINFO ||
                eventData.getCODE() == Constant.CODE_LOGIN || eventData.getCODE() == Constant.CODE_LOGOUT) {
            setUserInfo();
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
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

    @OnClick({R.id.rvUserInfo, R.id.rvUpdatePwd, R.id.rvNotice, R.id.rvPolit, R.id.rvHistory, R.id.btnLogout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rvUserInfo:
                jumpTo(UpdateUserInfoActivity.class);
                break;
            case R.id.rvUpdatePwd:
                jumpTo(UpdatePasswordActivity.class);
                break;
            case R.id.rvNotice:
                jumpTo(NoticeListActivity.class);
                break;
            case R.id.rvPolit:
                jumpTo(SignupActivity.class);
                break;
            case R.id.rvHistory:
                jumpTo(SignupHistoryListActivity.class);
                break;
            case R.id.btnLogout:
                if (mBuilder == null) {
                    mBuilder = new AlertDialog.Builder(getActivity()).setMessage("是否退出？").setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            EventBus.getDefault().post(new EventData(Constant.CODE_LOGOUT, "0"));
                        }
                    }).setNegativeButton("取消", null).create();
                }
                mBuilder.show();
                break;
        }
    }
}
