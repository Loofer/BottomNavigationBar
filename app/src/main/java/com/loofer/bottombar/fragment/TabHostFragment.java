package com.loofer.bottombar.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.loofer.bottombar.Constants;
import com.loofer.bottombar.R;
import com.loofer.bottombar.fragment.subfragment.HomeFragment;
import com.loofer.bottombar.fragment.subfragment.LikeFragment;
import com.loofer.bottombar.fragment.subfragment.LocationFragment;
import com.loofer.bottombar.fragment.subfragment.PersonFragment;

import java.util.Map;

public class TabHostFragment extends Fragment {


    public final String tabTextArrays[] = {
            getString(R.string.item_home),
            getString(R.string.item_location),
            getString(R.string.item_like),
            getString(R.string.item_person)
    };
    private int[] mTabImgs = new int[]{R.drawable.home, R.drawable.location, R.drawable.like, R.drawable.person};
    private int[] mTabPressImgs = new int[]{R.drawable.home_fill, R.drawable.location_fill, R.drawable.like_fill, R.drawable.person_fill};

    public Map<String, Integer> mTabMap;
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



    public static TabHostFragment newInstance(String s) {
        TabHostFragment tabHostFragment = new TabHostFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        tabHostFragment.setArguments(bundle);
        return tabHostFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabhost, null);

        mTabHost = (FragmentTabHost) view.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getFragmentManager(), R.id.real_tab_content);
        mTabHost.getTabWidget().setMinimumHeight(120);// 设置tab的高度
        mTabHost.getTabWidget().setDividerDrawable(null);

        // 添加菜单内容
        for (int i = 0; i < tabTextArrays.length; i++) {
            // 一个菜单就是一个TabSpec，然后添加到FragmentTabHost中
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tabTextArrays[i])
                    .setIndicator(getTabView(i));
            mTabHost.addTab(tabSpec, fragmentClass[i], null);
            // 默认让第一个选中
            mTabHost.getTabWidget().getChildAt(0)
                    .setBackgroundResource(mTabPressImgs[0]);
        }

        mTabHost.setCurrentTab(0);

        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
//                int currentTab = mTabHost.getCurrentTab();
//                int child = mTabMap.get(tabId);
//                tabImage1.setImageResource(R.drawable.a49);
//                tabImage2.setImageResource(R.drawable.a49);
//                tabImage3.setImageResource(R.drawable.a49);
//                tabText1.setTextColor(COLOR_GRAY_01);
//                tabText2.setTextColor(COLOR_GRAY_01);
//                tabText3.setTextColor(COLOR_GRAY_01);
//                switch (child) {
//                    case 0:
//                        tabImage1.setImageResource(R.drawable.a4a);
//                        tabText1.setTextColor(COLOR_GREEN_01);
//                        break;
//                    case 1:
//                        tabImage2.setImageResource(R.drawable.a4a);
//                        tabText2.setTextColor(COLOR_GREEN_01);
//                        break;
//                    case 2:
//                        tabImage3.setImageResource(R.drawable.a4a);
//                        tabText3.setTextColor(COLOR_GREEN_01);
//                        break;
//                }
            }
        });
        return view;
    }

    /**
     * set the tab view
     *
     * @param position
     * @return
     */
    public View getTabView(int position) {
        View view = View.inflate(getActivity(),R.layout.layout_tab_view, null);
        mTabView = (LinearLayout) view.findViewById(R.id.ll_tab_view);
        mTabText = (TextView) view.findViewById(R.id.tv_tab_text);
        mTabIcon = (ImageView) view.findViewById(R.id.iv_tab_icon);
        mTabText.setText(tabTextArrays[position]);
        mTabIcon.setImageResource(mTabImgs[position]);
        if (0 == position) {//the default color of item home is green
            mTabText.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
            mTabIcon.setImageResource(R.drawable.home_fill);
        }
        return view;
    }


}
