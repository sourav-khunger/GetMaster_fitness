package com.doozycod.getmaster.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.doozycod.getmaster.R;
import com.google.android.material.textfield.TextInputEditText;

public class AboutyouActivity extends AppCompatActivity {
    TextInputEditText mobileNumberET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutyou);
        getSupportActionBar().hide();
    }
}
