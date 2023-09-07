package com.example.firebase_crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginUser extends AppCompatActivity {

    EditText txtEmail, txtPassword;
    Button btnSignin;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        txtEmail = findViewById(R.id.txtloginEmail);
        txtPassword = findViewById(R.id.txtLoginPassword);

        btnSignin = findViewById(R.id.btnSignin);
        mAuth = FirebaseAuth.getInstance();

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signInWithEmailAndPassword(txtEmail.getText().toString(),txtPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"Login Successful", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(getApplicationContext(),ViewTask.class);
                                    startActivity(i);
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"UserName or Password is Incorrect", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
    }
}