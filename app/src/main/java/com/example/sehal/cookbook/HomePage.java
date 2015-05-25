package com.example.sehal.cookbook;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * THIS class is the code FOR home_page.xml
 * this class intitalizes a recycler view rhomepage  //TODO CHANGE THE VARIABLE NAME
 * cardList is assigned to rhomepage   //this is the name of the recycler thing in the xml file :P
 * Vertical Orientation  //TODO CHANGE THE VARIABLE NAME
 * ADAPTER OF THIS recycler view is RAdapter and the variable is radapter //TODO CHANGE THE VARIABLE NAME
 */

public class HomePage extends Fragment implements ClickListener {

    Communicator comm; //Declaring the Communication interface variable used to communicating between different fragment

    public HomePage() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.home_page, container, false);


        //RECYCLER VIEW SETUP (HOME PAGE) 
        RecyclerView rhomepage = (RecyclerView) layout.findViewById(R.id.cardList);
        rhomepage.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rhomepage.setLayoutManager(llm);
        RAdapter radapter = new RAdapter(getActivity(), createList(7));
        radapter.setClicklistener(this);     //used for on click
        rhomepage.setAdapter(radapter);

        return layout;
    }

    //PASSING DATA INTO THE RECYLCER VIEW //TODO CHANGE THE VARIABLE NAME LATER
    private List<HomeInfo> createList(int size) {
        List<HomeInfo> result = null;
        try {
            //TODO INSERT THE ARRAY DIRECTLY TO STRING XML FILE
            String icons[] = {"http://s7.postimg.org/kv4ztya9n/Appetizer_Recipes_header.jpg",
                    "http://s18.postimg.org/x6hah93mx/maincourse.png",
                    "http://s24.postimg.org/6b3m8o0p1/meatmain.png",
                    "http://s28.postimg.org/uvo3toztp/bakedmain.png",
                    "http://s14.postimg.org/aza926y1d/saladmain.png",
                    "http://s12.postimg.org/eovd3crsd/breakfastmain.png"};

            //TODO INSERT THE ARRAY DIRECTLY TO STRING XML FILE
            String homemenu[] = {"APPETIZER",
                    "MAIN COURSE",
                    "MEAT AND SEAFOOD",
                    "BAKED GOODS",
                    "SALAD",
                    "BREAKFAST"};

            result = new ArrayList<HomeInfo>();
            for (int i = 0; i <= size; i++) {
                HomeInfo ci = new HomeInfo();
                ci.dishname = homemenu[i];
                ci.iconid = icons[i];
                result.add(ci);
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

    //ITEM CLICK THIS IS PASSED TO MAIN ACTIVITY makes it faster i think
    @Override
    public void itemClicked(View view, int position) {
        comm.respond(position, "HOMEPAGE");
    }
}
