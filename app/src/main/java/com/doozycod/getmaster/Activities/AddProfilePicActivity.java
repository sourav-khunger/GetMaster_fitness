package com.doozycod.getmaster.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.doozycod.getmaster.CustomProgressBar;
import com.doozycod.getmaster.Model.AboutUserModel;
import com.doozycod.getmaster.Model.LanguageModel;
import com.doozycod.getmaster.Model.ProfileModel;
import com.doozycod.getmaster.Model.VerificationModel;
import com.doozycod.getmaster.R;
import com.doozycod.getmaster.Service.ApiService;
import com.doozycod.getmaster.Service.ApiUtils;
import com.doozycod.getmaster.SharedPreferenceMethod;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;
import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddProfilePicActivity extends AppCompatActivity {
    ImageView selectPicButton, backBTN, gredient;
    CircleImageView circleImageView;
    TextView weTxt, headerTxt, replaceTxt, userNameTxt;
    Button continueButtonProfile;
    SharedPreferenceMethod sharedPreferenceMethod;
    String filePath;
    ApiService apiService;
    CustomProgressBar customProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile_pic);

        backBTN = findViewById(R.id.backBTN);
        selectPicButton = findViewById(R.id.selectPicButton);
        gredient = findViewById(R.id.gredient);
        weTxt = findViewById(R.id.weTxt);
        headerTxt = findViewById(R.id.headerTxt);
        circleImageView = findViewById(R.id.profile_image);
        replaceTxt = findViewById(R.id.replaceTxt);
        userNameTxt = findViewById(R.id.userNameTxt);
        continueButtonProfile = findViewById(R.id.continueButtonProfile);
        apiService = ApiUtils.getAPIService();
        sharedPreferenceMethod = new SharedPreferenceMethod(this);
        replaceTxt.setEnabled(false);
        customProgressBar = new CustomProgressBar(this);

        if (getIntent().hasExtra("frag")) {
            userNameTxt.setVisibility(View.VISIBLE);
            circleImageView.setVisibility(View.VISIBLE);
            gredient.setVisibility(View.VISIBLE);
            selectPicButton.setEnabled(false);
            userNameTxt.setVisibility(View.VISIBLE);
            weTxt.setVisibility(View.GONE);
            replaceTxt.setEnabled(true);
            replaceTxt.setText("Replace with another");

            customProgressBar.showProgress();
            getProfileApi("6");
        }

        replaceTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(AddProfilePicActivity.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });
        selectPicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(AddProfilePicActivity.this)
                        .crop()                    //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });
        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        continueButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bm = BitmapFactory.decodeFile(filePath);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                byte[] byteArrayImage = baos.toByteArray();

                String encodedImage = Base64.encodeToString(byteArrayImage, Base64.DEFAULT);
                customProgressBar.showProgress();
                uploadProfilePic(encodedImage);
            }
        });
    }

    private void getProfileApi(String id) {
        apiService.getProfile(id).enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                Log.e("Get profile", "onResponse: " + response.body().getUserData().getFullName());

                headerTxt.setText("Looks Great!");
                continueButtonProfile.setText("Save");
                continueButtonProfile.setBackgroundResource(R.drawable.continue_purple);
                customProgressBar.hideProgress();
                byte[] decodedString = Base64.decode(response.body().getUserData().getProfilePic().trim(), Base64.NO_WRAP);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                Glide.with(AddProfilePicActivity.this).load(decodedByte).into(circleImageView);
                Glide.with(AddProfilePicActivity.this).load(decodedByte).into(selectPicButton);

            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                customProgressBar.hideProgress();
                Log.e("Get Profile", "onFailure: " + t.getMessage());
            }
        });
    }

    void uploadProfilePic(String base64Image) {
        apiService.addProfilePic(sharedPreferenceMethod.getId(), base64Image).enqueue(new Callback<VerificationModel>() {
            @Override
            public void onResponse(Call<VerificationModel> call, Response<VerificationModel> response) {
                customProgressBar.hideProgress();
                userNameTxt.setVisibility(View.VISIBLE);
                String userName = response.body().getAboutUserModel().getFullName();

                byte[] decodedString = Base64.decode(response.body().getAboutUserModel().getProfilePic().toString().trim(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

                Glide.with(AddProfilePicActivity.this).load(decodedByte).into(circleImageView);
                Glide.with(AddProfilePicActivity.this).load(decodedByte).into(selectPicButton);

//                Toast.makeText(AddProfilePicActivity.this, "you can upload Photos soon!", Toast.LENGTH_SHORT).show();
                if (getIntent().hasExtra("frag")) {

                } else {
                    startActivity(new Intent(AddProfilePicActivity.this, UploadPhotos.class));
                }
                Log.e("Get Master", "onResponse: " + response.body().getAboutUserModel().getProfilePic());
            }

            @Override
            public void onFailure(Call<VerificationModel> call, Throwable t) {
                Log.e("OnFailure", "onFailure: " + t.getMessage());
                customProgressBar.hideProgress();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            Uri fileUri = data.getData();
            selectPicButton.setImageURI(fileUri);
            circleImageView.setVisibility(View.VISIBLE);
            gredient.setVisibility(View.VISIBLE);
            circleImageView.setImageURI(fileUri);
            selectPicButton.setEnabled(false);
            userNameTxt.setVisibility(View.VISIBLE);

            weTxt.setVisibility(View.GONE);
            replaceTxt.setEnabled(true);
            headerTxt.setText("Looks Great!");
            replaceTxt.setText("Replace with another");
            continueButtonProfile.setText("Save");
            continueButtonProfile.setBackgroundResource(R.drawable.continue_purple);
            //You can get File object from intent
            File file = ImagePicker.Companion.getFile(data);
            //You can also get File Path from intent
            filePath = ImagePicker.Companion.getFilePath(data);
            userNameTxt.setText(sharedPreferenceMethod.getUserName().replace(" ", "\n"));
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();
        } else {
//            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}
