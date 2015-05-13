package com.example.sehal.cookbook;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by sehal on 5/4/2015.
 */
public class MyApplication extends Application {

    //public static final String API_KEY="dvxtJR73jmLSYoiYy6nT5agI52yxH7Ij"; //BIG OVEN API KEY //TODO try to get this apito work
    //public static final String API_KEY="54wzfswsa4qmjg8hjwa64d4c";   //ROTTEN TOMATOES API KEY
    public static final String API_KEY="431ef380e5facdc0893a5d250ba80041"; //Food2fork API KEY

    private static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        //PArse Setup
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "IojD0Vn7eHp6cidGAoU5oEjeTM9zgb4cxRZnfWAx", "01AItLQ6QP30GTtcbl35D1XOXXAwNJvMibJ9IOh9");
        ParseInstallation.getCurrentInstallation().saveInBackground();
        sInstance=this;
    }

    public static MyApplication getInstance(){
        return sInstance;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }
}
