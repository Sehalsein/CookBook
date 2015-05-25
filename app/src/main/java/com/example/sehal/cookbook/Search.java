package com.example.sehal.cookbook;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sehal.cookbook.tabs.SlidingTabLayout;
import com.facebook.CallbackManager;
import com.facebook.ProfileTracker;


public class Search extends ActionBarActivity implements Communicator {

    FragmentManager manager;
    ViewPager mPager;
    SlidingTabLayout mTabs;
    ProfileTracker mProfiletracker;
    String name;
    CallbackManager callbackManager;
    public static String IDNO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //TOOL BAR CODE
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //FRAGMENT HOME PAGE DISPLATY CODE
        SearchHome searchHomePage = new SearchHome();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragmentpage, searchHomePage);
        transaction.commit();

        //NAVIGATION DRAWER SETUP
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationDrawer drawerFragment =
                (NavigationDrawer) getSupportFragmentManager()
                        .findFragmentById(R.id.fragment_nav_drawer);

        drawerFragment.setup(drawerLayout, toolbar);


        //INTIIALIZING PAGER AND TABS
        mTabs = (SlidingTabLayout) findViewById(R.id.tabs);
        mPager = (ViewPager) findViewById(R.id.pager);
        //mTabs.setViewPager();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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

    @Override
    public void respond(int pos, String data) {
        HomePage homePage = new HomePage();
        RecipeList recipeList = new RecipeList();
        SIndRecipe indRecipe = new SIndRecipe();
        Ingredients ING = new Ingredients();
        Recipe rec = new Recipe();
        Account account = new Account();

        FragmentTransaction transaction = manager.beginTransaction();

        IDNO=data.toString();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        transaction.replace(R.id.fragmentpage, indRecipe);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
