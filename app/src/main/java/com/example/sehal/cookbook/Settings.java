package com.example.sehal.cookbook;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.sehal.cookbook.fragments.About;
import com.example.sehal.cookbook.fragments.Account;
import com.example.sehal.cookbook.fragments.Feedback;
import com.example.sehal.cookbook.fragments.NavigationDrawer;
import com.example.sehal.cookbook.fragments.Openscource;
import com.example.sehal.cookbook.fragments.SettingHome;
import com.example.sehal.cookbook.misc.Communicator;


public class Settings extends ActionBarActivity implements Communicator {
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


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
        SettingHome settingHome = new SettingHome();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.settings_fragment, settingHome);
        transaction.commit();


    }
    

    //BUTTON CLICK RESPONSE
    @Override
    public void respond(int pos, String data) {
        Account account = new Account();
        Feedback feedback=new Feedback();
        About about=new About();
        Openscource openscource=new Openscource();

        FragmentTransaction transaction = manager.beginTransaction();
        switch (pos) {
            case 1:
                //Toast.makeText(this, "Hey u just hit Setings LAbel POSITION" + pos, Toast.LENGTH_SHORT).show();
                transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left,R.anim.slide_in_right, R.anim.slide_out_right);
                transaction.replace(R.id.settings_fragment, about);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case 2:
               // Toast.makeText(this, "Hey u just hit Setings LAbel POSITION" + pos, Toast.LENGTH_SHORT).show();
                transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left,R.anim.slide_in_right, R.anim.slide_out_right);
                transaction.replace(R.id.settings_fragment, feedback);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

            case 0:
                //Toast.makeText(this, "Hey u just hit HOME POSITION" + pos, Toast.LENGTH_SHORT).show();
                transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left,R.anim.slide_in_right, R.anim.slide_out_right);
                transaction.replace(R.id.settings_fragment, account);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
        }

    }

}
