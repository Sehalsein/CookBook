package com.example.sehal.cookbook;

import android.app.Application;
import android.content.Context;

/**
 * Created by sehal on 5/4/2015.
 */
public class MyApplication extends Application {

    //public static final String API_KEY="dvxtJR73jmLSYoiYy6nT5agI52yxH7Ij"; //BIG OVEN API KEY
    public static final String API_KEY="54wzfswsa4qmjg8hjwa64d4c";   //ROTTEN TOMATOES API KEY

    private static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance=this;
    }

    public static MyApplication getInstance(){
        return sInstance;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }
}
