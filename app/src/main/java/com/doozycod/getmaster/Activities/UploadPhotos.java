package com.doozycod.getmaster.Activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.doozycod.getmaster.Adapter.UploadPhotoAdapter;
import com.doozycod.getmaster.R;
import com.github.dhaval2404.imagepicker.ImagePicker;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UploadPhotos extends AppCompatActivity implements View.OnClickListener {
    UploadPhotoAdapter uploadPhotoAdapter;
    List<String> filepath = new ArrayList<>();
    ImageView selectimg1, selectimg2, selectimg3, selectimg4, selectimg5, selectimg6, selectimg7, selectimg8, selectimg9;
    Uri uri;
    View view;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photos);
        recyclerView = findViewById(R.id.recyclerView);
        selectimg1 = findViewById(R.id.selectimg1);
        selectimg2 = findViewById(R.id.selectimg2);
        selectimg3 = findViewById(R.id.selectimg3);
        selectimg4 = findViewById(R.id.selectimg4);
        selectimg5 = findViewById(R.id.selectimg5);
        selectimg6 = findViewById(R.id.selectimg6);
        selectimg7 = findViewById(R.id.selectimg7);
        selectimg8 = findViewById(R.id.selectimg8);
        selectimg9 = findViewById(R.id.selectimg9);
        uploadPhotoAdapter = new UploadPhotoAdapter(this, filepath);

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        selectimg1.setOnClickListener(this);
        selectimg2.setOnClickListener(this);
        selectimg3.setOnClickListener(this);
        selectimg4.setOnClickListener(this);
        selectimg5.setOnClickListener(this);
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

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            Uri fileUri = data.getData();
            uri = fileUri;
            selectimg1.setImageURI(fileUri);
            //You can get File object from intent
            File file = ImagePicker.Companion.getFile(data);
            //You can also get File Path from intent
            String filePath = ImagePicker.Companion.getFilePath(data);
            filepath.add(filePath);
            recyclerView.setAdapter(uploadPhotoAdapter);

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
}
