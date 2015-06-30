package com.example.sehal.cookbook.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sehal.cookbook.R;
import com.example.sehal.cookbook.misc.CircleDP;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.widget.LikeView;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class Account extends Fragment {

    CallbackManager callbackManager;
    String UserName;
    TextView mprofilename;
    AccessTokenTracker mTokentracker;
    ProfileTracker mProfiletracker;
    ImageView displaypicture;
    Switch notification;
    Boolean switchnotification;
    String DISPLAY_PICTURE_URL;//=" https://graph.facebook.com/10206694830273269/picture?height=50&width=50&migration_overrides=%7Boctober_2012%3Atrue%7D";

    FacebookCallback<LoginResult> mcallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();

            if (profile != null) {
                // displaypicture.setImageURI(profile.getProfilePictureUri(50,50));
                DISPLAY_PICTURE_URL = " https://graph.facebook.com/" + profile.getId() + "/picture?height=200&width=200&migration_overrides=%7Boctober_2012%3Atrue%7D";
                Picasso.with(getActivity())
                        .load(DISPLAY_PICTURE_URL)
                        .into(displaypicture);
                UserName = profile.getName();
                mprofilename.setText(profile.getName());
                // UserName.setText("Welcome " + profile.getName());
                //Toast.makeText(getActivity(), "Welcome " + profile.getName(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onCancel() {
            Toast.makeText(getActivity(), "ACCOUNTCancel", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(FacebookException e) {
            Toast.makeText(getActivity(), "ACCOUNTERROR", Toast.LENGTH_SHORT).show();
        }
    };

    public Account() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        mTokentracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {

            }
        };
        mProfiletracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldprofile, Profile newprofile) {
                if (newprofile != null) {
                    DISPLAY_PICTURE_URL = " https://graph.facebook.com/" + newprofile.getId() + "/picture?height=200&width=200&migration_overrides=%7Boctober_2012%3Atrue%7D";
                    Picasso.with(getActivity())
                            .load(DISPLAY_PICTURE_URL)
                            .into(displaypicture);
                    mprofilename.setText(newprofile.getName());
                }
            }
        };
        mTokentracker.startTracking();
        mProfiletracker.startTracking();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_account, container, false);

        notification = (Switch) layout.findViewById(R.id.notificationswitch);
       // notification.setChecked(switchnotification);
        notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switchnotification = isChecked;
                   // Toast.makeText(getActivity(), "" + isChecked, Toast.LENGTH_LONG).show();
                } else {
                    switchnotification = isChecked;
                  //  Toast.makeText(getActivity(), "" + isChecked, Toast.LENGTH_LONG).show();
                }
            }
        });
        // Boolean switchnot=notification.isChecked();
        // Toast.makeText(getActivity(),""+switchnot,Toast.LENGTH_LONG).show();
        //CIRCLE DP
        displaypicture = (ImageView) layout.findViewById(R.id.displaypicture);
        mprofilename = (TextView) layout.findViewById(R.id.displayname);

        // displaypicture.setImageResource(R.drawable.dp);




        CircleDP circularImageView = (CircleDP) layout.findViewById(R.id.displaypicture);
        circularImageView.setBorderColor(getResources().getColor(R.color.dividercolor));
        circularImageView.setBorderWidth(3);
        circularImageView.addShadow();

        return layout;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends"); //for requesting permision
        loginButton.setFragment(this);
        loginButton.registerCallback(callbackManager, mcallback);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onResume() {
        super.onResume();
        Profile profile = Profile.getCurrentProfile();
        if (profile != null) {
            DISPLAY_PICTURE_URL = " https://graph.facebook.com/" + profile.getId() + "/picture?height=200&width=200&migration_overrides=%7Boctober_2012%3Atrue%7D";
            Picasso.with(getActivity())
                    .load(DISPLAY_PICTURE_URL)
                    .into(displaypicture);
            mprofilename.setText(profile.getName());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mTokentracker.stopTracking();
        mProfiletracker.stopTracking();
    }

}
