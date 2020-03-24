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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.bumptech.glide.Glide;
import com.doozycod.getmaster.Adapter.UploadPhotoAdapter;
import com.doozycod.getmaster.Model.UserPhotosModel;
import com.doozycod.getmaster.R;
import com.doozycod.getmaster.Service.ApiService;
import com.doozycod.getmaster.Service.ApiUtils;
import com.doozycod.getmaster.SharedPreferenceMethod;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class UploadPhotos extends AppCompatActivity implements View.OnClickListener {
    UploadPhotoAdapter uploadPhotoAdapter;
    List<String> filepath = new ArrayList<>();
    List<UserPhotosModel.UserPhoto> userPhotosModelList = new ArrayList<>();
    ImageView selectimg1, selectimg2, selectimg3, selectimg4, selectimg5, selectimg6, selectimg7, selectimg8, selectimg9;
    ImageView img1, img2, img3, img4, img5, img6, img7, img8, img9;
    ImageView add1, add2, add3, add4, add5, add6, add7, add8, add9;
    TextView upload1, upload2, upload3, upload4, upload5, upload6, upload7, upload8, upload9;
    FrameLayout frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9;
    Uri uri;
    View view;
    RecyclerView recyclerView;
    Button continueButtonProfile;
    boolean isFrameEnabled = true;
    ApiService apiService;
    SharedPreferenceMethod sharedPreferenceMethod;
    RoundCornerProgressBar progressBar, progressBar2, progressBar3, progressBar4, progressBar5, progressBar6, progressBar7, progressBar8, progressBar9;
    int i = 1, counter = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photos);
        initUI();

        uploadPhotoAdapter = new UploadPhotoAdapter(this, filepath);
        sharedPreferenceMethod = new SharedPreferenceMethod(this);
        apiService = ApiUtils.getAPIService();
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        selectimg1.setOnClickListener(this);
        selectimg2.setOnClickListener(this);
        selectimg3.setOnClickListener(this);
        selectimg4.setOnClickListener(this);
        selectimg5.setOnClickListener(this);
        recyclerView.setAdapter(uploadPhotoAdapter);


