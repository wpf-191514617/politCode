package com.minilive.library.adapter.recycler;

import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/1/2.
 */

public interface OnRecyclerItemClickListener {

    void onItemClick(ViewGroup parent, View itemView, int position);

}
