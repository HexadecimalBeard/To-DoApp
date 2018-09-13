package com.exampleapp.to_do;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    Button button;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        button=findViewById(R.id.buttonpass);
        mAuth=FirebaseAuth.getInstance();

        mAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if(firebaseAuth.getCurrentUser() ==null){
                    startActivity(new Intent(MainActivity.this,LoginActivity.class));
                }
            }
        };


    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void loginPass(View view){

        Intent intent= new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);

    }

    public void signout(View view){
        mAuth.signOut();
    }
}
