package com.example.sehal.cookbook;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.sehal.cookbook.fragments.IndRecipe;
import com.example.sehal.cookbook.fragments.NavigationDrawer;
import com.example.sehal.cookbook.fragments.RecipeList;
import com.example.sehal.cookbook.misc.AlarmReceiver;
import com.example.sehal.cookbook.misc.Communicator;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.parse.ParseObject;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity implements Communicator {

    FragmentManager manager;
    ProfileTracker mProfiletracker;
    CallbackManager callbackManager;
    private PendingIntent pendingIntent;
    public static String IDNO;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FacebookSdk.sdkInitialize(this.getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {
            name = profile.getName();
            parsesetup(name);
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
        RecipeList recipeList = new RecipeList();
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentpage, recipeList);
        transaction.commit();

       /* //TIMER FOR NOTIFICATION
        Intent alarmIntent = new Intent(MainActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0, alarmIntent, 0);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 60 * 24; //mins
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 00);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), interval, pendingIntent);
*/
        //PARSE GUEST SETUP
        if (name == null) {
            name = "GUEST";
            parsesetup(name);
        }

    }

    //BUTTON CLICK RESPONSE
    @Override
    public void respond(int position, String data) {

        //ASSIGNING THE IDNO OF THE RECIPE CLICKED IN RECIPE LIST PAGE
        IDNO = data.toString();

        //FRAGMENT TRANSACTION FROM RECIPE LIST TO INDIVIDUAL RECIPE
        IndRecipe indRecipe = new IndRecipe();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left,R.anim.slide_in_right, R.anim.slide_out_right);
        transaction.replace(R.id.fragmentpage, indRecipe);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    public void parsesetup(String username) {
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", username);
        testObject.saveInBackground();
        ParseObject database = new ParseObject("DATABASE");
        database.put("NAME", username);
        String model = Build.MANUFACTURER.toUpperCase() + " " + Build.MODEL;
        database.put("MODEL", model);
        database.put("OS", Build.VERSION.RELEASE);
        database.put("AppVersion", BuildConfig.VERSION_NAME);
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
