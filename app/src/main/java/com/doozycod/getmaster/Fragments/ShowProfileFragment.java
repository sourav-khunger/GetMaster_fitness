package com.doozycod.getmaster.Fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.doozycod.getmaster.Activities.AddProfilePicActivity;
import com.doozycod.getmaster.Activities.SelectLanguages;
import com.doozycod.getmaster.Adapter.InterestAdapter;
import com.doozycod.getmaster.Adapter.LanguageIspeakAdapter;
import com.doozycod.getmaster.Adapter.UploadPhotoAdapter;
import com.doozycod.getmaster.CustomProgressBar;
import com.doozycod.getmaster.Model.LanguageModel;
import com.doozycod.getmaster.Model.ProfileModel;
import com.doozycod.getmaster.Model.UserPhotosModel;
import com.doozycod.getmaster.R;
import com.doozycod.getmaster.Service.ApiService;
import com.doozycod.getmaster.Service.ApiUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowProfileFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerView languageRecycler;
    CircleImageView circleImageView;
    ApiService apiService;
    CustomProgressBar customProgressBar;
    List<String> list;
    List<UserPhotosModel.UserPhoto> userPhotosModelList;
    TextView userNameTxt;

    public ShowProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_profile, container, false);
        userNameTxt = view.findViewById(R.id.userNameProfile);
        circleImageView = view.findViewById(R.id.profilePicProfileFrag);
        recyclerView = view.findViewById(R.id.showProfileGallery);
        languageRecycler = view.findViewById(R.id.languageRecycler);
        languageRecycler.setHasFixedSize(true);
        languageRecycler.setItemAnimator(new DefaultItemAnimator());
        languageRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        apiService = ApiUtils.getAPIService();
        customProgressBar = new CustomProgressBar(getContext());
        getProfileApi("6");
        getPhotos("6");
        getLanguages("6");
        return view;
    }

    private void getPhotos(String id) {
        apiService.getPhotos(id).enqueue(new Callback<UserPhotosModel>() {
            @Override
            public void onResponse(Call<UserPhotosModel> call, Response<UserPhotosModel> response) {
                userPhotosModelList = response.body().getUserPhotos();
                Log.e("Show Profile Photos", "onResponse: " + userPhotosModelList.size());
                recyclerView.setAdapter(new UploadPhotoAdapter(getContext(), userPhotosModelList, true));

            }

            @Override
            public void onFailure(Call<UserPhotosModel> call, Throwable t) {
                Log.e("Get Photos", "onFailure: " + t.getMessage());
            }
        });
    }

    private void getLanguages(String id) {
        apiService.getLanguages(id).enqueue(new Callback<LanguageModel>() {
            @Override
            public void onResponse(Call<LanguageModel> call, Response<LanguageModel> response) {
                list = response.body().getUserLanguages().getLanguages();
                languageRecycler.setAdapter(new LanguageIspeakAdapter(getContext(), list));
                Log.e("Languages I speak", "onResponse: " + list.size());
            }

            @Override
            public void onFailure(Call<LanguageModel> call, Throwable t) {
                Log.e("Languages ", "onFailure: " + t.getMessage());
            }
        });
    }

    private void getProfileApi(String id) {
        apiService.getProfile(id).enqueue(new Callback<ProfileModel>() {
            @Override
            public void onResponse(Call<ProfileModel> call, Response<ProfileModel> response) {
                Log.e("Get profile", "onResponse: " + response.body().getUserData().getFullName());

                byte[] decodedString = Base64.decode(response.body().getUserData().getProfilePic().trim(), Base64.NO_WRAP);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                Glide.with(getActivity()).load(decodedByte).into(circleImageView);
                userNameTxt.setText(response.body().getUserData().getFullName());
            }

            @Override
            public void onFailure(Call<ProfileModel> call, Throwable t) {
                customProgressBar.hideProgress();
                Log.e("Get Profile", "onFailure: " + t.getMessage());
            }
        });
    }

}
