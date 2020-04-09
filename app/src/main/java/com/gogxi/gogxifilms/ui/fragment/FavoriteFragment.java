package com.gogxi.gogxifilms.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gogxi.gogxifilms.R;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class FavoriteFragment extends Fragment {
    private FragmentManager fragmentManager;;

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ChipNavigationBar bottomNav = view.findViewById(R.id.bottom_nav);

        if (savedInstanceState==null){
            bottomNav.setItemSelected(R.id.movie, true);
            fragmentManager = getChildFragmentManager();
            MovieFavoritFragment homeFragment = new MovieFavoritFragment();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, homeFragment)
                    .commit();

        }

        bottomNav.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int id) {
                Fragment fragment = null;
                switch (id){
                    case R.id.movie :
                        fragment = new MovieFavoritFragment();
                        break;
                    case R.id.tvShow :
                        fragment = new TvFavoriteFragment();
                        break;
                }

                if (fragment != null) {
                    fragmentManager = getChildFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                }
            }
        });
    }
}
