package com.doozycod.getmaster.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.alahammad.otp_view.OTPListener;
import com.alahammad.otp_view.OtpView;
import com.alahammad.otp_view.smsCatcher.OnSmsCatchListener;
import com.alahammad.otp_view.smsCatcher.SmsVerifyCatcher;
import com.doozycod.getmaster.CustomProgressBar;
import com.doozycod.getmaster.Model.AboutUserModel;
import com.doozycod.getmaster.Model.VerificationModel;
import com.doozycod.getmaster.R;
import com.doozycod.getmaster.Service.ApiService;
import com.doozycod.getmaster.Service.ApiUtils;
import com.doozycod.getmaster.SharedPreferenceMethod;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerificationActivity extends AppCompatActivity implements OTPListener, OnSmsCatchListener<String> {
    OtpView otpView;
    SmsVerifyCatcher smsVerifyCatcher;
    private TextView mOtpTextView;
    String userId;
    ApiService apiService;
    SharedPreferenceMethod sharedPreferenceMethod;
    CustomProgressBar customProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        sharedPreferenceMethod = new SharedPreferenceMethod(this);
        userId = getIntent().getStringExtra("id");
//        mOtpTextView = findViewById(R.id.tv_otp);
        otpView = findViewById(R.id.otp);
        otpView.setOnOtpFinished(this);
        smsVerifyCatcher = new SmsVerifyCatcher(this, this);
        apiService = ApiUtils.getAPIService();
        customProgressBar = new CustomProgressBar(this);
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(VerificationActivity.this, AboutyouActivity.class));
//            }
//        }, 2000);


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
        String code = parseCode(s);//Parse verification code

        otpView.setOTP(code);
        customProgressBar.showProgress();
        verifyUser(userId, code);
    }

    void verifyUser(String userId, String otp) {
        apiService.verifyOTP(userId, otp).enqueue(new Callback<VerificationModel>() {
            @Override
            public void onResponse(Call<VerificationModel> call, Response<VerificationModel> response) {
                Log.e("verify", "onResponse: VERIFY " + response.toString() + "  " + response.message());
                customProgressBar.hideProgress();
                sharedPreferenceMethod.saveId(userId);
//                Intent intent = new Intent(VerificationActivity.this, AboutUserModel.class);
                startActivity(new Intent(VerificationActivity.this, AboutyouActivity.class));
                finishAffinity();
            }

            @Override
            public void onFailure(Call<VerificationModel> call, Throwable t) {
                customProgressBar.hideProgress();
                Log.e("VERIFY", "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        smsVerifyCatcher.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private String parseCode(String message) {
        Pattern p = Pattern.compile("\\b\\d{4}\\b");
        Matcher m = p.matcher(message);
        String code = "";
        while (m.find()) {
            code = m.group(0);
        }
        return code;
    }
}
