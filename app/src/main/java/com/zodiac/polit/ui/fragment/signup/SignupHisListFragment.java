package com.zodiac.polit.ui.fragment.signup;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.minilive.library.BaseAdapterUtil;
import com.minilive.library.adapter.BaseViewHolderHelper;
import com.minilive.library.adapter.recycler.BaseRecyclerAdapter;
import com.minilive.library.adapter.recycler.widget.RecyclerDivider;
import com.minilive.library.entity.EventData;
import com.minilive.library.network.callback.StringCallback;
import com.minilive.library.util.StringUtils;
import com.minilive.library.util.Trace;
import com.zodiac.polit.Constant;
import com.zodiac.polit.R;
import com.zodiac.polit.TypeConstant;
import com.zodiac.polit.bean.ResponseBean;
import com.zodiac.polit.bean.response.SignupHisBean;
import com.zodiac.polit.bean.response.TypeResponse;
import com.zodiac.polit.http.provider.UserProvider;
import com.zodiac.polit.ui.activity.SignInfoActivity;
import com.zodiac.polit.ui.fragment.BaseRecyclerFragment;
import com.zodiac.polit.util.DicHelper;

import java.util.List;

import okhttp3.Call;

/**
 * Created by john on 2018/10/7.
 */

public class SignupHisListFragment extends BaseRecyclerFragment {

    private HisListAdapter mHisListAdapter;
    private List<SignupHisBean.ContentBean.MemberApplayListBean> mList;
    private int mDelIndex;

    @Override
    protected void initViewAndData() {
        super.initViewAndData();
        //  refreshLayout.autoRefresh(500);
        onRefresh(refreshLayout);
    }

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void onEventComming(EventData eventData) {
        super.onEventComming(eventData);
        if (eventData.getCODE() == Constant.CODE_LOGIN)
            refreshLayout.autoRefresh(500);
    }

