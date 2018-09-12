package com.exampleapp.to_do;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    RelativeLayout forgotpasssendUp,forgotpasssendDown;
    LinearLayout forgotpasssendLinear;
    CardView forgotpasssendCardview;

    Dialog forgotDailog;
    TextView forgotpasssendNotify,activityloginTextview;
    ImageButton forgotpasssendImagebutton;
    ImageView   forgotpasssendImageView;
    EditText   forgotpasssendEdittext;
    Button     forgotpasssendButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btn= findViewById(R.id.activitylogin_login_button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);

            }
        });

        forgotDailog =new Dialog(this);

        Window dialogWindow=forgotDailog.getWindow();
        WindowManager.LayoutParams lp=dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER | Gravity.LEFT | Gravity.TOP);

        // lp.x=100; lp.y=100;
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
                Toast.makeText(getApplicationContext(),forgotpasssendEdittext.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });


       //forgotDailog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        forgotDailog.show();

    }

}
