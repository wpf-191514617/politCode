package com.zodiac.polit.base;

import android.view.View;
import android.widget.TextView;

import com.android.tu.loadingdialog.LoadingDailog;
import com.minilive.library.entity.EventData;
import com.minilive.library.net.NetUtils;
import com.minilive.library.util.StringUtils;
import com.minilive.library.viewcontroller.callback.BaseView;
import com.zodiac.polit.Constant;
import com.zodiac.polit.R;
import com.zodiac.polit.ui.activity.user.LoginActivity;

import de.greenrobot.event.EventBus;

/**
 * Created by Administrator on 2018/1/8.
 */

public abstract class BaseFragment extends com.minilive.library.ui.BaseFragment implements BaseView {

    private LoadingDailog loadingDailog;

    protected void showLoadingDialog(){
        if (loadingDailog == null){
            LoadingDailog.Builder loadBuilder=new LoadingDailog.Builder(getActivity())
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

    protected void onUserVisible() {

    }

    @Override
    protected void onUserInVisible() {

    }

    @Override
    protected void onFirstUserInVisible() {

    }

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onEventComming(EventData eventData) {

    }

    public void showDefaultView(View view){
        toggleShowDefaultView(view);
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    public void showLoading(String msg)
    {
        toggleShowLoading(true, msg);
    }

    @Override
    public void hideLoading()
    {
        toggleShowLoading(false , null);
    }

    @Override
    public void showError(String msg)
    {
        toggleShowEmpty(true, msg, R.drawable.logo, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickReload();
            }
        });
        /*toggleShowError(true, msg, new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickReload();
            }
        });*/
    }

    @Override
    public void showException(String msg)
    {
        showError(msg);
    }

    @Override
    public void showNetError()
    {
        toggleNetworkError(true, new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickReload();
            }
        });
    }

    @Override
    public void showNormal()
    {

        toggleShowEmpty(true, getString(R.string.no_data), R.drawable.logo, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickReload();
            }
        });

       /* toggleShowEmpty(true, null, new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickReload();
            }
        });*/
    }

    public void showEmpty(String msg , int imgResource){
        toggleShowEmpty(true, msg , imgResource, new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onClickReload();
            }
        });
    }


    protected boolean checkNet(){
        if(!NetUtils.isNetworkConnected(getActivity())){
            showNetErrorDialog();
            return false;
        }else{
            return true;
        }
    }

    @Override
    protected boolean isRegisterEventBus() {
        return false;
    }

    protected void showNetErrorDialog(){}

    protected void onClickReload(){}


    protected void setText(TextView tv , String text){
        if (StringUtils.isEmpty(text)){
            text = "";
        }
        if (tv != null){
        tv.setText(text);
    }}

    @Override
    public void onResume() {
        super.onResume();
//        MobclickAgent.onPageStart(TAG);
    }

    @Override
    public void onPause() {
        super.onPause();
//        MobclickAgent.onPageEnd(TAG);
    }

    protected void onLogout(){
        EventBus.getDefault().post(new EventData(Constant.CODE_LOGOUT));
        jumpTo(LoginActivity.class);
    }

}
