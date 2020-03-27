package com.doozycod.getmaster.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import com.doozycod.getmaster.CustomProgressBar;
import com.doozycod.getmaster.Model.AboutUserModel;
import com.doozycod.getmaster.Model.ProfileModel;
import com.doozycod.getmaster.Model.VerificationModel;
import com.doozycod.getmaster.R;
import com.doozycod.getmaster.Service.ApiService;
import com.doozycod.getmaster.Service.ApiUtils;
import com.doozycod.getmaster.SharedPreferenceMethod;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutyouActivity extends AppCompatActivity {
    TextInputEditText fullnameET, emailET;
    ScrollView scrollView;
    Button continueButton;
    RadioGroup userType, gender;
    RadioButton personalTrainerRadioButton, fitnessRadioButton, maleRadioButton, femaleRadioButton;
    String usertype, genderType;
    boolean isUserTypeSelected = false;
    boolean isGenderSelected = false;
    ApiService apiService;
    SharedPreferenceMethod sharedPreferenceMethod;
    CustomProgressBar customProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutyou);
        scrollView = findViewById(R.id.scrollView);
        continueButton = findViewById(R.id.continueButton);
        userType = findViewById(R.id.usertype);
        gender = findViewById(R.id.gender);
        fullnameET = findViewById(R.id.fullnameET);
        emailET = findViewById(R.id.emailET);
        personalTrainerRadioButton = findViewById(R.id.personalRadioBtn);
        fitnessRadioButton = findViewById(R.id.fitnessRadioBtn);
        maleRadioButton = findViewById(R.id.maleRadioButton);
        femaleRadioButton = findViewById(R.id.femaleRadioButton);

        sharedPreferenceMethod = new SharedPreferenceMethod(this);
        apiService = ApiUtils.getAPIService();
        continueButton.setEnabled(false);
        customProgressBar = new CustomProgressBar(this);
        userType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (personalTrainerRadioButton.isChecked()) {
                    isUserTypeSelected = true;
                    usertype = "personal";
                }
                if (fitnessRadioButton.isChecked()) {
                    isUserTypeSelected = true;
                    usertype = "Fitness";
                }
            }
        });
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (maleRadioButton.isChecked()) {
                    usertype = "Personal Trainer";
                    isGenderSelected = true;
                }
                if (femaleRadioButton.isChecked()) {
                    isGenderSelected = true;
                    usertype = "Fitness Lover";

                }
            }
        });
        fullnameET.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                Log.e("onTextChanged", "onTextChanged: " + cs.length());
                if (cs.length() > 0) {
                    if (emailET.getText().toString().length() > 0 && emailET.getText().toString().length() > 0) {
                        continueButton.setEnabled(true);
                        continueButton.setBackgroundResource(R.drawable.continue_purple);
                    }
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
        if (getIntent().hasExtra("frag")) {
            customProgressBar.showProgress();
            getAboutYou("6");
            continueButton.setEnabled(true);
        }
        emailET.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                if (cs.length() > 0) {

                    if (emailET.getText().toString().length() > 0 && emailET.getText().toString().length() > 0 && isValidEmail(emailET.getText().toString())) {

                        Log.e("onTextChanged", "onTextChanged: " + cs.length());

                        if (isUserTypeSelected && isGenderSelected)

                            continueButton.setEnabled(true);
                        continueButton.setBackgroundResource(R.drawable.continue_purple);

                    }
                } else {
                    if (!isValidEmail(emailET.getText().toString())) {
                        continueButton.setEnabled(false);
                        continueButton.setBackgroundResource(R.drawable.continue_grey);
                    }
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
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (maleRadioButton.isChecked()) {
                    genderType = "Male";
                }
                if (femaleRadioButton.isChecked()) {
                    genderType = "Female";
                } else {
                    Log.e("Male", "onClick: select at least one");
                }
                if (personalTrainerRadioButton.isChecked()) {
                    isUserTypeSelected = true;
                    usertype = "Personal Trainer";
                }
                if (fitnessRadioButton.isChecked()) {
                    isUserTypeSelected = true;
                    usertype = "Fitness Lover";
                } else {
                    Log.e("Male", "onClick: select at least one");
                }
//                else {
                customProgressBar.showProgress();
                callApi(sharedPreferenceMethod.getId(), fullnameET.getText().toString(),
                        emailET.getText().toString(), genderType, usertype);
//                }
                Log.e("continue", "onClick: " + usertype + genderType);
                sharedPreferenceMethod.saveInterestType(usertype);
//                if()
//                startActivity(new Intent(AboutyouActivity.this, AddProfilePicActivity.class));
            }
        });
        scrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        });
        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    private void getAboutYou(String id) {
        apiService.getProfile(id).enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                customProgressBar.hideProgress();
                if (response.body().getUserData().getUserType().contains(fitnessRadioButton.getText())) {
                    fitnessRadioButton.setChecked(true);
                }
                if (response.body().getUserData().getUserType().contains(personalTrainerRadioButton.getText())) {
                    personalTrainerRadioButton.setChecked(true);
                }
                if (response.body().getUserData().getFullName().contains(" ")) {
                    String fullName = response.body().getUserData().getFullName().replace(" ", "\n");
                    fullnameET.setText(fullName);

                } else {
                    fullnameET.setText(response.body().getUserData().getFullName());

                }
                emailET.setText(response.body().getUserData().getEmail());
                if (response.body().getUserData().getGender().contains(maleRadioButton.getText())) {
                    maleRadioButton.setChecked(true);
                }
                if (response.body().getUserData().getGender().contains(femaleRadioButton.getText())) {
                    femaleRadioButton.setChecked(true);
                }
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                customProgressBar.hideProgress();
                Log.e("Get Details", "onFailure: " + t.getMessage());
            }
        });
    }

    void callApi(String id, String full_name, String email, String gender, String user_type) {
        apiService.aboutYouApi(id, full_name, email, gender, user_type).enqueue(new Callback<VerificationModel>() {
            @Override
            public void onResponse(Call<VerificationModel> call, Response<VerificationModel> response) {
                customProgressBar.hideProgress();
                sharedPreferenceMethod.saveUserName(full_name);
                Log.e("About You", "onResponse: " + response.toString());
                Intent intent = new Intent(AboutyouActivity.this, AddProfilePicActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<VerificationModel> call, Throwable t) {
                customProgressBar.hideProgress();
                Log.e("onFailure", "onFailure: " + t.getMessage());
            }
        });
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
