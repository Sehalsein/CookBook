package com.example.sehal.cookbook;

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

import java.util.List;

/**
 * Created by sehal on 5/11/2015.
 */
public class FAdapter extends RecyclerView.Adapter<FAdapter.CookingViewHolder> {

    private List<FavInfo> cookList;
    Context context;
    private Clicklistener clicklistener;
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
    public void onBindViewHolder(final CookingViewHolder cookingViewHolder, int viewType) {


        FavInfo ci = cookList.get(viewType);
        cookingViewHolder.loading.setVisibility(View.GONE);
        // cookingViewHolder.loading.setVisibility(View.VISIBLE);
       /* Picasso.with(context)
                .load(ci.iconid)
                .into(cookingViewHolder.vicon, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        cookingViewHolder.loading.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        cookingViewHolder.loading.setVisibility(View.GONE);
                        //NOTHING
                    }
                });
*/
        cookingViewHolder.vDishName.setText(ci.dishname);
        //rating=5%rating;
        cookingViewHolder.ratings.setRating((float) 3.5);
        //Set the font here
        cookingViewHolder.vDishName.setTypeface(roboto_reg);


    }

    //GETTING THE LAYoUT FOR INDIVIADUAL ROWS //TODO CHECK IF CORRECT
    @Override
    public CookingViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.recipe_list_card, viewGroup, false);

        return new CookingViewHolder(itemView);
    }

    //SETTING ON CLICK FOR EACH ROW //TODO FIX IT
    public void setClicklistener(Clicklistener clicklistener) {
        this.clicklistener = clicklistener;
    }

    //DECLARING AND INTIALIZING VARIABLE //TODO CHANGE THE NAME OF VARIABLE
    public class CookingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView vDishName;
        ImageView vicon;
        RatingBar ratings;
        ProgressBar loading;

        public CookingViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            vDishName = (TextView) v.findViewById(R.id.dish_name);
            vicon = (ImageView) v.findViewById(R.id.imageView);
            ratings = (RatingBar) v.findViewById(R.id.ratings);
            loading = (ProgressBar) v.findViewById(R.id.progress);
        }

        @Override
        public void onClick(View v) {
            //context.startActivity(new Intent(context, SubActivity.class));

            if (clicklistener != null) {
                clicklistener.itemClicked(v, getPosition());
            }
        }
    }

    //PASSES THE VALUE TO THE MAIN ACTIVITY
    public interface Clicklistener {
        public void itemClicked(View view, int position);
    }
}
