package com.example.sehal.cookbook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * THIS class is the ADAPTER FOR RECYCLEWR VIEW for NavigationDrawerr.class
 * //TODO CHANGE THE VIEWHODER NAME
 */
public class NAdapter extends RecyclerView.Adapter<NAdapter.NavigationViewHolder> {

    private List<NavigationInfo> navigationList;
    public Context context;
    private ClicklistenerNav clicklistenernav;


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

        navigationViewHolder.vicon.setImageResource(ci.Icon);
        navigationViewHolder.vText.setText(ci.Text);

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
    public void setClicklistener(ClicklistenerNav clicklistenernav) {
        this.clicklistenernav = clicklistenernav;
    }

    //DECLARING AND INTIALIZING VARIABLE //TODO CHANGE THE NAME OF VARIABLE
    public class NavigationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView vText;
        ImageView vicon;

        public NavigationViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            vText = (TextView) v.findViewById(R.id.nav_text_name);
            vicon = (ImageView) v.findViewById(R.id.ImageView);
        }

        @Override
        public void onClick(View v) {
            //context.startActivity(new Intent(context, SubActivity.class));
            if (clicklistenernav != null) {
                Log.d("SEHAL", "SADd");
                clicklistenernav.itemClickednav(v, getPosition());
            }
        }
    }

    //PASSES THE VALUE TO THE MAIN ACTIVITY
    public interface ClicklistenerNav {

        public void itemClickednav(View view, int position);

    }
}