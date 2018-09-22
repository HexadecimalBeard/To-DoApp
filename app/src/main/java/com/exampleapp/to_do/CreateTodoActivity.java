package com.exampleapp.to_do;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.UUID;

public class CreateTodoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener{

    EditText createtodo_writebutton;
    ImageButton createtodo_donebutton, createdtodo_previouspage,reminderButton;
    TextView dateSet,timeSet;
    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;



    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_todo);

        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference();

        createtodo_donebutton=findViewById(R.id.activitycreatenotes_doneButton);
        createtodo_writebutton=findViewById(R.id.createtodo_writebutton);
        createdtodo_previouspage=findViewById(R.id.gotopreviouspage_button);
        reminderButton=findViewById(R.id.createtodo_reminderbutton);
        dateSet=findViewById(R.id.createtodo_createdatetextview);
        timeSet=findViewById(R.id.createtodo_createtimetextview);






        createdtodo_previouspage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TodoMainPage.class);
                startActivity(intent);
            }
        });

        reminderButton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {


                Calendar now2 = Calendar.getInstance();
                final TimePickerDialog dpd2 = TimePickerDialog.newInstance(
                        CreateTodoActivity.this,
                        now2.get(Calendar.HOUR_OF_DAY), // Initial year selection
                        now2.get(Calendar.MINUTE), // Initial month selection
                        true // true= is 24 hours, false=12 hours


                );

                dpd2.show(getFragmentManager(), "Timepickerdialog");


                Calendar now=Calendar.getInstance();
                DatePickerDialog dpd=DatePickerDialog.newInstance(
                        CreateTodoActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(),"Datepickerdialog");

            }

        });

    }


    public void sendTodo(View view){



            String text = createtodo_writebutton.getText().toString();

            UUID uuıd = UUID.randomUUID();
            String uuidString = uuıd.toString();

            FirebaseUser user = mAuth.getCurrentUser();
            String useremail = user.getEmail().toString();
            String userid = user.getUid().toString();

            databaseReference.child(userid).child(uuidString).child("Todo").setValue(text);
            databaseReference.child(userid).child(uuidString).child("Useremail").setValue(useremail);
            databaseReference.child(userid).child(uuidString).child("Usersendtime").setValue(ServerValue.TIMESTAMP);




           // databaseReference.child(userid).child(uuidString).child("Date").setValue(dateSet.getText().toString());
            databaseReference.child(userid).child(uuidString).child("Time").setValue(timeSet.getText().toString());

            createtodo_writebutton.setText("");

            Intent intent = new Intent(getApplicationContext(), TodoMainPage.class);
            startActivity(intent);


    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

        String date=dayOfMonth+"/"+(monthOfYear+1)+"/"+year;

        DateFormat dfDay=new SimpleDateFormat("dd");
        String currentday1=dfDay.format(Calendar.getInstance().getTime());
        int currentday=Integer.valueOf(currentday1);

        DateFormat dfMonth=new SimpleDateFormat("MM");
        String currentmonth1=dfMonth.format(Calendar.getInstance().getTime());
        int currentmonth=Integer.valueOf(currentmonth1);

        DateFormat dfYear=new SimpleDateFormat("yyyy");
        String currentYear1=dfYear.format(Calendar.getInstance().getTime());
        int currentYear=Integer.valueOf(currentYear1);

            if(year< currentYear || monthOfYear<currentmonth || dayOfMonth<currentday){

                dateSet.setText("");
                Toast.makeText(getApplicationContext(),"Please enter a current time",Toast.LENGTH_LONG).show();
            }else{
                dateSet.setText(date);
            }

    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {

        String time=hourOfDay+":"+minute;
        timeSet.setText(time);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}

//check butonuna bastığında text kısmında veri var mı yok mu kontrol edilecek
//seçilen tarihin güncel telefon saat ve tarihi ile kıyaslaması yapılacak yok ise hata mesajı verilecek.
