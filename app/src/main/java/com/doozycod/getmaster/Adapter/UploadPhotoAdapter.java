package com.doozycod.getmaster.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.doozycod.getmaster.R;

import java.util.ArrayList;
import java.util.List;

public class UploadPhotoAdapter extends RecyclerView.Adapter<UploadPhotoAdapter.RecyclerHolder> {
    List<String> filepath = new ArrayList<>();
    Context context;

    public UploadPhotoAdapter(Context context, List<String> filepath) {
        this.context = context;
        this.filepath = filepath;
    }

    @NonNull
    @Override
    public UploadPhotoAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_grid_view, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UploadPhotoAdapter.RecyclerHolder holder, int position) {
        if(filepath.size()!=0){
            Glide.with(context).load(filepath.get(position)).into(holder.selectedImage);
        }
    }

    @Override
    public int getItemCount() {
        int i = 0;
        if (filepath.size() <= 0) {
            i=4;
        }
        if (filepath.size() > 4) {
            if (filepath.size() == 8) {
                i = filepath.size();
                return filepath.size();

            } else {
                i = filepath.size() + 1;
                return filepath.size() + 1;
            }
        }
        return i;
    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        ImageView selectedImage;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            selectedImage = itemView.findViewById(R.id.selectedImage);
        }
    }
}
