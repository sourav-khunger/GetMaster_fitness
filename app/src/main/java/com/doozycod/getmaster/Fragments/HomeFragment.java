package com.doozycod.getmaster.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dalong.francyconverflow.FancyCoverFlow;
import com.doozycod.getmaster.Adapter.RecentUserAdapter;
import com.doozycod.getmaster.Adapter.RecyclerScrollAdapter;
import com.doozycod.getmaster.Model.ExploreModel;
import com.doozycod.getmaster.Model.FeaturedUser;
import com.doozycod.getmaster.Model.RecentUser;
import com.doozycod.getmaster.R;
import com.doozycod.getmaster.Service.ApiService;
import com.doozycod.getmaster.Service.ApiUtils;
import com.doozycod.getmaster.ShadowTransformer;
import com.doozycod.getmaster.SharedPreferenceMethod;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>, DiscreteScrollView.ScrollStateChangeListener<RecyclerView.ViewHolder> {


    private List<RecentUser> mlist = new ArrayList<>();
    ApiService apiService;
    SharedPreferenceMethod sharedPreferenceMethod;

    List<FeaturedUser> mFancyCoverFlows = new ArrayList<>();

    private DiscreteScrollView mGalleryRecyclerView;
    RecyclerView recyclerView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        apiService = ApiUtils.getAPIService();
        getuserList();

        recyclerView = view.findViewById(R.id.recentUsersList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        mGalleryRecyclerView = view.findViewById(R.id.picker2);

        mGalleryRecyclerView.setSlideOnFling(true);
        mGalleryRecyclerView.addOnItemChangedListener(this);
        mGalleryRecyclerView.addScrollStateChangeListener(this);
        mGalleryRecyclerView.setItemTransitionTimeMillis(150);
        mGalleryRecyclerView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.9f)
                .build());
        return view;
    }

    private void getuserList() {
        apiService.getExplore("6").enqueue(new Callback<ExploreModel>() {
            @Override
            public void onResponse(Call<ExploreModel> call, Response<ExploreModel> response) {
                mFancyCoverFlows = response.body().getFeaturedUsers();
                Log.e(TAG, "onResponse: " + mFancyCoverFlows.size());
                mlist = response.body().getRecentUsers();
                recyclerView.setAdapter(new RecentUserAdapter(getContext(), mlist));
                mGalleryRecyclerView.setAdapter(new RecyclerScrollAdapter(getContext(), mFancyCoverFlows));

                mGalleryRecyclerView.scrollToPosition(mFancyCoverFlows.size() / 2);
            }

            @Override
            public void onFailure(Call<ExploreModel> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onScrollStart(RecyclerView.ViewHolder currentItemHolder, int adapterPosition) {

    }

    @Override
    public void onScrollEnd(RecyclerView.ViewHolder currentItemHolder, int adapterPosition) {

    }

    @Override
    public void onScroll(float scrollPosition, int currentPosition, int newPosition, RecyclerView.ViewHolder currentHolder, RecyclerView.ViewHolder newCurrent) {

    }

    @Override
    public void onCurrentItemChanged(RecyclerView.ViewHolder viewHolder, int adapterPosition) {

    }
}
