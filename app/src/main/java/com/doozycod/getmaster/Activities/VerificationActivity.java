package com.doozycod.getmaster.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.alahammad.otp_view.OTPListener;
import com.alahammad.otp_view.OtpView;
import com.alahammad.otp_view.smsCatcher.OnSmsCatchListener;
import com.alahammad.otp_view.smsCatcher.SmsVerifyCatcher;
import com.doozycod.getmaster.R;

public class VerificationActivity extends AppCompatActivity implements OTPListener, OnSmsCatchListener<String> {
    OtpView otpView;
    SmsVerifyCatcher smsVerifyCatcher;
    private TextView mOtpTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

//        mOtpTextView = findViewById(R.id.tv_otp);
        otpView = findViewById(R.id.otp);
        otpView.setOnOtpFinished(this);
        smsVerifyCatcher = new SmsVerifyCatcher(this, this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(VerificationActivity.this, AboutyouActivity.class));
            }
        }, 2000);
    }

    @Override
    public void onStart() {
        super.onStart();
        smsVerifyCatcher.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        smsVerifyCatcher.onStop();
    }

    @Override
    public void otpFinished(String s) {

    }

    @Override
    public void onSmsCatch(String s) {
        otpView.setOTP(s);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        smsVerifyCatcher.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}
