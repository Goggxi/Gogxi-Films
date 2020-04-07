package com.gogxi.gogxifilms.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gogxi.gogxifilms.R;
import com.gogxi.gogxifilms.ui.adapter.FragmentAdapterPage;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment {

    FragmentAdapterPage fragmentAdapterPage ;
    ViewPager viewPager;
    TabLayout tabs;

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



        fragmentAdapterPage = new FragmentAdapterPage(this, getChildFragmentManager());
        viewPager = view.findViewById(R.id.view_pager);
        viewPager.setAdapter(fragmentAdapterPage);

        tabs = view.findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }
}
