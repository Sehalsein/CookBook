package com.example.sehal.cookbook;

import android.content.Context;
import android.graphics.Typeface;
import android.provider.SyncStateContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sehal on 4/28/2015.
 * <p/>
 * THIS class is the ADAPTER FOR RECYCLEWR VIEW for RECIPE LIST
 * //TODO CHANGE THE VIEWHODER NAME
 */
public class RLAapter extends RecyclerView.Adapter<RLAapter.CookingViewHolder> {

    //contains the list of RecipeInfos
    private ArrayList<RecipeInfo> mRecipeList = new ArrayList<>();
    private LayoutInflater mInflater;
    private VolleySingleton mVolleySingleton;
    private ImageLoader mImageLoader;
    //formatter for parsing the dates in the specified format below
    //private DateFormat mFormatter = new SimpleDateFormat("yyyy-MM-dd");
    //keep track of the previous position for animations where scrolling down requires a different animation compared to scrolling up
    private int mPreviousPosition = 0;


    public RLAapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mVolleySingleton = VolleySingleton.getInstance();
        mImageLoader = mVolleySingleton.getImageLoader();
    }

    public void setRecipeInfo(ArrayList<RecipeInfo> listRecipeInfo) {
        this.mRecipeList = listRecipeInfo;
        //update the adapter to reflect the new set of RecipeInfos
        notifyDataSetChanged();
    }

    @Override
    public CookingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recipe_list_card, parent, false);
        CookingViewHolder viewHolder = new CookingViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CookingViewHolder holder, int position) {
        RecipeInfo currentRecipeInfo = mRecipeList.get(position);
        //one or more fields of the RecipeInfo object may be null since they are fetched from the web
        holder.vDishName.setText(currentRecipeInfo.getTitle());
        int audienceScore = currentRecipeInfo.getRating();
            holder.ratings.setRating(audienceScore / 20.0F);
            holder.ratings.setAlpha(1.0F);

        String urlThumnail = currentRecipeInfo.getUrlThumbnail();
        loadImages(urlThumnail, holder);
    }


    private void loadImages(String urlThumbnail, final CookingViewHolder holder) {
        // if (!urlThumbnail.equals(SyncStateContract.Constants.NA)) {
        mImageLoader.get(urlThumbnail, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.vicon.setImageBitmap(response.getBitmap());
                holder.loading.setVisibility(View.GONE);
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //  }
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    static class CookingViewHolder extends RecyclerView.ViewHolder {

        ImageView vicon;
        TextView vDishName;
        RatingBar ratings;
        ProgressBar loading;

        public CookingViewHolder(View v) {
            super(v);
            vDishName = (TextView) v.findViewById(R.id.dish_name);
            vicon = (ImageView) v.findViewById(R.id.imageView);
            loading = (ProgressBar) v.findViewById(R.id.progress);
            ratings = (RatingBar) v.findViewById(R.id.ratings);

        }
    }
}
