package com.example.sehal.cookbook;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by sehal on 5/4/2015.
 */
public class L {
    public static void m(String message){
        Log.d("SEHAL",""+message);
    }

    public static void t(Context context,String message){
        Toast.makeText(context, message + "SEHAL", Toast.LENGTH_SHORT).show();
    }
}