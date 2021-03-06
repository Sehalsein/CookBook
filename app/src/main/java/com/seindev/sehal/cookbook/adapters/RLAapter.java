package com.seindev.sehal.cookbook.adapters;

import android.content.Context;
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
import com.seindev.sehal.cookbook.R;
import com.seindev.sehal.cookbook.infos.RecipeInfo;
import com.seindev.sehal.cookbook.misc.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by sehal on 4/28/2015.
 * <p/>
 * THIS class is the ADAPTER FOR RECYCLEWR VIEW for RECIPE LIST
 * //TODO CHANGE THE VIEWHODER NAME
 */
public class RLAapter extends RecyclerView.Adapter<RLAapter.RecipleListViewHolder> {

    //contains the list of RecipeInfos
    private ArrayList<RecipeInfo> mRecipeList = new ArrayList<>();
    private LayoutInflater mInflater;
    private VolleySingleton mVolleySingleton;
    private ImageLoader mImageLoader;
    private RLCLickListner clicklistener;
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
    public RecipleListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recipe_list_card, parent, false);
        RecipleListViewHolder viewHolder = new RecipleListViewHolder(view);
        return viewHolder;
    }

    public void setClicklistener(RLCLickListner clicklistener) {
        this.clicklistener = clicklistener;
    }

    @Override
    public void onBindViewHolder(RecipleListViewHolder holder, int position) {
        RecipeInfo currentRecipeInfo = mRecipeList.get(position);
        //one or more fields of the RecipeInfo object may be null since they are fetched from the web
        holder.vDishName.setText(currentRecipeInfo.getTitle());
        holder.vloading.setVisibility(View.VISIBLE);
        int audienceScore = currentRecipeInfo.getRating();
            holder.ratings.setRating(audienceScore / 20.0F);
            holder.ratings.setAlpha(1.0F);

        String urlThumnail = currentRecipeInfo.getUrlThumbnail();
        loadImages(urlThumnail, holder);
    }


    private void loadImages(String urlThumbnail, final RecipleListViewHolder holder) {
        //
        // if (!urlThumbnail.equals(SyncStateContract.Constants.NA)) {
        mImageLoader.get(urlThumbnail, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                holder.vDishaImage.setImageBitmap(response.getBitmap());
                holder.vloading.setVisibility(View.GONE);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
               holder.vloading.setVisibility(View.GONE);
            }


        });
        //  }
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    public class RecipleListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView vDishaImage;
        TextView vDishName;
        RatingBar ratings;
        ProgressBar vloading;

        public RecipleListViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            vDishName = (TextView) v.findViewById(R.id.dish_name);
            vDishaImage = (ImageView) v.findViewById(R.id.dish_image);
            vloading = (ProgressBar) v.findViewById(R.id.progress);
            ratings = (RatingBar) v.findViewById(R.id.ratings);

        }

        @Override
        public void onClick(View v) {
            if (clicklistener != null) {
                RecipeInfo currentRecipeInfo = mRecipeList.get(getPosition());
                String idno=currentRecipeInfo.getId();
                clicklistener.itemClicked(v,idno);
            }
        }
    }

    public interface RLCLickListner{
        public void itemClicked(View view,String id);
    }

}
