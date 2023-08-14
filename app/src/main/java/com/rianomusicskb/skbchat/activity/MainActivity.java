package com.rianomusicskb.skbchat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.rianomusicskb.skbchat.R;
import com.rianomusicskb.skbchat.modelClass.db_model;

public class MainActivity extends AppCompatActivity {
    String pass1;
    db_model db_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                db_model=new db_model(getApplicationContext());
                pass1=getIntent().getStringExtra("KEY_EMAIL");

                boolean loggedin=db_model.login_user(pass1);

                if (loggedin==false){
                    Intent i = new Intent(MainActivity.this, passcode_activity.class);
                    startActivity(i);
                    finish();
                }
                else{

                    Intent intent =new Intent(MainActivity.this,login.calci_Activity.class);
                    startActivity(intent);
                    finish();
//
                }

            }
        }, 900);
    }

}