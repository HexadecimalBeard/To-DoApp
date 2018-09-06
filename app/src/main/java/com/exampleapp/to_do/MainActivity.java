package com.exampleapp.to_do;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.buttonpass);

    }


    public void loginPass(View view){

        Intent intent= new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);

    }
}
