package com.doozycod.getmaster.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.doozycod.getmaster.Model.UserPhotosModel;
import com.doozycod.getmaster.R;

import java.util.ArrayList;
import java.util.List;

public class UploadPhotoAdapter extends RecyclerView.Adapter<UploadPhotoAdapter.RecyclerHolder> {
    List<String> filepath = new ArrayList<>();
    Context context;
    List<UserPhotosModel.UserPhoto> userPhotosModelList;

    public UploadPhotoAdapter(Context context, List<String> filepath) {
        this.context = context;
        this.filepath = filepath;
    }

    boolean isGallery;


    public UploadPhotoAdapter(Context context, List<UserPhotosModel.UserPhoto> userPhotosModelList, boolean isGallery) {
        this.context = context;
        this.userPhotosModelList = userPhotosModelList;
        this.isGallery = isGallery;
    }

    @NonNull
    @Override
    public UploadPhotoAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_grid_view, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UploadPhotoAdapter.RecyclerHolder holder, int position) {
        if (isGallery) {
            holder.img1.setVisibility(View.GONE);
            holder.plus_IV.setVisibility(View.GONE);
            byte[] decodedString = Base64.decode(userPhotosModelList.get(position).getPhoto(), Base64.NO_WRAP);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            Glide.with(context).load(decodedByte).into(holder.selectedImage);

        } else {
            if (filepath.size() != 0) {
                Glide.with(context).load(filepath.get(position)).into(holder.selectedImage);
            }
        }
    }

    @Override
    public int getItemCount() {
        int i = 0;
//        if (filepath.size() <= 0) {
//            i = 4;
//        }
//        if (filepath.size() > 4) {
//            if (filepath.size() == 8) {
//                i = filepath.size();
//                return filepath.size();
//
//            } else {
//                i = filepath.size() + 1;
//                return filepath.size() + 1;
//            }
//        }
        if (isGallery) {
            i = userPhotosModelList.size();
        } else {
            i = filepath.size();
        }
        return i;

    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        ImageView selectedImage, img1, plus_IV;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            selectedImage = itemView.findViewById(R.id.selectedImage);
            img1 = itemView.findViewById(R.id.img1);
            plus_IV = itemView.findViewById(R.id.plus_IV);
        }
    }
}
