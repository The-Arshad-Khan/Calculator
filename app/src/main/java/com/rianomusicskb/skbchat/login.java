package com.rianomusicskb.skbchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.ktx.Firebase;

public class login extends AppCompatActivity {
//    FirebaseAuth Auth;
    EditText edtemail,edtPassword;
    Button loginbutton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtemail=findViewById(R.id.edtemail);
        edtPassword=findViewById(R.id.edtPassword);
        loginbutton = findViewById(R.id.loginbutton);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = edtemail.getText().toString();
                String Password = edtPassword.getText().toString();
                if(Email.isEmpty()){
                    edtemail.setError("Bhai kya kr rha hai tu?");
                } else if (Password.isEmpty()) {
                    edtPassword.setError("Passowrd to daal bhai!!");
                }
              //  Auth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                       if(task.isSuccessful()){
//
//                       }
//                    }
//                });
            }
        });

    }
}