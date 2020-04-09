package com.gogxi.gogxifilms.ui.activity;



import android.content.ContentValues;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gogxi.gogxifilms.BuildConfig;
import com.gogxi.gogxifilms.R;
import com.gogxi.gogxifilms.data.db.MovieHelper;
import com.gogxi.gogxifilms.data.model.Movie;
import com.google.android.material.appbar.AppBarLayout;
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
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_TITLE;

public class MovieDetailActivity extends AppCompatActivity  implements View.OnClickListener{

    public static final String EXTRA_MOVIE = "extra_movie";
    private Movie movie;
    private TextView titleMovie,releaseMovie,languageMovie,rateMovie,storylineMovie;
    private ImageView posterMovie, backDrop;
    private FloatingActionButton favAdd, favDel;
    private MovieHelper movieHelper;
    private String toastDel;
    private String toastAdd;
    private String failed;
    private NestedScrollView nestedScrollView;
    private AppBarLayout appBarLayout;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        titleMovie = findViewById(R.id.tv_title);
        releaseMovie = findViewById(R.id.tv_release);
        languageMovie = findViewById(R.id.tv_language);
        rateMovie = findViewById(R.id.tv_rate);
        storylineMovie = findViewById(R.id.tv_storyline);
        posterMovie = findViewById(R.id.iv_poster);
        backDrop = findViewById(R.id.img_detail_photo_banner);

        nestedScrollView = findViewById(R.id.nestedScrollView);
        appBarLayout = findViewById(R.id.appbar);
        progressBar = findViewById(R.id.progress_movie_detail);

        toastDel = getString(R.string.delete_favorite);
        toastAdd = getString(R.string.add_favorite);
        failed = getString(R.string.failed);

        movieHelper = MovieHelper.getInstance(getApplicationContext());
        movieHelper.open();
        String id = Integer.toString(movie.getId());
        favAdd = findViewById(R.id.favorite_add);
        favDel = findViewById(R.id.favorite_delete);
        favAdd.setOnClickListener(this);
        favDel.setOnClickListener(this);

        setData();

        if (getSupportActionBar() != null ){
            getSupportActionBar().setTitle(R.string.details);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        showLoading(true);


        /*
         * fungsi untuk mengecek id apakah sudah terdaftar di database
         * dan untuk mengatur tombol favorit
         * by. Gogxi (08-04-2020)
         * */
        if (movieHelper.checkMovie(id)){
            favAdd.setVisibility(View.GONE);
            favDel.setVisibility(View.VISIBLE);
        }
    }

    /**
     * fungsi untuk mengatur data yang berada di detai activity
     * by. Gogxi (08-04-2020)
     * */
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
            showLoading(false);
        }
    }

    /**
     * fungsi untuk mengatur format tanggal
     * by. Gogxi (08-04-2020)
     * */
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

    /**
     * fungsi untuk kembali ke halaman sebelumnya
     * by. Gogxi (08-04-2020)
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * fungsi untuk insert data ke database movie
     * by. Gogxi (08-04-2020)
     * */
    private void insertDB(){
        int movieId = movie.getId();
        String title = movie.getTitle();
        String date = movie.getReleaseDate();
        int rate = (int) movie.getVoteAverage();
        String language = movie.getOriginalLanguage();
        String overview = movie.getOverview();
        String poster = movie.getPosterPath();
        String back = movie.getBackdropPath();

        ContentValues values = new ContentValues();
        values.put(MOVIE_ID, movieId);
        values.put(MOVIE_TITLE, title);
        values.put(MOVIE_DATE, date);
        values.put(MOVIE_RATE, rate);
        values.put(MOVIE_LANGUAGE, language);
        values.put(MOVIE_OVERVIEW, overview);
        values.put(MOVIE_POSTER, poster);
        values.put(MOVIE_BACKRDOP, back);
        long result = movieHelper.insert(values);
        if (result > 0) {
            favAdd.setVisibility(View.GONE);
            favDel.setVisibility(View.VISIBLE);
            Toast.makeText(MovieDetailActivity.this, toastAdd, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MovieDetailActivity.this, failed, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * fungsi untuk menghapus data dari database berdasarkan id
     * by. Gogxi (08-04-2020)
     * */
    private void deleteDB(){
        long result = movieHelper.deleteById(String.valueOf(movie.getId()));
        if (result > 0) {
            Toast.makeText(MovieDetailActivity.this, toastDel, Toast.LENGTH_SHORT).show();
            favDel.setVisibility(View.GONE);
            favAdd.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(MovieDetailActivity.this, failed, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.favorite_add :
                insertDB();
                break;
            case R.id.favorite_delete:
                deleteDB();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        movieHelper.close();
    }

    private void showLoading(boolean state) {
        if (state){
            progressBar.setVisibility(View.GONE);
            nestedScrollView.setVisibility(View.VISIBLE);
            appBarLayout.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.VISIBLE);
            nestedScrollView.setVisibility(View.GONE);
            appBarLayout.setVisibility(View.GONE);
            favDel.setVisibility(View.GONE);
        }
    }
}
