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

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    Context mContext;
    List<TodoData> mTodoData;

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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.todo_text.setText(mTodoData.get(position).getTodoText());
        holder.todo_remainderText.setText(mTodoData.get(position).getTodoRemainingTime());
        holder.todo_icon.setImageResource(mTodoData.get(position).getIconId());//her satırda aynı işlem olacağı için bunların pozisyonuna gerekduyacakmıyız.



    }

    @Override
    public int getItemCount() {
        return mTodoData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView todo_text;
        private TextView todo_remainderText;
        private CheckBox todo_checkbox;
        private ImageView todo_icon;

        public MyViewHolder(View itemView) {
            super(itemView);

            todo_text=itemView.findViewById(R.id.lstitem_todoText);
            todo_remainderText=itemView.findViewById(R.id.lstitem_todoremainderText);
            todo_checkbox=itemView.findViewById(R.id.lstitem_checkBox);
            todo_icon=itemView.findViewById(R.id.lstitem_todoIcon);
        }
    }
}
