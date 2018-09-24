package com.exampleapp.to_do;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class NotesRecyclerViewAdapter extends RecyclerView.Adapter<NotesRecyclerViewAdapter.MyViewHolderNote>{

    Context mContext;
    List<NoteData> mNoteData;

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

        holder.titleText.setText(mNoteData.get(position).getTitleText());
        holder.noteText.setText(mNoteData.get(position).getNoteText());
        holder.removeNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
                // Not silme islemi...
                //
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNoteData.size();
    }

    public static class MyViewHolderNote extends RecyclerView.ViewHolder{

        private TextView titleText;
        private TextView noteText;
        private ImageButton removeNoteButton;

        public MyViewHolderNote(View itemView) {
            super(itemView);

            titleText=itemView.findViewById(R.id.lstnoteitem_titleText);
            noteText=itemView.findViewById(R.id.lstnoteitem_noteText);
            removeNoteButton=itemView.findViewById(R.id.lstnote_removebutton);

        }
    }
}
