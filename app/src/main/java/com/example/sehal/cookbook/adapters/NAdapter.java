package com.example.sehal.cookbook.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sehal.cookbook.misc.ClickListener;
import com.example.sehal.cookbook.infos.NavigationInfo;
import com.example.sehal.cookbook.R;

import java.util.List;

/**
 * THIS class is the ADAPTER FOR RECYCLEWR VIEW for NavigationDrawerr.class
 * //TODO CHANGE THE VIEWHODER NAME
 */
public class NAdapter extends RecyclerView.Adapter<NAdapter.NavigationViewHolder> {

    private List<NavigationInfo> navigationList;
    public Context context;
    private ClickListener clicklistener;


    public NAdapter(Context context, List<NavigationInfo> navigationList) {
        this.context = context;
        this.navigationList = navigationList;

    }


    @Override
    public int getItemCount() {
        return navigationList.size();
    }

    @Override
    public void onBindViewHolder(NavigationViewHolder navigationViewHolder, int position) {

        NavigationInfo ci = navigationList.get(position);

        navigationViewHolder.vNavIcon.setImageResource(ci.Icon);
        navigationViewHolder.vNavText.setText(ci.Text);

    }

    //GETTING THE LAYoUT FOR INDIVIADUAL ROWS
    @Override
    public NavigationViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.navigation_view, viewGroup, false);

        return new NavigationViewHolder(itemView);
    }

    //SETTING ON CLICK FOR EACH ROW
    public void setClicklistener(ClickListener clicklistener) {
        this.clicklistener = clicklistener;
    }

    //DECLARING AND INTIALIZING VARIABLE //TODO CHANGE THE NAME OF VARIABLE
    public class NavigationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView vNavText;
        ImageView vNavIcon;

        public NavigationViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            vNavText = (TextView) v.findViewById(R.id.nav_text_name);
            vNavIcon = (ImageView) v.findViewById(R.id.nav_icon);
        }

        @Override
        public void onClick(View v) {
            if (clicklistener != null) {
                clicklistener.itemClicked(v, getPosition());
            }
        }
        
        
    }
}