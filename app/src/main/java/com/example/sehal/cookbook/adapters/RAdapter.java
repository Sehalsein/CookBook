package com.example.sehal.cookbook.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.sehal.cookbook.misc.ClickListener;
import com.example.sehal.cookbook.infos.HomeInfo;
import com.example.sehal.cookbook.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sehal on 4/28/2015.
 *
 * THIS class is the ADAPTER FOR RECYCLEWR VIEW for HomePage
 *   //TODO CHANGE THE VIEWHODER NAME
 *
 *
 */
public class RAdapter extends RecyclerView.Adapter<RAdapter.HomeViewHolder> {

    private List<HomeInfo> cookList;
    Context context;
    private ClickListener clicklistener;
    private Typeface roboto_reg, roboto_bold, roboto_thin;

    public RAdapter(Context con, List<HomeInfo> cookList) {
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


    //ASSIGNING VALUES PASSED FROM THE CLASS
    @Override
    public void onBindViewHolder(final HomeViewHolder HomeViewHolder, int viewType) {
        
        HomeInfo ci = cookList.get(viewType);
        HomeViewHolder.vloading.setVisibility(View.VISIBLE);
        Picasso.with(context)
                .load(ci.iconid)
                .error(R.drawable.error) //TODO CHANGE TH
                .into(HomeViewHolder.vCategoryImage, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        HomeViewHolder.vloading.setVisibility(View.GONE);
                    }
                    @Override
                    public void onError() {
                        HomeViewHolder.vloading.setVisibility(View.GONE);
                        //NOTHING
                    }
                });

        Picasso.with(context).setIndicatorsEnabled(true);
        HomeViewHolder.vCategoryName.setText(ci.dishname);
        //Set the font here
        HomeViewHolder.vCategoryName.setTypeface(roboto_reg);


    }


    //GETTING THE LAYoUT FOR INDIVIADUAL ROWS //TODO CHECK IF CORRECT
    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new HomeViewHolder(itemView);
    }


    //SETTING ON CLICK FOR EACH ROW
    public void setClicklistener(ClickListener clicklistener) {
        this.clicklistener = clicklistener;
    }


    //DECLARING AND INTIALIZING VARIABLE //TODO CHANGE THE NAME OF VARIABLE
    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView vCategoryName;
        ImageView vCategoryImage;
        ProgressBar vloading;


        public HomeViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            vCategoryName = (TextView) v.findViewById(R.id.category_name);
            vCategoryImage = (ImageView) v.findViewById(R.id.category_image);
            vloading= (ProgressBar) v.findViewById(R.id.progress);
        }

        @Override
        public void onClick(View v) {
            if (clicklistener != null) {
                clicklistener.itemClicked(v, getPosition());
            }
        }
    }
}
