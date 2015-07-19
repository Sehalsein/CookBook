package com.seindev.sehal.cookbook.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.seindev.sehal.cookbook.infos.IngredinetsInfo;
import com.seindev.sehal.cookbook.R;
import com.seindev.sehal.cookbook.misc.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by sehal on 5/16/2015.
 */
public class IAdapter extends RecyclerView.Adapter<IAdapter.IngredientsViewHolder> {

    //contains the list of IngredinetsInfos
    private ArrayList<IngredinetsInfo> mIngredinetsList = new ArrayList<>();
    private LayoutInflater mInflater;
    private VolleySingleton mVolleySingleton;
    private ImageLoader mImageLoader;
    private int mPreviousPosition = 0;
    int i=1;


    public IAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        mVolleySingleton = VolleySingleton.getInstance();
        mImageLoader = mVolleySingleton.getImageLoader();
    }

    public void setIngredinetsInfo(ArrayList<IngredinetsInfo> listIngredinetsInfo) {
        this.mIngredinetsList = listIngredinetsInfo;
        //update the adapter to reflect the new set of IngredinetsInfos
        notifyDataSetChanged();
    }

    @Override
    public IngredientsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.ingredients_list, parent, false);
        IngredientsViewHolder viewHolder = new IngredientsViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(IngredientsViewHolder holder, int position) {
        IngredinetsInfo currentIngredinetsInfo = mIngredinetsList.get(position);

        if(position==i) {
            holder.inglist.setBackgroundColor(R.color.accentColor);
            i+=2;
        }
      
        holder.vingredients.setText(currentIngredinetsInfo.getingredinetsname());

    }



    @Override
    public int getItemCount() {
        return mIngredinetsList.size();
    }

    static class IngredientsViewHolder extends RecyclerView.ViewHolder {

        TextView vingredients;
        LinearLayout inglist;

        public IngredientsViewHolder(View v) {
            super(v);
            vingredients = (TextView) v.findViewById(R.id.ingriedints);
            inglist= (LinearLayout) v.findViewById(R.id.ingriedintsview);
            
        }
    }
}
