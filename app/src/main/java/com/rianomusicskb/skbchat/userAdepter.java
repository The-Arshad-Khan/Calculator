package com.rianomusicskb.skbchat;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class userAdepter extends RecyclerView.Adapter<userAdepter.viewHolder>{
    contacts contacts;
    ArrayList<UserModel> usersArrayList;
    public userAdepter(contacts contacts, ArrayList<UserModel> usersArrayList) {
        this.contacts=contacts;
        this.usersArrayList=usersArrayList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(contacts).inflate(R.layout.user_item,parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
       UserModel userModel = usersArrayList.get(position);
        holder.user_name.setText(userModel.username);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(contacts, chatUI.class);
                intent.putExtra("username" ,userModel.getUsername());
                intent.putExtra("uid" ,userModel.getUid());
                contacts.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return usersArrayList.size();
    }
    class viewHolder extends RecyclerView.ViewHolder{
        TextView user_name;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            user_name=itemView.findViewById(R.id.chat_username);
        }
    }
}
