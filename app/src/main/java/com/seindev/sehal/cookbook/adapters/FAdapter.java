package com.seindev.sehal.cookbook.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.seindev.sehal.cookbook.misc.ClickListener;
import com.seindev.sehal.cookbook.infos.FavInfo;
import com.seindev.sehal.cookbook.R;

import java.util.List;

/**
 * Created by sehal on 5/11/2015.
 */
public class FAdapter extends RecyclerView.Adapter<FAdapter.FavouriteViewHolder> {

    private List<FavInfo> cookList;
    Context context;
    private ClickListener clicklistener;
    private Typeface roboto_reg, roboto_bold, roboto_thin;


    public FAdapter(Context con, List<FavInfo> cookList) {
        this.context = con;
        this.cookList = cookList;

        //Initialize the fonts here from assets folder created TODO FONT
        roboto_reg = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        roboto_bold = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");
        roboto_thin = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Thin.ttf");

    }


    @Override
    public int getItemCount() {
        return cookList.size();
    }

    @Override
    public void onBindViewHolder(final FavouriteViewHolder cookingViewHolder, int viewType) {


        FavInfo ci = cookList.get(viewType);
        cookingViewHolder.vloading.setVisibility(View.GONE);
        cookingViewHolder.vDishName.setText(ci.dishname);
        cookingViewHolder.vratings.setRating((float) 3.5);
        //Set the font here
        cookingViewHolder.vDishName.setTypeface(roboto_reg);


    }

    //GETTING THE LAYoUT FOR INDIVIADUAL ROWS //TODO CHECK IF CORRECT
    @Override
    public FavouriteViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.recipe_list_card, viewGroup, false);

        return new FavouriteViewHolder(itemView);
    }

    //SETTING ON CLICK FOR EACH ROW //TODO FIX IT
    public void setClicklistener(ClickListener clicklistener) {
        this.clicklistener = clicklistener;
    }

    //DECLARING AND INTIALIZING VARIABLE //TODO CHANGE THE NAME OF VARIABLE
    public class FavouriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView vDishName;
        ImageView vDishImage;
        RatingBar vratings;
        ProgressBar vloading;

        public FavouriteViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            vDishName = (TextView) v.findViewById(R.id.dish_name);
            vDishImage = (ImageView) v.findViewById(R.id.dish_image);
            vratings = (RatingBar) v.findViewById(R.id.ratings);
            vloading = (ProgressBar) v.findViewById(R.id.progress);
        }

        @Override
        public void onClick(View v) {
            if (clicklistener != null) {
                clicklistener.itemClicked(v, getPosition());
            }
        }


    }
}
