package com.doozycod.getmaster.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.doozycod.getmaster.R;

public class ProfileReady extends AppCompatActivity {
    Button showProfile, exploreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_ready);
        showProfile = findViewById(R.id.showProfileButton);
        exploreButton = findViewById(R.id.exploreButton);
        exploreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileReady.this, Homepage.class));
            }
        });
    }
}
