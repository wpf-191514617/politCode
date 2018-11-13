package com.zodiac.polit.ui.fragment.user;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.minilive.library.adapter.BaseViewHolderHelper;
import com.minilive.library.adapter.recycler.BaseRecyclerAdapter;
import com.minilive.library.network.callback.StringCallback;
import com.zodiac.polit.Constant;
import com.zodiac.polit.R;
import com.zodiac.polit.bean.response.ArticleResponse;
import com.zodiac.polit.bean.response.NoticeReaponse;
import com.zodiac.polit.http.provider.HomeProvider;
import com.zodiac.polit.http.provider.UserProvider;
import com.zodiac.polit.ui.WebActivity;
import com.zodiac.polit.ui.activity.NewsDetailActivity;
import com.zodiac.polit.ui.fragment.BaseRecyclerFragment;
import com.zodiac.polit.ui.fragment.PolicyChildFragment;
import com.zodiac.polit.util.HtmlUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by john on 2018/9/30.
 */

public class NoticeListFragment extends PolicyChildFragment {

    protected List<NoticeReaponse.PageBean.ListBean> mDataList = new ArrayList<>();
    @Override
    protected void loadData() {
        UserProvider.getNoticeList(mCurrentPage, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
                showToast(e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
                NoticeReaponse mArticleResponse = new Gson().fromJson(response, NoticeReaponse.class);
                if (mArticleResponse == null){
                    showToast("暂无数据");
                    return;
                }

                if (mArticleResponse.getPage().getList() == null){
                    showToast("暂无数据");
                    return;
                }

                if (mArticleResponse.getPage().getList().size() < HomeProvider.LIMIT){
                    refreshLayout.setEnableLoadMore(false);
                }

                if (mCurrentPage == 1){
                    mDataList.clear();
                }
                mDataList.addAll(mArticleResponse.getPage().getList());
                if (mCurrentPage == 1){
                    mAdapter.setData(mArticleResponse.getPage().getList());
                } else {
                    mAdapter.addMoreData(mArticleResponse.getPage().getList());
                }
            }
        });
    }


    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new NoticeListAdapter(recyclerView);
    }


    class NoticeListAdapter extends BaseRecyclerAdapter<NoticeReaponse.PageBean.ListBean>{

        private int mItemSize;

        public NoticeListAdapter(RecyclerView recyclerView) {
            super(recyclerView,R.layout.item_policy);
            mItemSize = getResources().getDimensionPixelSize(R.dimen.dimen_70dp);
        }

        @Override
        protected void fillData(BaseViewHolderHelper helper, int position, final NoticeReaponse.PageBean.ListBean listBean) {
            ImageView imageView = helper.getImageView(R.id.ivNews);
            imageView.setVisibility(View.GONE);
            final TextView tvContent = helper.getView(R.id.tvContent);
            helper.setText(R.id.tvTitle , listBean.getTitle())
                    .setText(R.id.tvTime , listBean.getUpdateDate());
            helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Bundle bundle = new Bundle();
                    bundle.putString(WebActivity.KEY_TITLE , listBean.getTitle());
                    bundle.putString(WebActivity.KEY_WEB_URL , Constant.BASEURL + listBean.getUrl() + "?id="+listBean.getId() + "-28");
                    jumpTo(WebActivity.class , bundle);*/
                   /* Bundle bundle = new Bundle();
                    bundle.putString(NewsDetailActivity.KEY_CONTENT , listBean.getId());
                    bundle.putString(NewsDetailActivity.KEY_CATEGORY , listBean.getCategory().getId());
                    jumpTo(NewsDetailActivity.class , bundle);*/
                    Bundle bundle = new Bundle();
                    bundle.putString(WebActivity.KEY_WEB_URL , HtmlUtil.getDetailUrl(listBean.getId() , listBean.getCategory().getId()));
                    jumpTo(WebActivity.class , bundle);
                }
            });
            setText(tvContent , listBean.getDescription());
        }
    }

}
