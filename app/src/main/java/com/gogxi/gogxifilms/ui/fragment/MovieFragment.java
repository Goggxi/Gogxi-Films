package com.gogxi.gogxifilms.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.gogxi.gogxifilms.R;
import com.gogxi.gogxifilms.data.model.MoviePlayNow;
import com.gogxi.gogxifilms.data.model.MoviePopular;
import com.gogxi.gogxifilms.ui.adapter.MovieNowAdapter;
import com.gogxi.gogxifilms.ui.adapter.MoviePopularAdapter;
import com.gogxi.gogxifilms.viewmodel.MovieNowVM;
import com.gogxi.gogxifilms.viewmodel.MoviePopularVM;

import java.util.ArrayList;


public class MovieFragment extends Fragment {
    private MoviePopularAdapter moviePopularAdapter;
    private RecyclerView rvMoviePopular;
    private MoviePopularVM moviePopularVM;

    private MovieNowAdapter movieNowAdapter;
    private RecyclerView rvMovieNow;
    private MovieNowVM movieNowVM;

    private ProgressBar progressBar;

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


        rvMoviePopular = view.findViewById(R.id.rvMoviePopular);
        rvMovieNow = view.findViewById(R.id.rvMoviePlayNow);

        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        getPopular();
        getNow();
    }

    private void getPopular(){
        moviePopularAdapter = new MoviePopularAdapter(getContext());
        moviePopularAdapter.notifyDataSetChanged();
        rvMoviePopular.setLayoutManager(new GridLayoutManager(getContext(), 3));
//        rvMoviePopular.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        moviePopularVM = new ViewModelProvider(this).get(MoviePopularVM.class);
        moviePopularVM.setMoviePopular();
        moviePopularVM.getMoviePopular().observe(this,getMoviePopular);
        rvMoviePopular.setAdapter(moviePopularAdapter);
    }

    private Observer<ArrayList<MoviePopular>> getMoviePopular = new Observer<ArrayList<MoviePopular>>() {
        @Override
        public void onChanged(ArrayList<MoviePopular> moviePopular) {
            if (moviePopular != null){
                moviePopularAdapter.setData(moviePopular);
                progressBar.setVisibility(View.INVISIBLE);
            }
        }
    };

    private void getNow(){
        movieNowAdapter = new MovieNowAdapter(getContext());
        movieNowAdapter.notifyDataSetChanged();
        rvMovieNow.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
//        rvMovieNow.setLayoutManager(new GridLayoutManager(getContext(), 3));
        movieNowVM = new ViewModelProvider(this).get(MovieNowVM.class);
        movieNowVM.setMovieNow();
        movieNowVM.getMovieNow().observe(this,getMovieNow);
        rvMovieNow.setAdapter(movieNowAdapter);
    }

    private Observer<ArrayList<MoviePlayNow>> getMovieNow = new Observer<ArrayList<MoviePlayNow>>() {
        @Override
        public void onChanged(ArrayList<MoviePlayNow> movieNow) {
            if (movieNow != null){
                movieNowAdapter.setData(movieNow);
                progressBar.setVisibility(View.INVISIBLE);
            }
        }
    };
}
