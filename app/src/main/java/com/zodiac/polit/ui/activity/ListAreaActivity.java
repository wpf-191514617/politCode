package com.zodiac.polit.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.minilive.library.adapter.BaseViewHolderHelper;
import com.minilive.library.adapter.recycler.BaseRecyclerAdapter;
import com.minilive.library.adapter.recycler.OnRecyclerItemClickListener;
import com.minilive.library.adapter.recycler.widget.RecyclerDivider;
import com.minilive.library.network.callback.StringCallback;
import com.minilive.library.util.StringUtils;
import com.minilive.library.util.Trace;
import com.zodiac.polit.R;
import com.zodiac.polit.base.BaseActivity;
import com.zodiac.polit.bean.response.AreaResponse;
import com.zodiac.polit.http.provider.OtherProvider;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by john on 2018/10/7.
 */

public class ListAreaActivity extends BaseActivity {

    @BindView(R.id.rvList)
    RecyclerView rvList;

    public static final String KEY_TITLE = "key.title";
    public static final String KEY_ID = "key.id";

    private String title, id;
    private ListAdapter mListAdapter;
    private boolean isSchool;

    @Override
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    protected void initViewAndData() {
        if (!StringUtils.isEmpty(title))
            setTitle(title);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.addItemDecoration(RecyclerDivider.newShapeDivider());
        mListAdapter = new ListAreaActivity.ListAdapter(rvList);
        rvList.setAdapter(mListAdapter);
        mListAdapter.setOnRVItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View itemView, int position) {
                AreaResponse typeResponse = mListAdapter.getItem(position);
                Intent intent = new Intent();
                intent.putExtra("result", typeResponse);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        loadData();
    }

    private void loadData() {

        if (!StringUtils.isEmpty(id)) {
            OtherProvider.getChildByParentId(id, new StringCallback() {
                @Override
                public void onError(Call call, Exception e, int id) {
                    showToast("获取数据失败");
                }

                @Override
                public void onResponse(String response, int id) {
                    if (StringUtils.isEmpty(response)){
                        showToast("获取数据失败");
                        return;
                    }
                    List<AreaResponse> areaResponseList = new Gson().fromJson(response, new TypeToken<List<AreaResponse>>() {
                    }.getType());
                    if (isSchool){
                        AreaResponse areaResponse = new AreaResponse();
                        areaResponse.setId("-1");
                        areaResponse.setName("其它");
                        areaResponseList.add(areaResponse);
                    }
                    mListAdapter.setData(areaResponseList);
                }
            });

            return;
        }

    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        if (extras != null) {
            title = extras.getString(KEY_TITLE);
            id = extras.getString(KEY_ID, "");
            isSchool = extras.getBoolean("isSchool" , false);
        }
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_list_select;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    class ListAdapter extends BaseRecyclerAdapter<AreaResponse> {

        public ListAdapter(RecyclerView recyclerView) {
            super(recyclerView, R.layout.ietm_select);
        }

        @Override
        protected void fillData(BaseViewHolderHelper helper, int position, AreaResponse model) {
            helper.setText(R.id.tvText, model.getName());
        }
    }

}
