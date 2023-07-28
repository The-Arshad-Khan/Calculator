package com.rianomusicskb.skbchat.activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rianomusicskb.skbchat.R;
import com.rianomusicskb.skbchat.modelClass.UserModel;
import com.rianomusicskb.skbchat.Adeptar.userAdepter;

import java.util.ArrayList;

public class contacts extends AppCompatActivity {
    FirebaseAuth auth;
    private RecyclerView recyclerView;
    userAdepter adepter;
    ArrayList<UserModel> usersArrayList;
    FirebaseDatabase database;
    TextView user1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        user1=findViewById(R.id.user1);
        auth=FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        usersArrayList = new ArrayList<>();

        DatabaseReference reference= database.getReference().child("user");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                usersArrayList.clear();
                for(DataSnapshot Snapshot:snapshot.getChildren()){
                    UserModel userModel=Snapshot.getValue(UserModel.class);

                    if(!userModel.getUid().equals(auth.getUid())){

                        usersArrayList.add(userModel);
                    }
                    else {
                        user1.setText(userModel.getUsername());
                    }

                }adepter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerView=findViewById(R.id.recyler_user_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adepter=new userAdepter(contacts.this,usersArrayList);
        recyclerView.setAdapter(adepter);



        if(auth.getCurrentUser()==null){
            Intent intent = new Intent(contacts.this, lr.class);
            startActivity(intent);
            finish();
        }

    }

}