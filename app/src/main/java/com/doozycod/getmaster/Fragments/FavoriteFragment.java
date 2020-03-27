package com.doozycod.getmaster.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.doozycod.getmaster.Adapter.FavoriteUserAdapter;
import com.doozycod.getmaster.Model.FavoriteModel;
import com.doozycod.getmaster.Model.UserFavorite;
import com.doozycod.getmaster.R;
import com.doozycod.getmaster.Service.ApiService;
import com.doozycod.getmaster.Service.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FavoriteFragment extends Fragment {
    RecyclerView recyclerView;
    ApiService apiService;
    List<UserFavorite> list;
    LinearLayout homeFavButton, favMainButton, favPeopleButton, favMessageButton;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = view.findViewById(R.id.favoriteRecyclerView);
        homeFavButton = view.findViewById(R.id.homeFavButton);
        favMainButton = view.findViewById(R.id.favMainButton);
        favMessageButton = view.findViewById(R.id.messageFavButton);
        favPeopleButton = view.findViewById(R.id.peopleFavButton);
        apiService = ApiUtils.getAPIService();
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getFavorites("6");

        homeFavButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager()
                        .beginTransaction().replace(R.id.frameLayout, new HomeFragment(), "")
                        .addToBackStack("")
                        .commit();
            }
        });
        favMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        favPeopleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        favMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return view;
    }

    private void getFavorites(String id) {
        apiService.getFavoriteUsers(id).enqueue(new Callback<FavoriteModel>() {
            @Override
            public void onResponse(Call<FavoriteModel> call, Response<FavoriteModel> response) {
                if (response.body().getUserFavorites() == null) {
                    Toast.makeText(getContext(), "You haven't added to Favorite yet!", Toast.LENGTH_SHORT).show();
                } else {
                    list = response.body().getUserFavorites();
                    recyclerView.setAdapter(new FavoriteUserAdapter(getContext(), list));
                }
            }

            @Override
            public void onFailure(Call<FavoriteModel> call, Throwable t) {
                Log.e("Favorite", "onFailure: " + t.getMessage());
            }
        });
    }
}
