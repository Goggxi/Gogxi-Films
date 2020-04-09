package com.gogxi.gogxifilms.ui.activity;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;


import com.gogxi.gogxifilms.R;
import com.gogxi.gogxifilms.ui.adapter.FragmentAdapterPage;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    FragmentAdapterPage fragmentAdapterPage;
    ViewPager viewPager;
    Toolbar toolbar;
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentAdapterPage = new FragmentAdapterPage(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(fragmentAdapterPage);

        tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        Objects.requireNonNull(tabs.getTabAt(0)).setIcon(R.drawable.ic_movie);
        Objects.requireNonNull(tabs.getTabAt(1)).setIcon(R.drawable.ic_tv_show);
        Objects.requireNonNull(tabs.getTabAt(2)).setIcon(R.drawable.ic_favorite);

    }

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
