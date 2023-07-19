package com.rianomusicskb.skbchat;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class chatUI extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase database;
    String  reciever_uid,reciever_username,sender_uid;
    TextView reciver_username;
    EditText edtMassage;
    ImageButton send_btn;
    String sender_room,reciever_room;

    RecyclerView msg_adapter;

    ArrayList<massageAdeptar> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_ui);

        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        reciever_username=getIntent().getStringExtra("username");
        reciever_uid=getIntent().getStringExtra("uid");
        arrayList=new ArrayList<>();
        reciver_username=findViewById(R.id.reciver_username);
        reciver_username.setText(""+reciever_username);
        edtMassage=findViewById(R.id.edtmassege);
        msg_adapter=findViewById(R.id.msg_adepter);
        send_btn=findViewById(R.id.send_btn);
        sender_uid=auth.getUid();

        sender_room=sender_uid+reciever_uid;
        reciever_room=reciever_uid+sender_uid;

        DatabaseReference chatreference=database.getReference().child("chats").child(sender_room).child("massages");

        chatreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot datasnapshot:snapshot.getChildren()){
                    massageAdeptar massageAdeptar=datasnapshot.getValue(massageAdeptar.class);
                    arrayList.add(massageAdeptar);
                }
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
                massageAdeptar massageAdeptar=new massageAdeptar(massage,sender_uid,date.getTime());
                database.getReference().child("chats")
                        .child(sender_room)
                        .child("massages")
                        .push()
                        .setValue(massageAdeptar)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        database.getReference().child("chats")
                                .child(reciever_room)
                                .child("massages")
                                .push().setValue(massageAdeptar).addOnCompleteListener(new OnCompleteListener<Void>() {
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