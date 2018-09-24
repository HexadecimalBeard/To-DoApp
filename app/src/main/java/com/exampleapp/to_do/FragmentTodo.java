package com.exampleapp.to_do;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentTodo extends Fragment {

    View v;

    private RecyclerView mrecyclerview;
    private List<TodoData> lstTodo=new ArrayList<>();

    public HashMap<String,List<String>> listHashMap;

    FirebaseAuth mAuth,rAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference,removeDatabaseReference;
    RecyclerViewAdapter recyclerViewAdapter;



    public FragmentTodo() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.todo_fragment,container,false);
        mrecyclerview=(RecyclerView)v.findViewById(R.id.todofragment_recyclerview);
        recyclerViewAdapter=new RecyclerViewAdapter(getContext(),lstTodo);
        mrecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mrecyclerview.setAdapter(recyclerViewAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        removeDatabaseReference=firebaseDatabase.getReference();

        getData();


    }

    public void getData(){

        listHashMap =new HashMap<>();
        FirebaseUser user=mAuth.getCurrentUser();
        String userId=user.getUid().toString();

        DatabaseReference newReference=firebaseDatabase.getReference(userId);
        if(newReference!=null) {

            Query query = newReference.orderByChild("Usersendtime");

            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    lstTodo.clear();

                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        HashMap<String, String> hashMap = (HashMap<String, String>) ds.getValue();
                        String todo = hashMap.get("Todo");
                        String todoRemainder = hashMap.get("Time");
                        String specialtime=hashMap.get("Usersendtime");

                        if (todo != null) {

                            lstTodo.add(new TodoData(todo, todoRemainder,specialtime));

                        }

                        recyclerViewAdapter.notifyDataSetChanged();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                    Toast.makeText(getContext(), databaseError.getMessage().toString(), Toast.LENGTH_LONG).show();
                }
            });

        }else{

            lstTodo.clear();

        }
    }


}
