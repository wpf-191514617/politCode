package com.zodiac.polit.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.zodiac.polit.R;
import com.zodiac.polit.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by john on 2018/9/24.
 */

public class QuestionFragment extends BaseFragment {
    @BindView(R.id.flLayout)
    FrameLayout flQuestion;
    Unbinder unbinder;

    private QuestionChildFragment questionChildFragment;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_question;
    }

    @Override
    protected void initViewAndData() {

        questionChildFragment = new QuestionChildFragment();

        getChildFragmentManager().beginTransaction().add(R.id.flLayout, questionChildFragment).commit();
    }


    @Override
    protected void onFirstUserVisible() {
        super.onFirstUserVisible();
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
}
