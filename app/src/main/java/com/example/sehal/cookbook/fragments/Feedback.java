package com.example.sehal.cookbook.fragments;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.sehal.cookbook.BuildConfig;
import com.example.sehal.cookbook.R;


public class Feedback extends Fragment implements View.OnClickListener {


    public Feedback() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_feedback, container, false);

        RelativeLayout feedback = (RelativeLayout) layout.findViewById(R.id.feedback);
        feedback.setOnClickListener(this);
        return layout;
    }


    @Override
    public void onClick(View v) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"seindeveloper@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback about CookBook Android app");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hi CookBook" +
                "\n\nHere's what i think:" +
                "\n\n\n\n\n\n\n------------(type above this line)------------" +
                "\n\nCookBook Android App Version: " + BuildConfig.VERSION_NAME +
                "\nDEVICE: " + Build.MANUFACTURER.toUpperCase() + " " + Build.MODEL +
                "\nOS VERSION: " + Build.VERSION.RELEASE);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Choose Email Client..."));
    }
}
