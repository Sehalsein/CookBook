package com.example.sehal.cookbook;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sehal on 4/22/2015.
 */
public class RAdapter extends RecyclerView.Adapter<RAdapter.CookingViewHolder> {

    private List<HomeInfo> cookList;
    Context context;
    private Clicklistener clicklistener;
    private Typeface roboto_reg, roboto_bold, roboto_thin;

    public RAdapter(Context con, List<HomeInfo> cookList) {
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

    @Override
    public void onBindViewHolder(final CookingViewHolder cookingViewHolder, int viewType) {


        HomeInfo ci = cookList.get(viewType);


        Picasso.with(context)
                .load(ci.iconid)
                .into(cookingViewHolder.vicon);

        cookingViewHolder.vDishName.setText(ci.dishname);

        //Set the font here
        cookingViewHolder.vDishName.setTypeface(roboto_reg);


    }

    @Override
    public CookingViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new CookingViewHolder(itemView);
    }

    public void setClicklistener(Clicklistener clicklistener) {
        this.clicklistener = clicklistener;
    }

    public class CookingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView vDishName;
        ImageView vicon;


        public CookingViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            vDishName = (TextView) v.findViewById(R.id.dish_name);
            vicon = (ImageView) v.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View v) {
            //context.startActivity(new Intent(context, SubActivity.class));
            if (clicklistener != null) {
                clicklistener.itemClicked(v, getPosition());
            }
        }
    }

    public interface Clicklistener {
        public void itemClicked(View view, int position);
    }
}
