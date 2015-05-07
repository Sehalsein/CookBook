package com.example.sehal.cookbook;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by sehal on 4/28/2015.
 *
 * THIS class is the ADAPTER FOR RECYCLEWR VIEW for SettingHome
 *   //TODO CHANGE THE VIEWHODER NAME
 *
 *
 */
public class SAdapter extends RecyclerView.Adapter<SAdapter.CookingViewHolder> {

    private List<SettingsInfo> cookList;
    Context context;
    private Clicklistener clicklistener;
    private Typeface roboto_reg, roboto_bold, roboto_thin;

    public SAdapter(Context con, List<SettingsInfo> cookList) {
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

        SettingsInfo ci = cookList.get(viewType);
        cookingViewHolder.msettingsLabel.setText(ci.settingsLabel);
        //Set the font here
        cookingViewHolder.msettingsLabel.setTypeface(roboto_reg);

    }

    //GETTING THE LAYoUT FOR INDIVIADUAL ROWS //TODO CHECK IF CORRECT
    @Override
    public CookingViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.settings_list, viewGroup, false);

        return new CookingViewHolder(itemView);
    }

    //SETTING ON CLICK FOR EACH ROW
    public void setClicklistener(Clicklistener clicklistener) {
        this.clicklistener = clicklistener;
    }


    //DECLARING AND INTIALIZING VARIABLE //TODO CHANGE THE NAME OF VARIABLE
    public class CookingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        protected TextView msettingsLabel;

        public CookingViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
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

    //PASSES THE VALUE TO THE MAIN ACTIVITY
    public interface Clicklistener {
        public void itemClicked(View view, int position);
    }
}
