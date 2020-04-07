package com.gogxi.gogxifilms.ui.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;


import com.gogxi.gogxifilms.R;
import com.gogxi.gogxifilms.ui.adapter.FragmentAdapterPage;
import com.gogxi.gogxifilms.ui.fragment.FavoriteFragment;
import com.gogxi.gogxifilms.ui.fragment.MovieFragment;
import com.gogxi.gogxifilms.ui.fragment.TvShowFragment;
import com.google.android.material.tabs.TabLayout;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    FragmentAdapterPage fragmentAdapterPage;
    ViewPager viewPager;
    Toolbar toolbar;
    TabLayout tabs;
    ChipNavigationBar bottomNav;
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNav = findViewById(R.id.bottom_nav);

        if (savedInstanceState==null){
            bottomNav.setItemSelected(R.id.movie, true);
            fragmentManager = getSupportFragmentManager();
            MovieFragment homeFragment = new MovieFragment();
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
                        fragment = new MovieFragment();
                        break;
                    case R.id.tv :
                        fragment = new TvShowFragment();
                        break;
                    case R.id.favorite :
                        fragment = new FavoriteFragment();
                        break;
                }

                if (fragment != null) {
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.fragment_container, fragment)
                            .commit();
                }
            }
        });


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        fragmentAdapterPage = new FragmentAdapterPage(this, getSupportFragmentManager());
//        viewPager = findViewById(R.id.view_pager);
//        viewPager.setAdapter(fragmentAdapterPage);
//
//        tabs = findViewById(R.id.tabs);
//        tabs.setupWithViewPager(viewPager);
//
//        Objects.requireNonNull(tabs.getTabAt(0)).setIcon(R.drawable.ic_movie);
//        Objects.requireNonNull(tabs.getTabAt(1)).setIcon(R.drawable.ic_tv_show);

    }

//    public void getFavorite(){
//        fragmentManager = getSupportFragmentManager();
//        FavoriteFragment favoriteFragment = new FavoriteFragment();
//        fragmentManager.beginTransaction()
//                .replace(R.id.fragment_container, favoriteFragment)
//                .commit();
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.language_settings) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}
