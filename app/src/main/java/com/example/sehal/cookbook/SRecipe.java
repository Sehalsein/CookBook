package com.example.sehal.cookbook;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.example.sehal.cookbook.Keys.EndpointRecipe.KEY_IMAGE_URL;
import static com.example.sehal.cookbook.Keys.EndpointRecipe.KEY_RECEPE_ID;
import static com.example.sehal.cookbook.Keys.EndpointRecipe.KEY_RECIPE;
import static com.example.sehal.cookbook.Keys.EndpointRecipe.KEY_SOCIAL_RANK;
import static com.example.sehal.cookbook.Keys.EndpointRecipe.KEY_SOURCE_URL;
import static com.example.sehal.cookbook.Keys.EndpointRecipe.KEY_TITLE;

/**
 * Created by sehal on 5/25/2015.
 */
public class SRecipe extends Fragment {


    //Declaring variables
    TextView textView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    public static final String URL_RECIPE = "http://food2fork.com/api/search";
    public static final String URL_RECIPE_IND = "http://food2fork.com/api/get";
    Communicator comm;
    ImageView dishimage;
    RatingBar ratingsbar;
    TextView dishname;
    WebView wb;
    FragmentManager manager;
    private ImageLoader imageLoader;
    private VolleySingleton volleySingleton;
    private VolleySingleton mVolleySingleton;
    private ImageLoader mImageLoader;
    private RequestQueue requestQueue;
    private ArrayList<IndRecipeInfo> recipelists = new ArrayList<>();
    // IRAdapter irAdapter;

    public SRecipe() {
        // Required empty public constructor
        //mImageLoader = mVolleySingleton.getImageLoader();
    }

    public static SRecipe getInstance(int position) {
        SRecipe recipe = new SRecipe();
        Bundle args = new Bundle();
        args.putInt("position", position);
        recipe.setArguments(args);
        //args.put
        return recipe;
    }


    public static String getRequestUrl(int limit) {
        //return URL_BIG_OVEN + "&rpp=" + limit + "&apikey=" + MyApplication.API_KEY;   //BIGOVEn URL
        //return URL_RECIPE + "?key=" + MyApplication.API_KEY;          //ROTTEN TOMATOES
        return URL_RECIPE_IND + "?key=" + MyApplication.API_KEY + "&rId=" + Search.IDNO;          //ROTTEN TOMATOES
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();
        mImageLoader = volleySingleton.getImageLoader();
        sendjsonrequest();
    }

    public void sendjsonrequest() {
        //DECLARING A JSONOBJECT FOR API

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, getRequestUrl(10), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                parseJsonresponse(response);
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
        View layout = inflater.inflate(R.layout.fragment_recipe, container, false);


        wb = (WebView) layout.findViewById(R.id.webpage);
        wb.setWebViewClient(new WebViewClient());

        ratingsbar = (RatingBar) layout.findViewById(R.id.ratingsbarind);
        dishname = (TextView) layout.findViewById(R.id.recipename);

        return layout;
    }


    private ArrayList<IndRecipeInfo> parseJsonresponse(JSONObject response) {
        ArrayList<IndRecipeInfo> listrecipe = new ArrayList<>();
        if (response != null && response.length() > 0) {
            try {
                StringBuilder data = new StringBuilder();
                IndRecipeInfo indRecipeInfo = new IndRecipeInfo();
                JSONObject recipe = response.getJSONObject(KEY_RECIPE);
                String id = recipe.getString(KEY_RECEPE_ID);
                String title = recipe.getString(KEY_TITLE);
                int ratings = recipe.getInt(KEY_SOCIAL_RANK);
                String dirwebpage=recipe.getString(KEY_SOURCE_URL);
                wb.loadUrl(dirwebpage);
                String image = null;
                image = recipe.getString(KEY_IMAGE_URL);
                indRecipeInfo.setId(id);
                indRecipeInfo.setTitle(title);

                indRecipeInfo.setRating(ratings);
                indRecipeInfo.setUrlThumbnail(image);

                dishname.setText(title);
                ratingsbar.setRating(ratings / 20.0F);
                ratingsbar.setAlpha(1.0F);
                listrecipe.add(indRecipeInfo);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        return listrecipe;
    }
/*
    private void loadImages(String urlThumbnail) {
        // if (!urlThumbnail.equals(SyncStateContract.Constants.NA)) {
        mImageLoader.get(urlThumbnail, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                dishimage.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //  }
    }*/
}
