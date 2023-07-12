package com.rianomusicskb.skbchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomeScreen extends AppCompatActivity {
FirebaseAuth auth;

    Button logoutbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);
        auth = FirebaseAuth.getInstance();
        logoutbtn=findViewById(R.id.logout);

        if(auth.getCurrentUser()==null){
            startActivity(new Intent(HomeScreen.this, HomeLoginActivity.class));
            finish();
        }

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                startActivity(new Intent(HomeScreen.this, HomeLoginActivity.class));
                finish();
            }
        });
    }
}