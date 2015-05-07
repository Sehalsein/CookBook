package com.example.sehal.cookbook;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //BUTTON CLICK RESPONSE
    @Override
    public void respond(int pos, String data) {

        switch (pos) {
            case 1:
                Toast.makeText(this, "Hey u just hit Setings LAbel POSITION" + pos, Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "Hey u just hit Setings LAbel POSITION" + pos, Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(this, "Hey u just hit Setings LAbel POSITION" + pos, Toast.LENGTH_SHORT).show();
                break;
            case 0:
                Toast.makeText(this, "Hey u just hit Setings LAbel POSITION" + pos, Toast.LENGTH_SHORT).show();
                break;
        }

    }

}