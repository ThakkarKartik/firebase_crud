package com.example.firebase_crud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
//        Intent i = new Intent(this, RegisterUser.class);
//        startActivity(i);

        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser user = mAuth.getCurrentUser();
                if(user!=null){


                    Intent i = new Intent(getApplicationContext(), ViewTask.class);
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(getApplicationContext(), RegisterUser.class);
                    startActivity(i);
                }

            }
        });


    }
}