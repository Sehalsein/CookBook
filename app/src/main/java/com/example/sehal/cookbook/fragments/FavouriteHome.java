package com.example.sehal.cookbook.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sehal.cookbook.misc.ClickListener;
import com.example.sehal.cookbook.R;
import com.example.sehal.cookbook.adapters.FAdapter;
import com.example.sehal.cookbook.infos.FavInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sehal on 5/11/2015.
 */
public class FavouriteHome extends Fragment implements ClickListener {

    FragmentManager manager;
    public FavouriteHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.favourite_home, container, false);

        //RECYCLER VIEW SETUP (RECIPE LIST) //TODO CHANGE THE VARIABLE NAME LATER
        RecyclerView rfavhome = (RecyclerView) layout.findViewById(R.id.favrecipeList);
        rfavhome.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rfavhome.setLayoutManager(llm);
        FAdapter fadapter = new FAdapter(getActivity(), createList(4));
        fadapter.setClicklistener(this);     //used for on click
        rfavhome.setAdapter(fadapter);


        //Fragment
        manager = getFragmentManager();


        return layout;
    }

    private List<FavInfo> createList(int size) {
        List<FavInfo> result = null;
        try {
            result = new ArrayList<FavInfo>();
            for (int i = 0; i <= size; i++) {
                FavInfo ci = new FavInfo();
                ci.dishname = "" + i;
                result.add(ci);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        return result;
    }

    @Override
    public void itemClicked(View view, int position) {
        IndRecipe indRecipe = new IndRecipe();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        transaction.replace(R.id.fragmentfavpage, indRecipe);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

