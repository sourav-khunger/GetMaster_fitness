package com.doozycod.getmaster.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.doozycod.getmaster.CustomProgressBar;
import com.doozycod.getmaster.OtpModel;
import com.doozycod.getmaster.R;
import com.doozycod.getmaster.Service.ApiService;
import com.doozycod.getmaster.Service.ApiUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SendOTPActivity extends AppCompatActivity {
    TextInputEditText mobileNumberET;
    TextInputLayout textInputLayout;
    Button continueButton;
    TextView countryTV;
    CountryCodePicker countryCodePicker;
    String countryName, CountryCode;
    ApiService apiService;
    private String TAG = "SendOtpActivity";
    CustomProgressBar customProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);
        mobileNumberET = findViewById(R.id.mobileNumberET);
        textInputLayout = findViewById(R.id.textInputLayout);
        continueButton = findViewById(R.id.continueButton);
        countryTV = findViewById(R.id.country);
        countryCodePicker = findViewById(R.id.countryCodePicker);
        apiService = ApiUtils.getAPIService();

        customProgressBar = new CustomProgressBar(this);
        countryName = countryCodePicker.getDefaultCountryName();
        CountryCode = countryCodePicker.getDefaultCountryCodeWithPlus();
        countryTV.setText(countryName + " (" + CountryCode + ")");
        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                countryName = countryCodePicker.getSelectedCountryName();
                CountryCode = countryCodePicker.getSelectedCountryCodeWithPlus();
                countryTV.setText(countryName + " (" + CountryCode + ")");
            }
        });
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customProgressBar.showProgress();
                getOtp(mobileNumberET.getText().toString(), CountryCode);
            }
        });
        mobileNumberET.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                Log.e("onTextChanged", "onTextChanged: " + cs.length());
                if (cs.length() >= 9) {
                    continueButton.setEnabled(true);
                    continueButton.setBackgroundResource(R.drawable.continue_purple);
                } else {
                    continueButton.setEnabled(false);
                    continueButton.setBackgroundResource(R.drawable.continue_grey);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {
//                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });
    }

    void getOtp(String mobile, String countryCode) {
        apiService.sendOTP(mobile, countryCode).enqueue(new Callback<OtpModel>() {
            @Override
            public void onResponse(Call<OtpModel> call, Response<OtpModel> response) {
                customProgressBar.hideProgress();
                Log.e(TAG, "onResponse: " + response.raw());
                if (response.isSuccessful()) {
                    Intent intent = new Intent(SendOTPActivity.this, VerificationActivity.class);
                    intent.putExtra("id", response.body().getUser_data().getId());
                    startActivity(intent);
                    Log.e("api Call", "onResponse: " + response.body().getUser_data().getId());
                }
            }

            @Override
            public void onFailure(Call<OtpModel> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                customProgressBar.hideProgress();

            }
        });
    }

}

