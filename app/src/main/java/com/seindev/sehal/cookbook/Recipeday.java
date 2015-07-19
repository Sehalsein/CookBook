package com.seindev.sehal.cookbook;

import android.app.PendingIntent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.seindev.sehal.cookbook.fragments.DIndRecipe;
import com.seindev.sehal.cookbook.fragments.NavigationDrawer;
import com.seindev.sehal.cookbook.misc.Communicator;
import com.facebook.CallbackManager;
import com.facebook.ProfileTracker;


public class Recipeday extends ActionBarActivity implements Communicator {

    FragmentManager manager;
    ProfileTracker mProfiletracker;
    CallbackManager callbackManager;
    private PendingIntent pendingIntent;
    public static String IDNO;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipeday);

        //ADS
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //TOOL BAR CODE
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        //NAVIGATION DRAWER SETUP
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationDrawer drawerFragment =
                (NavigationDrawer) getSupportFragmentManager()
                        .findFragmentById(R.id.fragment_nav_drawer);
        drawerFragment.setup(drawerLayout, toolbar);


        //FRAGMENT HOME PAGE DISPLATY CODE
        DIndRecipe dIndRecipe = new DIndRecipe();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentpage, dIndRecipe);
        transaction.commit();

    }



    @Override
    public void respond(int pos, String data) {
        IDNO = data.toString();
      //  Toast.makeText(this, "RECIPE ING ERROR " + IDNO.toString(), Toast.LENGTH_SHORT).show();
    }
}
