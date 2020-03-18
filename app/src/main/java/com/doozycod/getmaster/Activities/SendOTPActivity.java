package com.doozycod.getmaster.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.doozycod.getmaster.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class SendOTPActivity extends AppCompatActivity {
    TextInputEditText mobileNumberET;
    TextInputLayout textInputLayout;
    Button continueButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_otp);
        mobileNumberET = findViewById(R.id.mobileNumberET);
        textInputLayout = findViewById(R.id.textInputLayout);
        continueButton = findViewById(R.id.continueButton);
        getSupportActionBar().hide();
   /*     if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }*/
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SendOTPActivity.this, VerificationActivity.class));
            }
        });
        mobileNumberET.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                Log.e("onTextChanged", "onTextChanged: " + cs.length());
                if (cs.length() > 0) {
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
                Toast.makeText(getApplicationContext(), "after text change", Toast.LENGTH_LONG).show();
            }
        });
    }


}

