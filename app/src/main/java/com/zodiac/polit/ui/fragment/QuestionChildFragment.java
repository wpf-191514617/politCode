package com.zodiac.polit.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.minilive.library.adapter.BaseViewHolderHelper;
import com.minilive.library.adapter.recycler.BaseRecyclerAdapter;
import com.minilive.library.adapter.recycler.widget.RecyclerDivider;
import com.minilive.library.network.callback.StringCallback;
import com.zodiac.polit.R;
import com.zodiac.polit.bean.response.ArticleResponse;
import com.zodiac.polit.http.provider.HomeProvider;
import com.zodiac.polit.ui.WebActivity;
import com.zodiac.polit.ui.activity.NewsDetailActivity;
import com.zodiac.polit.util.AppHelper;
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

public class QuestionChildFragment extends BaseRecyclerFragment {

    private List<ArticleResponse.PageBean.ListBean> mList = new ArrayList<>();

    @Override
    protected BaseRecyclerAdapter getAdapter() {
        return new QuestionListAdapter(recyclerView);
    }

    @Override
    protected void initViewAndData() {
        super.initViewAndData();
        //refreshLayout.autoRefresh(500);
        onRefresh(refreshLayout);
    }

    @Override
    protected void initLayoutManager() {
        super.initLayoutManager();
        recyclerView.addItemDecoration(RecyclerDivider.newTransparentDivider5height());
    }

    @Override
    protected void loadData() {
        HomeProvider.loadQuestionData(mCurrentPage, new StringCallback() {
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
                ArticleResponse mArticleResponse = new Gson().fromJson(response, ArticleResponse.class);
                if (mArticleResponse == null) {
                    showToast("暂无数据");
                    return;
                }

                if (mArticleResponse.getList().size() < HomeProvider.LIMIT) {
                    refreshLayout.setEnableLoadMore(false);
                }

                if (mCurrentPage == 1) {
                    mList.clear();
                }
                mList.addAll(mArticleResponse.getPage().getList());

                if (mCurrentPage == 1) {
                    mAdapter.setData(mArticleResponse.getList());
                } else {
                    mAdapter.addMoreData(mArticleResponse.getList());
                }
            }
        });
    }


    class QuestionListAdapter extends BaseRecyclerAdapter<ArticleResponse.ListBeanX> {

        public QuestionListAdapter(RecyclerView recyclerView) {
            super(recyclerView, R.layout.item_question);
        }

        @Override
        protected void fillData(BaseViewHolderHelper helper, int position, final ArticleResponse.ListBeanX model) {

            final ArticleResponse.PageBean.ListBean listBean = mList.get(position);
            final TextView tvContent = helper.getView(R.id.tvAnswer);
            // .setText(R.id.tvAnswer , Html.fromHtml(model.getContent()))
            helper.setText(R.id.tvQuestion, mList.get(position).getTitle());

            helper.getConvertView().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Bundle bundle = new Bundle();
                    bundle.putString(NewsDetailActivity.KEY_CATEGORY, listBean.getCategory().getId());
                    bundle.putString(NewsDetailActivity.KEY_CONTENT, listBean.getId());
                    jumpTo(NewsDetailActivity.class, bundle);*/
                    Bundle bundle = new Bundle();
                    bundle.putString(WebActivity.KEY_WEB_URL , HtmlUtil.getDetailUrl(listBean.getId() , listBean.getCategory().getId()));
                    jumpTo(WebActivity.class , bundle);
                }
            });
            setText(tvContent , HtmlUtil.getHtmlText(model.getContent()));
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
                            tvContent.setText(text);
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
