package com.rianomusicskb.skbchat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class lr extends AppCompatActivity {

    EditText lr_login,lr_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lr);

        lr_login.findViewById(R.id.lr_login);
        lr_reg.findViewById(R.id.lr_reg);

        lr_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(lr.this,login.class));
            }
        });

        lr_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(lr.this,Registration.class));
            }
        });

    }
}