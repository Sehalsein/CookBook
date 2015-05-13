package com.example.sehal.cookbook;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class Account extends Fragment {

    CallbackManager callbackManager;
    String UserName;
    AccessTokenTracker mTokentracker;
    ProfileTracker mProfiletracker;
    ImageView displaypicture;
    String DISPLAY_PICTURE_URL;//=" https://graph.facebook.com/10206694830273269/picture?height=50&width=50&migration_overrides=%7Boctober_2012%3Atrue%7D";

    FacebookCallback<LoginResult> mcallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            AccessToken accessToken = loginResult.getAccessToken();
            Profile profile = Profile.getCurrentProfile();

            if (profile != null) {
               // displaypicture.setImageURI(profile.getProfilePictureUri(50,50));
                DISPLAY_PICTURE_URL=" https://graph.facebook.com/"+profile.getId()+"/picture?height=200&width=200&migration_overrides=%7Boctober_2012%3Atrue%7D";
                Picasso.with(getActivity())
                        .load(DISPLAY_PICTURE_URL)
                        .into(displaypicture);
                UserName=profile.getName();
               // UserName.setText("Welcome " + profile.getName());
                Toast.makeText(getActivity(), "Welcome " + profile.getName(), Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        public void onCancel() {
            Toast.makeText(getActivity(), "Cancel", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(FacebookException e) {
            Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getActivity(), "Welcome new " + newprofile.getName(), Toast.LENGTH_SHORT).show();
                    UserName=newprofile.getName();
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

        //CIRCLE DP
        displaypicture= (ImageView) layout.findViewById(R.id.displaypicture);
       // displaypicture.setImageResource(R.drawable.dp);

        CircleDP circularImageView = (CircleDP) layout.findViewById(R.id.displaypicture);
        //circularImageView.setBorderColor(getResources().getColor(R.color.GrayLight));
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
            //UserName.setText("Welcome " + profile.getName());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        mTokentracker.stopTracking();
        mProfiletracker.stopTracking();
    }
}
