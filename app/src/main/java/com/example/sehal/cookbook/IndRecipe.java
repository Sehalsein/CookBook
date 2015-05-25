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
import android.widget.TextView;
import android.widget.Toast;

import com.example.sehal.cookbook.tabs.SlidingTabLayout;


/**
 * THIS class is used to display the individual recipe and its recipe
 * this is the backend of the page this class is used to setup up the tabs and the pager
 * any the fragments are accessed dusing this class
 */
public class IndRecipe extends Fragment {

    //INITIALIING
    ViewPager mPager;
    SlidingTabLayout mTabs;
    TextView name;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        name = (TextView) getActivity().findViewById(R.id.recipename);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.ind_recipe, container, false);

        //INTIIALIZING PAGER AND TABS
        mPager = (ViewPager) layout.findViewById(R.id.pager);
        mPager.setAdapter(new MyPagerAdapter(getChildFragmentManager()));
        mTabs = (SlidingTabLayout) layout.findViewById(R.id.tabs);
        mTabs.setDistributeEvenly(true);
        //  mTabs.setBackgroundColor(R.color.primarycolor);
        // mTabs.setCustomTabColorizer(#ffff);
        mTabs.setViewPager(mPager);
        return layout;
    }

    //THIS CLASS iS USED AS AN ADAPTER TO SWTITCH BETWEEN TWO FRAGMENTS IN VIEW PAGER
    class MyPagerAdapter extends FragmentPagerAdapter {

        /**
         * THIS IS THE VALUE OF THE TABS IE WHAT IT SHIULD DISPLAY
         * ITS ALREADT DECLARED IN THE STRING XML FILE
         * ANY CHANGES MUST BE DONE FROM THERE
         */
        String[] tabs;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            tabs = getResources().getStringArray(R.array.tabs);
        }

        //ASSIGNING WHICH FRAGMENT MUST DISPLAY IN EACH TAB
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 1:
                    fragment = Ingredients.newInstance("", "");
                    break;
                case 0:
                    fragment = Recipe.getInstance(position);
                    break;
            }

            return fragment;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        //NO OF TABS REQUIRED ITS CHANGEABLE
        @Override
        public int getCount() {
            return 2;
        }
    }

}
