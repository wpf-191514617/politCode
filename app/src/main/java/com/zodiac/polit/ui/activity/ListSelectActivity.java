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
import com.zodiac.polit.TypeConstant;
import com.zodiac.polit.base.BaseActivity;
import com.zodiac.polit.bean.response.TypeResponse;
import com.zodiac.polit.http.provider.OtherProvider;
import com.zodiac.polit.util.SignHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by john on 2018/10/5.
 */

public class ListSelectActivity extends BaseActivity {
    @BindView(R.id.rvList)
    RecyclerView rvList;

    public static final String KEY_TITLE = "key.title";
    public static final String KEY_VALUE = "key.value";

    private String title, value;

    private ListAdapter mListAdapter;

    @Override
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    protected void initViewAndData() {
        if (!StringUtils.isEmpty(title) && !StringUtils.isEmpty(value))
            setTitle(title);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.addItemDecoration(RecyclerDivider.newShapeDivider());
        mListAdapter = new ListAdapter(rvList);
        rvList.setAdapter(mListAdapter);
        mListAdapter.setOnRVItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(ViewGroup parent, View itemView, int position) {
                TypeResponse typeResponse = mListAdapter.getItem(position);
                Intent intent = new Intent();
                intent.putExtra("result", typeResponse);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        loadData();
    }

    private void loadData() {

        /*if (value.equals(TypeConstant.TYPE_CUSTOM_YEAR)) {
            List<TypeResponse> yearTypeResponseList = SignHelper.getTypeListByTag(TypeConstant.TYPE_CUSTOM_YEAR);
            mListAdapter.setData(yearTypeResponseList);
            return;
        }*/

        if (value.equals(TypeConstant.TYPE_CUSTOM_YEAR_TEN)) {
            List<TypeResponse> yearTypeResponseList = SignHelper.getTypeListByTag(TypeConstant.TYPE_CUSTOM_YEAR_TEN);
            mListAdapter.setData(yearTypeResponseList);
            return;
        }

        OtherProvider.getListData(value, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                showToast("获取数据失败");
            }

            @Override
            public void onResponse(String response, int id) {
                Trace.d("list" , "res----" + response);
                if (!StringUtils.isEmpty(response)) {
                    List<TypeResponse> typeResponseList = new Gson().fromJson(response, new TypeToken<List<TypeResponse>>() {
                    }.getType());
                    mListAdapter.setData(typeResponseList);
                }
            }
        });
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        if (extras != null) {
            title = extras.getString(KEY_TITLE);
            value = extras.getString(KEY_VALUE);
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

    class ListAdapter extends BaseRecyclerAdapter<TypeResponse> {

        public ListAdapter(RecyclerView recyclerView) {
            super(recyclerView, R.layout.ietm_select);
        }

        @Override
        protected void fillData(BaseViewHolderHelper helper, int position, TypeResponse model) {
            helper.setText(R.id.tvText, model.getLabel());
        }
    }

}
