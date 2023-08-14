package com.rianomusicskb.skbchat.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.rianomusicskb.skbchat.R;
import com.rianomusicskb.skbchat.modelClass.db_model;

public class passcode_activity extends AppCompatActivity {

    EditText pass;
    db_model db_model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passcode);
        pass = findViewById(R.id.pass);
        db_model = new db_model(getApplicationContext());

    }

    public void pass_backbtn(View view){
        String value = pass.getText().toString();
        int len = value.length();
        if (len > 0) {
            pass.setText(value.substring(0, len - 1));
        }
    }

    public void register(View view) {

        String pass1 = pass.getText().toString().trim();
        if(pass1.isEmpty()){
            Toast.makeText(this, "please enter password", Toast.LENGTH_SHORT).show();
        }
        else if (pass1.length()<6) {
            pass.setError("enter above 6 digit password");
        } else{
            boolean l = db_model.register_user(pass1);
            if (l) {

                Toast.makeText(this, "Set password", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this ,login.calci_Activity.class);
                intent.putExtra("KEY_EMAIL",(CharSequence) pass1);
                startActivity(intent);
                finish();
                pass.setText("");
            } else {
                Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show();
            }
        }


    }
}