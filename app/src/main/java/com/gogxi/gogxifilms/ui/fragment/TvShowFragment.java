package com.gogxi.gogxifilms.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.gogxi.gogxifilms.R;
import com.gogxi.gogxifilms.data.model.TVShow;
import com.gogxi.gogxifilms.ui.adapter.TVShowAdapter;
import com.gogxi.gogxifilms.ui.adapter.TVShowTodayAdapter;
import com.gogxi.gogxifilms.ui.adapter.TVShowTopAdapter;
import com.gogxi.gogxifilms.viewmodel.TVShowPopularVM;
import com.gogxi.gogxifilms.viewmodel.TVShowTodayVM;
import com.gogxi.gogxifilms.viewmodel.TVShowTopVM;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private TVShowAdapter tvShowAdapter;
    private TVShowTopAdapter tvShowTopAdapter;
    private TVShowTodayAdapter tvShowTodayAdapter;
    private MultiSnapRecyclerView rvTvPopular, rvTvTop, rvTvToday;
    ProgressBar progressBar;
    NestedScrollView nestedScrollView;

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvTvPopular = view.findViewById(R.id.first_recycler_view);
        rvTvTop = view.findViewById(R.id.second_recycler_view);
        rvTvToday = view.findViewById(R.id.third_recycler_view);
        progressBar = view.findViewById(R.id.progress_tv);
        nestedScrollView = view.findViewById(R.id.scroll_tv);

        getPopular();
        getTop();
        getToday();
        showLoading(false);
    }

    private void getPopular(){
        tvShowAdapter = new TVShowAdapter(getContext());
        tvShowAdapter.notifyDataSetChanged();
        rvTvPopular.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        TVShowPopularVM tvShowPopularVM = new ViewModelProvider(this).get(TVShowPopularVM.class);
        tvShowPopularVM.setTvPopular(getString(R.string.language));
        tvShowPopularVM.getTvPopular().observe(this,getTvPopular);
        rvTvPopular.setAdapter(tvShowAdapter);
    }

    private Observer<ArrayList<TVShow>> getTvPopular = new Observer<ArrayList<TVShow>>() {
        @Override
        public void onChanged(ArrayList<TVShow> tvPopular) {
            if (tvPopular != null){
                tvShowAdapter.setData(tvPopular);
                showLoading(true);
            }
        }
    };

    private void getTop(){
        tvShowTopAdapter = new TVShowTopAdapter(getContext());
        tvShowTopAdapter.notifyDataSetChanged();
        rvTvTop.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        TVShowTopVM tvShowTopVM = new ViewModelProvider(this).get(TVShowTopVM.class);
        tvShowTopVM.setTvTop(getString(R.string.language));
        tvShowTopVM.getTvTop().observe(this,getTvTop);
        rvTvTop.setAdapter(tvShowTopAdapter);
    }

    private Observer<ArrayList<TVShow>> getTvTop = new Observer<ArrayList<TVShow>>() {
        @Override
        public void onChanged(ArrayList<TVShow> tvPopular) {
            if (tvPopular != null){
                tvShowTopAdapter.setData(tvPopular);
                showLoading(true);
            }
        }
    };

    private void getToday(){
        tvShowTodayAdapter = new TVShowTodayAdapter(getContext());
        tvShowTodayAdapter.notifyDataSetChanged();
        rvTvToday.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        TVShowTodayVM tvShowTodayVM = new ViewModelProvider(this).get(TVShowTodayVM.class);
        tvShowTodayVM.setTvToday(getString(R.string.language));
        tvShowTodayVM.getTvToday().observe(this,getTvToday);
        rvTvToday.setAdapter(tvShowTodayAdapter);
    }

    private Observer<ArrayList<TVShow>> getTvToday = new Observer<ArrayList<TVShow>>() {
        @Override
        public void onChanged(ArrayList<TVShow> tvPopular) {
            if (tvPopular != null){
                tvShowTodayAdapter.setData(tvPopular);
                showLoading(true);
            }
        }
    };

    private void showLoading(boolean state) {
        if (state){
            progressBar.setVisibility(View.GONE);
            nestedScrollView.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.VISIBLE);
            nestedScrollView.setVisibility(View.GONE);
        }
    }
}
