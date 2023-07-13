package com.rianomusicskb.skbchat;

import static android.text.TextUtils.isEmpty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class login extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseDatabase database;
    EditText edtemail,edtpassword,edtusername;
    Button loginbutton;
    TextView txtregiternow;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtemail=findViewById(R.id.edtemail);
        edtusername=findViewById(R.id.edtemail);
        edtpassword=findViewById(R.id.edtpasswordlogin);
        loginbutton = findViewById(R.id.loginbtn);
        txtregiternow=findViewById(R.id.txtregisternow);
        auth=FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();

        if(auth.getCurrentUser()!=null){
            Intent intent = new Intent(login.this, contacts.class);
            startActivity(intent);
            finish();
        }

        txtregiternow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(login.this,Registration.class);
                startActivity(intent);
            }
        });


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edtusername.getText().toString();
                String password=edtpassword.getText().toString();
                String email=edtemail.getText().toString();
                if(isEmpty(username)){
                    edtusername.setError("Enter the Email or Username");
                    Toast.makeText(login.this, "Please Enter Email or Username", Toast.LENGTH_SHORT).show();

                }
                else if (email.isEmpty()) {
                    edtemail.setError("Enter the Email or Username");
                    Toast.makeText(login.this, "Please Enter Email or Username", Toast.LENGTH_SHORT).show();

                }else if(isEmpty(password)){
                    edtpassword.setError("Enter the password");
                    Toast.makeText(login.this, "Please Enter password", Toast.LENGTH_SHORT).show();

                }else {

                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(login.this, "Succesfully login", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(login.this, contacts.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(login.this, "login failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }

            }
        });

    }
}