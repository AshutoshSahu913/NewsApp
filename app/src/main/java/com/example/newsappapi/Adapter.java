package com.example.newsappapi;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import kotlin.jvm.internal.Lambda;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {
    ArrayList<ModelClass>list;
    Context context;

    public Adapter(ArrayList<ModelClass> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.viewHolder holder, int position) {
        ModelClass modelClass=list.get(position);
        holder.mheading.setText(modelClass.getTitle());
        holder.mauthor.setText("Author : "+modelClass.getAuthor());
        holder.mtime.setText(modelClass.getPublishedAt());
        holder.mcontent.setText(modelClass.getDescription());
        Glide.with(context).load(modelClass.getUrlToImage())
                .into(holder.imageView);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,webView.class);
                i.putExtra("url",modelClass.getUrl());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView mheading,mcontent,mauthor,mtime;
        CardView cardView;
        ImageView imageView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            mheading=itemView.findViewById(R.id.mainHeading);
            mcontent=itemView.findViewById(R.id.content);
            mauthor=itemView.findViewById(R.id.author);
            mtime=itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.imageview);
            cardView=itemView.findViewById(R.id.cdView);
        }
    }
}
