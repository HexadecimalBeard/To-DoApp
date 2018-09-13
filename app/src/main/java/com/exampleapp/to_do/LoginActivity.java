package com.exampleapp.to_do;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    CardView forgotpasssendCardview, forgotpassCheckCardview;
    Dialog forgotDailog, forgotCheckDialog;
    TextView forgotpasssendNotify,activityloginTextview, forgotpassCheckTextview;
    ImageButton forgotpasssendImagebutton, forgotpassCheckImageButton;
    ImageView forgotpasssendImageView, forgotpassCheckImageView;
    EditText forgotpasssendEdittext,activitylogin_EmailEdittext,activitylogin_PasswordEdittext;
    Button forgotpasssendButton, forgotpassCheckButton,activitylogin_EnterappButton,activitylogin_ResgisterButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();

        activitylogin_EnterappButton= findViewById(R.id.activitylogin_enterapp_button);
        forgotDailog =new Dialog(this);
        forgotCheckDialog = new Dialog(this);

        //forgotCheckDialog
        Window dialogWindowCheck = forgotCheckDialog.getWindow();
        WindowManager.LayoutParams lpCheck = dialogWindowCheck.getAttributes();
        dialogWindowCheck.setGravity(Gravity.CENTER | Gravity.LEFT | Gravity.TOP| Gravity.RIGHT |Gravity.BOTTOM);

        lpCheck.width = 500; lpCheck.height = 600; lpCheck.alpha = 1f;
        dialogWindowCheck.setAttributes(lpCheck);

        //forgotDailog
        Window dialogWindow=forgotDailog.getWindow();
        WindowManager.LayoutParams lp=dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER | Gravity.LEFT | Gravity.TOP| Gravity.RIGHT |Gravity.BOTTOM);


        lp.width=500; lp.height=600; lp.alpha=1f;
        dialogWindow.setAttributes(lp);

        activityloginTextview=findViewById(R.id.activitylogin_forgotpassword_textview);

        activityloginTextview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //textview basıldığında yapılacaklar
                showdialog();
            }
        });
    }
    public void showdialog(){

        forgotDailog.setContentView(R.layout.forgotpass_enteremail);
        forgotpasssendButton=forgotDailog.findViewById(R.id.forgotpasssend_button);
        forgotpasssendImagebutton=forgotDailog.findViewById(R.id.forgotpasssend_imagebutton);
        forgotpasssendImageView=forgotDailog.findViewById(R.id.forgotpassend_imageview);
        forgotpasssendNotify=forgotDailog.findViewById(R.id.forgotpasssend_notifytextview);
        forgotpasssendEdittext=forgotDailog.findViewById(R.id.forgotpasssend_edittext);
        forgotpasssendCardview=forgotDailog.findViewById(R.id.forgotpass_entercardview);

        forgotpasssendImagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgotDailog.dismiss();
            }
        });

        forgotpasssendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!forgotpasssendEdittext.getText().toString().isEmpty()){

                    forgotDailog.dismiss();

                    showdialogCheck();
                } else {
                    Toast.makeText(getApplicationContext(),"Enter valid email address!",Toast.LENGTH_LONG).show();
                }
            }
        });
        forgotDailog.show();
    }
    public void showdialogCheck(){

        forgotCheckDialog.setContentView(R.layout.forgotpass_checkemail);
        forgotpassCheckButton = forgotCheckDialog.findViewById(R.id.forgotpasscheck_closebutton);
        forgotpassCheckTextview = forgotCheckDialog.findViewById(R.id.forgotpass_check_textview);
        forgotpassCheckImageView = forgotCheckDialog.findViewById(R.id.forgotpass_checkimageview);
        forgotpassCheckCardview = forgotCheckDialog.findViewById(R.id.forgotpass_checkcardview);
        forgotpassCheckImageButton = forgotCheckDialog.findViewById(R.id.forgotpass_checkimagebutton);

        forgotpassCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotCheckDialog.dismiss();
            }
        });
        forgotpassCheckImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgotCheckDialog.dismiss();
            }
        });
        forgotCheckDialog.show();
    }


    public void register(View view){

        Intent intent= new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(intent);

    }

    public void enterApp(View view){

        activitylogin_EmailEdittext=findViewById(R.id.activitylogin_email_edittext);
        activitylogin_PasswordEdittext=findViewById(R.id.activitylogin_password_edittext);

        if(!activitylogin_EmailEdittext.getText().toString().isEmpty() && !activitylogin_PasswordEdittext.getText().toString().isEmpty()){

            mAuth.signInWithEmailAndPassword(activitylogin_EmailEdittext.getText().toString(),activitylogin_PasswordEdittext.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            //edittext dolu mu bos mu kontrol et.

                            if (task.isSuccessful()){

                                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);

                            }else{

                                //giriş yapılamadı toast
                                Toast.makeText(getApplicationContext(),"Check your information",Toast.LENGTH_LONG).show();
                            }
                        }
                    });

        }else{

            Toast.makeText(getApplicationContext(),"Please fill necessary information!",Toast.LENGTH_LONG).show();
        }




    }

}
