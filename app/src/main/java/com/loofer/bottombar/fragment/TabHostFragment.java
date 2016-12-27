package com.loofer.bottombar.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.loofer.bottombar.Constants;
import com.loofer.bottombar.R;
import com.loofer.bottombar.fragment.subfragment.HomeFragment;
import com.loofer.bottombar.fragment.subfragment.LikeFragment;
import com.loofer.bottombar.fragment.subfragment.LocationFragment;
import com.loofer.bottombar.fragment.subfragment.PersonFragment;

public class TabHostFragment extends Fragment {

    public String tabTextArrays[];
    private int[] mTabSelector = new int[]{R.drawable.radiobutton_bg_home, R.drawable.radiobutton_bg_location, R.drawable.radiobutton_bg_like, R.drawable.radiobutton_bg_me};
    public FragmentTabHost mTabHost;
    private Class[] fragmentClass = {HomeFragment.class, LocationFragment.class, LikeFragment.class, PersonFragment.class};

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
        initTabs();
        mTabHost = (FragmentTabHost) view.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.real_tab_content);
        mTabHost.getTabWidget().setMinimumHeight(120);// 设置tab的高度
        mTabHost.getTabWidget().setDividerDrawable(null);

        for (int i = 0; i < tabTextArrays.length; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tabTextArrays[i])
                    .setIndicator(getTabView(i));
            Bundle bundle = new Bundle();
            bundle.putString(Constants.ARGS, tabTextArrays[i]);
            mTabHost.addTab(tabSpec, fragmentClass[i], bundle);
        }
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {

            }
        });
        return view;
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
        TextView mTabText = (TextView) view.findViewById(R.id.tv_tab_text);
        ImageView mTabIcon = (ImageView) view.findViewById(R.id.iv_tab_icon);
        mTabText.setText(tabTextArrays[position]);
        mTabIcon.setImageResource(mTabSelector[position]);
        return view;
    }


}
