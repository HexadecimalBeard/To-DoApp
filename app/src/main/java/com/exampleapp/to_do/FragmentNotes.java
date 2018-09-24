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

public class FragmentNotes extends Fragment {

    View v;
    private RecyclerView mrecyclerviewnote;
    private List<NoteData> lstNote=new ArrayList<>();

    public HashMap<String,List<String>> listHashMap;

    FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    NotesRecyclerViewAdapter notesRecyclerViewAdapter;





    public FragmentNotes() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        v=inflater.inflate(R.layout.notes_fragment,container,false);
        mrecyclerviewnote=v.findViewById(R.id.notefragment_recyclerview);
        notesRecyclerViewAdapter=new NotesRecyclerViewAdapter(getContext(),lstNote);
        mrecyclerviewnote.setLayoutManager(new LinearLayoutManager(getActivity()));
        mrecyclerviewnote.setAdapter(notesRecyclerViewAdapter);


        return v;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();

        getData();
    }

    public void getData(){

        listHashMap =new HashMap<>();
        final List<String> noteList=new ArrayList<>();

        FirebaseUser user=mAuth.getCurrentUser();
        String userId=user.getUid().toString();

        DatabaseReference newReference=firebaseDatabase.getReference(userId);

        Query query=newReference.orderByChild("Usersendtime");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                lstNote.clear();

                for (DataSnapshot ds: dataSnapshot.getChildren()){

                    HashMap<String,String> hashMap=(HashMap<String ,String>)ds.getValue();
                    String title=hashMap.get("NoteTitle");
                    String noteText=hashMap.get("Note");
                    String timeText=hashMap.get("Usersendtime");

                    if (noteText!=null){

                        lstNote.add(new NoteData(title,noteText,timeText));
                    }

                    notesRecyclerViewAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getContext(),databaseError.getMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });


    }

}
