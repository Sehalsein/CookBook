package com.seindev.sehal.cookbook.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seindev.sehal.cookbook.R;
import com.seindev.sehal.cookbook.infos.OpenInfo;
import com.seindev.sehal.cookbook.misc.ClickListener;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sehal on 5/31/2015.
 */
public class OAdapter extends RecyclerView.Adapter<OAdapter.OpenViewHolder> {

    private List<OpenInfo> cookList;
    Context context;
    private ClickListener clicklistener;
    private Typeface roboto_reg, roboto_bold, roboto_thin;

    public OAdapter(Context con, List<OpenInfo> cookList) {
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
    public void onBindViewHolder(final OpenViewHolder OpenViewHolder, int viewType) {

        OpenInfo ci = cookList.get(viewType);
        

        Picasso.with(context).setIndicatorsEnabled(true);
        OpenViewHolder.vTitle.setText(ci.Title);
        OpenViewHolder.vProjectURL.setText(ci.ProjectURL);
        OpenViewHolder.vCopyRight.setText(ci.Copyright);
        OpenViewHolder.vLicenseURL.setText(ci.LicenseURL);
       // OpenViewHolder.vCategoryName.setTypeface(roboto_reg);

    }


    //GETTING THE LAYoUT FOR INDIVIADUAL ROWS //TODO CHECK IF CORRECT
    @Override
    public OpenViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.openscource_list, viewGroup, false);

        return new OpenViewHolder(itemView);
    }


    //SETTING ON CLICK FOR EACH ROW
    public void setClicklistener(ClickListener clicklistener) {
        this.clicklistener = clicklistener;
    }


    //DECLARING AND INTIALIZING VARIABLE //TODO CHANGE THE NAME OF VARIABLE
    public class OpenViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView vTitle;
        protected TextView vProjectURL;
        protected TextView vCopyRight;
        protected TextView vLicenseURL;


        public OpenViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            vTitle = (TextView) v.findViewById(R.id.title);
            vProjectURL = (TextView) v.findViewById(R.id.projecturl);
            vCopyRight= (TextView) v.findViewById(R.id.copyright);
            vLicenseURL= (TextView) v.findViewById(R.id.licenseurl);
        }

        @Override
        public void onClick(View v) {
            if (clicklistener != null) {
                clicklistener.itemClicked(v, getPosition());
            }
        }
    }
}
