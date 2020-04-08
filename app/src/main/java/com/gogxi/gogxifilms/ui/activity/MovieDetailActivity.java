package com.gogxi.gogxifilms.ui.activity;



import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gogxi.gogxifilms.BuildConfig;
import com.gogxi.gogxifilms.R;
import com.gogxi.gogxifilms.data.db.DatabaseContract;
import com.gogxi.gogxifilms.data.db.DatabaseHelper;
import com.gogxi.gogxifilms.data.db.MovieHelper;
import com.gogxi.gogxifilms.data.model.Movie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_BACKRDOP;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_DATE;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_ID;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_LANGUAGE;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_OVERVIEW;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_POSTER;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_RATE;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_TABLE_NAME;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_TITLE;

public class MovieDetailActivity extends AppCompatActivity  implements View.OnClickListener{

    public static final String EXTRA_MOVIE = "extra_movie";
    private Movie movie;
    private TextView titleMovie,releaseMovie,languageMovie,rateMovie,storylineMovie;
    private ImageView posterMovie, backDrop;



    private FloatingActionButton fabFavAdd, fabFavDel;
    private MovieHelper movieHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        titleMovie = findViewById(R.id.tv_title);
        releaseMovie = findViewById(R.id.tv_release);
        languageMovie = findViewById(R.id.tv_language);
        rateMovie = findViewById(R.id.tv_rate);
        storylineMovie = findViewById(R.id.tv_storyline);
        posterMovie = findViewById(R.id.iv_poster);
        backDrop = findViewById(R.id.img_detail_photo_banner);

        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        setData();

        if (getSupportActionBar() != null ){
            getSupportActionBar().setTitle(R.string.details);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        fabFavAdd = findViewById(R.id.favorite_add);
        fabFavDel = findViewById(R.id.favorite_delete);
        fabFavAdd.setOnClickListener(this);
        fabFavDel.setOnClickListener(this);
    }

    private void setData(){
        if (movie != null){
            titleMovie.setText(movie.getTitle());
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

    private void saveFavorite() {
        Log.d("POSTER", "saveFavorite: " + movie.getPosterPath());
        movieHelper = new MovieHelper(this);
        String poster = movie.getPosterPath();
        String detailPoster = movie.getBackdropPath();
        String movieName = movie.getTitle();
        int movieVote = (int) movie.getVoteAverage();
        String overview = movie.getOverview();
        String releaseDate = movie.getReleaseDate();
        String lang = movie.getOriginalLanguage();

        movie.setTitle(movieName);
        movie.setOverview(overview);
        movie.setPosterPath(poster);
        movie.setReleaseDate(releaseDate);
        movie.setVoteAverage(movieVote);
        movie.setBackdropPath(detailPoster);
        movie.setOriginalLanguage(lang);

        movieHelper.open();
        movieHelper.insert(movie);
        movieHelper.close();
    }

    public boolean Exist(String item){
        String selected = MOVIE_TITLE+" =?";
        String[] selectedArgs={item};
        String limit="1";
        movieHelper= new MovieHelper(this);
        movieHelper.open();
        DatabaseHelper dataBaseHelper= new DatabaseHelper(MovieDetailActivity.this);
        SQLiteDatabase database = dataBaseHelper.getWritableDatabase();
        Cursor cursor= database.query(MOVIE_TABLE_NAME,null,selected,selectedArgs,null,null,null,limit);
        boolean exists;
        exists=(cursor.getCount() > 0);
        cursor.close();
        movieHelper.close();
        return exists;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.favorite_delete) {
            saveFavorite();
            Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            fabFavDel.setVisibility(View.GONE);
            fabFavAdd.setVisibility(View.VISIBLE);
        } else if (v.getId() == R.id.favorite_add){
            if (Exist(movie.getTitle())) {
                Log.d("EXIST", "movies exist");
                int movie_id = movie.getId();
                movieHelper = new MovieHelper(MovieDetailActivity.this);
                movieHelper.open();
                movieHelper.deleteMovie(movie_id);
                movieHelper.close();
                Toast.makeText(getApplicationContext(), "Removed", Toast.LENGTH_LONG).show();
                fabFavDel.setVisibility(View.VISIBLE);
                fabFavAdd.setVisibility(View.GONE);
            }
        }
    }
}
