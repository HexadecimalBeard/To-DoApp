package com.exampleapp.to_do;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;

public class TodoMainPage extends AppCompatActivity {

    FloatingActionButton fab_plus,fab_calendar,fab_makenote,fab_todo;
    Animation FabOpen,FabClose,FabRClockwise,FabRantiClockwise;
    TextView todoFabText,noteFabText,calendarFabText;
    boolean isOpen=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_main_page);

        Toolbar myToolbar=(Toolbar)findViewById(R.id.todomainpage_toolbar);
        setSupportActionBar(myToolbar);

        fab_plus=findViewById(R.id.fab_plus);
        fab_calendar=findViewById(R.id.fab_calendar);
        fab_makenote=findViewById(R.id.fab_makenote);
        fab_todo=findViewById(R.id.fab_todo);
        todoFabText=findViewById(R.id.todoFabTextview);
        noteFabText=findViewById(R.id.noteFabTextview);
        calendarFabText=findViewById(R.id.calendarFabTextview);
        FabOpen= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        FabClose=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        FabRClockwise=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_clockwise);
        FabRantiClockwise=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_anticlockwise);
    }



    public void fabOnClick(View view){

        if(isOpen){

            fab_calendar.startAnimation(FabClose);
            fab_todo.startAnimation(FabClose);
            fab_makenote.startAnimation(FabClose);
            calendarFabText.startAnimation(FabClose);
            todoFabText.startAnimation(FabClose);
            noteFabText.startAnimation(FabClose);
            fab_plus.startAnimation(FabRantiClockwise);
            fab_calendar.setClickable(false);
            fab_todo.setClickable(false);
            fab_makenote.setClickable(false);
            isOpen=false;


        }else{

            fab_calendar.startAnimation(FabOpen);
            fab_todo.startAnimation(FabOpen);
            fab_makenote.startAnimation(FabOpen);
            calendarFabText.startAnimation(FabOpen);
            todoFabText.startAnimation(FabOpen);
            noteFabText.startAnimation(FabOpen);
            fab_plus.startAnimation(FabRClockwise);
            fab_calendar.setClickable(true);
            fab_todo.setClickable(true);
            fab_makenote.setClickable(true);
            isOpen=true;

        }
    }

    public void todoLogin(View view){


        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    public void noteLogin(View view){

        //note yazmaya giriş
    }

    public void calendarLogin(View view){

        //calendar sayfasına giriş


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.todomainpage_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId()==R.id.nightmode){

            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
