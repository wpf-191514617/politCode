package com.zodiac.polit.ui.activity.user;

import android.os.Bundle;

import com.zodiac.polit.R;
import com.zodiac.polit.base.BaseActivity;
import com.zodiac.polit.ui.fragment.user.NoticeListFragment;

/**
 * Created by john on 2018/9/30.
 */

public class NoticeListActivity extends BaseActivity {
    @Override
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    protected void initViewAndData() {
        setTitle("通知公告");
        getSupportFragmentManager().beginTransaction().add(R.id.flLayout ,new NoticeListFragment()).commit();
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_noticelist;
    }
}
