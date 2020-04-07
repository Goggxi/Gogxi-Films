package com.gogxi.gogxifilms.ui.adapter;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.gogxi.gogxifilms.R;
import com.gogxi.gogxifilms.ui.fragment.FavoriteFragment;
import com.gogxi.gogxifilms.ui.fragment.MovieFavoritFragment;
import com.gogxi.gogxifilms.ui.fragment.MovieFragment;
import com.gogxi.gogxifilms.ui.fragment.TvFavoriteFragment;
import com.gogxi.gogxifilms.ui.fragment.TvShowFragment;

public class FragmentAdapterPage extends FragmentPagerAdapter {
    private final FavoriteFragment mContext;

    private final int[] TAB_TITLES = new int[]{
            R.string.title_1,
            R.string.title_2
    };

    public FragmentAdapterPage(FavoriteFragment context, FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new MovieFavoritFragment();
                break;

            case 1:
                fragment = new TvShowFragment();
                break;
        }
        assert fragment != null;
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

}
