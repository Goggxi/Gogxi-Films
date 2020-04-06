package com.gogxi.gogxifilms.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gogxi.gogxifilms.BuildConfig;
import com.gogxi.gogxifilms.R;
import com.gogxi.gogxifilms.data.model.TVShow;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TvDetailActivity extends AppCompatActivity {

    public static final String EXTRA_TV = "extra_tv";
    TVShow tvShow;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;
    TextView title,release,language,rate,storyline;
    ImageView poster, backDrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);

        collapsingToolbarLayout = findViewById(R.id.collapse_toolbar);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = findViewById(R.id.tv_title);
        release = findViewById(R.id.tv_release);
        language = findViewById(R.id.tv_language);
        rate = findViewById(R.id.tv_rate);
        storyline = findViewById(R.id.tv_storyline);
        poster = findViewById(R.id.iv_poster);
        backDrop = findViewById(R.id.img_detail_photo_banner);

        tvShow = getIntent().getParcelableExtra(EXTRA_TV);

        if (tvShow != null){
            title.setText(tvShow.getName());
            language.setText(tvShow.getOriginalLanguage());
            rate.setText(String.valueOf(tvShow.getVoteAverage()));
            storyline.setText(tvShow.getOverview());
            Glide.with(this)
                    .load( BuildConfig.POSTER + tvShow.getPosterPath())
                    .apply(new RequestOptions().override(150, 350))
                    .into(poster);
            Glide.with(this)
                    .load( BuildConfig.BACKDROP + tvShow.getBackdropPath())
                    .apply(new RequestOptions().override(500, 350))
                    .into(backDrop);
            getReleaseDate();
        }

        if (getSupportActionBar() != null ){
            getSupportActionBar().setTitle(R.string.details);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getReleaseDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String date = tvShow.getFirstAirDate();
        try {
            Date newDate = dateFormat.parse(date);
            dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
            assert newDate != null;
            release.setText(dateFormat.format(newDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
