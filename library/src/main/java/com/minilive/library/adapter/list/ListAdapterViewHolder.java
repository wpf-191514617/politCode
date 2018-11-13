package com.minilive.library.adapter.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minilive.library.adapter.BaseViewHolderHelper;

public class ListAdapterViewHolder {

    protected View mConvertView;
    protected BaseViewHolderHelper mViewHolderHelper;

    private ListAdapterViewHolder(ViewGroup parent, int layoutId) {
        mConvertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
        mViewHolderHelper = new BaseViewHolderHelper(parent, mConvertView);
    }

    public static ListAdapterViewHolder dequeueReusableAdapterViewHolder(View convertView, ViewGroup parent, int layoutId) {
        if (convertView == null) {
            return new ListAdapterViewHolder(parent, layoutId);
        }
        return (ListAdapterViewHolder) convertView.getTag();
    }

    public BaseViewHolderHelper getViewHolderHelper() {
        return mViewHolderHelper;
    }

    public View getConvertView() {
        return mConvertView;
    }

}
