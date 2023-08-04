package com.rianomusicskb.skbchat.activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
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
    ImageView img_logout;
    EditText searchView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        user1=findViewById(R.id.user1);
        auth=FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance();
        usersArrayList = new ArrayList<>();

        searchView=findViewById(R.id.search_view);
        img_logout=findViewById(R.id.img_logout);

        img_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog=new Dialog(contacts.this,R.style.Dialog);
                dialog.setContentView(R.layout.dialog_logout_layout);
                TextView nobtn,yesbtn;
                nobtn=dialog.findViewById(R.id.no);
                yesbtn=dialog.findViewById(R.id.yes);
                yesbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseAuth .getInstance().signOut();
                        startActivity(new Intent(contacts.this,login.class));
                        finish();
                    }
                });
                nobtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });



        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchUsers(s.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        DatabaseReference reference= database.getReference().child("user");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (searchView.getText().toString().equals("")) {
                    usersArrayList.clear();
                    for (DataSnapshot Snapshot : snapshot.getChildren()) {
                        UserModel userModel = Snapshot.getValue(UserModel.class);

                        if (!userModel.getUid().equals(auth.getUid())) {
                            usersArrayList.add(userModel);

                        } else {
                            user1.setText(userModel.getUsername());
                        }

                    }
                    adepter.notifyDataSetChanged();


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerView=findViewById(R.id.recyler_user_item);
        recyclerView.setLayoutManager(new LinearLayoutManager(contacts.this));
        adepter=new userAdepter(contacts.this,usersArrayList);
        recyclerView.setAdapter(adepter);

        if(auth.getCurrentUser()==null){
            Intent intent = new Intent(contacts.this, lr.class);
            startActivity(intent);
            finish();
        }

    }

    private void searchUsers(String s) {
        Query query = FirebaseDatabase.getInstance().getReference("user").orderByChild("search")
                .startAt(s)
                .endAt(s + "\uf8ff");
        query.addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot Snapshot : snapshot.getChildren()) {
                        usersArrayList.clear();

                        UserModel userModel = Snapshot.getValue(UserModel.class);
                        usersArrayList.add(userModel);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        if (searchView.getText().toString().equals("")){
            DatabaseReference reference= database.getReference().child("user");
            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (searchView.getText().toString().equals("")) {
                        usersArrayList.clear();
                        for (DataSnapshot Snapshot : snapshot.getChildren()) {
                            UserModel userModel = Snapshot.getValue(UserModel.class);

                            if (!userModel.getUid().equals(auth.getUid())) {
                                usersArrayList.add(userModel);

                            } else {
                                user1.setText(userModel.getUsername());
                            }

                        }
                        adepter.notifyDataSetChanged();


                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            recyclerView=findViewById(R.id.recyler_user_item);
            recyclerView.setLayoutManager(new LinearLayoutManager(contacts.this));
            adepter=new userAdepter(contacts.this,usersArrayList);
            recyclerView.setAdapter(adepter);

        }else
        {
            adepter=new userAdepter(contacts.this,usersArrayList);
            recyclerView.setAdapter(adepter);
        }

    }

}