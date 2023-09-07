package com.example.firebase_crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class addTask extends AppCompatActivity {

    EditText txtTask;
    Button btnAddTask;
    FirebaseDatabase db;
    FirebaseAuth mAuth;
    DatabaseReference TaskRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        txtTask = findViewById(R.id.txtTask);
        btnAddTask = findViewById(R.id.btnAddTask);
        db = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        TaskRef = db.getReference("Todo");

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String TaskID = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmss")).toString();
                String Task = txtTask.getText().toString();
                String UserID = mAuth.getCurrentUser().getUid();
                boolean isCompleted = false;
                Todo T = new Todo(TaskID,Task,isCompleted,UserID);

                TaskRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        TaskRef.child(TaskID).setValue(T);
                        Toast.makeText(getApplicationContext(), "Item Added", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(addTask.this,ViewTask.class);
                        startActivity(i);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(addTask.this, "Failed ..", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


    }
}