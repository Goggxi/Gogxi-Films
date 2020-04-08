package com.gogxi.gogxifilms.ui.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gogxi.gogxifilms.R;
import com.gogxi.gogxifilms.data.db.MovieHelper;
import com.gogxi.gogxifilms.data.model.Movie;
import com.gogxi.gogxifilms.ui.adapter.MovieFavoriteAdapter;
import com.gogxi.gogxifilms.ui.adapter.MovieNowAdapter;
import com.gogxi.gogxifilms.ui.adapter.MoviePopularAdapter;
import com.gogxi.gogxifilms.ui.adapter.MovieUpcomingAdapter;
import com.gogxi.gogxifilms.viewmodel.MovieNowVM;
import com.gogxi.gogxifilms.viewmodel.MoviePopularVM;
import com.google.android.material.snackbar.Snackbar;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFavoritFragment extends Fragment {
    private RecyclerView recyclerView;
    private MovieFavoriteAdapter adapter;
    private MovieHelper showHelper;
    private ArrayList<Movie> movieList;


    public MovieFavoritFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_favorit, container, false);
        recyclerView = view.findViewById(R.id.rv_favorite_movie);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new MovieFavoriteAdapter(getContext());
        showHelper = new MovieHelper(getContext());
        showHelper.open();
        movieList = new ArrayList<>();
        movieList = showHelper.queryAll();
        adapter.setData(movieList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        MovieHelper item = new MovieHelper(getContext());
        item.open();
        movieList=item.queryAll();
        adapter.setData(movieList);
        recyclerView.setAdapter(adapter);
    }
}
