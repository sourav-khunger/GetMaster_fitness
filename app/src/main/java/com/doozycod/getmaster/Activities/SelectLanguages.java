package com.doozycod.getmaster.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.doozycod.getmaster.Adapter.InterestAdapter;
import com.doozycod.getmaster.CustomProgressBar;
import com.doozycod.getmaster.Model.LanguageModel;
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

public class SelectLanguages extends AppCompatActivity implements InterestAdapter.CheckedInterest {
    String languageArray[] = {"English", "Arabic", "Urdu", "Hindi", "Malayalam"};
    InterestAdapter interestAdapter;
    RecyclerView recyclerView;
    String languages;
    Button continueButtonLangButton;
    List<String> list = new ArrayList<>();
    SharedPreferenceMethod sharedPreferenceMethod;
    ApiService apiService;
    CustomProgressBar customProgressBar;
    ImageView backBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_languages);
        recyclerView = findViewById(R.id.speakListRV);
        continueButtonLangButton = findViewById(R.id.continueButtonLangBTN);
        backBTN = findViewById(R.id.backBTN);
        sharedPreferenceMethod = new SharedPreferenceMethod(this);
        customProgressBar = new CustomProgressBar(this);
        apiService = ApiUtils.getAPIService();
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        interestAdapter = new InterestAdapter(this, languageArray);
        recyclerView.setAdapter(interestAdapter);
        interestAdapter.setListner(this::onCheckListener);
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        continueButtonLangButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                languages = TextUtils.join(", ", list);
                customProgressBar.showProgress();
                LanguageApi(languages);
                Log.e("language", "onClick: " + languages);
            }
        });
    }

    void LanguageApi(String languages) {
        apiService.addLanguage(sharedPreferenceMethod.getId(), languages).enqueue(new Callback<LanguageModel>() {
            @Override
            public void onResponse(Call<LanguageModel> call, Response<LanguageModel> response) {
                customProgressBar.hideProgress();
                Log.e("Language", "onResponse: " + response.body().getUserLanguages().getResponse());
                startActivity(new Intent(SelectLanguages.this, ProfileReady.class));
            }

            @Override
            public void onFailure(Call<LanguageModel> call, Throwable t) {
                Log.e("Language api", "onFailure: " + t.getMessage());
                customProgressBar.hideProgress();

                Toast.makeText(SelectLanguages.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onCheckListener(List<String> interest) {
        if (interest.size() == 0) {
//            continueButtonLangButton.setText("Finish");
            continueButtonLangButton.setBackground(getDrawable(R.drawable.continue_grey));
            continueButtonLangButton.setEnabled(false);

        }
        if (interest.size() > 0) {
//            continueButtonLangButton.setText("Finish");
            continueButtonLangButton.setBackground(getDrawable(R.drawable.continue_purple));
            continueButtonLangButton.setEnabled(true);

        }
        list = interest;
        Log.e("Interest", "onCheckListener: " + list.size());
    }
}
