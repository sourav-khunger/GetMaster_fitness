package com.doozycod.getmaster.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.doozycod.getmaster.Model.FeaturedUser;
import com.doozycod.getmaster.R;

import java.util.List;


public class RecyclerScrollAdapter extends RecyclerView.Adapter<RecyclerScrollAdapter.RecyclerHolder> {
    Context mContext;
    List<FeaturedUser> list;

    public RecyclerScrollAdapter(Context mContext, List<FeaturedUser> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerScrollAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fancycoverflow, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerScrollAdapter.RecyclerHolder holder, int position) {

//        if (mBaseElevation == 0) {
//            mBaseElevation = cardView.getCardElevation();
//        }


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (mOnItemClickListener != null) mOnItemClickListener.onClick(position);
            }
        });

        final FeaturedUser item = list.get(position);

        byte[] decodedString = Base64.decode(item.getProfilePic(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            String fullName = item.getFullName().replace(" ","\n");
        Glide.with(mContext).load(decodedByte).into(holder.mIcon);
        holder.mName.setText(fullName);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView mName;
        ImageView mIcon;
        CardView cardView;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            mName = (TextView) itemView.findViewById(R.id.name);
            cardView = itemView.findViewById(R.id.cardView);
            mIcon = itemView.findViewById(R.id.viewpagerImage);

        }
    }
}
