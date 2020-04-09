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
    private ArrayList<Movie> movieList;
    private TextView mTextView;


    public MovieFavoritFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_favorit, container, false);

        recyclerView = view.findViewById(R.id.rv_favorite_movie);
        mTextView = view.findViewById(R.id.tvNone);


        mTextView.setVisibility(View.GONE);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new MovieFavoriteAdapter(getContext());
        MovieHelper showHelper = new MovieHelper(getContext());
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
