package com.gogxi.gogxifilms.ui.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gogxi.gogxifilms.R;
import com.gogxi.gogxifilms.data.model.Movie;
import com.gogxi.gogxifilms.ui.adapter.MovieNowAdapter;
import com.gogxi.gogxifilms.ui.adapter.MoviePopularAdapter;
import com.gogxi.gogxifilms.ui.adapter.MovieUpcomingAdapter;
import com.gogxi.gogxifilms.viewmodel.MovieNowVM;
import com.gogxi.gogxifilms.viewmodel.MoviePopularVM;
import com.gogxi.gogxifilms.viewmodel.MovieUpcomingVM;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.ArrayList;


public class MovieFragment extends Fragment {
    private MoviePopularAdapter moviePopularAdapter;
    private MovieUpcomingAdapter movieUpcomingAdapter;
    private MovieNowAdapter movieNowAdapter;
    private MultiSnapRecyclerView rvMovieNow, rvMoviePopular , rvMovieUpcoming;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvMoviePopular = view.findViewById(R.id.rvFrist);
        rvMovieNow = view.findViewById(R.id.rvSecond);
        rvMovieUpcoming = view.findViewById(R.id.rvThird);

        getPopular();
        getNow();
        getUpcoming();
    }

    private void getPopular(){
        moviePopularAdapter = new MoviePopularAdapter(getContext());
        moviePopularAdapter.notifyDataSetChanged();
        rvMoviePopular.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        MoviePopularVM moviePopularVM = new ViewModelProvider(this).get(MoviePopularVM.class);
        moviePopularVM.setMoviePopular(getString(R.string.language));
        moviePopularVM.getMoviePopular().observe(this,getMoviePopular);
        rvMoviePopular.setAdapter(moviePopularAdapter);
    }

    private Observer<ArrayList<Movie>> getMoviePopular = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movie) {
            if (movie != null){
                moviePopularAdapter.setData(movie);
            }
        }
    };

    private void getNow(){
        movieNowAdapter = new MovieNowAdapter(getContext());
        movieNowAdapter.notifyDataSetChanged();
        rvMovieNow.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        MovieNowVM movieNowVM = new ViewModelProvider(this).get(MovieNowVM.class);
        movieNowVM.setMovieNow(getString(R.string.language));
        movieNowVM.getMovieNow().observe(this,getMovieNow);
        rvMovieNow.setAdapter(movieNowAdapter);
    }

    private Observer<ArrayList<Movie>> getMovieNow = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movie) {
            if (movie != null){
                movieNowAdapter.setData(movie);
            }
        }
    };

    private void getUpcoming(){
        movieUpcomingAdapter = new MovieUpcomingAdapter(getContext());
        movieUpcomingAdapter.notifyDataSetChanged();
        rvMovieUpcoming.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        MovieUpcomingVM movieUpcomingVM = new ViewModelProvider(this).get(MovieUpcomingVM.class);
        movieUpcomingVM.setMovieUpcoming(getString(R.string.language));
        movieUpcomingVM.getMovieUpcoming().observe(this,getMovieUpcoming);
        rvMovieUpcoming.setAdapter(movieUpcomingAdapter);
    }

    private Observer<ArrayList<Movie>> getMovieUpcoming = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movie) {
            if (movie != null){
                movieUpcomingAdapter.setData(movie);
            }
        }
    };
}
