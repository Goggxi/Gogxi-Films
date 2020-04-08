package com.gogxi.gogxifilms.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gogxi.gogxifilms.R;
import com.gogxi.gogxifilms.data.db.TvHelper;
import com.gogxi.gogxifilms.data.model.TVShow;
import com.gogxi.gogxifilms.ui.adapter.TVShowFavoriteAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvFavoriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private TVShowFavoriteAdapter adapter;
    private TvHelper showHelper;
    private ArrayList<TVShow> movieList;

    public TvFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_favorite, container, false);
        recyclerView = view.findViewById(R.id.rv_favorite_tv);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new TVShowFavoriteAdapter(getContext());
        showHelper = new TvHelper(getContext());
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
        TvHelper item = new TvHelper(getContext());
        item.open();
        movieList=item.queryAll();
        adapter.setData(movieList);
        recyclerView.setAdapter(adapter);
    }
}
