package com.gogxi.gogxifilms.ui.activity;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gogxi.gogxifilms.BuildConfig;
import com.gogxi.gogxifilms.R;
import com.gogxi.gogxifilms.data.model.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    TextView titleMovie,releaseMovie,directorMovie,runtimeMovie,languageMovie,rateMovie,genreMovie,storylineMovie;
    ImageView posterMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        titleMovie = findViewById(R.id.tv_title);
        releaseMovie = findViewById(R.id.tv_release);
        directorMovie = findViewById(R.id.tv_director);
        runtimeMovie = findViewById(R.id.tv_runtime);
        languageMovie = findViewById(R.id.tv_language);
        rateMovie = findViewById(R.id.tv_rate);
        genreMovie = findViewById(R.id.tv_genre);
        storylineMovie = findViewById(R.id.tv_storyline);
        posterMovie = findViewById(R.id.iv_poster);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        assert movie != null;
        titleMovie.setText(movie.getTitle());
        releaseMovie.setText(movie.getReleaseDate());
       // directorMovie.setText(movie.get);
       // runtimeMovie.setText(movie.get);
        languageMovie.setText(movie.getOriginalLanguage());
        rateMovie.setText(String.valueOf(movie.getVoteAverage()));
      //  genreMovie.setText(movie.getGenreMovie());
        storylineMovie.setText(movie.getOverview());
        Glide.with(this)
                .load( BuildConfig.IMG_URL + movie.getPosterPath())
                .apply(new RequestOptions().override(150, 350))
                .into(posterMovie);
    }
}
