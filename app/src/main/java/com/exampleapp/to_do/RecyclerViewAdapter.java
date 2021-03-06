package com.exampleapp.to_do;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
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

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<TodoData> mTodoData;

    FirebaseAuth rAuth;

    public  RecyclerViewAdapter(Context mContext,List<TodoData> mTodoData){

        this.mContext=mContext;
        this.mTodoData=mTodoData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(mContext).inflate(R.layout.lst_item,parent,false);
        MyViewHolder vHolder=new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        final int itemPos=position;
        final TodoData todoDataid=mTodoData.get(position);
        holder.todo_text.setText(mTodoData.get(position).getTodoText());
        holder.todo_remainderText.setText(mTodoData.get(position).getTodoRemainingTime());
        holder.todo_icon.setImageResource(mTodoData.get(position).getIconId());
        holder.todo_specialtime.setText(mTodoData.get(position).getSpecialtime());



        holder.todo_icon.setEnabled(false);

        holder.todo_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(holder.todo_checkbox.isChecked()){
                    holder.todo_text.setAlpha(0.75f);
                    holder.todo_icon.setBackgroundResource(R.mipmap.todogarbagecan);
                    holder.todo_remainderText.setVisibility(View.INVISIBLE);
                    holder.todo_icon.setEnabled(true);
                    holder.todo_icon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            delete(itemPos);
                        }
                    });
                }else{
                    holder.todo_text.setAlpha(1);
                    holder.todo_icon.setBackgroundResource(R.mipmap.remaindericon);
                    holder.todo_remainderText.setVisibility(View.VISIBLE);
                    holder.todo_icon.setEnabled(false);
                }
            }
        });

       /* holder.todo_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               delete(itemPos);
            }
        });*/
    }
    private void delete(final int position){
        final TodoData todoDataid=mTodoData.get(position);
        rAuth= FirebaseAuth.getInstance();
        final FirebaseUser user=rAuth.getCurrentUser();
        final String userid=user.getUid().toString();

        final String todo=todoDataid.getTodoText();
        final String specialtime=todoDataid.getSpecialtime();

        final DatabaseReference newReference=FirebaseDatabase.getInstance().getReference().child(userid);
        Query query=newReference.orderByChild("Todo");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    HashMap<String, String> hashMap = (HashMap<String, String>) ds.getValue();
                    String todoText = hashMap.get("Todo");
                    String special=hashMap.get("Usersendtime");

                    if(todo==todoText && specialtime==special){


                        //burada silme işlemi
                        ds.getRef().removeValue();

                        mTodoData.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position,mTodoData.size());
                        //silme her zaman gerçekleşmiyor (if.tasksuccesfull) dene


                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    @Override
    public int getItemCount() {
        return mTodoData.size();
    }

    public  class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView todo_text;
        private TextView todo_remainderText;
        private CheckBox todo_checkbox;
        private ImageView todo_icon;
        private TextView todo_specialtime;


        public MyViewHolder(View itemView) {
            super(itemView);

            todo_text=itemView.findViewById(R.id.lstitem_todoText);
            todo_remainderText=itemView.findViewById(R.id.lstitem_todoremainderText);
            todo_checkbox=itemView.findViewById(R.id.lstitem_checkBox);
            todo_icon=itemView.findViewById(R.id.lstitem_todoIcon);
            todo_specialtime=itemView.findViewById(R.id.lstitem_specialtime);
        }
    }
}
