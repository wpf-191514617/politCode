package com.zodiac.polit.base;

import android.content.pm.ActivityInfo;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.minilive.library.entity.EventData;
import com.minilive.library.net.NetUtils;
import com.minilive.library.ui.BaseAppcomtActivity;
import com.minilive.library.util.StringUtils;
import com.minilive.library.viewcontroller.callback.BaseView;
import com.zodiac.polit.Constant;
import com.zodiac.polit.R;
import com.zodiac.polit.ui.activity.user.LoginActivity;

import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2018/1/8.
 */

public abstract class BaseActivity extends BaseAppcomtActivity implements BaseView {

    protected Toolbar mToolbar;

    protected TextView tvTilte;

    private boolean isResume = false;

    private LoadingDailog loadingDailog;

    protected void showLoadingDialog(){
        if (loadingDailog == null){
            LoadingDailog.Builder loadBuilder=new LoadingDailog.Builder(this)
                    .setShowMessage(false)
                    .setCancelable(false)
                    .setCancelOutside(false);
            loadingDailog = loadBuilder.create();
        }
        loadingDailog.show();
    }

    protected void dismissLoadingDialog(){
        if (loadingDailog != null && loadingDailog.isShowing()){
            loadingDailog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        super.onResume();
        isResume = true;
    }

    @Override
    public void setContentView(int layoutResId) {
        super.setContentView(layoutResId);
        mToolbar = ButterKnife.findById(this, R.id.common_toolbar);
        if (null != mToolbar) {
                setSupportActionBar(mToolbar);
                getSupportActionBar().setHomeButtonEnabled(true);
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle("");
                mToolbar.setNavigationIcon(R.drawable.left);
                tvTilte = (TextView) findViewById(R.id.tvTitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onCustomBack();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void onCustomBack() {
        onBackPressed();
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
        if (tvTilte != null && title != null && !StringUtils.isEmpty(title.toString())) {
            tvTilte.setText(title);
        }
    }

    @Override
    public void setTitle(int titleId) {
        super.setTitle(titleId);
        if (tvTilte != null && titleId != 0) {
            tvTilte.setText(titleId);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isResume = false;
    }


    protected void onLogout(){
        sendEvent(new EventData(Constant.CODE_LOGOUT));
        jumpTo(LoginActivity.class);
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void onEventComming(EventData data) {

    }

    @Override
    public void showLoading(String msg) {
        toggleShowLoading(true, null);
    }

    @Override
    public void hideLoading() {
        toggleShowLoading(false, null);
    }

    @Override
    public void showError(String msg) {
        /*toggleShowError(true, msg, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickReload();
            }
        });*/

        toggleShowEmpty(true, msg, R.drawable.logo, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickReload();
            }
        });

    }


    @Override
    public void showException(String msg) {
        showError(msg);
    }

    @Override
    public void showNetError() {
        toggleNetworkError(true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickReload();
            }
        });
    }

    public void showEmpty(String msg, int imgResourceId) {
        toggleShowEmpty(true, msg, imgResourceId, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickReload();
            }
        });
    }


    public void showDefaultView(View view) {
        toggleShowDefaultView(view);
    }

    @Override
    public void showNormal() {
        toggleShowEmpty(true, getString(R.string.no_data), R.drawable.logo, new View
                .OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickReload();
            }
        });
        /*toggleNetworkError(true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickReload();
            }
        });*/
    }

    protected boolean checkNetStatus() {
        if (!NetUtils.isNetworkConnected(this)) {
            showNetErrorDialog();
            return false;
        } else {
            return true;
        }
    }

    protected void showNetErrorDialog() {
    }

    protected void onClickReload() {
    }


    protected void setText(TextView tv, String value) {
        if (StringUtils.isEmpty(value)) {
            value = "";
        }
        tv.setText(value);
    }

    protected void sendEvent(EventData data){
        EventBus.getDefault().post(data);
    }

}
