package com.gogxi.gogxifilms.ui.fragment;


import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gogxi.gogxifilms.R;
import com.gogxi.gogxifilms.data.db.MovieHelper;
import com.gogxi.gogxifilms.data.model.Movie;
import com.gogxi.gogxifilms.ui.adapter.MovieFavoriteAdapter;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFavoritFragment extends Fragment {
    private RecyclerView recyclerView;
    private MovieFavoriteAdapter adapter;
    private TextView textView;


    public MovieFavoritFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_favorit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.tvNone);
        recyclerView = view.findViewById(R.id.rv_favorite_movie);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        adapter = new MovieFavoriteAdapter(getContext());
        stement();
    }

    private void stement(){
        MovieHelper showHelper = new MovieHelper(getContext());
        showHelper.open();
        ArrayList<Movie> movieList;
        movieList = showHelper.queryAll();
        adapter.setData(movieList);
        if(adapter.getItemCount() != 0){
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
            textView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        stement();
    }
}
