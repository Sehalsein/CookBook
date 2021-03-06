package com.seindev.sehal.cookbook;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.seindev.sehal.cookbook.fragments.NavigationDrawer;
import com.seindev.sehal.cookbook.fragments.SIndRecipe;
import com.seindev.sehal.cookbook.fragments.SearchHome;
import com.seindev.sehal.cookbook.misc.Communicator;

import com.blunderer.materialdesignlibrary.activities.Activity;
import com.blunderer.materialdesignlibrary.handlers.ActionBarHandler;
import com.blunderer.materialdesignlibrary.handlers.ActionBarSearchHandler;
import com.blunderer.materialdesignlibrary.listeners.OnSearchListener;


public class Search extends Activity implements Communicator {

    FragmentManager manager;
    public static String searchkeyword;
    public static String IDNO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_search);

        //ADS
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        //TOOL BAR CODE FOR LIBRARY TOOLBAR
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        //setSupportActionBar(toolbar);
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setDisplayShowHomeEnabled(true);


        //NAVIGATION DRAWER SETUP
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationDrawer drawerFragment =
                (NavigationDrawer) getSupportFragmentManager()
                        .findFragmentById(R.id.fragment_nav_drawer);

        drawerFragment.setup(drawerLayout, toolbar);

    }

    @Override
    protected int getContentView() {
        // Return your layout resource.

        return R.layout.activity_search;
    }


    @Override
    protected ActionBarHandler getActionBarHandler() {
        return new ActionBarSearchHandler(this, new OnSearchListener() {

            @Override
            public void onSearched(String text) {
                text = text.replaceAll("\\s+", "%20").trim();
                //Toast.makeText(getApplicationContext(), "Welcome " + text.toString(), Toast.LENGTH_SHORT).show();
                searchkeyword = text;

                //DISPLAY THE SEARCH RECIPE LIST AFTER U SEARCH FOR THE RECIPE
                SearchHome searchHomePage = new SearchHome();
                manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragmentpage, searchHomePage);
                transaction.commit();
            }

        })
                .enableAutoCompletion();

    }


    @Override
    public void respond(int pos, String data) {

        //ASSIGNING THE IDNO OF THE RECIPE CLICKED IN RECIPE LIST PAGE
        IDNO = data.toString();

        //FRAGMENT TRANSACTION FROM RECIPE LIST TO INDIVIDUAL RECIPE
        SIndRecipe indRecipe = new SIndRecipe();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_right);
        transaction.add(R.id.fragmentpage, indRecipe);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
