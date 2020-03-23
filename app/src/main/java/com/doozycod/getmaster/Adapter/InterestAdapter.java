package com.doozycod.getmaster.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doozycod.getmaster.Activities.InterestedIn;
import com.doozycod.getmaster.R;

public class InterestAdapter extends RecyclerView.Adapter<InterestAdapter.InterestHolder> {
    Context context;
    String[] checkBoxes;

    public InterestAdapter(Context context, String[] checkBoxes) {
        this.context = context;
        this.checkBoxes = checkBoxes;
    }

    @NonNull
    @Override
    public InterestAdapter.InterestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.interest_list_view, parent, false);
        return new InterestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InterestAdapter.InterestHolder holder, int position) {

        holder.checkBoxes.setText(checkBoxes[position]);
    }

    @Override
    public int getItemCount() {
        return checkBoxes.length;
    }

    class InterestHolder extends RecyclerView.ViewHolder {
        CheckBox checkBoxes;

        public InterestHolder(@NonNull View itemView) {
            super(itemView);
            checkBoxes = itemView.findViewById(R.id.checkBoxes);
        }
    }
}
