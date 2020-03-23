package com.doozycod.getmaster.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.doozycod.getmaster.Adapter.InterestAdapter;
import com.doozycod.getmaster.R;
import com.doozycod.getmaster.SharedPreferenceMethod;

public class InterestedIn extends AppCompatActivity {
    RecyclerView recyclerView;
    InterestAdapter interestAdapter;
    TextView headerTxt;
    SharedPreferenceMethod sharedPreferenceMethod;
    String interestArray[] = {"Strength Training", "Muscle Training", "Transformation and Weight loss",
            "Zumba", "Aerobics", "Kickboxing", "Yoga", "Pilates"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interested_in);
        headerTxt = findViewById(R.id.headerTxtIn);
        recyclerView = findViewById(R.id.interestListRV);
        sharedPreferenceMethod = new SharedPreferenceMethod(this);
        if (sharedPreferenceMethod.getinterestedType().equals("Fitness Lover")) {
            headerTxt.setText("Interested in");
        }
        if (sharedPreferenceMethod.getinterestedType().equals("Personal Trainer")) {
            headerTxt.setText("Select Expertise");
        }
        recyclerView = findViewById(R.id.interestListRV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new InterestAdapter(this,interestArray));

    }
}
