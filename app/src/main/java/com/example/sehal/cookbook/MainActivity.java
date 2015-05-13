package com.example.sehal.cookbook;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
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
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sehal.cookbook.tabs.SlidingTabLayout;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.parse.Parse;
import com.parse.ParseObject;
import com.squareup.picasso.Picasso;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import me.drakeet.materialdialog.MaterialDialog;


public class MainActivity extends ActionBarActivity implements Communicator {

    FragmentManager manager;
    ViewPager mPager;
    SlidingTabLayout mTabs;
    ProfileTracker mProfiletracker;
    String name;
    CallbackManager callbackManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {
            name = profile.getName();
        }
        mProfiletracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldprofile, Profile newprofile) {
                if (newprofile != null) {
                    name = newprofile.getName();
                    parsesetup(name);
                    Toast.makeText(MainActivity.this, "Welcome Back " + name, Toast.LENGTH_SHORT).show();
                }
            }
        };
        mProfiletracker.startTracking();

        //TO GET KEYHASH FACEBOOK
        /*
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.sehal.cookbook",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        }  catch (NoSuchAlgorithmException e) {

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
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

        //PARSE GUEST SETUP
        if (name == null) {
            name = "GUEST";
            parsesetup(name);
        }

    }

    //BUTTON CLICK RESPONSE
    @Override
    public void respond(int position, String data) {
        HomePage homePage = new HomePage();
        RecipeList recipeList = new RecipeList();
        IndRecipe indRecipe = new IndRecipe();
        Account account = new Account();
        final MaterialDialog mMaterialDialog = new MaterialDialog(this);

        FragmentTransaction transaction = manager.beginTransaction();

        Toast.makeText(this, "Hey u just hit HOME POSITION" + position, Toast.LENGTH_SHORT).show();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        transaction.replace(R.id.fragmentpage, recipeList);
        transaction.addToBackStack(null);
        transaction.commit();



       /* switch (position) {
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
                        });

                mMaterialDialog.show();
                Toast.makeText(this, "Hey u just hit HOME POSITION" + position, Toast.LENGTH_SHORT).show();
                break;
            case 4:
                Toast.makeText(this, "Hey u just hit HOME POSITION" + position, Toast.LENGTH_SHORT).show();
                transaction.replace(R.id.fragmentpage, account);
                transaction.addToBackStack(null);
                transaction.commit();
                break;

        }*/

    }

    public void parsesetup(String loginusername) {
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", loginusername);
        testObject.saveInBackground();
        ParseObject database = new ParseObject("DATABASE");
        database.put("NAME", loginusername);
        database.saveInBackground();
    }


    //FACEBOOK THINGY
    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    //FACEBOOK THINGY
    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mProfiletracker.stopTracking();
    }
}
