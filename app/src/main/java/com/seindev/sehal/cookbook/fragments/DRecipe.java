package com.seindev.sehal.cookbook.fragments;

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
import com.seindev.sehal.cookbook.R;
import com.seindev.sehal.cookbook.infos.IndRecipeInfo;
import com.seindev.sehal.cookbook.misc.Communicator;
import com.seindev.sehal.cookbook.misc.MyApplication;
import com.seindev.sehal.cookbook.misc.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import me.drakeet.materialdialog.MaterialDialog;

import static com.seindev.sehal.cookbook.misc.Keys.EndpointRecipe.KEY_IMAGE_URL;
import static com.seindev.sehal.cookbook.misc.Keys.EndpointRecipe.KEY_RECEPE_ID;
import static com.seindev.sehal.cookbook.misc.Keys.EndpointRecipe.KEY_RECIPES;
import static com.seindev.sehal.cookbook.misc.Keys.EndpointRecipe.KEY_SOCIAL_RANK;
import static com.seindev.sehal.cookbook.misc.Keys.EndpointRecipe.KEY_SOURCE_URL;
import static com.seindev.sehal.cookbook.misc.Keys.EndpointRecipe.KEY_TITLE;

/**
 * Created by sehal on 5/30/2015.
 */
public class DRecipe extends Fragment {


    //Declaring variables
    TextView textView;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    public static final String URL_RECIPE = "http://food2fork.com/api/search";
    public static final String URL_RECIPE_IND = "http://food2fork.com/api/get";
    public static int formattedMonth;
    public static int formattedDate;
    public static String id;

    Communicator comm;
    ImageView dishimage;
    RatingBar ratingsbar;
    TextView dishname;
    WebView wb;
    MaterialDialog mMaterialDialog;

    FragmentManager manager;
    private ImageLoader imageLoader;
    private VolleySingleton volleySingleton;
    private VolleySingleton mVolleySingleton;
    private ImageLoader mImageLoader;
    private RequestQueue requestQueue;
    private ArrayList<IndRecipeInfo> recipelists = new ArrayList<>();
    // IRAdapter irAdapter;

    public DRecipe() {
        // Required empty public constructor
        //mImageLoader = mVolleySingleton.getImageLoader();
    }

    public static DRecipe getInstance(int position) {
        DRecipe recipe = new DRecipe();
        Bundle args = new Bundle();
        args.putInt("position", position);
        recipe.setArguments(args);
        //args.put
        return recipe;
    }


    public static String getRequestUrl(int limit) {
        //return URL_BIG_OVEN + "&rpp=" + limit + "&apikey=" + MyApplication.API_KEY;   //BIGOVEn URL
        //return URL_RECIPE + "?key=" + MyApplication.API_KEY;          //ROTTEN TOMATOES
        return URL_RECIPE + "?key=" + MyApplication.API_KEY + "&page=" + formattedMonth;//+ Search.IDNO;          //ROTTEN TOMATOES
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat mf = new SimpleDateFormat("MM");
        SimpleDateFormat df = new SimpleDateFormat("dd");
        formattedMonth = Integer.parseInt(mf.format(c.getTime()));
        formattedMonth = formattedMonth + 30;
        formattedDate = Integer.parseInt(df.format(c.getTime()));
        //Toast.makeText(getActivity(), "PAGE" + formattedMonth, Toast.LENGTH_SHORT).show();
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
                if (error.getMessage() == null) {
                    sendjsonrequest();
                } else {
                    Toast.makeText(getActivity(), "RECIPE ERROR " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }

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
                JSONArray arrayrecipes = response.getJSONArray(KEY_RECIPES);
                if (formattedDate >= 30) {
                    formattedDate = 0;
                }
                JSONObject recipe = arrayrecipes.getJSONObject(formattedDate);
                id = recipe.getString(KEY_RECEPE_ID);
                String title = recipe.getString(KEY_TITLE);
                int ratings = recipe.getInt(KEY_SOCIAL_RANK);
                String dirwebpage = recipe.getString(KEY_SOURCE_URL);
                wb.loadUrl(dirwebpage);
                String image = null;
                image = recipe.getString(KEY_IMAGE_URL);
                //Toast.makeText(getActivity(), "PAGE NID" + id, Toast.LENGTH_SHORT).show();
                IndRecipeInfo indRecipeInfo = new IndRecipeInfo();
                indRecipeInfo.setId(id);
                indRecipeInfo.setTitle(title);
                indRecipeInfo.setRating(ratings);
                indRecipeInfo.setUrlThumbnail(image);
                comm.respond(99, id);
                dishname.setText(title);
                ratingsbar.setRating(ratings / 20.0F);
                ratingsbar.setAlpha(1.0F);
                listrecipe.add(indRecipeInfo);
            } catch (JSONException e) {
                e.printStackTrace();
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
            }


        }
        return listrecipe;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        comm = (Communicator) getActivity();
        super.onActivityCreated(savedInstanceState);
    }

}
