package com.doozycod.getmaster.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doozycod.getmaster.Activities.AboutyouActivity;
import com.doozycod.getmaster.Activities.AddProfilePicActivity;
import com.doozycod.getmaster.Activities.InterestedIn;
import com.doozycod.getmaster.Activities.SelectLanguages;
import com.doozycod.getmaster.Activities.UploadPhotos;
import com.doozycod.getmaster.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowEditFragment extends Fragment {
    TextView editDetailsNaviButton, editProfileNaviButton,
            editInterestNaviButton, editLanguageNaviButton, manageNaviButton;

    public ShowEditFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_show_edit, container, false);
        initUI(view);
        onClick();
        return view;
    }

    private void onClick() {
        editLanguageNaviButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SelectLanguages.class);
                intent.putExtra("frag", "edit");
                startActivity(intent);
            }
        });
        editDetailsNaviButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AboutyouActivity.class);
                intent.putExtra("frag", "edit");
                startActivity(intent);

            }
        });
        editInterestNaviButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), InterestedIn.class);
                intent.putExtra("frag", "edit");
                startActivity(intent);
            }
        });
        editProfileNaviButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddProfilePicActivity.class);
                intent.putExtra("frag", "edit");
                startActivity(intent);
            }
        });
        manageNaviButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), UploadPhotos.class);
                intent.putExtra("frag", "edit");
                startActivity(intent);
            }
        });
    }

    private void initUI(View view) {
        editDetailsNaviButton = view.findViewById(R.id.editDetailsNaviButton);
        editProfileNaviButton = view.findViewById(R.id.editProfileNaviButton);
        editInterestNaviButton = view.findViewById(R.id.editInterestNaviButton);
        editLanguageNaviButton = view.findViewById(R.id.editLanguageNaviButton);
        manageNaviButton = view.findViewById(R.id.manageNaviButton);
    }

}
