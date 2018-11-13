package com.zodiac.polit.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.minilive.library.adapter.BaseViewHolderHelper;
import com.minilive.library.adapter.recycler.BaseRecyclerAdapter;
import com.minilive.library.adapter.recycler.widget.RecyclerDivider;
import com.minilive.library.entity.EventData;
import com.minilive.library.network.callback.StringCallback;
import com.minilive.library.util.StringUtils;
import com.zodiac.polit.Constant;
import com.zodiac.polit.R;
import com.zodiac.polit.bean.response.ArticleResponse;
import com.zodiac.polit.http.provider.HomeProvider;
import com.zodiac.polit.ui.WebActivity;
import com.zodiac.polit.ui.activity.NewsDetailActivity;
import com.zodiac.polit.util.HtmlUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by john on 2018/9/24.
 */

public class PolicyChildFragment extends BaseRecyclerFragment {

    protected List<ArticleResponse.PageBean.ListBean> mList = new ArrayList<>();

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new PolicyListAdapter(recyclerView);
    }

    @Override
    protected void initViewAndData() {
        super.initViewAndData();
        autoRefresh();
    }

    protected void autoRefresh(){
        //refreshLayout.autoRefresh(500);
        onRefresh(refreshLayout);
    }

    @Override
    protected void initLayoutManager() {
        super.initLayoutManager();
        recyclerView.addItemDecoration(RecyclerDivider.newShapeDivider());
    }

    @Override
    protected void loadData() {
        HomeProvider.loadPolicyData(mCurrentPage, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
                showToast(e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                setData(response);
            }
        });
    }

    protected void setData(String response) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        ArticleResponse mArticleResponse = new Gson().fromJson(response, ArticleResponse.class);
        if (mArticleResponse == null){
            showToast("暂无数据");
            return;
        }

        if (mArticleResponse.getList() == null){
            showToast("暂无数据");
            return;
        }

        if (mArticleResponse.getList().size() < HomeProvider.LIMIT){
            refreshLayout.setEnableLoadMore(false);
        }

        if (mCurrentPage == 1){
            mList.clear();
        }
        mList.addAll(mArticleResponse.getPage().getList());
        if (mCurrentPage == 1){
            mAdapter.setData(mArticleResponse.getList());
        } else {
            mAdapter.addMoreData(mArticleResponse.getList());
        }
    }


    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void onEventComming(EventData eventData) {
        super.onEventComming(eventData);
        if (eventData.getCODE() == Constant.CODE_LOGIN){
            autoRefresh();
        } else if (eventData.getCODE() == Constant.CODE_CITY1){
            autoRefresh();
        }
    }


    class PolicyListAdapter extends BaseRecyclerAdapter<ArticleResponse.ListBeanX>{

        private int mItemSize;

        public PolicyListAdapter(RecyclerView recyclerView) {
            super(recyclerView,R.layout.item_policy);
            mItemSize = getResources().getDimensionPixelSize(R.dimen.dimen_70dp);
        }

        @Override
        protected void fillData(BaseViewHolderHelper helper, int position, final ArticleResponse.ListBeanX model) {
            final ArticleResponse.PageBean.ListBean listBean = mList.get(position);
            ImageView imageView = helper.getImageView(R.id.ivNews);
            if (StringUtils.isEmpty(listBean.getImageSrc())){
                imageView.setVisibility(View.GONE);
            } else {
                imageView.setVisibility(View.VISIBLE);
                Glide.with(PolicyChildFragment.this).load(Constant.BASEURL + listBean.getImageSrc())
                        .into(imageView);
            }

            final TextView tvContent = helper.getView(R.id.tvContent);

            helper.setText(R.id.tvTitle , listBean.getTitle())
            .setText(R.id.tvTime , listBean.getCreateDate());

            helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Bundle bundle = new Bundle();
                    bundle.putString(NewsDetailActivity.KEY_CATEGORY , listBean.getCategory().getId());
                    bundle.putString(NewsDetailActivity.KEY_CONTENT , listBean.getId());
                    jumpTo(NewsDetailActivity.class , bundle);*/
                    Bundle bundle = new Bundle();
                    bundle.putString(WebActivity.KEY_WEB_URL , HtmlUtil.getDetailUrl(listBean.getId() , listBean.getCategory().getId()));
                    jumpTo(WebActivity.class , bundle);
                }
            });
            String content = HtmlUtil.getHtmlText(model.getContent());
            setText(tvContent , content);

            LinearLayout linearLayout = helper.getView(R.id.layoutItem);

            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
            if (StringUtils.isEmpty(content)){
                layoutParams.height = mItemSize;
            } else {
                layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
            }
            linearLayout.setLayoutParams(layoutParams);

            /*tvContent.post(new Runnable() {
                @Override
                public void run() {
                    tvContent.setText("");
                    showDataSync(model.getContent(), tvContent);
                }
            });*/

        }
    }


    private Subscription subsLoading;


    private void showDataSync(final String content , final TextView tvContent) {
        subsLoading = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                showEditData(subscriber, content);
            }
        })
                .onBackpressureBuffer()
                .subscribeOn(Schedulers.io())//生产事件在io
                .observeOn(AndroidSchedulers.mainThread())//消费事件在UI线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        showToast("解析错误：图片不存在或已损坏");
                    }

                    @Override
                    public void onNext(String text) {
                        if (text.contains("<img") && text.contains("src=")) {
                            //imagePath可能是本地路径，也可能是网络地址
                                /*String imagePath = HtmlUtil.getImgSrc(text);
                                tvContent.addImageViewAtIndex(tvContent.getLastIndex(), Constant.BASEURL + imagePath);*/
                        } else {
                            tvContent.setText(Html.fromHtml(text));
                        }
                    }
                });

    }

    private void showEditData(Subscriber<? super String> subscriber, String html) {
        try {
            List<String> textList = HtmlUtil.cutStringByImgTag(html);
            for (int i = 0; i < textList.size(); i++) {
                String text = textList.get(i);
                subscriber.onNext(text);
            }
            subscriber.onCompleted();
        } catch (Exception e){
            e.printStackTrace();
            subscriber.onError(e);
        }
    }


}