//        selectimg6.setOnClickListener(this);
//        selectimg7.setOnClickListener(this);
//        selectimg8.setOnClickListener(this);
//        selectimg9.setOnClickListener(this);
        /*selectimg1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImagePicker.Companion.with(UploadPhotos.this)
                        //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
            }
        });*/
        continueButtonProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UploadPhotos.this, InterestedIn.class));
            }
        });
    }

    void uploadPhotos(String filepath) {
        apiService.uploadPhotos(sharedPreferenceMethod.getId(), filepath).enqueue(new Callback<UserPhotosModel>() {
            @Override
            public void onResponse(Call<UserPhotosModel> call, Response<UserPhotosModel> response) {
                i = 60;
                counter = 101;
                startHandler();
                i = 1;
                counter = 60;
                userPhotosModelList = response.body().getUserPhotos();

                if (userPhotosModelList.size() > 5) {
                    continueButtonProfile.setText("2 more steps");
                    continueButtonProfile.setBackground(getResources().getDrawable(R.drawable.continue_purple));
                }
                for (int i = 0; i < userPhotosModelList.size(); i++) {
                    if (i == 0) {
                        byte[] decodedString = Base64.decode(userPhotosModelList.get(i).getPhoto(), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                        selectimg1.setImageBitmap(decodedByte);
                        img1.setVisibility(View.VISIBLE);
                        upload1.setVisibility(GONE);
                        progressBar.setVisibility(GONE);
                        add1.setVisibility(GONE);

                        Glide.with(UploadPhotos.this).load(decodedByte).into(selectimg1);
                    }
                    if (i == 1) {

                        img2.setVisibility(View.VISIBLE);
                        upload2.setVisibility(GONE);
                        progressBar2.setVisibility(GONE);
                        add2.setVisibility(GONE);
                        byte[] decodedString = Base64.decode(userPhotosModelList.get(i).getPhoto(), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                        selectimg1.setImageBitmap(decodedByte);
                        Glide.with(UploadPhotos.this).load(decodedByte).into(selectimg2);
                    }
                    if (i == 2) {
                        img3.setVisibility(View.VISIBLE);
                        upload3.setVisibility(GONE);
                        progressBar3.setVisibility(GONE);
                        add3.setVisibility(GONE);
                        byte[] decodedString = Base64.decode(userPhotosModelList.get(i).getPhoto(), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                        selectimg1.setImageBitmap(decodedByte);
                        Glide.with(UploadPhotos.this).load(decodedByte).into(selectimg3);
                    }
                    if (i == 3) {
                        img4.setVisibility(View.VISIBLE);
                        upload4.setVisibility(GONE);
                        progressBar4.setVisibility(GONE);
                        add4.setVisibility(GONE);
                        byte[] decodedString = Base64.decode(userPhotosModelList.get(i).getPhoto(), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                        selectimg1.setImageBitmap(decodedByte);
                        Glide.with(UploadPhotos.this).load(decodedByte).into(selectimg4);
                    }
                    if (i == 4) {
                        img5.setVisibility(View.VISIBLE);
                        upload5.setVisibility(GONE);
                        progressBar5.setVisibility(GONE);
                        add5.setVisibility(GONE);
                        byte[] decodedString = Base64.decode(userPhotosModelList.get(i).getPhoto(), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                        selectimg1.setImageBitmap(decodedByte);
                        Glide.with(UploadPhotos.this).load(decodedByte).into(selectimg5);
                    }
                    if (i == 5) {
                        img6.setVisibility(View.VISIBLE);
                        upload6.setVisibility(GONE);
                        progressBar6.setVisibility(GONE);
                        add6.setVisibility(GONE);
                        byte[] decodedString = Base64.decode(userPhotosModelList.get(i).getPhoto(), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                        selectimg1.setImageBitmap(decodedByte);
                        Glide.with(UploadPhotos.this).load(decodedByte).into(selectimg6);
                    }
                    if (i == 6) {
                        img7.setVisibility(View.VISIBLE);
                        upload7.setVisibility(GONE);
                        progressBar7.setVisibility(GONE);
                        add7.setVisibility(GONE);
                        byte[] decodedString = Base64.decode(userPhotosModelList.get(i).getPhoto(), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                        selectimg1.setImageBitmap(decodedByte);
                        Glide.with(UploadPhotos.this).load(decodedByte).into(selectimg7);
                    }
                    if (i == 7) {
                        img8.setVisibility(View.VISIBLE);
                        upload8.setVisibility(GONE);
                        progressBar8.setVisibility(GONE);
                        add8.setVisibility(GONE);
                        byte[] decodedString = Base64.decode(userPhotosModelList.get(i).getPhoto(), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                        selectimg1.setImageBitmap(decodedByte);
                        Glide.with(UploadPhotos.this).load(decodedByte).into(selectimg8);
                    }
                    if (i == 8) {
                        img9.setVisibility(View.VISIBLE);
                        upload9.setVisibility(GONE);
                        progressBar9.setVisibility(GONE);
                        add9.setVisibility(GONE);
                        byte[] decodedString = Base64.decode(userPhotosModelList.get(i).getPhoto(), Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//                        selectimg1.setImageBitmap(decodedByte);
                        Glide.with(UploadPhotos.this).load(decodedByte).into(selectimg9);
                    }
                }

                Log.e("upload Photos", "onResponse: photo" + response.body().getUserPhotos().size());
            }

            @Override
            public void onFailure(Call<UserPhotosModel> call, Throwable t) {
                Log.e("upload Photos", "onResponse: " + t.getMessage());
            }
        });
    }

    void startHandler() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if (i < counter) {
                    progressBar.setProgress(i);
                    i++;
                }
                handler.postDelayed(this, 20);

            }
        }, 500);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            Uri fileUri = data.getData();
            uri = fileUri;
            //You can get File object from intent
            File file = ImagePicker.Companion.getFile(data);

            //You can also get File Path from intent
            String filePath = ImagePicker.Companion.getFilePath(data);
            filepath.add(filePath);
            recyclerView.setAdapter(uploadPhotoAdapter);
            i = 0;
            counter = 60;
            if (filepath.size() == 1) {
//                selectimg1.setImageURI(fileUri);
//                img1.setVisibility(View.VISIBLE);
                upload1.setVisibility(View.VISIBLE);
                add1.setVisibility(GONE);
                progressBar.setVisibility(View.VISIBLE);
                startHandler();
                Bitmap bm = BitmapFactory.decodeFile(filePath);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                byte[] byteArrayImage = baos.toByteArray();
                String encodedImage = Base64.encodeToString(byteArrayImage, Base64.NO_WRAP);
                Log.e("TAG", "onActivityResult: " + encodedImage.trim());
                uploadPhotos(encodedImage);
            }
            if (filepath.size() == 2) {
                progressBar2.setVisibility(View.VISIBLE);
                img2.setVisibility(View.VISIBLE);
                upload2.setVisibility(View.VISIBLE);
                add2.setVisibility(GONE);
                Bitmap bm = BitmapFactory.decodeFile(filePath);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                byte[] byteArrayImage = baos.toByteArray();

                String encodedImage = Base64.encodeToString(byteArrayImage, Base64.NO_WRAP);
                uploadPhotos(encodedImage);
            }
            if (filepath.size() == 3) {
                progressBar3.setVisibility(View.VISIBLE);
                img3.setVisibility(View.VISIBLE);
                upload3.setVisibility(View.VISIBLE);
                add3.setVisibility(GONE);

                Bitmap bm = BitmapFactory.decodeFile(filePath);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                byte[] byteArrayImage = baos.toByteArray();

                String encodedImage = Base64.encodeToString(byteArrayImage, Base64.NO_WRAP);
                uploadPhotos(encodedImage);
            }
            if (filepath.size() == 4) {
                progressBar4.setVisibility(View.VISIBLE);
                img4.setVisibility(View.VISIBLE);
                upload4.setVisibility(View.VISIBLE);
                add4.setVisibility(GONE);

                Bitmap bm = BitmapFactory.decodeFile(filePath);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                byte[] byteArrayImage = baos.toByteArray();

                String encodedImage = Base64.encodeToString(byteArrayImage, Base64.NO_WRAP);
                uploadPhotos(encodedImage);
            }
            if (filepath.size() == 5) {
                progressBar5.setVisibility(View.VISIBLE);
                img5.setVisibility(View.VISIBLE);
                upload5.setVisibility(View.VISIBLE);
                add5.setVisibility(GONE);
                Bitmap bm = BitmapFactory.decodeFile(filePath);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                byte[] byteArrayImage = baos.toByteArray();

                String encodedImage = Base64.encodeToString(byteArrayImage, Base64.NO_WRAP);
                uploadPhotos(encodedImage);
            }
            if (filepath.size() == 6) {
                progressBar6.setVisibility(View.VISIBLE);
                img6.setVisibility(View.VISIBLE);
                upload6.setVisibility(View.VISIBLE);
                add6.setVisibility(GONE);
                Bitmap bm = BitmapFactory.decodeFile(filePath);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                byte[] byteArrayImage = baos.toByteArray();

                String encodedImage = Base64.encodeToString(byteArrayImage, Base64.NO_WRAP);
                uploadPhotos(encodedImage);
            }
            if (filepath.size() == 7) {
                progressBar7.setVisibility(View.VISIBLE);
                img7.setVisibility(View.VISIBLE);
                upload7.setVisibility(View.VISIBLE);
                add7.setVisibility(GONE);
                Bitmap bm = BitmapFactory.decodeFile(filePath);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                byte[] byteArrayImage = baos.toByteArray();

                String encodedImage = Base64.encodeToString(byteArrayImage, Base64.NO_WRAP);
                uploadPhotos(encodedImage);
            }
            if (filepath.size() == 8) {
                progressBar8.setVisibility(View.VISIBLE);
                img8.setVisibility(View.VISIBLE);
                upload8.setVisibility(View.VISIBLE);
                add8.setVisibility(GONE);
                Bitmap bm = BitmapFactory.decodeFile(filePath);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                byte[] byteArrayImage = baos.toByteArray();

                String encodedImage = Base64.encodeToString(byteArrayImage, Base64.NO_WRAP);
                uploadPhotos(encodedImage);
            }
            if (filepath.size() == 9) {
                progressBar9.setVisibility(View.VISIBLE);
                img9.setVisibility(View.VISIBLE);
                upload9.setVisibility(View.VISIBLE);
                add9.setVisibility(GONE);
                Bitmap bm = BitmapFactory.decodeFile(filePath);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                byte[] byteArrayImage = baos.toByteArray();

                String encodedImage = Base64.encodeToString(byteArrayImage, Base64.NO_WRAP);
                uploadPhotos(encodedImage);
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.Companion.getError(data), Toast.LENGTH_SHORT).show();

        } else {
//            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        view = v;
        switch (v.getId()) {
            case R.id.selectimg1:
            case R.id.selectimg2:
            case R.id.selectimg3:
            case R.id.selectimg4:
            case R.id.selectimg5:
            case R.id.selectimg6:
            case R.id.selectimg7:
            case R.id.selectimg8:
            case R.id.selectimg9:
                ImagePicker.Companion.with(UploadPhotos.this)
                        //Crop image(Optional), Check Customization for more option
                        .compress(1024)            //Final image size will be less than 1 MB(Optional)
                        .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                        .start();
                break;
        }
    }

    void initUI() {
        recyclerView = findViewById(R.id.recyclerView);
        continueButtonProfile = findViewById(R.id.continueButtonProfile);
        progressBar = findViewById(R.id.progressbar);
        progressBar5 = findViewById(R.id.progressbar5);
        progressBar2 = findViewById(R.id.progressbar2);
        progressBar3 = findViewById(R.id.progressbar3);
        progressBar4 = findViewById(R.id.progressbar4);
        progressBar6 = findViewById(R.id.progressbar6);
        progressBar7 = findViewById(R.id.progressbar7);
        progressBar8 = findViewById(R.id.progressbar8);
        progressBar9 = findViewById(R.id.progressbar9);
        selectimg1 = findViewById(R.id.selectimg1);
        selectimg2 = findViewById(R.id.selectimg2);
        selectimg3 = findViewById(R.id.selectimg3);
        selectimg4 = findViewById(R.id.selectimg4);
        selectimg5 = findViewById(R.id.selectimg5);
        selectimg6 = findViewById(R.id.selectimg6);
        selectimg7 = findViewById(R.id.selectimg7);
        selectimg8 = findViewById(R.id.selectimg8);
        selectimg9 = findViewById(R.id.selectimg9);
        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);
        img5 = findViewById(R.id.img5);
        img6 = findViewById(R.id.img6);
        img7 = findViewById(R.id.img7);
        img8 = findViewById(R.id.img8);
        img9 = findViewById(R.id.img9);
        frame1 = findViewById(R.id.frame1);
        frame2 = findViewById(R.id.frame2);
        frame3 = findViewById(R.id.frame3);
        frame4 = findViewById(R.id.frame4);
        frame5 = findViewById(R.id.frame5);
        frame6 = findViewById(R.id.frame6);
        frame7 = findViewById(R.id.frame7);
        frame8 = findViewById(R.id.frame8);
        frame9 = findViewById(R.id.frame9);
        upload1 = findViewById(R.id.upload1);
        upload2 = findViewById(R.id.upload2);
        upload3 = findViewById(R.id.upload3);
        upload4 = findViewById(R.id.upload4);
        upload5 = findViewById(R.id.upload5);
        upload6 = findViewById(R.id.upload6);
        upload7 = findViewById(R.id.upload7);
        upload8 = findViewById(R.id.upload8);
        upload9 = findViewById(R.id.upload9);
        add1 = findViewById(R.id.add1);
        add2 = findViewById(R.id.add2);
        add3 = findViewById(R.id.add3);
        add4 = findViewById(R.id.add4);
        add5 = findViewById(R.id.add5);
        add6 = findViewById(R.id.add6);
        add7 = findViewById(R.id.add7);
        add8 = findViewById(R.id.add8);
        add9 = findViewById(R.id.add9);
    }

}
