package com.rianomusicskb.skbchat;

import static android.text.TextUtils.isEmpty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeLoginActivity extends AppCompatActivity {
    FirebaseAuth auth;
    EditText edtusername,edtpassword,edtemail;
    Button loginbtn,registertbn;
    FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homelogin);
        edtusername=findViewById(R.id.edtusername);
        edtpassword=findViewById(R.id.edtpassword);
        edtemail=findViewById(R.id.edtemail);
        loginbtn=findViewById(R.id.loginbtn);
        registertbn=findViewById(R.id.registerbtn);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        if(auth.getCurrentUser()!=null){
            Intent intent = new Intent(HomeLoginActivity.this, HomeScreen.class);
          startActivity(intent);
        finish();
        }


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=edtusername.getText().toString();
                String password=edtpassword.getText().toString();
                String email=edtemail.getText().toString();


                if(isEmpty(username)){
                    edtusername.setError("Enter the Email");
                    Toast.makeText(HomeLoginActivity.this, "Enter username", Toast.LENGTH_SHORT).show();

                }
                else if (email.isEmpty()) {
                    edtemail.setError("email dalo bhai");
                }else if(isEmpty(password)){
                    edtpassword.setError("Enter the password");
                    Toast.makeText(HomeLoginActivity.this, "Please Enter password", Toast.LENGTH_SHORT).show();

                }else{
                    auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                    Toast.makeText(HomeLoginActivity.this, "Succesfully login", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(HomeLoginActivity.this, HomeScreen.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(HomeLoginActivity.this, "login failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }


            }
        });


        registertbn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtemail.getText().toString();
                String pass = edtpassword.getText().toString();
                String username = edtusername.getText().toString();

                if (email.isEmpty()) {
                    edtemail.setError("email dalo bhai");

                } else if (pass.isEmpty()) {
                    edtpassword.setError("password dalo bhai");
                } else if (username.isEmpty()) {
                    edtusername.setError("username dalo bhai");
                } else {

                auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            DatabaseReference reference = database.getReference().child("user").child(auth.getUid());
                            UserModel data = new UserModel(auth.getUid(), email, username);
                            reference.setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()) {
                                        Toast.makeText(HomeLoginActivity.this, "Succesfully Register", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(HomeLoginActivity.this, HomeScreen.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(HomeLoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });
                        }
                    }
                });
            }
            }
            });

//        if(auth.getCurrentUser()==null){
//
//

//        }

    }
}