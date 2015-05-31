package com.example.sehal.cookbook.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.sehal.cookbook.misc.ClickListener;
import com.example.sehal.cookbook.R;
import com.example.sehal.cookbook.infos.SettingsInfo;

import java.util.List;

/**
 * Created by sehal on 4/28/2015.
 * <p/>
 * THIS class is the ADAPTER FOR RECYCLEWR VIEW for SettingHome
 * //TODO CHANGE THE VIEWHODER NAME
 */
public class SAdapter extends RecyclerView.Adapter<SAdapter.SettingViewHolder> {

    private List<SettingsInfo> cookList;
    Context context;
    private ClickListener clicklistener;
    private Typeface roboto_reg, roboto_bold, roboto_thin,wa;

    public SAdapter(Context con, List<SettingsInfo> cookList) {
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
    public void onBindViewHolder(final SettingViewHolder cookingViewHolder, int position) {

        SettingsInfo ci = cookList.get(position);
        if (position==1){
            cookingViewHolder.settingslayout.setBackgroundColor(R.color.accentColor);
        }
        cookingViewHolder.msettingsLabel.setText(ci.settingsLabel);
        


        //Set the font here
        cookingViewHolder.msettingsLabel.setTypeface(roboto_bold);

    }

    //GETTING THE LAYoUT FOR INDIVIADUAL ROWS //TODO CHECK IF CORRECT
    @Override
    public SettingViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.settings_list, viewGroup, false);

      
        return new SettingViewHolder(itemView);
    }

    //SETTING ON CLICK FOR EACH ROW
    public void setClicklistener(ClickListener clicklistener) {
        this.clicklistener = clicklistener;
    }


    //DECLARING AND INTIALIZING VARIABLE //TODO CHANGE THE NAME OF VARIABLE
    public class SettingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView msettingsLabel;
        RelativeLayout settingslayout;

        public SettingViewHolder(View v) {
            super(v);

            v.setOnClickListener(this);
            settingslayout= (RelativeLayout) v.findViewById(R.id.settings_layout);
            msettingsLabel = (TextView) v.findViewById(R.id.settings_label);

        }

        @Override
        public void onClick(View v) {
            //context.startActivity(new Intent(context, SubActivity.class));
            if (clicklistener != null) {
                clicklistener.itemClicked(v, getPosition());
            }
        }

    }
}
