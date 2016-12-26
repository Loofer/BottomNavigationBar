package com.loofer.bottombar.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.loofer.bottombar.Constants;
import com.loofer.bottombar.R;
import com.loofer.bottombar.adapter.TabLayoutFragmentAdapter;
import com.loofer.bottombar.fragment.subfragment.HomeFragment;
import com.loofer.bottombar.fragment.subfragment.LikeFragment;
import com.loofer.bottombar.fragment.subfragment.LocationFragment;
import com.loofer.bottombar.fragment.subfragment.PersonFragment;

import java.util.ArrayList;
import java.util.List;


public class TabLayoutFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private TextView mTextView;
    private List<String> mTabList = new ArrayList<>();
    private int[] mTabImgs = new int[]{R.drawable.home, R.drawable.location, R.drawable.like, R.drawable.person};
    private TabLayoutFragmentAdapter mAdapter;
    private List<Fragment> mFragments = new ArrayList<>();

    public static TabLayoutFragment newInstance(String s) {
        TabLayoutFragment tabLayoutFragment = new TabLayoutFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ARGS, s);
        tabLayoutFragment.setArguments(bundle);
        return tabLayoutFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tablayout, container, false);
        mTextView = (TextView) view.findViewById(R.id.activity_text_view);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String s = bundle.getString(Constants.ARGS);
            if (!TextUtils.isEmpty(s)) {
                mTextView.setText(s);
            }
        }
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mTabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        initTabList();
        mAdapter = new TabLayoutFragmentAdapter(getChildFragmentManager(), mTabList, getActivity(), mFragments, mTabImgs);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            mTabLayout.getTabAt(i).setCustomView(mAdapter.getTabView(i));
        }
        mTabLayout.addOnTabSelectedListener(this);
//        mViewPager.setCurrentItem(0);
//        mTabLayout.getTabAt(0).setIcon(R.drawable.home);
//        mTabLayout.getTabAt(1).setIcon(R.drawable.location);
//        mTabLayout.getTabAt(2).setIcon(R.drawable.like);
//        mTabLayout.getTabAt(3).setIcon(R.drawable.person);
//        setDefaultFragment();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initFragmentList();
    }

    private void setDefaultFragment() {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.sub_content, HomeFragment.newInstance(getString(R.string.item_home)))
                .commit();
    }

    /**
     * add Fragment
     */
    public void initFragmentList() {
        mFragments.clear();
        mFragments.add(HomeFragment.newInstance(getString(R.string.item_home)));
        mFragments.add(LocationFragment.newInstance(getString(R.string.item_location)));
        mFragments.add(LikeFragment.newInstance(getString(R.string.item_like)));
        mFragments.add(PersonFragment.newInstance(getString(R.string.item_person)));

    }

    /**
     * init the tab list.
     */
    private void initTabList() {
        mTabList.clear();
        mTabList.add(getString(R.string.item_home));
        mTabList.add(getString(R.string.item_location));
        mTabList.add(getString(R.string.item_like));
        mTabList.add(getString(R.string.item_person));
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        setTabSelectedState(tab);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
        setTabUnSelectedState(tab);
    }


    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void setTabSelectedState(TabLayout.Tab tab) {
        View customView = tab.getCustomView();
        TextView tabText = (TextView) customView.findViewById(R.id.tv_tab_text);
        ImageView tabIcon = (ImageView) customView.findViewById(R.id.iv_tab_icon);
        tabText.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        String s = tabText.getText().toString();
        if (getString(R.string.item_home).equals(s)) {
            tabIcon.setImageResource(R.drawable.home_fill);
        } else if (getString(R.string.item_location).equals(s)) {
            tabIcon.setImageResource(R.drawable.location_fill);
        } else if (getString(R.string.item_like).equals(s)) {
            tabIcon.setImageResource(R.drawable.like_fill);
        } else if (getString(R.string.item_person).equals(s)) {
            tabIcon.setImageResource(R.drawable.person_fill);
        }
    }

    private void setTabUnSelectedState(TabLayout.Tab tab) {
        View customView = tab.getCustomView();
        TextView tabText = (TextView) customView.findViewById(R.id.tv_tab_text);
        ImageView tabIcon = (ImageView) customView.findViewById(R.id.iv_tab_icon);
        tabText.setTextColor(ContextCompat.getColor(getActivity(), R.color.black_1));
        String s = tabText.getText().toString();
        if (getString(R.string.item_home).equals(s)) {
            tabIcon.setImageResource(R.drawable.home);
        } else if (getString(R.string.item_location).equals(s)) {
            tabIcon.setImageResource(R.drawable.location);
        } else if (getString(R.string.item_like).equals(s)) {
            tabIcon.setImageResource(R.drawable.like);
        } else if (getString(R.string.item_person).equals(s)) {
            tabIcon.setImageResource(R.drawable.person);
        }
    }
}
