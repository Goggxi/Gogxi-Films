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
import com.gogxi.gogxifilms.data.model.TVShow;
import com.gogxi.gogxifilms.ui.adapter.TVShowAdapter;
import com.gogxi.gogxifilms.viewmodel.TVShowPopularVM;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private TVShowAdapter tvShowAdapter;
    private MultiSnapRecyclerView rvTvPopular;
    private TVShowPopularVM tvShowPopularVM;

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

        getPopular();
    }

    private void getPopular(){
        tvShowAdapter = new TVShowAdapter(getContext());
        tvShowAdapter.notifyDataSetChanged();
        rvTvPopular.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        tvShowPopularVM = new ViewModelProvider(this).get(TVShowPopularVM.class);
        tvShowPopularVM.setTvPopular();
        tvShowPopularVM.getTvPopular().observe(this,getTvPopular);
        rvTvPopular.setAdapter(tvShowAdapter);
    }

    private Observer<ArrayList<TVShow>> getTvPopular = new Observer<ArrayList<TVShow>>() {
        @Override
        public void onChanged(ArrayList<TVShow> tvPopular) {
            if (tvPopular != null){
                tvShowAdapter.setData(tvPopular);
            }
        }
    };
}
