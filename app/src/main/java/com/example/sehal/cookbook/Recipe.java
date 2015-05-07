package com.example.sehal.cookbook;


//import android.app.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;

import java.util.ArrayList;
import java.util.List;


/**
 * THIS class is the code FOR fragment_recipe.xml
 * this class intitalizes a recycler view recnav  //TODO CHANGE THE VARIABLE NAME
 * cardList is assigned to recnav   //this is the name of the recycler thing in the xml file :P
 * Vertical Orientation  //TODO CHANGE THE VARIABLE NAME
 * ADAPTER OF THIS recycler view is RAdapter and the variable is nav //TODO CHANGE THE VARIABLE NAME
 *
 */
public class Recipe extends Fragment {


    //Declaring variables
    TextView textView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    Communicator comm;

    public Recipe() {
        // Required empty public constructor
    }

    public static Recipe getInstance(int position) {
        Recipe recipe = new Recipe();
        Bundle args = new Bundle();
        args.putInt("position", position);
        recipe.setArguments(args);
        return recipe;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_recipe, container, false);


        //RECYCLER VIEW SETUP ( IND RECIPE) //TODO CHANGE THE VARIABLE NAME LATER
        RecyclerView recnav = (RecyclerView) layout.findViewById(R.id.indrecipe);
        RecyclerViewHeader header = RecyclerViewHeader.fromXml(getActivity(), R.layout.ind_recipe_header); //GIVES HEADER TO THE RECYCLER VIEW LIBARIES
        recnav.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recnav.setLayoutManager(llm);
        IRAdapter nav = new IRAdapter(getActivity(), createList(4));
        //nav.setClicklistener(this);     //used for on click
        recnav.setAdapter(nav);
        header.attachTo(recnav);

        Bundle bundle = getArguments();


        return layout;
    }


    //PASSING DATA INTO THE RECYLCER VIEW //TODO CHANGE THE VARIABLE NAME LATER
    private List<IndRecipeInfo> createList(int size) {
        List<IndRecipeInfo> result = null;
        try {
            //TODO SAVE IT IN AN ARRAY IN STRING XML (UPDATE) :P
            String icons[] = {"http://s28.postimg.org/hh6fjx3rx/appetizermain.png",
                    "http://s18.postimg.org/x6hah93mx/maincourse.png",
                    "http://s24.postimg.org/6b3m8o0p1/meatmain.png",
                    "http://s28.postimg.org/uvo3toztp/bakedmain.png",
                    "http://s14.postimg.org/aza926y1d/saladmain.png",
                    "http://s12.postimg.org/eovd3crsd/breakfastmain.png"};

            //TODO SAVE IT IN AN ARRAY IN STRING XML (UPDATE) :P
            /*String homemenu[] = {"APPETIZER",
                    "MAIN COURSE",
                    "MEAT AND SEAFOOD",
                    "BAKED GOODS",
                    "SALAD",
                    "BREAKFAST"};
            */
            result = new ArrayList<IndRecipeInfo>();
            for (int i = 0; i <= size; i++) {
                IndRecipeInfo ci = new IndRecipeInfo();
                ci.recipestep = "STEP "+i;
                ci.recipesteppics= icons[i];
                ci.recipestepno=""+i;
                result.add(ci);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        return result;
    }



}
