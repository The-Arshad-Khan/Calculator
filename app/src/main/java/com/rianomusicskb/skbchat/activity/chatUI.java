package com.rianomusicskb.skbchat.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.rianomusicskb.skbchat.Adeptar.MessageAdeptar;
import com.rianomusicskb.skbchat.R;
import com.rianomusicskb.skbchat.modelClass.Masseges;
import com.rianomusicskb.skbchat.modelClass.UserModel;

import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class chatUI extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;


    String reciever_uid, reciever_username, sender_uid;
    TextView reciver_username;
    EditText edtMassage;
    ImageButton send_btn;
    ImageView send_image;
    ImageView backbutton;
    String sender_room, reciever_room;
    RecyclerView messageAdapter;
    String imageUri;
    Uri image_uri;
    ProgressDialog progressDialog;

    ArrayList<Masseges> massegesArrayList;
    MessageAdeptar Adeptar;
//    private String checker="",myUrl="";
//    private StorageTask UploadTask;
//    private Uri fileuri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_ui);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("ruk ja bhaii");
        progressDialog.setCancelable(false);


        send_image = findViewById(R.id.send_image);
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();
        reciever_username = getIntent().getStringExtra("username");
        reciever_uid = getIntent().getStringExtra("uid");
        massegesArrayList = new ArrayList<>();
        reciver_username = findViewById(R.id.reciver_username);
        reciver_username.setText(" " + reciever_username);
        edtMassage = findViewById(R.id.edtmassege);

        backbutton = findViewById(R.id.backbutton);
        Adeptar = new MessageAdeptar(chatUI.this, massegesArrayList);
        messageAdapter = findViewById(R.id.messaegesAdepter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        messageAdapter.setLayoutManager(linearLayoutManager);
        messageAdapter.setAdapter(Adeptar);


        send_btn = findViewById(R.id.send_btn);
        sender_uid = auth.getUid();

        sender_room = sender_uid + reciever_uid;
        reciever_room = reciever_uid + sender_uid;

        DatabaseReference chatreference = database.getReference().child("chats").child(sender_room).child("massages");
        DatabaseReference imagereference = database.getReference().child("images").child(sender_room).child("image");

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    image_uri = data.getData();
                    if (image_uri != null) {
                        Toast.makeText(chatUI.this, "image select ho gyi bhai send kar de", Toast.LENGTH_SHORT).show();

                    }

                }
            }
        });
        send_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ImagePicker.Companion.with(Registration.this).crop().maxResults(10,10);
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult();
                activityResultLauncher.launch(intent);

            }
        });


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chatUI.this, contacts.class);
                startActivity(intent);
                finish();
            }
        });


        imagereference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                massegesArrayList.clear();
                for (DataSnapshot Snapshot : snapshot.getChildren()) {
                    Masseges massages = snapshot.getValue(Masseges.class);

                        massegesArrayList.add(massages);


                }
                Adeptar.notifyDataSetChanged();

                messageAdapter.smoothScrollToPosition(messageAdapter.getAdapter().getItemCount());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        chatreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                massegesArrayList.clear();

                for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                    Masseges massages = datasnapshot.getValue(Masseges.class);
                    massegesArrayList.add(massages);
                }
                Adeptar.notifyDataSetChanged();
                messageAdapter.smoothScrollToPosition(messageAdapter.getAdapter().getItemCount());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.show();
                StorageReference storageReference = storage.getReference().child("upload1").child(auth.getUid());
                DatabaseReference imageref = database.getReference().child("images").child(sender_room).child("image");

                if (image_uri != null) {
                    storageReference.putFile(image_uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                            if (task.isSuccessful()) {
                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        String massage = edtMassage.getText().toString();
                                        Date date = new Date();
                                        imageUri = uri.toString();
                                        Masseges massages = new Masseges(imageUri, sender_uid, date.getTime());
                                        imageref.setValue(massages).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    image_uri = null;
                                                    progressDialog.dismiss();

                                                    Toast.makeText(chatUI.this, "sahi hai", Toast.LENGTH_SHORT).show();
                                                } else {
                                                    progressDialog.dismiss();

                                                    Toast.makeText(chatUI.this, "Failed", Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });

                                    }
                                });

                            } else {
                                Toast.makeText(chatUI.this, "kuch bekar", Toast.LENGTH_SHORT).show();
                                image_uri = null;
                            }

                        }
                    });
                } else {
                    progressDialog.dismiss();

                    String massage = edtMassage.getText().toString();
                    if (massage.isEmpty()) {
                        progressDialog.dismiss();
                        return;
                    }
                    edtMassage.setText("");
                    Date date = new Date();
                    Masseges massages = new Masseges(massage, sender_uid, date.getTime());
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
                                                    progressDialog.dismiss();

                                                }
                                            });
                                }
                            });

                }

            }
        });
    }


}









