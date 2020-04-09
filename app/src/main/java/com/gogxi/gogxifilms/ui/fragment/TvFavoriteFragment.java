package com.gogxi.gogxifilms.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gogxi.gogxifilms.R;
import com.gogxi.gogxifilms.data.db.TvHelper;
import com.gogxi.gogxifilms.data.model.TVShow;
import com.gogxi.gogxifilms.ui.adapter.TVShowFavoriteAdapter;

import java.util.ArrayList;


public class TvFavoriteFragment extends Fragment {
    private RecyclerView recyclerView;
    private TVShowFavoriteAdapter adapter;
    private TextView textView;

    public TvFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_favorite, container, false);
        textView = view.findViewById(R.id.tvnone);
        recyclerView = view.findViewById(R.id.rv_favorite_tv);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new TVShowFavoriteAdapter(getContext());
        stement();
        return view;
    }

    private void stement(){
        TvHelper showHelper = new TvHelper(getContext());
        showHelper.open();
        ArrayList<TVShow> tvList;
        tvList = showHelper.queryAll();
        adapter.setData(tvList);
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
