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

import com.doozycod.getmaster.R;
import com.google.android.material.textfield.TextInputEditText;

public class AboutyouActivity extends AppCompatActivity {
    TextInputEditText fullnameET, emailET;
    ScrollView scrollView;
    Button continueButton;
    RadioGroup userType, gender;
    RadioButton personalTrainerRadioButton, fitnessRadioButton, maleRadioButton, femaleRadioButton;
    String usertype;
    boolean isUserTypeSelected = false;
    boolean isGenderSelected = false;

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

        continueButton.setEnabled(false);

        userType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (!personalTrainerRadioButton.isChecked() && !fitnessRadioButton.isChecked()) {
                    Log.e("personalTrainer", "onCheckedChanged: Not checked!");
                }
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
                if (!maleRadioButton.isChecked() || !femaleRadioButton.isChecked()) {
                    Log.e("maleRadioButton", "onCheckedChanged: Not checked!");

                }
                if (maleRadioButton.isChecked()) {
                    usertype = "personal";
                    isGenderSelected = true;
                }
                if (femaleRadioButton.isChecked()) {
                    isGenderSelected = true;
                    usertype = "Fitness";

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

        emailET.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                if (cs.length() > 0) {

                    if (emailET.getText().toString().length() > 0 && emailET.getText().toString().length() > 0 && isValidEmail(emailET.getText().toString())) {

                        Log.e("onTextChanged", "onTextChanged: " + cs.length());

                        if(isUserTypeSelected && isGenderSelected)

                            continueButton.setEnabled(true);
                            continueButton.setBackgroundResource(R.drawable.continue_purple);

                    }
                } else {
                    if(!isValidEmail(emailET.getText().toString())){
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
//                if()
                startActivity(new Intent(AboutyouActivity.this, AddProfilePicActivity.class));
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


    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }
}
