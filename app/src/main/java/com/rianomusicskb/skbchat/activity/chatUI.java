package com.rianomusicskb.skbchat.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rianomusicskb.skbchat.Adeptar.MessageAdeptar;
import com.rianomusicskb.skbchat.R;
import com.rianomusicskb.skbchat.modelClass.Masseges;

import java.util.ArrayList;
import java.util.Date;

public class chatUI extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase database;
    String  reciever_uid,reciever_username,sender_uid;
    TextView reciver_username;
    EditText edtMassage;
    ImageButton send_btn;

    ImageView backbutton;
    String sender_room,reciever_room;
    RecyclerView messageAdapter;

    ArrayList<Masseges> massegesArrayList;
    MessageAdeptar Adeptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_ui);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        reciever_username=getIntent().getStringExtra("username");
        reciever_uid=getIntent().getStringExtra("uid");
        massegesArrayList =new ArrayList<>();
        reciver_username=findViewById(R.id.reciver_username);
        reciver_username.setText(" "+reciever_username);
        edtMassage=findViewById(R.id.edtmassege);
        messageAdapter =findViewById(R.id.messaegesAdepter);
        backbutton=findViewById(R.id.backbutton);
        Adeptar =new MessageAdeptar(chatUI.this, massegesArrayList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);

        messageAdapter.setLayoutManager(linearLayoutManager);
        messageAdapter.setAdapter(Adeptar);
//        messageAdapter.setLayoutManager(linearLayoutManager);




        send_btn=findViewById(R.id.send_btn);
        sender_uid=auth.getUid();

        sender_room=sender_uid+reciever_uid;
        reciever_room=reciever_uid+sender_uid;


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(chatUI.this,contacts.class);
                startActivity(intent);
                finish();
            }
        });

        DatabaseReference chatreference=database.getReference().child("chats").child(sender_room).child("massages");

        chatreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                massegesArrayList.clear();

                for(DataSnapshot datasnapshot:snapshot.getChildren()){
                    Masseges massages=datasnapshot.getValue(Masseges.class);
                    massegesArrayList.add(massages);
                }
                Adeptar.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        DatabaseReference reference=database.getReference().child("user").child(auth.getUid());
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                snapshot.child()
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String massage=edtMassage.getText().toString();
                if(massage.isEmpty()){
                    return;
                }
                edtMassage.setText("");
                Date date=new Date();
               Masseges massages=new Masseges(massage,sender_uid,date.getTime());
                database.getReference().child("chats")
                        .child(sender_room)
                        .child("massages")
                        .push()
                        .setValue(massages)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                database.getReference().child("chats")
                                        .child(reciever_room)
                                        .child("massages")
                                        .push().setValue(massages).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                            }
                                        });
                            }
                        });

            }
        });
    }

}









