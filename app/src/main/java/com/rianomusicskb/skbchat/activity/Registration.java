package com.rianomusicskb.skbchat.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rianomusicskb.skbchat.R;
import com.rianomusicskb.skbchat.modelClass.UserModel;

public class Registration extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase database;
    EditText edtemail_reg,edtpassword_reg,edtc_password_reg,edtusername_reg;
    Button register_button;
    TextView txtloginnow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        edtusername_reg=findViewById(R.id.edtusername_reg);
        edtemail_reg=findViewById(R.id.edtemail_reg);
        edtpassword_reg=findViewById(R.id.edtpassword_reg);
        edtc_password_reg=findViewById(R.id.edtc_password_reg);
        register_button=findViewById(R.id.registerbtn);
        txtloginnow=findViewById(R.id.txtloginnow);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        txtloginnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registration.this,login.class);
                startActivity(intent);
                finish();
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtusername_reg.getText().toString();
                String email = edtemail_reg.getText().toString();
                String pass = edtpassword_reg.getText().toString();
                String c_pass=edtc_password_reg.getText().toString();
                if (username.isEmpty()) {
                    edtemail_reg.setError("Required");

                }else if (email.isEmpty()) {
                    edtusername_reg.setError("Required");
                } else if (pass.isEmpty()) {
                    edtpassword_reg.setError("Enter password");
                } else if(c_pass.isEmpty()){
                    edtc_password_reg.setError("please Enter confirm password");
                }
                else if(!pass.equals(c_pass)){
                    edtc_password_reg.setError("password does'nt match");

                }else{

                    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                DatabaseReference reference = database.getReference().child("user").child(auth.getUid());
                                UserModel data = new UserModel(auth.getUid(), email, username,c_pass);
                                reference.setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            Toast.makeText(Registration.this, "Succesfully Register", Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(Registration.this, contacts.class);
                                            startActivity(intent);
                                            finish();
                                        } else {
                                            Toast.makeText(Registration.this, "Failed", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                            }
                        }
                    });


                }


            }
        });


    }
}