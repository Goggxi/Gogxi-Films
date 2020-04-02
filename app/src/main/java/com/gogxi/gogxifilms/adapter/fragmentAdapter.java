package com.gogxi.gogxifilms.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.gogxi.gogxifilms.fragment.MovieFragment;
import com.gogxi.gogxifilms.fragment.TvShowFragment;

public class fragmentAdapter extends FragmentStateAdapter {

    public fragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 :
                return new MovieFragment();
            default:
                return new TvShowFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
