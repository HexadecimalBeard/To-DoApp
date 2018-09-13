package com.exampleapp.to_do;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText signupUsernameEdittext,signupEmailEdittext,signupPasswordEdittext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth=FirebaseAuth.getInstance();
    }

    public void register(View view){

        //signupUsernameEdittext şimdilik alınmadı

        signupEmailEdittext=findViewById(R.id.activitysignup_email_edittext);
        signupPasswordEdittext=findViewById(R.id.activitysignup_password_edittext);

        if(!signupEmailEdittext.getText().toString().isEmpty() && !signupPasswordEdittext.getText().toString().isEmpty()){

            mAuth.createUserWithEmailAndPassword(signupEmailEdittext.getText().toString(),signupPasswordEdittext.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if(task.isSuccessful()){

                                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);

                            }else{

                                Toast.makeText(getApplicationContext(),"Check your information",Toast.LENGTH_LONG).show();

                            }

                        }
                    });

         }else{

            Toast.makeText(getApplicationContext(),"Please fill necessary information!",Toast.LENGTH_LONG).show();

        }




    }

    public void backlogin(View view){

        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
        startActivity(intent);
    }

}
