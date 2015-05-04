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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeList extends Fragment implements RAdapter.Clicklistener {

    //public static final String URL_BIG_OVEN = "http://api.bigoven.com/recipes?title_kw=oysters&pg=1";
    public static final String URL_BIG_OVEN = "http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json";
    Communicator comm;
    RatingBar ratings;
    private ImageLoader imageLoader;
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;


    public static String getRequestUrl(int limit) {
        //return URL_BIG_OVEN + "&rpp=" + limit + "&apikey=" + MyApplication.API_KEY;
        return URL_BIG_OVEN +"?apikey="+MyApplication.API_KEY+"&limit="+limit;
    }

    public RecipeList() {


        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        volleySingleton = VolleySingleton.getInstance();
        requestQueue = volleySingleton.getRequestQueue();


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, getRequestUrl(10), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                L.t(getActivity(), response.toString());
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
        View layout = inflater.inflate(R.layout.recipe_list, container, false);

        ratings = (RatingBar) layout.findViewById(R.id.ratings);

        //ratings.setRating(50);

        RecyclerView recnav = (RecyclerView) layout.findViewById(R.id.recipeList);
        // RecyclerViewHeader header = RecyclerViewHeader.fromXml(getActivity(), R.layout.card_layout);
        recnav.setHasFixedSize(true);
        GridLayoutManager llm = new GridLayoutManager(getActivity(), 2);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recnav.setLayoutManager(llm);


        // header.attachTo(recnav);
        RLAapter nav = new RLAapter(getActivity(), createList(4));
        //nav.setClicklistener(this);     //used for on click
        recnav.setAdapter(nav);
    /*    RequestQueue requestQueue= VolleySingleton.getInstance().getRequestQueue();
        StringRequest request=new StringRequest(Request.Method.GET, "http://php.net/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getActivity(), "RESPONSE" + response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "ERROR" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request);*/
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
            String homemenu[] = {"WAA",
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
