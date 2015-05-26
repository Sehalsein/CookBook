package com.example.sehal.cookbook;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
 * THIS class is the code FOR navigation_drawer.xml
 * this class intitalizes a recycler view recnav  //TODO CHANGE THE VARIABLE NAME
 * cardList is assigned to recnav   //this is the name of the recycler thing in the xml file :P
 * Vertical Orientation  //TODO CHANGE THE VARIABLE NAME
 * ADAPTER OF THIS recycler view is RAdapter and the variable is nadapter //TODO CHANGE THE VARIABLE NAME
 */
public class NavigationDrawer extends Fragment implements ClickListener {

    //Declaring variables
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    Communicator comm;
    FragmentManager manager;


    public NavigationDrawer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.navigation_drawer, container, false);


        //RECYCLER VIEW SETUP (NAVIGATION DRWAER) //TODO CHANGE THE VARIABLE NAME LATER
        RecyclerView recnav = (RecyclerView) layout.findViewById(R.id.navigationList);
        recnav.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recnav.setLayoutManager(llm);
        NAdapter nadapter = new NAdapter(getActivity(), createList(7));
        recnav.setAdapter(nadapter);
        nadapter.setClicklistener(this);

        //PROFILE PICTURE IN NAVIGATION DRAWER //TODO TRY TO GET A SMALLER CODE
        CircleDP circularImageView = (CircleDP) layout.findViewById(R.id.displaypicture);
        //circularImageView.setBorderColor(getResources().getColor(R.color.GrayLight));
        circularImageView.setBorderWidth(3);
        circularImageView.addShadow();


        //FRAGMENT MANAGER DISPLATY CODE
        manager = getFragmentManager();
        return layout;
    }

    //PASSING DATA INTO THE RECYLCER VIEW //TODO CHANGE THE VARIABLE NAME LATER
    private List<NavigationInfo> createList(int size) {
        List<NavigationInfo> result = null;
        try {
            //TODO INSERT THE ARRAY DIRECTLY TO STRING XML FILE
            int icons[] = {R.drawable.home,
                    R.drawable.search,
                //    R.drawable.favourite,
                    R.drawable.settings,
            };

            //TODO INSERT THE ARRAY DIRECTLY TO STRING XML FILE
            String navmenu[] = {"Main Menu",
                    "Search",
                  //  "Favourite",
                    "Settings",
            };

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

    //seting the navigation drawer to work
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

    //INITIALIZING THE Communication InterFACE (I think):P
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        comm = (Communicator) getActivity();
        super.onActivityCreated(savedInstanceState);

    }


    //ITEM CLICK THIS IS PASSED TO MAIN ACTIVITY
    @Override
    public void itemClicked(View view, int position) {
        //mDrawerLayout.closeDrawers();
        switch (position) {
            case 1:
                mDrawerLayout.closeDrawers();
                startActivity(new Intent(getActivity(), Search.class));
                break;
            case 3:
                mDrawerLayout.closeDrawers();
                startActivity(new Intent(getActivity(), Favourite.class));
                break;
            case 2:
                mDrawerLayout.closeDrawers();
                startActivity(new Intent(getActivity(), Settings.class));
                break;
            case 0:
                startActivity(new Intent(getActivity(), MainActivity.class));
                mDrawerLayout.closeDrawers();
                break;
        }
    }
}
