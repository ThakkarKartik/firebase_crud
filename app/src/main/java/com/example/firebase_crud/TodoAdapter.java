package com.example.firebase_crud;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TodoAdapter extends BaseAdapter {

    ArrayList<Todo> todoList;
    Activity context;

    public TodoAdapter(ArrayList<Todo> todoList, Activity context) {
        this.todoList = todoList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return todoList.size();
    }

    @Override
    public Object getItem(int i) {
        return todoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = context.getLayoutInflater();
        View myView = inflater.inflate(R.layout.list_item,null,true);

        TextView txtTask = myView.findViewById(R.id.txtItemTask);
        Button btnDone = myView.findViewById(R.id.btnComplete);
        if(!todoList.get(i).isComplete()) {
            txtTask.setText(todoList.get(i).getTask());
            //txtTask.setTextColor(Color.GRAY);
        }
        else {
            txtTask.setText(todoList.get(i).getTask());
            txtTask.setTextColor(Color.GRAY);
            btnDone.setText("Undone");
            txtTask.setPaintFlags(txtTask.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference Ref = FirebaseDatabase.getInstance().getReference("Todo").child(todoList.get(i).getTaskID());
                Todo T = todoList.get(i);
                if(!T.isComplete())
                    T.setComplete(true);
                else
                    T.setComplete(false);

                Ref.setValue(T);
                Intent intent = new Intent(context,ViewTask.class);
                context.startActivity(intent);
            }
        });

        myView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Todo T = todoList.get(i);
                Intent intent = new Intent(context,TaskDetails.class);
                intent.putExtra("TaskID",T.getTaskID().toString());
                context.startActivity(intent);

            }
        });

        return  myView;
    }


}
