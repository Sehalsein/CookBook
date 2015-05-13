package com.example.sehal.cookbook;


import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
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
import com.android.volley.toolbox.StringRequest;
import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;

import static com.example.sehal.cookbook.Keys.EndpointRecipe.*;


/**
 * THIS class is the code FOR recipe_list.xml
 * this class intitalizes a recycler view recnav  //TODO CHANGE THE VARIABLE NAME
 * cardList is assigned to recnav   //this is the name of the recycler thing in the xml file :P
 * Vertical Orientation  //TODO CHANGE THE VARIABLE NAME
 * ADAPTER OF THIS recycler view is RLAdapter and the variable is nav //TODO CHANGE THE VARIABLE NAME
 */
public class RecipeList extends Fragment {//implements RLAapter.Clicklistener {

    //Declaring variables
    //public static final String URL_RECIPE = "http://api.bigoven.com/recipes?title_kw=oysters&pg=1";
    //public static final String URL_RECIPE = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json";
    public static final String URL_RECIPE = "http://food2fork.com/api/search";
    Communicator comm;
    RatingBar ratings;
    FragmentManager manager;
    private ImageLoader imageLoader;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private ArrayList<RecipeInfo> recipelists =new ArrayList<>();
    RLAapter rlAapter;



    //DECALRING THE URL FOR THE API IN ONE STRING
    public static String getRequestUrl(int limit) {
        //return URL_BIG_OVEN + "&rpp=" + limit + "&apikey=" + MyApplication.API_KEY;   //BIGOVEn URL
        return URL_RECIPE + "?key=" + MyApplication.API_KEY;          //ROTTEN TOMATOES
    }

    public RecipeList() {


        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
        sendjsonrequest();

    }

    public void sendjsonrequest() {
        //DECLARING A JSONOBJECT FOR API

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, getRequestUrl(10), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               // L.t(getActivity(), response.toString());
                rlAapter.setRecipeInfo(parseJsonresponse(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "ERROR" + error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        requestQueue.add(request);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.recipe_list, container, false);

        ratings = (RatingBar) layout.findViewById(R.id.ratings);
        manager = getFragmentManager();

        //RECYCLER VIEW SETUP (RECIPE LIST) //TODO CHANGE THE VARIABLE NAME LATER
        RecyclerView recnav = (RecyclerView) layout.findViewById(R.id.recipeList);
        recnav.setHasFixedSize(true);
        GridLayoutManager llm = new GridLayoutManager(getActivity(), 2);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recnav.setLayoutManager(llm);
       // RLAapter nav = new RLAapter(getActivity(), createList(4));
        //nav.setClicklistener(this);     //used for on click
       // recnav.setAdapter(nav);
        rlAapter=new RLAapter(getActivity());
        recnav.setAdapter(rlAapter);

        return layout;
    }

    private ArrayList<RecipeInfo> parseJsonresponse(JSONObject response) {
        ArrayList<RecipeInfo> listrecipe=new ArrayList<>();
        if (response != null && response.length() > 0) {
            try {
                StringBuilder data = new StringBuilder();

                JSONArray arrayrecipes = response.getJSONArray(KEY_RECIPES);
                for (int i = 0; i < arrayrecipes.length(); i++) {
                    JSONObject currentrecipe = arrayrecipes.getJSONObject(i);
                    String id = currentrecipe.getString(KEY_RECEPE_ID);
                    String title = currentrecipe.getString(KEY_TITLE);
                    int ratings = currentrecipe.getInt(KEY_SOCIAL_RANK);
                    String image = null;
                    image = currentrecipe.getString(KEY_IMAGE_URL);
                    data.append(title + "\n");
                    RecipeInfo recipeInfo = new RecipeInfo();
                    recipeInfo.setId(id);
                    recipeInfo.setTitle(title);
                    recipeInfo.setRating(ratings);
                    recipeInfo.setUrlThumbnail(image);
                    listrecipe.add(recipeInfo);


                }
                L.t(getActivity(), listrecipe.toString());


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
