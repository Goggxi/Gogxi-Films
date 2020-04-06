package com.gogxi.gogxifilms.ui.activity;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gogxi.gogxifilms.BuildConfig;
import com.gogxi.gogxifilms.R;
import com.gogxi.gogxifilms.data.model.Movie;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";
    Movie movie;

    TextView titleMovie,releaseMovie,languageMovie,rateMovie,storylineMovie;
    ImageView posterMovie, backDrop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapse_toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    //    getSupportActionBar().setHomeButtonEnabled(true);
    //    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        collapsingToolbarLayout.setTitle("Dicoding");

//        collapsingToolbarLayout.setCollapsedTitleTextColor(
//                ContextCompat.getColor(this, R.color.colorAccentLight));
//        collapsingToolbarLayout.setExpandedTitleColor(
//                ContextCompat.getColor(this, R.color.colorPrimary));

        titleMovie = findViewById(R.id.tv_title);
        releaseMovie = findViewById(R.id.tv_release);
        languageMovie = findViewById(R.id.tv_language);
        rateMovie = findViewById(R.id.tv_rate);
        storylineMovie = findViewById(R.id.tv_storyline);
        posterMovie = findViewById(R.id.iv_poster);
        backDrop = findViewById(R.id.img_detail_photo_banner);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        assert movie != null;
        titleMovie.setText(movie.getTitle());
//        releaseMovie.setText(movie.getReleaseDate());
        languageMovie.setText(movie.getOriginalLanguage());
        rateMovie.setText(String.valueOf(movie.getVoteAverage()));
        storylineMovie.setText(movie.getOverview());
        Glide.with(this)
                .load( BuildConfig.POSTER + movie.getPosterPath())
                .apply(new RequestOptions().override(150, 350))
                .into(posterMovie);
        Glide.with(this)
                .load( BuildConfig.BACKDROP + movie.getBackdropPath())
                .apply(new RequestOptions().override(500, 350))
                .into(backDrop);

        getReleaseDate();

        if (getSupportActionBar() != null ){
            getSupportActionBar().setTitle(R.string.details);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void getReleaseDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        String date = movie.getReleaseDate();
        try {
            Date newDate = dateFormat.parse(date);
            dateFormat = new SimpleDateFormat("dd MMMM yyyy", Locale.US);
            assert newDate != null;
            releaseMovie.setText(dateFormat.format(newDate));
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
