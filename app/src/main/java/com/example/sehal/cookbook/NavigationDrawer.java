package com.example.sehal.cookbook;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawer extends Fragment implements NAdapter.ClicklistenerNav {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    Communicator comm;

    public NavigationDrawer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View layout = inflater.inflate(R.layout.navigation_drawer, container, false);

        ImageView DisplayPicture = (ImageView) layout.findViewById(R.id.displaypicture);
        RecyclerView recnav = (RecyclerView) layout.findViewById(R.id.navigationList);
        recnav.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recnav.setLayoutManager(llm);
        NAdapter nav = new NAdapter(getActivity(), createList(7));
        recnav.setAdapter(nav);
        nav.setClicklistener(this);
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.dp);

        DisplayPicture.setImageBitmap(icon);

        return layout;
    }

    private List<NavigationInfo> createList(int size) {
        List<NavigationInfo> result = null;
        try {
            int icons[] = {R.drawable.home,
                    R.drawable.search,
                    R.drawable.favourite,
                    R.drawable.settings,
                    R.drawable.info};

            String navmenu[] = {"Main Menu",
                    "Search",
                    "Favourite",
                    "Settings",
                    "Information"};

            result = new ArrayList<NavigationInfo>();
            for (int i = 0; i <= size; i++) {
                NavigationInfo ci = new NavigationInfo();
                ci.Text = navmenu[i];
                ci.Icon = icons[i];
                result.add(ci);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        return result;
    }

    public void setup(DrawerLayout drawerlayout, final Toolbar toolbar) {
        mDrawerLayout = drawerlayout;

        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerlayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
/*
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                if (slideOffset < 0.6) {
                    toolbar.setAlpha(1 - slideOffset);
                }
                Log.d("SEHAL", "OFFSET" + slideOffset);
            }*/
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        comm = (Communicator) getActivity();
        super.onActivityCreated(savedInstanceState);

    }


    //ITEM CLICK THIS IS PASSED TO MAIN ACTIVITY


    @Override
    public void itemClickednav(View view, int position) {
       // Log.d("SEHAL","SAD");
       // Toast.makeText(getActivity(), "Hey u just hit" + position, Toast.LENGTH_SHORT).show();

        comm.respond(position,"NAVIGATION DRAWER");

        switch (position){
            case 1: mDrawerLayout.closeDrawers();
                Toast.makeText(getActivity(), "Hey u just hit NAVIGATION DRAWER POSITION" + position, Toast.LENGTH_SHORT).show();
                break;
            case 2: mDrawerLayout.closeDrawers();
                Toast.makeText(getActivity(), "Hey u just hit NAVIGATION DRAWER POSITION" + position, Toast.LENGTH_SHORT).show();
                break;
            case 3: mDrawerLayout.closeDrawers();
                Toast.makeText(getActivity(), "Hey u just hit NAVIGATION DRAWER POSITION" + position, Toast.LENGTH_SHORT).show();
                break;
            case 4: mDrawerLayout.closeDrawers();
                Toast.makeText(getActivity(), "Hey u just hit NAVIGATION DRAWER POSITION" + position, Toast.LENGTH_SHORT).show();
                break;
            case 0: startActivity(new Intent(getActivity(), MainActivity.class));
                mDrawerLayout.closeDrawers();
                Toast.makeText(getActivity(), "Hey u just hit NAVIGATION DRAWER POSITION" + position, Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
