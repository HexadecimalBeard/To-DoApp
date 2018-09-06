package com.exampleapp.to_do;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    @Override
    protected void onStart() {
        super.onStart();

        ImageView loginScreenIcon = ( ImageView ) findViewById(R.id.activityLogin_Icon);

        ImageView loginscreenblueupper_imageView = ( ImageView ) findViewById(R.id.loginscreenblue_imageview);

        ImageView loginscreenwhitedown_imageView = ( ImageView ) findViewById(R.id.loginscreenwhite_imageview);

        loginscreenblueupper_imageView.setY(-1000);

        loginscreenwhitedown_imageView.setY(1000);

        loginscreenblueupper_imageView.animate().translationYBy(1000).setDuration(675);

        loginscreenwhitedown_imageView.animate().translationYBy(-1000).setDuration(675);

        Animation startAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        loginScreenIcon.startAnimation(startAnimation);

    }


}
