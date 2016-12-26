package com.loofer.bottombar.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.loofer.bottombar.Constants;
import com.loofer.bottombar.R;
import com.loofer.bottombar.fragment.subfragment.HomeFragment;
import com.loofer.bottombar.fragment.subfragment.LikeFragment;
import com.loofer.bottombar.fragment.subfragment.LocationFragment;
import com.loofer.bottombar.fragment.subfragment.PersonFragment;

public class TabHostFragment extends Fragment {


    public String tabTextArrays[];
    private int[] mTabImgs = new int[]{R.drawable.home, R.drawable.location, R.drawable.like, R.drawable.person};
    private int[] mTabPressImgs = new int[]{R.drawable.home_fill, R.drawable.location_fill, R.drawable.like_fill, R.drawable.person_fill};
    public FragmentTabHost mTabHost;
    private Class[] fragmentClass = {
            HomeFragment.class,
            LocationFragment.class,
            LikeFragment.class,
            PersonFragment.class
    };
    private LinearLayout mTabView;
    private TextView mTabText;
    private ImageView mTabIcon;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabhost, null);
        initTabs();
        mTabHost = (FragmentTabHost) view.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.real_tab_content);
        mTabHost.getTabWidget().setMinimumHeight(120);// 设置tab的高度
        mTabHost.getTabWidget().setDividerDrawable(null);

        // 添加菜单内容
        for (int i = 0; i < tabTextArrays.length; i++) {
            // 一个菜单就是一个TabSpec，然后添加到FragmentTabHost中
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tabTextArrays[i])
                    .setIndicator(getTabView(i));
            Bundle bundle = new Bundle();
            bundle.putString(Constants.ARGS, tabTextArrays[i]);
            mTabHost.addTab(tabSpec, fragmentClass[i], bundle);
        }

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equals(tabTextArrays[0])) {
                    updateTabs(0);
                } else if (tabId.equals(tabTextArrays[1])) {
                    updateTabs(1);
                } else if (tabId.equals(tabTextArrays[2])) {
                    updateTabs(2);
                } else if (tabId.equals(tabTextArrays[3])) {
                    updateTabs(3);
                }
            }
        });
        mTabHost.setCurrentTab(0);

//        TabWidget tabWidget = mTabHost.getTabWidget();
//        View childTabView = tabWidget.getChildTabViewAt(0);
//        ImageView icon = (ImageView) childTabView.findViewById(R.id.iv_tab_icon);
//        icon.setImageResource(mTabPressImgs[0]);
        return view;
    }

    private void updateTabs(int currentPos) {
        TabWidget tabWidget = mTabHost.getTabWidget();
        for (int i = 0; i < tabTextArrays.length; i++) {
            View childTabView = tabWidget.getChildTabViewAt(i);
            View icon = childTabView.findViewById(R.id.iv_tab_icon);
            if (i == currentPos) {
                icon.setBackgroundResource(mTabPressImgs[i]);
            } else {
                icon.setBackgroundResource(mTabImgs[i]);
            }
        }
    }

    public static TabHostFragment newInstance(String s) {
        TabHostFragment tabHostFragment = new TabHostFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        tabHostFragment.setArguments(bundle);
        return tabHostFragment;
    }


    private void initTabs() {
        tabTextArrays = new String[]{
                getString(R.string.item_home),
                getString(R.string.item_location),
                getString(R.string.item_like),
                getString(R.string.item_person)
        };
    }

    /**
     * set the tab view
     *
     * @param position
     * @return
     */
    public View getTabView(int position) {
        View view = View.inflate(getActivity(), R.layout.layout_tab_view, null);
        mTabView = (LinearLayout) view.findViewById(R.id.ll_tab_view);
        mTabText = (TextView) view.findViewById(R.id.tv_tab_text);
        mTabIcon = (ImageView) view.findViewById(R.id.iv_tab_icon);
        mTabText.setText(tabTextArrays[position]);
        mTabIcon.setImageResource(mTabImgs[position]);
        return view;
    }


}
