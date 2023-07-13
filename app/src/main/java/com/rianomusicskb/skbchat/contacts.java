package com.rianomusicskb.skbchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class contacts extends AppCompatActivity {
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        auth=FirebaseAuth.getInstance();

        if(auth.getCurrentUser()==null){
            Intent intent = new Intent(contacts.this, lr.class);
            startActivity(intent);
            finish();
        }
    }
}