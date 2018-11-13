package com.minilive.library.adapter.recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.minilive.library.adapter.BaseViewHolderHelper;
import com.minilive.library.listener.OnNoDoubleClickListener;

/**
 * Created by Administrator on 2018/1/2.
 */

public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {

    private Context mContext;
    private OnRecyclerItemClickListener mRecyclerItemClickListener;
    private OnRecyclerItemLongClickListener mRecyclerItemLongClickListener;
    private BaseViewHolderHelper mViewHolderHelper;
    private RecyclerView mRecyclerView;
    private BaseRecyclerAdapter mRecyclerAdapter;

    public BaseRecyclerViewHolder(BaseRecyclerAdapter recyclerAdapter , RecyclerView recyclerView ,
                                  View itemView , OnRecyclerItemClickListener itemClickListener ,
                                  OnRecyclerItemLongClickListener itemLongClickListener) {
        super(itemView);
        mRecyclerAdapter = recyclerAdapter;
        mRecyclerView = recyclerView;
        mContext = recyclerView.getContext();
        mRecyclerItemClickListener = itemClickListener;
        mRecyclerItemLongClickListener = itemLongClickListener;
        itemView.setOnClickListener(new OnNoDoubleClickListener() {
            @Override
            protected void onSingleClick(View view) {
                if (view.getId() == BaseRecyclerViewHolder.this.itemView.getId() &&
                        mRecyclerItemClickListener != null){
                    mRecyclerItemClickListener.onItemClick(mRecyclerView , view , getAdapterPositionWrapper());
                }
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (v.getId() == BaseRecyclerViewHolder.this.itemView.getId() && null != mRecyclerItemLongClickListener) {
                    return mRecyclerItemLongClickListener.onItemLongClick(mRecyclerView, v, getAdapterPositionWrapper());
                }
                return false;
            }
        });

        mViewHolderHelper = new BaseViewHolderHelper(mRecyclerView , this);
    }

    public BaseViewHolderHelper getViewHolderHelper(){
        return mViewHolderHelper;
    }

    public int getAdapterPositionWrapper() {
        if (mRecyclerAdapter.getHeadersCount() > 0) {
            return getAdapterPosition() - mRecyclerAdapter.getHeadersCount();
        } else {
            return getAdapterPosition();
        }
    }


}