    @Override
    protected void initLayoutManager() {
        super.initLayoutManager();
        recyclerView.addItemDecoration(RecyclerDivider.newTransparentDivider5height());
        refreshLayout.setEnableLoadMore(false);
    }

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        mHisListAdapter = new HisListAdapter(recyclerView);
        return mHisListAdapter;
    }

    @Override
    protected void loadData() {
        showLoadingDialog();
        UserProvider.getSignupHistoryList(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
                refreshLayout.finishRefresh();
                showToast(e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                refreshLayout.finishRefresh();
                if (!StringUtils.isEmpty(response)) {
                    SignupHisBean signupHisBean = new Gson().fromJson(response, SignupHisBean.class);
                    if (signupHisBean.getCode().equals("0")) {
                        if (signupHisBean.getContent() != null) {
                            mList = signupHisBean.getContent().getMemberApplayList();
                            mDelIndex = 0;
                            queryStatus();
                        }
                    } else {
                        dismissLoadingDialog();
                        showToast(signupHisBean.getMessage());
                        if (signupHisBean.getCode().equals("400")) {
                            onLogout();
                        }
                    }
                }
            }
        });
    }

    private void queryStatus() {
        if (mDelIndex >= mList.size()) {
            dismissLoadingDialog();
            mHisListAdapter.setData(mList);
        } else {
            UserProvider.judgeCanDelete(mList.get(mDelIndex).getId(), new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    mList.get(mDelIndex).setDel(false);
                    mDelIndex++;
                    queryStatus();
                }

                @Override
                public void onResponse(String response, int id) {
                    if (StringUtils.isEmpty(response)) {
                        mList.get(mDelIndex).setDel(false);
                        mDelIndex++;
                        queryStatus();
                        return;
                    }
                    ResponseBean responseBean = new Gson().fromJson(response, ResponseBean.class);
                    if (responseBean != null && responseBean.getCode().equals("0")) {
                        //TODO 可以删除
                        mList.get(mDelIndex).setDel(true);
                        mDelIndex++;
                        queryStatus();
                    } else {
                        // TODO  不可删除
                        mList.get(mDelIndex).setDel(false);
                        mDelIndex++;
                        queryStatus();
                    }
                }
            });
        }
    }


    class HisListAdapter extends BaseRecyclerAdapter<SignupHisBean.ContentBean.MemberApplayListBean> {

        public HisListAdapter(RecyclerView recyclerView) {
            super(recyclerView, R.layout.item_sign1);
        }

        public void notifyData(SignupHisBean.ContentBean.MemberApplayListBean model){
            for (int i = 0;i < mData.size();i++){
                if (model.getId().equals(mData.get(i).getId())){
                    mData.get(i).setDel(model.isDel());
                    notifyDataSetChanged();
                }
            }
        }

        public void deleteModel(SignupHisBean.ContentBean.MemberApplayListBean model){
            for (int i = 0;i < mData.size();i++){
                if (model.getId().equals(mData.get(i).getId())){
                    mData.remove(i);
                    notifyDataSetChanged();
                }
            }
        }

        @Override
        protected void fillData(final BaseViewHolderHelper helper, int position, final SignupHisBean.ContentBean.MemberApplayListBean model) {
            helper.setText(R.id.tvName, model.getOffice().getName())
                    .setText(R.id.tvTime, model.getMemberApplayTask().getApplayDateStart() + " - " + model.getMemberApplayTask().getApplayDateEnd())
                    .setText(R.id.tvID, model.getCardId());

            Trace.d("status" , "status-----" + model.getPhaseStatus() + "----name-----" + model.getPhaseName());


            DicHelper.getInstance().getTypeResponseByValueAndType(model.getPhaseName(), TypeConstant.TYPE_PHASE_NAME, new DicHelper.OnTypeCallback() {
                @Override
                public void callBack(TypeResponse typeResponse) {
                    final String name = typeResponse.getLabel();
                    DicHelper.getInstance().getTypeResponseByValueAndType(model.getPhaseStatus(), TypeConstant.TYPE_PHASE_STATUS, new DicHelper.OnTypeCallback() {
                        @Override
                        public void callBack(TypeResponse typeResponse) {
                            helper.setText(R.id.tvStatus, name + " " + typeResponse.getLabel());
                        }
                    });
                }
            });
            helper.getView(R.id.tvMake).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString(SignInfoActivity.KEY_ID, model.getId());
                    bundle.putString(SignInfoActivity.KEY_NAME, model.getOffice().getName());
                    bundle.putString(SignInfoActivity.KEY_TIME, model.getMemberApplayTask().getApplayDateStart() + " 至 " + model.getMemberApplayTask().getApplayDateEnd());
                    bundle.putString(SignInfoActivity.KEY_YEAR, model.getApplayYear());
                    bundle.putString(SignInfoActivity.KEY_STUDENT_TYPE, model.getStudentType());
                    jumpTo(SignInfoActivity.class, bundle);
                }
            });

            final TextView tvDel = helper.getView(R.id.tvDelete);
            if (model.isDel()) {
                tvDel.setSelected(true);
            } else {
                tvDel.setSelected(false);
            }

            helper.getView(R.id.tvDelete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tvDel.isSelected())
                        showDeleteSignIn(model);
                }
            });
        }
    }

    private AlertDialog.Builder mDelBuilder;

    private void showDeleteSignIn(final SignupHisBean.ContentBean.MemberApplayListBean model) {
        if (mDelBuilder == null) {
            mDelBuilder = new AlertDialog.Builder(getActivity())
                    .setMessage("是否删除该条记录");
        }
        mDelBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                deleSignIn(model);
            }
        }).setNegativeButton("取消", null).create().show();
    }

    private void deleSignIn(final SignupHisBean.ContentBean.MemberApplayListBean model) {
        showLoadingDialog();
        UserProvider.judgeCanDelete(model.getId(), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                showToast("删除失败");
                dismissLoadingDialog();
            }

            @Override
            public void onResponse(String response, int id) {
                if (StringUtils.isEmpty(response)) {
                    dismissLoadingDialog();
                    showToast("删除失败");
                    return;
                }
                ResponseBean responseBean = new Gson().fromJson(response, ResponseBean.class);
                if (responseBean != null && responseBean.getCode().equals("0")) {
                    delMember(model);
                } else {
                    if (responseBean != null)
                    {
                        dismissLoadingDialog();
                        showToast(responseBean.getMessage());
                        model.setDel(false);
                        mHisListAdapter.notifyData(model);
                    }
                }
            }
        });
    }

    private void delMember(final SignupHisBean.ContentBean.MemberApplayListBean model){
        UserProvider.delete(model.getId(), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissLoadingDialog();
                showToast("删除失败");
            }

            @Override
            public void onResponse(String response, int id) {
                dismissLoadingDialog();
                if (StringUtils.isEmpty(response)) {
                    showToast("删除失败");
                    return;
                }

                ResponseBean responseBean = new Gson().fromJson(response, ResponseBean.class);
                if (responseBean != null && responseBean.getCode().equals("0")) {
                    mHisListAdapter.deleteModel(model);
                    showToast("删除成功");
                } else {
                    if (responseBean != null)
                    {
                        dismissLoadingDialog();
                        showToast(responseBean.getMessage());
                        model.setDel(false);
                        mHisListAdapter.notifyData(model);
                    }
                }
            }
        });
    }

}
