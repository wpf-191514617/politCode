package com.zodiac.polit.ui.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.minilive.library.network.callback.StringCallback;
import com.minilive.library.util.StringUtils;
import com.minilive.library.util.Trace;
import com.sendtion.xrichtext.RichTextView;
import com.zodiac.polit.Constant;
import com.zodiac.polit.R;
import com.zodiac.polit.base.BaseActivity;
import com.zodiac.polit.bean.response.NewDetailResponse;
import com.zodiac.polit.http.provider.HomeProvider;
import com.zodiac.polit.util.HtmlUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by john on 2018/9/27.
 */

public class NewsDetailActivity extends BaseActivity {
    @BindView(R.id.tvNewTitle)
    TextView tvNewTitle;
    @BindView(R.id.tvFrom)
    TextView tvFrom;
    @BindView(R.id.tvBrowseCount)
    TextView tvBrowseCount;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvContent)
    RichTextView tvContent;

    public static final String KEY_CATEGORY = "key.categoryId";
    public static final String KEY_CONTENT = "key.content";

    private String categoryID;
    private String contentID;

    @Override
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    protected View getLoadingTargetView() {
        return findViewById(R.id.layoutContent);
    }

    @Override
    protected void initViewAndData() {
        tvTilte.setBackgroundResource(R.drawable.logo);
        loadData();
    }

    private void loadData() {
        showLoading("");
        HomeProvider.loadNewsDetail(categoryID, contentID, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                showError("");
            }

            @Override
            public void onResponse(String response, int id) {
                reStoreView();
                if (!StringUtils.isEmpty(response)){
                    final NewDetailResponse newDetailResponse = new Gson().fromJson(response , NewDetailResponse.class);
                    if (newDetailResponse != null){
                        setText(tvNewTitle , newDetailResponse.getTitle());
                        setText(tvTime,newDetailResponse.getCreateDate());
                        tvContent.post(new Runnable() {
                            @Override
                            public void run() {
                                tvContent.clearAllLayout();
                                showDataSync(filter(newDetailResponse.getArticleData().getContent()));
                            }
                        });
                    }
                }
            }
        });
    }

    private String filter(String content){
        /*String key = "<p style='text-align;center,'>&nbsp;</p>";
        if (content.contains(key)){
            content.replace(key,"");
        }*/
        return content;
    }


    private ProgressDialog loadingDialog;
   private Subscription subsLoading;

    private void showDataSync(final String content) {
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
                            String imagePath = HtmlUtil.getImgSrc(text);
                            tvContent.addImageViewAtIndex(tvContent.getLastIndex(), Constant.BASEURL + imagePath);
                        } else {
                            tvContent.addTextViewAtIndex(tvContent.getLastIndex(), Html.fromHtml(text));
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


    @Override
    protected void getBundleExtras(Bundle extras) {
        categoryID = extras.getString(KEY_CATEGORY);
        contentID = extras.getString(KEY_CONTENT);
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_newsdetail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
