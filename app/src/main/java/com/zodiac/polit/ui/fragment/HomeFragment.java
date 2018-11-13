package com.zodiac.polit.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.minilive.library.entity.EventData;
import com.minilive.library.util.StringUtils;
import com.zodiac.polit.Constant;
import com.zodiac.polit.R;
import com.zodiac.polit.base.BaseFragment;
import com.zodiac.polit.bean.CityEntity;
import com.zodiac.polit.bean.response.SendSMSCodeResponse;
import com.zodiac.polit.ui.activity.CityListActivity;
import com.zodiac.polit.ui.fragment.home.ConnectUsFragment;
import com.zodiac.polit.ui.fragment.home.GuideFragment;
import com.zodiac.polit.ui.fragment.home.HomeChildFragment;
import com.zodiac.polit.ui.fragment.home.MessageFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.greenrobot.event.EventBus;

/**
 * Created by john on 2018/9/22.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.vpHome)
    ViewPager vpHome;
    Unbinder unbinder;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.layoutAddress)
    LinearLayout layoutAddress;

    private String[] mTitle = {
            "要讯", "公告", "指南", "政策", "联系"
    };
    private int REQUEST_CITY = 50;

    @Override
    protected boolean isRegisterEventBus() {
        return true;
    }

    @Override
    protected void onEventComming(EventData eventData) {
        super.onEventComming(eventData);
        if (eventData.getCODE() == Constant.CODE_SELECTED) {
            if (vpHome != null) {
                vpHome.setCurrentItem(0);
            }
        } else if (eventData.getCODE() == Constant.CODE_CITY) {
            BDLocation bdLocation = (BDLocation) eventData.getData();
            if (StringUtils.isEmpty(tvAddress.getText().toString())) {
                setText(tvAddress, bdLocation.getCity());
                //Constant.cityCode = bdLocation.getAdCode();
                EventBus.getDefault().post(new EventData(Constant.CODE_CITY1));
            }
        }
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViewAndData() {
        vpHome.setOffscreenPageLimit(5);
        vpHome.setAdapter(new HomePagerAdapter(getChildFragmentManager()));
        tabLayout.setupWithViewPager(vpHome);
        if (Constant.location != null) {
            setText(tvAddress, Constant.location.getCity());
        } else {
            setText(tvAddress, "北京市");
        }

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

    @OnClick(R.id.layoutAddress)
    public void onViewClicked() {
        if (StringUtils.isEmpty(tvAddress.getText().toString())) {
            return;
        }
        Bundle bundle = new Bundle();
              /* String name = tvLocation.getText().toString();
                String code = locationObj != null ? locationObj.getAdCode() : "110102";*/
        CityEntity cityEntity = new CityEntity();
        cityEntity.setName(tvAddress.getText().toString());
        cityEntity.setCode(Constant.cityCode);
        bundle.putParcelable("city", cityEntity);
        jumpToForResult(CityListActivity.class, REQUEST_CITY, bundle);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CITY && resultCode == Activity.RESULT_OK) {
            CityEntity cityEntity = data.getParcelableExtra("result");
            if (cityEntity != null) {
                Constant.cityCode = cityEntity.getCode();
                setText(tvAddress, cityEntity.getName());
                EventBus.getDefault().post(new EventData(Constant.CODE_CITY1));
            }
        }
    }

    class HomePagerAdapter extends FragmentStatePagerAdapter {

        public HomePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new HomeChildFragment();
            } else if (position == 1) {
                return new MessageFragment();
            } else if (position == 2) {
                return new GuideFragment();
            } else if (position == 3) {
                return new PolicyFragment();
            }
            return new ConnectUsFragment();


        }

        @Override
        public int getCount() {
            return 5;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle[position];
        }
    }

}
