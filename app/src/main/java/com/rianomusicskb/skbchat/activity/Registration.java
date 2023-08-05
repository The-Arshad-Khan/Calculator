package com.rianomusicskb.skbchat.activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rianomusicskb.skbchat.R;
import com.rianomusicskb.skbchat.modelClass.UserModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;

public class Registration extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase database;
    FirebaseStorage storage;
    EditText edtemail_reg,edtpassword_reg,edtc_password_reg,edtusername_reg;
    Button register_button;
    TextView txtloginnow;
    ImageButton backbtn_reg;
    CircleImageView profile_image;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Uri image_uri;
    String  imageUri;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("ruk ja bhaii");
        progressDialog.setCancelable(false);
        edtusername_reg=findViewById(R.id.edtusername_reg);
        edtemail_reg=findViewById(R.id.edtemail_reg);
        edtpassword_reg=findViewById(R.id.edtpassword_reg);
        edtc_password_reg=findViewById(R.id.edtc_password_reg);
        register_button=findViewById(R.id.registerbtn);
        txtloginnow=findViewById(R.id.txtloginnow);
        profile_image=findViewById(R.id.profile_image);
        backbtn_reg=findViewById(R.id.backbtn_reg);
        auth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();


        backbtn_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Registration.this,lr.class);
                startActivity(intent);
                finish();
            }
        });

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
                progressDialog.show();
                String username = edtusername_reg.getText().toString();
                String email = edtemail_reg.getText().toString();
                String pass = edtpassword_reg.getText().toString();
                String c_pass=edtc_password_reg.getText().toString();

                if (username.isEmpty()) {
                    edtusername_reg.setError("Required");
                    progressDialog.dismiss();

                }else if (email.isEmpty()) {
                    edtemail_reg.setError("Required");
                    progressDialog.dismiss();


                } else if(!email.matches(emailPattern)){
                    edtemail_reg.setError("Invalid email");
                    progressDialog.dismiss();

                    Toast.makeText(Registration.this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();

                }
                else if (pass.isEmpty()) {
                    edtpassword_reg.setError("Enter password");
                    progressDialog.dismiss();

                }else if(!isValidPassword(pass.trim())){
                    edtpassword_reg.setError("Week Password");
                    progressDialog.dismiss();

                    Toast.makeText(Registration.this, "Please Enter Valid Password", Toast.LENGTH_SHORT).show();

                } else if(c_pass.isEmpty()){
                    progressDialog.dismiss();

                    edtc_password_reg.setError("please Enter confirm password");
                }
                else if(!pass.equals(c_pass)){
                    progressDialog.dismiss();

                    edtc_password_reg.setError("password does'nt match");

                }else{

                    auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                progressDialog.dismiss();

                                DatabaseReference reference = database.getReference().child("user").child(auth.getUid());
                                StorageReference storageReference= storage.getReference().child("upload").child(auth.getUid());

                                if(image_uri!=null){
                                    storageReference.putFile(image_uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                                            if(task.isSuccessful()){
                                                progressDialog.dismiss();

                                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(Uri uri) {
                                                        imageUri=uri.toString();
                                                        UserModel data = new UserModel(auth.getUid(), email, username,c_pass,imageUri,username.toLowerCase());
                                                        reference.setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<Void> task) {

                                                                if (task.isSuccessful()) {
                                                                    progressDialog.dismiss();

                                                                    Toast.makeText(Registration.this, "Succesfully Register", Toast.LENGTH_SHORT).show();
                                                                    Intent intent = new Intent(Registration.this, contacts.class);
                                                                    startActivity(intent);
                                                                    finish();
                                                                } else {
                                                                    progressDialog.dismiss();

                                                                    Toast.makeText(Registration.this, "Failed", Toast.LENGTH_SHORT).show();
                                                                }

                                                            }
                                                        });

                                                    }
                                                });
                                            }
                                        }
                                    });
                                }else {

                                    imageUri="https://firebasestorage.googleapis.com/v0/b/skbchat-a31e0.appspot.com/o/user%20(1).png?alt=media&token=c934df53-868f-46e7-8292-9535b32aa2f3";

                                    UserModel data = new UserModel(auth.getUid(), email, username,c_pass,imageUri,username.toLowerCase());
                                    reference.setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {

                                            if (task.isSuccessful()) {
                                                progressDialog.dismiss();

                                                Toast.makeText(Registration.this, "Succesfully Register", Toast.LENGTH_SHORT).show();
                                                Intent intent = new Intent(Registration.this, contacts.class);
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                progressDialog.dismiss();

                                                Toast.makeText(Registration.this, "Failed", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    });

                                }

                            }
                            else{
                                progressDialog.dismiss();

                                Toast.makeText(Registration.this, "Something went Wrong ", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }


            }
        });


        ActivityResultLauncher<Intent> activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode()== Activity.RESULT_OK){
                    Intent data= result.getData();
                    image_uri=data.getData();
//                    try {
//                        //bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),image_uri);
//                        profile_image.setImageURI(image_uri);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                    profile_image.setImageURI(image_uri);

                }
            }
        });
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ImagePicker.Companion.with(Registration.this).crop().maxResults(10,10);
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult();
                        activityResultLauncher.launch(intent);
            }
        });



    }


    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();

    }


}