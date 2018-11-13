package com.zodiac.polit.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minilive.library.adapter.recycler.BaseRecyclerAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zodiac.polit.R;
import com.zodiac.polit.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by john on 2018/9/23.
 */

public abstract class BaseRecyclerFragment extends BaseFragment implements OnRefreshListener , OnLoadMoreListener{

    protected int mCurrentPage;

    @BindView(R.id.recyclerView)
    protected RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    protected SmartRefreshLayout refreshLayout;
    Unbinder unbinder;
    protected BaseRecyclerAdapter mAdapter;


    @Override
    protected int getContentViewLayoutID() {
        return R.layout.recyclerview;
    }

    @Override
    protected void initViewAndData() {
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
        mAdapter = getAdapter();
        initLayoutManager();
        recyclerView.setAdapter(mAdapter);
    }

    protected abstract BaseRecyclerAdapter getAdapter();

    protected void initLayoutManager() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void onFirstUserVisible() {
        super.onFirstUserVisible();
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                onRefresh(refreshLayout);
            }
        },200);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mCurrentPage++;
        loadData();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mCurrentPage = 1;
        refreshLayout.setEnableLoadMore(true);
        loadData();
    }

    protected abstract void loadData();
}
