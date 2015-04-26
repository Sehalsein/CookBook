package com.example.sehal.cookbook;


//import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Recipe extends Fragment {

    TextView textView;

    public Recipe() {
        // Required empty public constructor
    }

    public static Recipe getInstance(int position) {
        Recipe recipe = new Recipe();
        Bundle args = new Bundle();
        args.putInt("position", position);
        recipe.setArguments(args);
        return recipe;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_recipe, container, false);
        textView = (TextView) layout.findViewById(R.id.texts);

        Bundle bundle = getArguments();
        if (bundle != null) {
            textView.setText("THE PAGE SELECTED IS " + bundle.getInt("position"));
            Log.d("SREHAL","VIEWPAGEW");
        }
        return layout;
    }


}
