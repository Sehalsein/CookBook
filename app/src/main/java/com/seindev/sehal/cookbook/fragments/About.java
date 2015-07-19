package com.seindev.sehal.cookbook.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.seindev.sehal.cookbook.R;

import me.drakeet.materialdialog.MaterialDialog;


public class About extends Fragment implements View.OnClickListener {

    MaterialDialog mMaterialDialog;
    FragmentManager manager;

    public About() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_about, container, false);

        LinearLayout info = (LinearLayout) layout.findViewById(R.id.infoclick);
        info.setOnClickListener(this);
        TextView description = (TextView) layout.findViewById(R.id.description);
        description.setText("CookBook is now available in mobile with easy access to your favorite recipes on the go.CookBook connects foodies everywhere in the go.If you are a foodie you have come to the right place");

        manager = getFragmentManager();

        return layout;
    }


    @Override
    public void onClick(View v) {
        Openscource openscource = new Openscource();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_right, R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        transaction.add(R.id.settings_fragment, openscource);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
