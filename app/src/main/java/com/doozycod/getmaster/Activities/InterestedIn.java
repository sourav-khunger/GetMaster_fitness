package com.doozycod.getmaster.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.doozycod.getmaster.Adapter.InterestAdapter;
import com.doozycod.getmaster.CustomProgressBar;
import com.doozycod.getmaster.Model.UserInterestModel;
import com.doozycod.getmaster.R;
import com.doozycod.getmaster.Service.ApiService;
import com.doozycod.getmaster.Service.ApiUtils;
import com.doozycod.getmaster.SharedPreferenceMethod;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterestedIn extends AppCompatActivity implements InterestAdapter.CheckedInterest {
    RecyclerView recyclerView;
    InterestAdapter interestAdapter;
    Button continueButtonInterest;
    TextView headerTxt;
    SharedPreferenceMethod sharedPreferenceMethod;
    String interestArray[] = {"Strength Training", "Muscle Training", "Transformation and Weight loss",
            "Zumba", "Aerobics", "Kickboxing", "Yoga", "Pilates"};
    List<String> list = new ArrayList<>();
    String interest = "";
    ApiService apiService;
    CustomProgressBar customProgressBar;
    ImageView backBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interested_in);
        headerTxt = findViewById(R.id.headerTxtIn);
        recyclerView = findViewById(R.id.interestListRV);
        backBTN = findViewById(R.id.backBTNInterest);
        continueButtonInterest = findViewById(R.id.continueButtonInterest);
        customProgressBar = new CustomProgressBar(this);
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
        interestAdapter = new InterestAdapter(this, interestArray);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(interestAdapter);

        apiService = ApiUtils.getAPIService();
        interestAdapter.setListner(this::onCheckListener);
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        continueButtonInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interest = TextUtils.join(", ", list);
                customProgressBar.showProgress();
                InterestApi(interest);
                Log.e("Interest", "onClick: " + interest);
            }
        });
    }

    void InterestApi(String interests) {
        apiService.addInterest(sharedPreferenceMethod.getId(), interests).enqueue(new Callback<UserInterestModel>() {
            @Override
            public void onResponse(Call<UserInterestModel> call, Response<UserInterestModel> response) {
                customProgressBar.hideProgress();

                Log.e("interest api", "onResponse: " + response.body().getUserInterests().getResponse());
                startActivity(new Intent(InterestedIn.this, SelectLanguages.class));
            }

            @Override
            public void onFailure(Call<UserInterestModel> call, Throwable t) {
                customProgressBar.hideProgress();
                Log.e("", "onFailure: " + t.getMessage());
                Toast.makeText(InterestedIn.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override

    public void onCheckListener(List<String> interest) {
        if (interest.size() == 0) {
            continueButtonInterest.setText("Continue");
            continueButtonInterest.setBackground(getDrawable(R.drawable.continue_grey));
            continueButtonInterest.setEnabled(false);

        }
        if (interest.size() > 0) {
            continueButtonInterest.setText("1 more step");
            continueButtonInterest.setBackground(getDrawable(R.drawable.continue_purple));
            continueButtonInterest.setEnabled(true);

        }
        list = interest;
        Log.e("Interest", "onCheckListener: " + list.size());
    }
}
