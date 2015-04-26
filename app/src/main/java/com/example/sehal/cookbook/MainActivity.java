package com.example.sehal.cookbook;

//import android.app.FragmentManager;
//import android.app.FragmentTransaction;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.sehal.cookbook.tabs.SlidingTabLayout;


public class MainActivity extends ActionBarActivity implements Communicator {

    FragmentManager manager;
    ViewPager mPager;
    SlidingTabLayout mTabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        HomePage homePage = new HomePage();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentpage, homePage);
        transaction.commit();


        //INTIIALIZING PAGER AND TABS
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mPager = (ViewPager) findViewById(R.id.pager);
        //mTabs.setViewPager();

    }

    //BUTTON CLICK RESPONSE
    @Override
    public void respond(int position, String data) {
        HomePage homePage = new HomePage();
        RecipeList recipeList = new RecipeList();
        IndRecipe indRecipe = new IndRecipe();
        FragmentTransaction transaction = manager.beginTransaction();
        //Toast.makeText(this, "Hey u just hit" + position + " DATA " + data, Toast.LENGTH_SHORT).show();
        //CHOOSING WHAT TO DO
        //if (data == "AS") {
        switch (position) {
            case 0:
                Toast.makeText(this, "Hey u just hit HOME POSITION" + position, Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "Hey u just hit HOME POSITION" + position, Toast.LENGTH_SHORT).show();
                /*getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.gla_there_come, R.anim.gla_there_gone)
                        .replace(R.id.fragmentpage, indRecipe);
                        .commit();
                */
                transaction.replace(R.id.fragmentpage, indRecipe);
                transaction.commit();
                break;
            case 2:
                Toast.makeText(this, "Hey u just hit HOME POSITION" + position, Toast.LENGTH_SHORT).show();
                // mShowingNext = true;
                /* getFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(R.anim.gla_there_come, R.anim.gla_there_gone)
                        .replace(R.id.fragmentpage, recipeList)
                        .commit();
                */
                transaction.replace(R.id.fragmentpage, recipeList);
                transaction.commit();
                break;
            case 3:
                Toast.makeText(this, "Hey u just hit HOME POSITION" + position, Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "Hey u just hit HOME POSITION" + position, Toast.LENGTH_SHORT).show();
                break;

        }

       /* } else if (data == "ASS") {
            switch (position) {
                case 0: startActivity(new Intent(this, MainActivity.class));
                    Toast.makeText(this, "Hey u just hit NAVIGATION DRAWER POSITION" + position, Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    Toast.makeText(this, "Hey u just hit NAVIGATION DRAWER POSITION" + position, Toast.LENGTH_SHORT).show();
                    break;
                case 2:
                    Toast.makeText(this, "Hey u just hit NAVIGATION DRAWER POSITION" + position, Toast.LENGTH_SHORT).show();
                    break;
                case 3:
                    Toast.makeText(this, "Hey u just hit NAVIGATION DRAWER POSITION" + position, Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(this, "Hey u just hit NAVIGATION DRAWER POSITION" + position, Toast.LENGTH_SHORT).show();
                    break;

            }
        }*/

        // transaction.commit();
    }

}
