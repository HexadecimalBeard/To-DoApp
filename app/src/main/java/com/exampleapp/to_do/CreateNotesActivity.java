package com.exampleapp.to_do;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class CreateNotesActivity extends AppCompatActivity {

    TextView writenoteText,titleText;
    ImageButton doneButton;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_notes);

        writenoteText=findViewById(R.id.activitycreatenotes_writenoteText);
        titleText=findViewById(R.id.activitycreatenotes_titleText);
        doneButton=findViewById(R.id.activitycreatenotes_doneButton);

        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference();

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sendNotes(View v){

        DateFormat random=new SimpleDateFormat("HSFSd");
        String sendtime=random.format(Calendar.getInstance().getTime());

        //done buttona basıldığında yapılacaklar.
        String textTitle=titleText.getText().toString();
        String textNote=writenoteText.getText().toString();

        UUID uuıd = UUID.randomUUID();
        String uuidString = uuıd.toString();

        FirebaseUser user = mAuth.getCurrentUser();
        String useremail = user.getEmail().toString();
        String userid = user.getUid().toString();

        databaseReference.child(userid).child(uuidString).child("NoteTitle").setValue(textTitle);
        databaseReference.child(userid).child(uuidString).child("Note").setValue(textNote);
        databaseReference.child(userid).child(uuidString).child("Useremail").setValue(useremail);
        databaseReference.child(userid).child(uuidString).child("Usersendtime").setValue(sendtime);

        titleText.setText("");
        writenoteText.setText("");

        Intent intent = new Intent(getApplicationContext(), TodoMainPage.class);
        startActivity(intent);

    }


}
