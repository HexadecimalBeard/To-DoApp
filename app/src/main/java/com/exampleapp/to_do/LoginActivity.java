package com.exampleapp.to_do;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    boolean anim=false;
    Animation startAnimationRelativeLayout;
    Animation startAnimationIcon;
    Animation startAnimationForgotPass;
    Animation startAnimationDontHaveAccount;
    Animation startAnimationSignInGoogle;
    Animation startAnimationLineOrLine;
    Animation startAnimationSignUpTextview;
    Animation startAnimationLoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(!anim) {

            RelativeLayout loginactivity_relativelayout = findViewById(R.id.activitylogin_relativelayout);

            ImageView loginScreenIcon = findViewById(R.id.activityLogin_Icon);

            ImageView loginscreenblueupper_imageView = findViewById(R.id.loginscreenblue_imageview);

            ImageView loginscreenwhitedown_imageView =  findViewById(R.id.loginscreenwhite_imageview);

            TextView loginscreenforgotpass = findViewById(R.id.activitylogin_forgotpass_textview);

            TextView loginscreendonthaveanaccount = findViewById(R.id.activitylogin_donthaveanacc_textview);

            Button loginscreensigningooglebutton = findViewById(R.id.activitylogin_signingoogle_button);

            TextView loginscreensignuptextview = findViewById(R.id.activitylogin_signup_textview);

            Button loginscreenloginbutton = findViewById(R.id.activitylogin_loginbutton);

            ImageView loginscreenlineorlineimageview = findViewById(R.id.activitylogin_lineorline_imageview);

            loginscreenblueupper_imageView.setY(-1000);

            loginscreenwhitedown_imageView.setY(1000);

            loginscreenblueupper_imageView.animate().translationYBy(1000).setDuration(675);

            loginscreenwhitedown_imageView.animate().translationYBy(-1000).setDuration(675);

            startAnimationIcon = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
            loginScreenIcon.startAnimation(startAnimationIcon);

            startAnimationRelativeLayout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_relativelayout);
            loginactivity_relativelayout.startAnimation(startAnimationRelativeLayout);

            startAnimationForgotPass = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_forgotpass);
            loginscreenforgotpass.startAnimation(startAnimationForgotPass);

            startAnimationDontHaveAccount = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_donthaveaccount);
            loginscreendonthaveanaccount.startAnimation(startAnimationDontHaveAccount);

            startAnimationSignInGoogle = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in_signingoogle);
            loginscreensigningooglebutton.startAnimation(startAnimationSignInGoogle);

            startAnimationSignUpTextview = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_signuptextview);
            loginscreensignuptextview.startAnimation(startAnimationSignUpTextview);

            startAnimationLoginButton = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_loginbutton);
            loginscreenloginbutton.startAnimation(startAnimationLoginButton);

            startAnimationLineOrLine = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_lineorline);
            loginscreenlineorlineimageview.startAnimation(startAnimationLineOrLine);


            anim=true;

        }else{

            startAnimationIcon.cancel();
            startAnimationRelativeLayout.cancel();
        }

    }

}
