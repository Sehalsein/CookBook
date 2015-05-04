package com.example.sehal.cookbook;


import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sehal.cookbook.tabs.SlidingTabLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class IndRecipe extends Fragment {
    ViewPager mPager;
    SlidingTabLayout mTabs;

    public IndRecipe() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View layout = inflater.inflate(R.layout.ind_recipe, container, false);

        //INTIIALIZING PAGER AND TABS

        mPager = (ViewPager) layout.findViewById(R.id.pager);
        mPager.setAdapter(new MyPagerAdapter(getFragmentManager()));

        mTabs = (SlidingTabLayout) layout.findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);
       // mTabs.setCustomTabColorizer(#ffff);
        mTabs.setViewPager(mPager);
        return layout;
    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        String[] tabs;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            tabs = getResources().getStringArray(R.array.tabs);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment=null;
            switch (position){
                case 1: fragment=Ingredients.newInstance("","");
                    break;
                case 0: fragment = Recipe.getInstance(position);
                    break;
            }

            return fragment;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
