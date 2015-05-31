package com.example.sehal.cookbook.fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sehal.cookbook.BuildConfig;
import com.example.sehal.cookbook.R;
import com.example.sehal.cookbook.adapters.OAdapter;
import com.example.sehal.cookbook.adapters.RAdapter;
import com.example.sehal.cookbook.infos.OpenInfo;

import java.util.ArrayList;
import java.util.List;


public class Openscource extends Fragment implements OnClickListener {

    FragmentManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_openscource, container, false);


        RecyclerView rhomepage = (RecyclerView) layout.findViewById(R.id.openscource);
        rhomepage.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rhomepage.setLayoutManager(llm);
        OAdapter oAdapter = new OAdapter(getActivity(), createList(7));
        rhomepage.setAdapter(oAdapter);

        TextView appversion = (TextView) layout.findViewById(R.id.appversion);
        appversion.setText("(v" + BuildConfig.VERSION_NAME + ")");
        ImageView close = (ImageView) layout.findViewById(R.id.close);
        close.setOnClickListener(this);

        manager = getFragmentManager();

        return layout;
    }

    private List<OpenInfo> createList(int size) {
        List<OpenInfo> result = null;
        try {
            //TODO INSERT THE ARRAY DIRECTLY TO STRING XML FILE
            String title[] = {"Picasso",
                    "Material Dialog",
                    "Volley",
            };

            //TODO INSERT THE ARRAY DIRECTLY TO STRING XML FILE
            String project[] = {"https://github.com/square/picasso",
                    "https://github.com/drakeet/MaterialDialog",
                    "https://github.com/mcxiaoke/android-volley",
            };

            String copyright[] = {"Copyright 2013 Square, Inc",
                    "Copyright 2014 drakeet",
                    "Copyright (C) 2014 Xiaoke Zhang\n" +
                            "Copyright (C) 2011 The Android Open Source Project",
            };
            String license[] = {"http://www.apache.org/licenses/LICENSE-2.0",
                    "http://www.apache.org/licenses/LICENSE-2.0",
                    "http://www.apache.org/licenses/LICENSE-2.0",};

            result = new ArrayList<OpenInfo>();
            for (int i = 0; i <= size; i++) {
                OpenInfo ci = new OpenInfo();
                ci.Title = title[i];
                ci.ProjectURL = project[i];
                ci.Copyright = copyright[i];
                ci.LicenseURL = license[i];
                result.add(ci);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        return result;
    }

    @Override
    public void onClick(View v) {
        Openscource openscource = new Openscource();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(openscource);
        transaction.setCustomAnimations(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
        transaction.commit();
        manager.popBackStack();

    }
}
