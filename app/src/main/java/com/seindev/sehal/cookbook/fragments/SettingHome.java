package com.seindev.sehal.cookbook.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.seindev.sehal.cookbook.misc.ClickListener;
import com.seindev.sehal.cookbook.misc.Communicator;
import com.seindev.sehal.cookbook.R;
import com.seindev.sehal.cookbook.adapters.SAdapter;
import com.seindev.sehal.cookbook.infos.SettingsInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * THIS class is the code FOR setting_home.xml
 * this class intitalizes a recycler view recnav  //TODO CHANGE THE VARIABLE NAME
 * cardList is assigned to recnav   //this is the name of the recycler thing in the xml file :P
 * Vertical Orientation  //TODO CHANGE THE VARIABLE NAME
 * ADAPTER OF THIS recycler view is SAdapter and the variable is nav //TODO CHANGE THE VARIABLE NAME
 *
 */
public class SettingHome extends Fragment implements ClickListener {

    Communicator comm;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SettingHome.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingHome newInstance(String param1, String param2) {
        SettingHome fragment = new SettingHome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SettingHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.setting_home, container, false);

        //RECYCLER VIEW SETUP (Settings Page) //TODO CHANGE THE VARIABLE NAME LATER
        RecyclerView recnav = (RecyclerView) layout.findViewById(R.id.settings_list);
        recnav.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recnav.setLayoutManager(llm);
        SAdapter nav = new SAdapter(getActivity(), createList(7));
        nav.setClicklistener(this);     //used for on click
        recnav.setAdapter(nav);


        return layout;
    }

    //PASSING DATA INTO THE RECYLCER VIEW //TODO CHANGE THE VARIABLE NAME LATER
    private List<SettingsInfo> createList(int size) {
        List<SettingsInfo> result = null;
        try {

            //TODO INSERT THE ARRAY DIRECTLY TO STRING XML FILE
            String Slabel[] = {"My Account",
                    "About CookBook",
                    "FeedBack",};

            result = new ArrayList<SettingsInfo>();
            for (int i = 0; i <= size; i++) {
                SettingsInfo si = new SettingsInfo();
                si.settingsLabel = Slabel[i];
                result.add(si);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        return result;
    }

    //INITIALIZING THE Communication InterFACE (I think):P
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        comm = (Communicator) getActivity();
        super.onActivityCreated(savedInstanceState);
    }

    //ITEM CLICK THIS IS PASSED TO MAIN ACTIVITY
    @Override
    public void itemClicked(View view, int position) {
        comm.respond(position,"HOME PAGE");
    }
}
