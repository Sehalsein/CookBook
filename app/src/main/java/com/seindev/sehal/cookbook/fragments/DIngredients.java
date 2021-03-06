package com.seindev.sehal.cookbook.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.seindev.sehal.cookbook.R;
import com.seindev.sehal.cookbook.adapters.DIAdapter;
import com.seindev.sehal.cookbook.infos.DIngredientsInfo;
import com.seindev.sehal.cookbook.misc.Communicator;
import com.seindev.sehal.cookbook.misc.MyApplication;
import com.seindev.sehal.cookbook.misc.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.seindev.sehal.cookbook.misc.Keys.EndpointRecipe.KEY_INGREDIENTS;
import static com.seindev.sehal.cookbook.misc.Keys.EndpointRecipe.KEY_RECIPE;

/**
 * Created by sehal on 5/30/2015.
 */
public class DIngredients extends Fragment {

    //Declaring variables
    //public static final String URL_RECIPE = "http://api.bigoven.com/recipes?title_kw=oysters&pg=1";
    //public static final String URL_RECIPE = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json";
    public static final String URL_RECIPE = "http://food2fork.com/api/search";
    public static final String URL_RECIPE_IND = "http://food2fork.com/api/get";
    Communicator comm;
    RatingBar ratings;
    FragmentManager manager;
    private ImageLoader imageLoader;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private ArrayList<DIngredientsInfo> recipelists = new ArrayList<>();
    DIAdapter diAdapter;
    static String dishid;


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
     * @return A new instance of fragment Ingredients.
     */
    // TODO: Rename and change types and number of parameters
    public static DIngredients newInstance(String param1, String param2) {
        DIngredients fragment = new DIngredients();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public DIngredients() {

    }


    //DECALRING THE URL FOR THE API IN ONE STRING
    public static String getRequestUrl(int limit) {
        //return URL_BIG_OVEN + "&rpp=" + limit + "&apikey=" + MyApplication.API_KEY;   //BIGOVEn URL
        //return URL_RECIPE + "?key=" + MyApplication.API_KEY;          //ROTTEN TOMATOES
        return URL_RECIPE_IND + "?key=" + MyApplication.API_KEY + "&rId=";// + Recipeday.IDNO;// + Search.IDNO;          //ROTTEN TOMATOES
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();


    }

    public void sendjsonrequest() {
        //DECLARING A JSONOBJECT FOR API

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, getRequestUrl(10), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                diAdapter.setIngredinetsInfo(parseJsonresponse(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getMessage() == null) {
                    sendjsonrequest();
                } else {
                    Toast.makeText(getActivity(), "RECIPE ING ERROR " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        requestQueue.add(request);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_ingredients, container, false);
        sendjsonrequest();
        ratings = (RatingBar) layout.findViewById(R.id.ratings);
        manager = getFragmentManager();
        //RECYCLER VIEW SETUP (RECIPE LIST) //TODO CHANGE THE VARIABLE NAME LATER
        RecyclerView ringredients = (RecyclerView) layout.findViewById(R.id.indrecipeingriedints);
        ringredients.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        ringredients.setLayoutManager(llm);
        diAdapter = new DIAdapter(getActivity());
        ringredients.setAdapter(diAdapter);

        return layout;
    }

    private ArrayList<DIngredientsInfo> parseJsonresponse(JSONObject response) {
        ArrayList<DIngredientsInfo> listrecipe = new ArrayList<>();
        if (response != null && response.length() > 0) {
            try {
                StringBuilder data = new StringBuilder();

                JSONObject indrecipe = response.getJSONObject(KEY_RECIPE);
                JSONArray arrayrecipes = indrecipe.getJSONArray(KEY_INGREDIENTS);
                for (int i = 0; i < arrayrecipes.length(); i++) {
                    String ing = arrayrecipes.getString(i);
                    DIngredientsInfo ingredinetsInfo = new DIngredientsInfo();
                    ingredinetsInfo.setingredinetsname(ing);
                    listrecipe.add(ingredinetsInfo);
                    // L.t(getActivity(), ing.toString());
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        return listrecipe;
    }


    //INITIALIZING THE Communication InterFACE (I think):P
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        comm = (Communicator) getActivity();
        super.onActivityCreated(savedInstanceState);

    }

}