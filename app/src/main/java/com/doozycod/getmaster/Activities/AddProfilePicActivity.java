package com.doozycod.getmaster.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.doozycod.getmaster.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddProfilePicActivity extends AppCompatActivity {
    ImageView selectPicButton, backBTN, gredient;
    CircleImageView circleImageView;
    TextView weTxt, headerTxt, replaceTxt, userNameTxt;
    Button continueButtonProfile;

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
        replaceTxt.setEnabled(false);
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
                startActivity(new Intent(AddProfilePicActivity.this, UploadPhotos.class));
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
            String filePath = ImagePicker.Companion.getFilePath(data);
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();

        } else {
//            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }
}
