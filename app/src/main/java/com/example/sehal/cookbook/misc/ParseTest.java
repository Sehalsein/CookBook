package com.example.sehal.cookbook.misc;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

/**
 * Created by sehal on 5/3/2015.
 */
public class ParseTest extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "IojD0Vn7eHp6cidGAoU5oEjeTM9zgb4cxRZnfWAx", "01AItLQ6QP30GTtcbl35D1XOXXAwNJvMibJ9IOh9");
        ParseInstallation.getCurrentInstallation().saveInBackground();


    }
}