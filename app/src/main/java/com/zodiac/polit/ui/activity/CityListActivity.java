package com.zodiac.polit.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.minilive.library.BaseAdapterUtil;
import com.zodiac.polit.R;
import com.zodiac.polit.base.BaseActivity;
import com.zodiac.polit.bean.CityBean;
import com.zodiac.polit.bean.CityEntity;
import com.zodiac.polit.util.GetJsonDataUtil;
import com.zodiac.polit.view.IndexStickyViewDecoration;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ittiger.indexlist.IndexStickyView;
import cn.ittiger.indexlist.adapter.IndexHeaderFooterAdapter;
import cn.ittiger.indexlist.adapter.IndexStickyViewAdapter;
import cn.ittiger.indexlist.listener.OnItemClickListener;

/**
 * Created by john on 2018/11/3.
 */

public class CityListActivity extends BaseActivity implements OnItemClickListener<CityEntity> {

    @BindView(R.id.indexStickyView)
    IndexStickyView indexStickyView;

    private CityAdapter mAdapter;

    @Override
    protected boolean isRegisterEventBus() {
        return false;
    }

    @Override
    protected void initViewAndData() {
        setTitle("选择城市");
        Intent intent = getIntent();
        if(intent.getParcelableExtra("city") != null){
            mCityEntity = intent.getParcelableExtra("city");
        }
        new CityLoadTask().execute();
        indexStickyView.addItemDecoration(new IndexStickyViewDecoration(this));
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_select_city;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    private List<CityEntity> loadCityData() {
        String JsonData = new GetJsonDataUtil().getJson(this, "city.json");
        try {
            JSONArray data = new JSONArray(JsonData);
            Gson gson = new Gson();
            List<CityBean> cityBeans = new ArrayList<>();
            for (int i = 0; i < data.length(); i++) {
                CityBean entity = gson.fromJson(data.optJSONObject(i).toString(), CityBean.class);
                cityBeans.add(entity);
            }
            List<CityEntity> cityEntities = new ArrayList<>();
            for (int i = 0;i < cityBeans.size();i++){
                CityBean cityBean = cityBeans.get(i);
                for (int j = 0;j < cityBean.getChildren().size();j++){
                    CityBean.ChildrenBeanX beanX = cityBean.getChildren().get(j);
                    CityEntity cityEntity = new CityEntity();
                    cityEntity.setCode(beanX.getCode());
                    cityEntity.setName(beanX.getName());
                    cityEntities.add(cityEntity);
                }
            }

            return cityEntities;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public void onItemClick(View childView, int position, CityEntity item) {
        Intent intent = new Intent();
        intent.putExtra("result" , item);
        setResult(RESULT_OK , intent);
        finish();
    }


    class CityLoadTask extends AsyncTask<Void,Void,List<CityEntity>> {

        @Override
        protected List<CityEntity> doInBackground(Void... voids) {
            return loadCityData();
        }

        @Override
        protected void onPostExecute(List<CityEntity> cityEntities) {
            super.onPostExecute(cityEntities);
            mAdapter = new CityAdapter(cityEntities);
            mAdapter.setOnItemClickListener(CityListActivity.this);
            indexStickyView.setAdapter(mAdapter);
            addHead();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }



    private CityEntity mCityEntity;


    List<CityEntity> initLocationCitys() {
        List<CityEntity> list = new ArrayList<>();
        list.add(mCityEntity);
        return list;
    }

    private void addHead(){
        IndexHeaderFooterAdapter<CityEntity> locationCityHeaderAdapter = new IndexHeaderFooterAdapter<CityEntity>(
                "定", "当前城市", initLocationCitys()
        ) {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent) {

                View view = LayoutInflater.from(CityListActivity.this).inflate(android.R.layout.simple_list_item_1, parent, false);
                return new CityViewHolder(view);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, CityEntity itemData) {
                CityViewHolder cityViewHolder = (CityViewHolder) holder;
                cityViewHolder.mTextView.setText(itemData.getName());
            }
        };
        locationCityHeaderAdapter.setOnItemClickListener(this);
        indexStickyView.addIndexHeaderAdapter(locationCityHeaderAdapter);
    }




    class CityAdapter extends IndexStickyViewAdapter<CityEntity> {

        private List<CityEntity> originalList;

        public CityAdapter(List<CityEntity> originalList) {
            super(originalList);
            setData(originalList);
        }

        private void setData(List<CityEntity> originalList) {
            if (BaseAdapterUtil.isListNotEmpty(originalList)){
                this.originalList = originalList;
            } else {
                this.originalList = new ArrayList<>();
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateIndexViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(CityListActivity.this).inflate(R.layout.indexsticky_item_index, parent, false);
            return new IndexViewHolder(view);
        }

        @Override
        public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
            View view = LayoutInflater.from(CityListActivity.this).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new CityViewHolder(view);
        }

        @Override
        public void onBindIndexViewHolder(RecyclerView.ViewHolder holder, int position, String indexName) {
            IndexViewHolder indexViewHolder = (IndexViewHolder) holder;
            indexViewHolder.mTextView.setText(indexName);
        }

        @Override
        public void onBindContentViewHolder(RecyclerView.ViewHolder holder, int position, CityEntity itemData) {
            CityViewHolder cityViewHolder = (CityViewHolder) holder;
            cityViewHolder.mTextView.setText(itemData.getName());
        }
    }


    class CityViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        public CityViewHolder(View itemView) {

            super(itemView);
            mTextView = (TextView) itemView;
        }
    }

    class IndexViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView;
        public IndexViewHolder(View itemView) {

            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tv_index);
        }
    }


}
