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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.doozycod.getmaster.Model.UserPhotosModel;
import com.doozycod.getmaster.R;

import java.util.ArrayList;
import java.util.List;

public class LanguageIspeakAdapter extends RecyclerView.Adapter<LanguageIspeakAdapter.RecyclerHolder> {
    List<String> filepath = new ArrayList<>();
    Context context;


    public LanguageIspeakAdapter(Context context, List<String> list) {
        this.context = context;
        this.filepath = list;
    }


    @NonNull
    @Override
    public LanguageIspeakAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.languagespeak_list, parent, false);
        return new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageIspeakAdapter.RecyclerHolder holder, int position) {

        if (filepath.size() != 0) {
            holder.setLangTxt.setText(filepath.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return filepath.size();

    }

    class RecyclerHolder extends RecyclerView.ViewHolder {
        TextView setLangTxt;

        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            setLangTxt = itemView.findViewById(R.id.languageUSpeak);
        }
    }
}
