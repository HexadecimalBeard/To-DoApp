package com.exampleapp.to_do;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class TodoMainPage extends AppCompatActivity {

    FloatingActionButton fab_plus,fab_calendar,fab_makenote,fab_todo;
    Animation FabOpen,FabClose,FabRClockwise,FabRantiClockwise;
    TextView todoFabText,noteFabText,calendarFabText, todomainpage_textviewexpend;
    boolean isOpen=false;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_main_page);

        Toolbar myToolbar=(Toolbar)findViewById(R.id.todomainpage_toolbar);
        myToolbar.setElevation(0);
        setSupportActionBar(myToolbar);


        tabLayout=findViewById(R.id.activitytodo_tabLayout);
        viewPager=findViewById(R.id.activitytodo_viewPager);
        adapter=new ViewPagerAdapter(getSupportFragmentManager());


        adapter.AddFragment(new FragmentTodo(),"Todo");
        adapter.AddFragment(new FragmentNotes(),"Notes");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference();

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
/*

        todomainpage_textviewexpend = findViewById(R.id.lstgroup_Item);

        todomainpage_textviewexpend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isTextViewClicked){

                    todomainpage_textviewexpend.setMaxLines(1);
                    isTextViewClicked = false;

                }else {

                    todomainpage_textviewexpend.setMaxLines(Integer.MAX_VALUE);
                    isTextViewClicked = true;

                }
            }
        });
*/
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

        Intent intent=new Intent(getApplicationContext(),CreateTodoActivity.class);
        startActivity(intent);
    }

    public void noteLogin(View view){

        Intent intent=new Intent(getApplicationContext(),CreateNotesActivity.class);
        startActivity(intent);
        //note yazmaya giriş
    }

    public void calendarLogin(View view){

        //calendar sayfasına giriş

        Intent intent=new Intent(getApplicationContext(),CreateTodoActivity.class);
        startActivity(intent);

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
        }else if(item.getItemId()==R.id.logout){

            mAuth.signOut();
            Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }



}
