package com.example.sehal.cookbook;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sehal on 4/28/2015.
 *
 * THIS class is the ADAPTER FOR RECYCLEWR VIEW for Recipe.class
 *   //TODO CHANGE THE VIEWHODER NAME
 *
 *
 */

public class IRAdapter extends RecyclerView.Adapter<IRAdapter.CookingViewHolder> {

    private List<IndRecipeInfo> cookList;
    Context context;
    //public float rating=1f;
    private Clicklistener clicklistener;
    private Typeface roboto_reg, roboto_bold, roboto_thin;


    public IRAdapter(Context con, List<IndRecipeInfo> cookList) {
        this.context = con;
        this.cookList = cookList;

       /* //Initialize the fonts here from assets folder created TODO FONT
        roboto_reg = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
        roboto_bold = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Bold.ttf");
        roboto_thin = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Thin.ttf");
*/
    }


    @Override
    public int getItemCount() {
        return cookList.size();
    }

    //ASSIGNING VALUES PASSED FROM THE CLASS
    @Override
    public void onBindViewHolder(final CookingViewHolder cookingViewHolder, int viewType) {


        IndRecipeInfo ci = cookList.get(viewType);


        Picasso.with(context)
                .load(ci.recipesteppics)
                .into(cookingViewHolder.vicon);

        cookingViewHolder.vDishName.setText(ci.recipestepno);
        cookingViewHolder.vRecipeStep.setText(ci.recipestep);
        //rating=5%rating;
        //cookingViewHolder.ratings.setRating((float) 3.5);
        //Set the font here
        cookingViewHolder.vDishName.setTypeface(roboto_reg);


    }

    //GETTING THE LAYoUT FOR INDIVIADUAL ROWS //TODO CHECK IF CORRECT
    @Override
    public CookingViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.ind_recipe_layout, viewGroup, false);

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
        protected TextView vRecipeStep;
        RatingBar ratings;

        public CookingViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            vDishName = (TextView) v.findViewById(R.id.dish_name);
            vicon = (ImageView) v.findViewById(R.id.imageView);
            ratings = (RatingBar) v.findViewById(R.id.ratings);
            vRecipeStep= (TextView) v.findViewById(R.id.dish_recipe_step);
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
