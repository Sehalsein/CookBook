package com.example.sehal.cookbook.misc;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import com.example.sehal.cookbook.MainActivity;
import com.example.sehal.cookbook.R;
import com.example.sehal.cookbook.Recipeday;
import com.example.sehal.cookbook.fragments.DIndRecipe;

/**
 * Created by sehal on 5/28/2015.
 */
public class AlarmReceiver extends BroadcastReceiver {
    public static Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        Toast.makeText(context, "I'COOKBOOK running", Toast.LENGTH_SHORT).show();
         no.notification();

    }

    public static class no extends Activity {

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        public static void notification() {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
            Intent notificationIntent = new Intent(context, Recipeday.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, notificationIntent, 0);
            android.app.Notification notification = new android.app.Notification.Builder(context)
                    .setContentTitle("COOKBOOK")
                    .setContentText("Don't forget to check out the Recipe of the Day")
                    .setSmallIcon(R.mipmap.cookbook)
                    .setDefaults(android.app.Notification.DEFAULT_SOUND | android.app.Notification.DEFAULT_VIBRATE)
                    .setContentIntent(pendingIntent)
                    .build();
            notification.flags = android.app.Notification.FLAG_AUTO_CANCEL;
            notificationManager.notify(10001, notification);
        }
    }

}
