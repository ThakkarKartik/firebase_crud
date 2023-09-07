package com.example.firebase_crud;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Ref;

public class TaskDetails extends AppCompatActivity {

    EditText txtTask;
    FirebaseAuth mAuth;
    Todo T;
    Button btnUpdate, btnDelete;
    FirebaseDatabase db;
    DatabaseReference RefTask, Ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_details);

        txtTask = findViewById(R.id.txtUpdateTask);
        mAuth = FirebaseAuth.getInstance();
        btnUpdate = findViewById(R.id.btnUpdateTask);
        btnDelete = findViewById(R.id.btnDeleteTask);
        db = FirebaseDatabase.getInstance();
        RefTask  =db.getReference("Todo");
        String taskID = getIntent().getStringExtra("TaskID");
        Ref = RefTask.child(taskID);
        Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                T = snapshot.getValue(Todo.class);
                Log.e("TodoData",T.getTask());
                txtTask.setText(T.getTask());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                T.setTask(txtTask.getText().toString());
                Ref.setValue(T);
                Intent intent = new Intent(TaskDetails.this,ViewTask.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Ref.removeValue();
                        Intent intent = new Intent(TaskDetails.this,ViewTask.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

    }
}