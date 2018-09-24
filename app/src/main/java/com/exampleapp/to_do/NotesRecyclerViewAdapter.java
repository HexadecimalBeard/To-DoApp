package com.exampleapp.to_do;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

public class NotesRecyclerViewAdapter extends RecyclerView.Adapter<NotesRecyclerViewAdapter.MyViewHolderNote>{

    Context mContext;
    List<NoteData> mNoteData;

    FirebaseAuth rAuth;

    public NotesRecyclerViewAdapter(Context mContext, List<NoteData> mNoteData) {
        this.mContext = mContext;
        this.mNoteData = mNoteData;
    }

    @NonNull
    @Override
    public MyViewHolderNote onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.lstnote_item,parent,false);
        MyViewHolderNote vHolderNote=new MyViewHolderNote(v);

        return vHolderNote;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderNote holder, int position) {
        final int itempos=position;
        holder.titleText.setText(mNoteData.get(position).getTitleText());
        holder.noteText.setText(mNoteData.get(position).getNoteText());
        holder.timeText.setText(mNoteData.get(position).getTimeText());
        holder.removeNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                // Not silme islemi...
                //
                delete(itempos);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNoteData.size();
    }

    private void delete(final int position){
        final NoteData noteDataid=mNoteData.get(position);
        rAuth=FirebaseAuth.getInstance();
        final FirebaseUser user=rAuth.getCurrentUser();
        final String userid=user.getUid().toString();

        final String note=noteDataid.getNoteText();
        final String specialtime=noteDataid.getTimeText();

        final DatabaseReference newReference= FirebaseDatabase.getInstance().getReference().child(userid);
        Query query=newReference.orderByChild("Note");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    HashMap<String,String> hashMap=(HashMap<String, String>)ds.getValue();
                    String noteText=hashMap.get("Note");
                    String specialtimeText=hashMap.get("Usersendtime");

                    if(note==noteText && specialtime==specialtimeText){

                        ds.getRef().removeValue();

                        mNoteData.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position,mNoteData.size());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public static class MyViewHolderNote extends RecyclerView.ViewHolder{

        private TextView titleText;
        private TextView noteText;
        private ImageButton removeNoteButton;
        private TextView timeText;

        public MyViewHolderNote(View itemView) {
            super(itemView);

            titleText=itemView.findViewById(R.id.lstnoteitem_titleText);
            noteText=itemView.findViewById(R.id.lstnoteitem_noteText);
            removeNoteButton=itemView.findViewById(R.id.lstnote_removebutton);
            timeText=itemView.findViewById(R.id.lstitemnote_specialtime);

        }
    }
}
