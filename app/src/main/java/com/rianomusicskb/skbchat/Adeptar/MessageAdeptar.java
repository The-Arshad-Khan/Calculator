package com.rianomusicskb.skbchat.Adeptar;

import android.content.Context;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.rianomusicskb.skbchat.R;
import com.rianomusicskb.skbchat.modelClass.Masseges;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MessageAdeptar extends RecyclerView.Adapter {
    Context context;
    ArrayList<Masseges> massegesArrayList;
    int ITEM_SEND = 1;
    int ITEM_REC = 2;



    public MessageAdeptar(Context context, ArrayList<Masseges> massegesArrayList) {
        this.context = context;
        this.massegesArrayList = massegesArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_SEND) {
            View view = LayoutInflater.from(context).inflate(R.layout.senderlayout_item, parent, false);
            return new SenderViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.reciverlayout_item, parent, false);
            return new ReciverViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Masseges masseges = massegesArrayList.get(position);
        if (holder.getClass() == SenderViewHolder.class) {
            SenderViewHolder viewHolder = (SenderViewHolder) holder;

//                Picasso.get().load(masseges.getMsg()).into(viewHolder.send_image);
//                viewHolder.send_image.setVisibility(View.VISIBLE);


                // viewHolder.send_image.setVisibility(View.VISIBLE);



            viewHolder.txtmassages.setText(masseges.getMsg());


        } else {
            ReciverViewHolder viewHolder = (ReciverViewHolder) holder;
          //  viewHolder.send_image.setVisibility(View.GONE);
//            Picasso.get().load(masseges.getMsg()).into(viewHolder.send_image);
//            viewHolder.send_image.setVisibility(View.VISIBLE);
            viewHolder.txtmassages.setText(masseges.getMsg());
        }
    }

    @Override
    public int getItemCount() {
        return massegesArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Masseges masseges = massegesArrayList.get(position);
        if (FirebaseAuth.getInstance().getCurrentUser().getUid().equals(masseges.getSenderId())) {
            return ITEM_SEND;
        } else {
            return ITEM_REC;
        }
    }

    class SenderViewHolder extends RecyclerView.ViewHolder {
        TextView txtmassages;
        ImageView send_image;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            send_image=itemView.findViewById(R.id.send_image);
            txtmassages = itemView.findViewById(R.id.txtmassages);

        }
    }

    class ReciverViewHolder extends RecyclerView.ViewHolder {
        TextView txtmassages;
        ImageView send_image;

        public ReciverViewHolder(@NonNull View itemView) {
            super(itemView);
            send_image=itemView.findViewById(R.id.send_image);
            txtmassages = itemView.findViewById(R.id.txtmassages);

        }
    }
}
