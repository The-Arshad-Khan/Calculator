package com.rianomusicskb.skbchat.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
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
    RecyclerView recyclerView;
    userAdepter adepter;
    ArrayList<UserModel> usersArrayList;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        auth=FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        usersArrayList = new ArrayList<>();




        DatabaseReference reference= database.getReference().child("user");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot:snapshot.getChildren()){

                    UserModel userModel=dataSnapshot.getValue(UserModel.class);
                    usersArrayList.add(userModel);
                }
                adepter.notifyDataSetChanged();

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