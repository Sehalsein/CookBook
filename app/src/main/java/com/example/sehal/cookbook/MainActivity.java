package com.example.sehal.cookbook;

import android.graphics.Color;
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
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.example.sehal.cookbook.tabs.SlidingTabLayout;
import com.parse.Parse;
import com.parse.ParseObject;

import me.drakeet.materialdialog.MaterialDialog;


public class MainActivity extends ActionBarActivity implements Communicator {

    FragmentManager manager;
    ViewPager mPager;
    SlidingTabLayout mTabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Enable Local Datastore.

        /*Parse.enableLocalDatastore(this);

        Parse.initialize(this, "IojD0Vn7eHp6cidGAoU5oEjeTM9zgb4cxRZnfWAx", "01AItLQ6QP30GTtcbl35D1XOXXAwNJvMibJ9IOh9");
*/

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

/*  //TODO GET A WAY TO MAKE TWO SINGLETON CLASS PARSE AND VOLLEY
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
        ParseObject database = new ParseObject("DATABASE");
        database.put("NAME", "USESR");
        database.saveInBackground();
*/

    }

    //BUTTON CLICK RESPONSE
    @Override
    public void respond(int position, String data) {
        HomePage homePage = new HomePage();
        RecipeList recipeList = new RecipeList();
        IndRecipe indRecipe = new IndRecipe();
        final MaterialDialog mMaterialDialog = new MaterialDialog(this);

        FragmentTransaction transaction = manager.beginTransaction();

        switch (position) {
            case 0:
                Toast.makeText(this, "Hey u just hit HOME POSITION" + position, Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "Hey u just hit HOME POSITION" + position, Toast.LENGTH_SHORT).show();
                transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                transaction.replace(R.id.fragmentpage, indRecipe);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case 2:
                Toast.makeText(this, "Hey u just hit HOME POSITION" + position, Toast.LENGTH_SHORT).show();
                transaction.replace(R.id.fragmentpage, recipeList);
                transaction.addToBackStack(null);
                transaction.commit();
                break;
            case 3:

                mMaterialDialog.setTitle("Information")
                        .setMessage("Hello world!")
                        .setPositiveButton("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();

                            }
                        });
                         /*
                        .setNegativeButton("CANCEL", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();

                            }
                        });*/

                mMaterialDialog.show();
                Toast.makeText(this, "Hey u just hit HOME POSITION" + position, Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "Hey u just hit HOME POSITION" + position, Toast.LENGTH_SHORT).show();
                break;

        }

    }

}
