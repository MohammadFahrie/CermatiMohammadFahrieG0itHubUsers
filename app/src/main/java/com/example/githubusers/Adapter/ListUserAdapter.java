package com.example.githubusers.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.githubusers.Models.ItemsItem;
import com.example.githubusers.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListUserAdapter extends RecyclerView.Adapter<ListUserAdapter.ViewHolder>{

    Context context;
    ArrayList<ItemsItem> listUser;
    LayoutInflater layoutInflater;


    public ListUserAdapter(Context context, ArrayList<ItemsItem> listUser) {
        this.context = context;
        this.listUser = listUser;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ListUserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.custom_view_users, parent, false);

        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull ListUserAdapter.ViewHolder holder, int position) {

        String avatarurl = listUser.get(position).avatar_url;
        Glide.with(context)
                .load(avatarurl)
                .placeholder(R.drawable.ic_baseline_account_circle_24)
                .centerCrop()
                .into(holder.imageProfile);
        holder.textName.setText(listUser.get(position).login);
    }

    @Override
    public int getItemCount() {
        return listUser.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final CircleImageView imageProfile;
        private final TextView textName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageProfile = itemView.findViewById(R.id.image_profile);
            textName     = itemView.findViewById(R.id.text_name);
        }
    }
}
