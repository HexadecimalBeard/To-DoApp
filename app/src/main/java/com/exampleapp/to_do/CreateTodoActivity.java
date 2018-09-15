package com.exampleapp.to_do;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class CreateTodoActivity extends AppCompatActivity {

    EditText et;
    Button bt;
    public List<String> listDataHeader1;
    public HashMap<String,List<String>> listHash1;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_todo);

        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference();

        bt=findViewById(R.id.button);
        et=findViewById(R.id.editText);



    }

    public void sendTodo(View view){

        String text=et.getText().toString();

        UUID uuıd=UUID.randomUUID();
        String uuidString=uuıd.toString();

        FirebaseUser user=mAuth.getCurrentUser();
        String useremail=user.getEmail().toString();
        String userid=user.getUid().toString();

        databaseReference.child(userid).child(uuidString).child("Todo").setValue(text);
        databaseReference.child(userid).child(uuidString).child("Useremail").setValue(useremail);
        databaseReference.child(userid).child(uuidString).child("Usersendtime").setValue(ServerValue.TIMESTAMP);

        et.setText("");

        Intent intent=new Intent(getApplicationContext(),TodoMainPage.class);
        startActivity(intent);


    }


}
