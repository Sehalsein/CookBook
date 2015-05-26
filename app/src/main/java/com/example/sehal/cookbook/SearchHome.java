package com.example.sehal.cookbook;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import me.drakeet.materialdialog.MaterialDialog;

import static com.example.sehal.cookbook.Keys.EndpointRecipe.*;


public class SearchHome extends Fragment implements SHAdapter.RLCLickListner {//implements SHAdapter.Clicklistener {

    //Declaring variables
    //public static final String URL_RECIPE = "http://api.bigoven.com/recipes?title_kw=oysters&pg=1";
    //public static final String URL_RECIPE = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json";
    public static final String URL_RECIPE = "http://food2fork.com/api/search";
    Communicator comm;
    static String searchkey;
    RatingBar ratings;
    SearchView searchview;
    FragmentManager manager;
    private ImageLoader imageLoader;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;
    private ArrayList<SHInfo> recipelists = new ArrayList<>();
    SHAdapter shAdapter;
    MaterialDialog mMaterialDialog;


    //DECALRING THE URL FOR THE API IN ONE STRING
    public static String getRequestUrl(int limit) {
        //return URL_BIG_OVEN + "&rpp=" + limit + "&apikey=" + MyApplication.API_KEY;   //BIGOVEn URL
        return URL_RECIPE + "?key=" + MyApplication.API_KEY+"&q="+Search.searchkeyword;          //ROTTEN TOMATOES
    }

    public SearchHome() {

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
                shAdapter.setRecipeInfo(parseJsonresponse(response));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(getActivity(), "SEARCHLISTERROR" + error.getMessage(), Toast.LENGTH_SHORT).show();
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

            }
        });

        requestQueue.add(request);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.search_home, container, false);

       // ratings = (RatingBar) layout.findViewById(R.id.ratings);
        manager = getFragmentManager();

        //RECYCLER VIEW SETUP (RECIPE LIST) //TODO CHANGE THE VARIABLE NAME LATER
        RecyclerView rrecipelist = (RecyclerView) layout.findViewById(R.id.searchlist);
        rrecipelist.setHasFixedSize(true);
       // GridLayoutManager llm = new GridLayoutManager(getActivity(), 2);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rrecipelist.setLayoutManager(llm);
        shAdapter = new SHAdapter(getActivity());
        shAdapter.setClicklistener(this);
        rrecipelist.setAdapter(shAdapter);


//        searchview= (SearchView) layout.findViewById(R.id.recipesearch);
        searchkey="oyster";

        return layout;
    }

    private ArrayList<SHInfo> parseJsonresponse(JSONObject response) {
        ArrayList<SHInfo> listrecipe = new ArrayList<>();
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
                   // L.t(getActivity(),id);
                    SHInfo recipeInfo = new SHInfo();
                    recipeInfo.setId(id);
                    recipeInfo.setTitle(title);
                    recipeInfo.setRating(ratings);
                    recipeInfo.setUrlThumbnail(image);
                    listrecipe.add(recipeInfo);
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

    @Override
    public void itemClicked(View view, String Id) {
        comm.respond(99, Id);

    }


}
