package com.example.firebase_crud;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ViewTask extends AppCompatActivity {
    FirebaseAuth mAuth;
    Button btnAddNewTask;
    FirebaseDatabase db;
    DatabaseReference TaskRef;
    ArrayList<Todo> todoList;
    ListView MyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        TaskRef = db.getReference("Todo");
        MyListView = findViewById(R.id.listTodo);

        todoList = new ArrayList();

        if(mAuth.getCurrentUser()!=null){

            TaskRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    todoList.add(snapshot.getValue(Todo.class));
                    //Log.e("TodoListData","Size is :"+ todoList.size());

                    TodoAdapter adapter = new TodoAdapter(todoList, ViewTask.this);
                    MyListView.setAdapter(adapter);


                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot snapshot) {


                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

            btnAddNewTask = findViewById(R.id.btnAddNewTask);
            btnAddNewTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ViewTask.this,addTask.class);
                    startActivity(intent);
                }
            });


        }
    }
}