package com.seindev.sehal.cookbook;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.seindev.sehal.cookbook.misc.AlarmReceiver;

import java.util.Calendar;


public class Splash extends ActionBarActivity {

    private PendingIntent pendingIntent;
    boolean myBoolean;
    public ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        logo = (ImageView) findViewById(R.id.logo);
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                // Code here will run in UI thread

                ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(Splash.this, R.animator.flipping);
                anim.setTarget(logo);
                anim.setDuration(2000);
                anim.start();

                final Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.login_logo_heartbeat);
                logo.startAnimation(animation);
            }
        });
        int myTimer = 3000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                Intent i = new Intent(Splash.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        }, myTimer);

        //TIMER FOR NOTIFICATION
        Intent alarmIntent = new Intent(Splash.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(Splash.this, 0, alarmIntent, 0);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 60 * 24; //mins
        Calendar calendar = Calendar.getInstance();
        Calendar currentCal = Calendar.getInstance();

        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 20);
        calendar.set(Calendar.MINUTE, 00);

        long intendedTime = calendar.getTimeInMillis();
        long currentTime = currentCal.getTimeInMillis();

        if (intendedTime > currentTime) {
            manager.setRepeating(AlarmManager.RTC_WAKEUP, intendedTime, AlarmManager.INTERVAL_DAY, pendingIntent);
        } else {
            //set from next day
            // you might consider using calendar.add() for adding one day to the current day
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            intendedTime = calendar.getTimeInMillis();
            manager.setRepeating(AlarmManager.RTC, intendedTime, AlarmManager.INTERVAL_DAY, pendingIntent);
        }
        //  Toast.makeText(this, "SUCESES" + myBoolean, Toast.LENGTH_LONG).show();
        if (myBoolean == true) {
            Toast.makeText(this, "SUCESES" + myBoolean, Toast.LENGTH_LONG).show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
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
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        myBoolean = savedInstanceState.getBoolean("NOTIFICATIONSWITCH");
    }
}
