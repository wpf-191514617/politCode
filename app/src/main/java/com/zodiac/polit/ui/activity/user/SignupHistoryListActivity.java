package com.zodiac.polit.ui.activity.user;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.zodiac.polit.R;
import com.zodiac.polit.base.BaseActivity;
import com.zodiac.polit.ui.fragment.signup.SignupHisListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by john on 2018/10/7.
 */

public class SignupHistoryListActivity extends BaseActivity {
    @BindView(R.id.flLayout)
    FrameLayout flLayout;

    @Override
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    protected void initViewAndData() {
        getSupportFragmentManager().beginTransaction().add(R.id.flLayout , new SignupHisListFragment()).commit();
        setTitle("报名记录");
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_noticelist;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
