package com.example.sehal.cookbook.fragments;


import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.sehal.cookbook.misc.Communicator;
import com.example.sehal.cookbook.R;
import com.example.sehal.cookbook.misc.VolleySingleton;
import com.example.sehal.cookbook.adapters.RLAapter;
import com.example.sehal.cookbook.infos.RecipeInfo;
import com.example.sehal.cookbook.misc.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import me.drakeet.materialdialog.MaterialDialog;

import static com.example.sehal.cookbook.misc.Keys.EndpointRecipe.*;


/**
 * THIS class is the code FOR recipe_list.xml
 * this class intitalizes a recycler view rrecipelist  //TODO CHANGE THE VARIABLE NAME
 * cardList is assigned to rrecipelist   //this is the name of the recycler thing in the xml file :P
 * Vertical Orientation  //TODO CHANGE THE VARIABLE NAME
 * ADAPTER OF THIS recycler view is RLAdapter and the variable is nav //TODO CHANGE THE VARIABLE NAME
 */
public class RecipeList extends Fragment implements RLAapter.RLCLickListner {//implements RLAapter.Clicklistener {

    //Declaring variables
    //public static final String URL_RECIPE = "http://api.bigoven.com/recipes?title_kw=oysters&pg=1";
    //public static final String URL_RECIPE = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json";
    public static final String URL_RECIPE = "http://food2fork.com/api/search";
    Communicator comm;
    RatingBar ratings;
    ProgressBar loading;
    FragmentManager manager;
    private VolleySingleton volleySingleton;
    public static String formattedDate;
    private RequestQueue requestQueue;
    private ArrayList<RecipeInfo> recipelists = new ArrayList<>();
    RLAapter rlAapter;
    MaterialDialog mMaterialDialog;

    String errorcheck = "First";
    int counter = 1;

    //DECALRING THE URL FOR THE API IN ONE STRING
    public static String getRequestUrl(int limit) {
        //return URL_BIG_OVEN + "&rpp=" + limit + "&apikey=" + MyApplication.API_KEY;   //BIGOVEn URL
        return URL_RECIPE + "?key=" + MyApplication.API_KEY + "&page=" + formattedDate;          //ROTTEN TOMATOES
    }

    public RecipeList() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd");
        formattedDate = df.format(c.getTime());
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
        sendjsonrequest();

    }

    public void sendjsonrequest() {
        //DECLARING A JSONOBJECT FOR API
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, getRequestUrl(10), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                rlAapter.setRecipeInfo(parseJsonresponse(response));
                loading.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(getActivity(), "RECIPELISTERROR" + error.getMessage(), Toast.LENGTH_SHORT).show();
                if (error.getMessage() != null) {
                    if (errorcheck == "First" || counter == 2) {
                        try {
                            mMaterialDialog = new MaterialDialog(getActivity())
                                    .setTitle("NO INTERNET")
                                    .setMessage("Please check your network settings")
                                    .setNegativeButton("CLOSE", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            mMaterialDialog.dismiss();
                                        }
                                    });
                            mMaterialDialog.show();
                        } catch (NullPointerException e) {

                        }
                        errorcheck = "DONE";
                    }
                    loading.setVisibility(View.GONE);
                } else if (error.getMessage() == null) {
                    counter++;
                    sendjsonrequest();
                }

            }
        });

        requestQueue.add(request);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.recipe_list, container, false);
        loading = (ProgressBar) layout.findViewById(R.id.progress);
        ratings = (RatingBar) layout.findViewById(R.id.ratings);
        manager = getFragmentManager();
        loading.setVisibility(View.VISIBLE);

        //RECYCLER VIEW SETUP (RECIPE LIST) //TODO CHANGE THE VARIABLE NAME LATER
        RecyclerView rrecipelist = (RecyclerView) layout.findViewById(R.id.recipeList);
        rrecipelist.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rrecipelist.setLayoutManager(llm);
        rlAapter = new RLAapter(getActivity());
        rlAapter.setClicklistener(this);
        rrecipelist.setAdapter(rlAapter);

        return layout;
    }

    private ArrayList<RecipeInfo> parseJsonresponse(JSONObject response) {
        ArrayList<RecipeInfo> listrecipe = new ArrayList<>();
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
            } catch (JSONException e) {
                e.printStackTrace();
                if (errorcheck == "First") {
                    mMaterialDialog = new MaterialDialog(getActivity())
                            .setTitle("ERROR")
                            .setMessage("Sorry for the inconvenience but the server is currently under maintenance ")
                            .setNegativeButton("CLOSE", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    mMaterialDialog.dismiss();
                                }
                            });
                    mMaterialDialog.show();
                    errorcheck = "DONE";
                }
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

    @Override
    public void itemClicked(View view, String Id) {
        comm.respond(99, Id);
    }

    public void onResume() {
        super.onResume();
        sendjsonrequest();
    }


}
