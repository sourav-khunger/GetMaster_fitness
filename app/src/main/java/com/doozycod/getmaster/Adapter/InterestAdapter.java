package com.doozycod.getmaster.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doozycod.getmaster.Activities.InterestedIn;
import com.doozycod.getmaster.R;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class InterestAdapter extends RecyclerView.Adapter<InterestAdapter.InterestHolder> {
    Context context;
    String[] checkBoxes;
    List<String> selectedInterests = new ArrayList<>();
    List<String> selectedInterestsApi = new ArrayList<>();

    CheckedInterest checkedInterestListener;
    boolean isEditing;

    public InterestAdapter(Context context, String[] checkBoxes) {
        this.context = context;
        this.checkBoxes = checkBoxes;
    }

    public InterestAdapter(Context context, String[] checkBoxes, List<String> selectedInterestsApi, boolean isEditing) {
        this.context = context;
        this.checkBoxes = checkBoxes;
        this.isEditing = isEditing;
        this.selectedInterestsApi = selectedInterestsApi;
    }

    public void setListner(CheckedInterest checkedInterestListener) {
        this.checkedInterestListener = checkedInterestListener;
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
        if (isEditing) {
            for (int i = 0; i < selectedInterestsApi.size(); i++) {
                if (holder.checkBoxes.getText().equals(selectedInterestsApi.get(i))) {
                    holder.checkBoxes.setChecked(true);
                    selectedInterests.add(holder.checkBoxes.getText().toString());

                }
            }
        }
        holder.checkBoxes.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    selectedInterests.add(holder.checkBoxes.getText().toString());
                    Log.e(TAG, "onCheckedChanged: Added " + holder.checkBoxes.getText().toString());
                    checkedInterestListener.onCheckListener(selectedInterests);
                } else {
                    Log.e(TAG, "onCheckedChanged: removed!" + holder.checkBoxes.getText().toString());
                    selectedInterests.remove(holder.checkBoxes.getText().toString());
                    checkedInterestListener.onCheckListener(selectedInterests);
                }

                Log.e(TAG, "onCheckedChanged: " + selectedInterests.size());
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
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

    public interface CheckedInterest {
        void onCheckListener(List<String> interest);
    }
}
