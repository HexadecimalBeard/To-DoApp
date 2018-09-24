package com.exampleapp.to_do;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;

public class TodoData  {


    private String todoText;
    private String todoRemainingTime;
    //private int checkboxId;
    private int iconId;
    private String specialtime;

    public HashMap<String,List<String>> listHash;

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public TodoData(){

    }

    public TodoData(String todoText, String todoRemainingTime, int iconId,String specialtime) {
        this.todoText = todoText;
        this.todoRemainingTime = todoRemainingTime;
        //this.checkboxId = checkboxId;
        this.iconId = iconId;
        this.specialtime=specialtime;
    }

    public String getSpecialtime() {
        return specialtime;
    }

    public void setSpecialtime(String specialtime) {
        this.specialtime = specialtime;
    }

    public String getTodoText() {
        return todoText;
    }

    public void setTodoText(String todoText) {
        this.todoText = todoText;
    }

    public String getTodoRemainingTime() {
        return todoRemainingTime;
    }

    public void setTodoRemainingTime(String todoRemainingTime) {
        this.todoRemainingTime = todoRemainingTime;
    }


    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

}
