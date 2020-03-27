package com.doozycod.getmaster.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.doozycod.getmaster.Model.RecentUser;
import com.doozycod.getmaster.Model.UserFavorite;
import com.doozycod.getmaster.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FavoriteUserAdapter extends RecyclerView.Adapter<FavoriteUserAdapter.RecyclerHolder> {
    Context context;
    List<UserFavorite> list;
    LinearLayout favoriteButton, homeButton, peopleButton, messagesButton;

    public FavoriteUserAdapter(Context context, List<UserFavorite> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FavoriteUserAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_list_home_recycler, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteUserAdapter.RecyclerHolder holder, int position) {
        holder.recentJoinedName.setText(list.get(position).getFullName());
        byte[] decodedString = Base64.decode(list.get(position).getProfilePic(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        Glide.with(context).load(decodedByte).into(holder.recentJoinedImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView recentJoinedName;
        CircleImageView recentJoinedImage;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            recentJoinedName = itemView.findViewById(R.id.userNameHome);
            recentJoinedImage = itemView.findViewById(R.id.recentJoinedImage);
        }
    }
}
