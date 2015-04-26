package com.example.sehal.cookbook;


import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeList extends Fragment implements RAdapter.Clicklistener {


    Communicator comm;

    public RecipeList() {


        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.home_page, container, false);

        RecyclerView recnav = (RecyclerView) layout.findViewById(R.id.cardList);
        recnav.setHasFixedSize(true);
        GridLayoutManager llm = new GridLayoutManager(getActivity(), 2);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recnav.setLayoutManager(llm);
        RAdapter nav = new RAdapter(getActivity(), createList(3));
        nav.setClicklistener(this);     //used for on click
        recnav.setAdapter(nav);

        return layout;
    }

    //PASS DATA INTO THE RECYLCER VIEW
    private List<HomeInfo> createList(int size) {
        List<HomeInfo> result = null;
        try {
            String icons[] = {"http://s28.postimg.org/hh6fjx3rx/appetizermain.png",
                    "http://s18.postimg.org/x6hah93mx/maincourse.png",
                    "http://s24.postimg.org/6b3m8o0p1/meatmain.png",
                    "http://s28.postimg.org/uvo3toztp/bakedmain.png",
                    "http://s14.postimg.org/aza926y1d/saladmain.png",
                    "http://s12.postimg.org/eovd3crsd/breakfastmain.png"};

            //TODO SAVE IT IN AN ARRAY IN STRING XML (UPDATE) :P
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


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        comm = (Communicator) getActivity();
        super.onActivityCreated(savedInstanceState);

    }



    //ITEM CLICK THIS IS PASSED TO MAIN ACTIVITY
    @Override
    public void itemClicked(View view, int position) {
        // comm.respond(position);
        Toast.makeText(getActivity(), "Hey u just hit" + position, Toast.LENGTH_SHORT).show();
    }
}