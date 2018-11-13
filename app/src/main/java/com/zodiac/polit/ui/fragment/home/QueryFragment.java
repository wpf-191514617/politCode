package com.zodiac.polit.ui.fragment.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.minilive.library.network.callback.StringCallback;
import com.minilive.library.util.StringUtils;
import com.zodiac.polit.R;
import com.zodiac.polit.TypeConstant;
import com.zodiac.polit.base.BaseFragment;
import com.zodiac.polit.bean.request.QueryRequest;
import com.zodiac.polit.bean.response.QueryResponse;
import com.zodiac.polit.bean.response.TypeResponse;
import com.zodiac.polit.http.provider.HomeProvider;
import com.zodiac.polit.ui.activity.ListSelectActivity;
import com.zodiac.polit.util.IDCardUtil;
import com.zodiac.polit.view.CodeUtil;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by john on 2018/9/22.
 */

public class QueryFragment extends BaseFragment {
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etIDCard)
    EditText etIDCard;
    @BindView(R.id.ivCode)
    ImageView ivCode;
    @BindView(R.id.btnQuery)
    LinearLayout btnQuery;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvIDCard)
    TextView tvIDCard;
    @BindView(R.id.tvRole)
    TextView tvRole;
    @BindView(R.id.tvResult)
    TextView tvResult;
    @BindView(R.id.layoutQueryContent)
    LinearLayout layoutQueryContent;
    Unbinder unbinder;
    @BindView(R.id.etCode)
    EditText etCode;
    private final int REQUEST_YEAR = 111;
    @BindView(R.id.layoutYear)
    LinearLayout layoutYear;
    @BindView(R.id.tvMsgInfo)
    TextView tvMsgInfo;
    @BindView(R.id.layoutName)
    LinearLayout layoutName;
    @BindView(R.id.layoutID)
    LinearLayout layoutID;
    @BindView(R.id.layoutArea)
    LinearLayout layoutArea;
    @BindView(R.id.layoutResult)
    LinearLayout layoutResult;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_query;
    }

    @Override
    protected void initViewAndData() {
        ivCode.setImageBitmap(CodeUtil.getInstance().createBitmap());
        etIDCard.setKeyListener(DigitsKeyListener.getInstance("0123456789xX"));
        //etName.setOnClickListener(new OnSelectListener("招飞年份", TypeConstant.TYPE_CUSTOM_YEAR, REQUEST_YEAR));
        etName.setOnClickListener(new OnSelectListener("招飞年份", TypeConstant.TYPE_CUSTOM_YEAR_TEN, REQUEST_YEAR));
        etName.setText(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
        layoutQueryContent.setVisibility(View.INVISIBLE);
        etIDCard.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().length() > 17) {
                    if (!IDCardUtil.checkIdentityCode(charSequence.toString())) {
                        showToast("请输入正确的身份证号码");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
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

    @OnClick({R.id.ivCode, R.id.btnQuery})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivCode:
                ivCode.setImageBitmap(CodeUtil.getInstance().createBitmap());
                break;
            case R.id.btnQuery:
                query();
                break;
        }
    }

    private void query() {
        String name = etName.getText().toString();
        String idCard = etIDCard.getText().toString();
        String code = etCode.getText().toString();
        if (StringUtils.isEmpty(idCard) || idCard.length() < 18) {
            showToast("请输入正确的号码");
            return;
        }
        if (!code.toLowerCase().equals(CodeUtil.getInstance().getCode().toLowerCase())) {
            showToast("请输入正确的验证码");
            return;
        }

        QueryRequest queryRequest = new QueryRequest();
        queryRequest.applayYear = name;
        queryRequest.cardId = idCard;
        HomeProvider.query(queryRequest, new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                showToast("请求失败");
            }

            @Override
            public void onResponse(String response, int id) {
                if (!StringUtils.isEmpty(response)) {
                    QueryResponse queryResponse = new Gson().fromJson(response, QueryResponse.class);
                    if (queryResponse != null && queryResponse.getContent() != null && queryResponse.getCode().equals("0")) {
                        layoutQueryContent.setVisibility(View.VISIBLE);
                        tvMsgInfo.setVisibility(View.GONE);
                        layoutArea.setVisibility(View.VISIBLE);
                        layoutName.setVisibility(View.VISIBLE);
                        layoutID.setVisibility(View.VISIBLE);
                        layoutResult.setVisibility(View.VISIBLE);
                        setText(tvName, queryResponse.getContent().getRealName());
                        setText(tvIDCard, queryResponse.getContent().getCardId());
                        setText(tvRole, queryResponse.getContent().getOffice().getName());
                        setText(tvResult, queryResponse.getContent().getPhaseMsg());

                        /*List<QueryResponse.ContentBean.ListBean> list = queryResponse.getContent().getList();
                        if (BaseAdapterUtil.isListNotEmpty(list)) {
                            layoutQueryContent.setVisibility(View.VISIBLE);
                            QueryResponse.ContentBean.ListBean listBean = list.get(0);
                            setText(tvName, listBean.getRealName());
                            setText(tvIDCard, listBean.getCardId());*/
                            /*setText(tvRole , listBean.get);
                        setText(tvResult , list);
                        }*/
                    } else {
                        layoutQueryContent.setVisibility(View.VISIBLE);
                        tvMsgInfo.setVisibility(View.VISIBLE);
                        setText(tvMsgInfo,"该人员未参加本年度招飞选拔！");
                        layoutArea.setVisibility(View.GONE);
                        layoutName.setVisibility(View.GONE);
                        layoutID.setVisibility(View.GONE);
                        layoutResult.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == Activity.RESULT_OK) {
            TypeResponse typeResponse = data.getParcelableExtra("result");
            if (typeResponse == null) {
                return;
            }
            switch (requestCode) {
                case REQUEST_YEAR:
                    etName.setText(typeResponse.getLabel());
                    break;
            }
        }
    }

    class OnSelectListener implements View.OnClickListener {
        private String title, type;
        private int requestCode;

        public OnSelectListener(String title, String type, int requestCode) {
            this.title = title;
            this.type = type;
            this.requestCode = requestCode;
        }

        @Override
        public void onClick(View view) {
            Bundle bundle = new Bundle();
            bundle.putString(ListSelectActivity.KEY_TITLE, title);
            bundle.putString(ListSelectActivity.KEY_VALUE, type);
            jumpToForResult(ListSelectActivity.class, requestCode, bundle);
        }
    }

}
