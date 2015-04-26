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


public class NAdapter extends RecyclerView.Adapter<NAdapter.NavigationViewHolder> {

    private List<NavigationInfo> navigationList;
    public Context context;
    private ClicklistenerNav clicklistenernav;


    public NAdapter(Context context,List<NavigationInfo> navigationList) {
        this.context=context;
        this.navigationList = navigationList;

    }


    @Override
    public int getItemCount() {
        return navigationList.size();
    }

    @Override
    public void onBindViewHolder( NavigationViewHolder navigationViewHolder, int position) {

        NavigationInfo ci = navigationList.get(position);

      /*  Picasso.with(context)
                .load(ci.Icon)
                .into(navigationViewHolder.vicon);*/

        navigationViewHolder.vicon.setImageResource(ci.Icon);
        navigationViewHolder.vText.setText(ci.Text);

    }

    @Override
    public NavigationViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.navigation_view, viewGroup, false);

        return new  NavigationViewHolder(itemView);
    }

    public void setClicklistener(ClicklistenerNav clicklistenernav) {
        this.clicklistenernav = clicklistenernav;
    }

    public class NavigationViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        protected TextView vText;
        ImageView vicon;

        public NavigationViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            vText = (TextView)  v.findViewById(R.id.nav_text_name);
            vicon = (ImageView)v.findViewById(R.id.ImageView);
        }

        @Override
        public void onClick(View v) {
            //context.startActivity(new Intent(context, SubActivity.class));
            if (clicklistenernav != null) {
                Log.d("SEHAL","SADd");
                clicklistenernav.itemClickednav(v, getPosition());
            }
        }
    }

    public interface ClicklistenerNav {

        public void itemClickednav(View view, int position);

    }
}