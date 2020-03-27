package com.doozycod.getmaster.Fragments;

import android.content.Intent;
import android.os.Bundle;


import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.doozycod.getmaster.Activities.LoginActivity;
import com.doozycod.getmaster.Model.ResponseModel;
import com.doozycod.getmaster.R;
import com.doozycod.getmaster.Service.ApiService;
import com.doozycod.getmaster.Service.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class NavigationFragment extends Fragment {
    TextView homeBtn, messageNaviButton, favoriteNaviButton,
            profileNaviButton, editDetailsNaviButton, supportNaviButton,
            inviteNaviButton, logoutNaviButton;
    ApiService apiService;
    String deviceId;

    public NavigationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigation, container, false);
        initUI(view);
        apiService = ApiUtils.getAPIService();
        deviceId = Settings.Secure.getString(getContext().getContentResolver(),
                Settings.Secure.ANDROID_ID);
        onClickListners();
        return view;
    }

    private void onClickListners() {
        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                        .replace(R.id.frameLayout, new HomeFragment(), "").commit();

            }
        });
        profileNaviButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
//                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                        .replace(R.id.frameLayout, new ShowProfileFragment(), "")
                        .commit();

            }
        });
        editDetailsNaviButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                        .replace(R.id.frameLayout, new ShowEditFragment(), "")
                        .addToBackStack("")
                        .commit();

            }
        });
        supportNaviButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                        .replace(R.id.frameLayout, new HomeFragment(), "").commit();

            }
        });
        favoriteNaviButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction()
//                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                        .replace(R.id.frameLayout, new HomeFragment(), "").commit();

            }
        });
        logoutNaviButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutUser("6", deviceId);
            }
        });
        messageNaviButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getChildFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                        .replace(R.id.frameLayout, new HomeFragment(), "").commit();

            }
        });
        inviteNaviButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getChildFragmentManager().beginTransaction()
                        .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                        .replace(R.id.frameLayout, new HomeFragment(), "").commit();

            }
        });
    }

    private void logoutUser(String userId, String deviceId) {
        apiService.logoutUser(userId, deviceId).enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getResponse().equals("success")) {
                        startActivity(new Intent(getContext(), LoginActivity.class));
                        getActivity().finishAffinity();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
                Toast.makeText(getContext(), "Logged", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finishAffinity();
            }
        });
    }

    public void replaceFragments(Class fragmentClass) {
        Fragment fragment = null;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.frameLayout, fragment)
                .commit();
    }

    private void initUI(View view) {
        homeBtn = view.findViewById(R.id.homeNaviButton);
        messageNaviButton = view.findViewById(R.id.messageNaviButton);
        favoriteNaviButton = view.findViewById(R.id.favoriteNaviButton);
        profileNaviButton = view.findViewById(R.id.profileNaviButton);
        editDetailsNaviButton = view.findViewById(R.id.editDetailsNaviButton);
        supportNaviButton = view.findViewById(R.id.supportNaviButton);
        inviteNaviButton = view.findViewById(R.id.inviteNaviButton);
        logoutNaviButton = view.findViewById(R.id.logoutNaviButton);
    }
}
