package com.gogxi.gogxifilms.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gogxi.gogxifilms.BuildConfig;
import com.gogxi.gogxifilms.R;
import com.gogxi.gogxifilms.data.db.TvHelper;
import com.gogxi.gogxifilms.data.model.TVShow;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_BACKRDOP;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_DATE;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_ID;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_LANGUAGE;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_OVERVIEW;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_POSTER;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_RATE;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_TITLE;

public class TvDetailActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String EXTRA_TV = "extra_tv";
    private TVShow tvShow;
    private TextView title,release,language,rate,storyline;
    private ImageView poster, backDrop;
    private FloatingActionButton favAdd, fabFavDel;
    private TvHelper tvHelper;
    private String toastDel, toastAdd, failed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        title = findViewById(R.id.tv_title);
        release = findViewById(R.id.tv_release);
        language = findViewById(R.id.tv_language);
        rate = findViewById(R.id.tv_rate);
        storyline = findViewById(R.id.tv_storyline);
        poster = findViewById(R.id.iv_poster);
        backDrop = findViewById(R.id.img_detail_photo_banner);

        toastDel = getString(R.string.delete_favorite);
        toastAdd = getString(R.string.add_favorite);
        failed = getString(R.string.failed);

        tvShow = getIntent().getParcelableExtra(EXTRA_TV);
        setData();

        if (getSupportActionBar() != null ){
            getSupportActionBar().setTitle(R.string.details);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        tvHelper = TvHelper.getInstance(getApplicationContext());
        tvHelper.open();
        String movieId = Integer.toString(tvShow.getId());

        favAdd = findViewById(R.id.favorite_add_tv);
        fabFavDel = findViewById(R.id.favorite_delete_tv);
        favAdd.setOnClickListener(this);
        fabFavDel.setOnClickListener(this);


        if (tvHelper.checkTvShow(movieId)){
            favAdd.setVisibility(View.GONE);
            fabFavDel.setVisibility(View.VISIBLE);
        }
    }

    private void setData(){
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

    private void insertDB(){
        int movieId = tvShow.getId();
        String title = tvShow.getName();
        String date = tvShow.getFirstAirDate();
        int score = (int) tvShow.getVoteAverage();
        String language = tvShow.getOriginalLanguage();
        String overview = tvShow.getOverview();
        String poster = tvShow.getPosterPath();
        String back = tvShow.getBackdropPath();

        ContentValues values = new ContentValues();
        values.put(TV_ID, movieId);
        values.put(TV_TITLE, title);
        values.put(TV_DATE, date);
        values.put(TV_RATE, score);
        values.put(TV_LANGUAGE, language);
        values.put(TV_OVERVIEW, overview);
        values.put(TV_POSTER, poster);
        values.put(TV_BACKRDOP, back);
        long result = tvHelper.insert(values);
        if (result > 0) {
            favAdd.setVisibility(View.GONE);
            fabFavDel.setVisibility(View.VISIBLE);
            Toast.makeText(TvDetailActivity.this, toastAdd, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(TvDetailActivity.this, failed, Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteDB(){
        long result = tvHelper.deleteById(String.valueOf(tvShow.getId()));
        if (result > 0) {
            Toast.makeText(TvDetailActivity.this, toastDel, Toast.LENGTH_SHORT).show();
            fabFavDel.setVisibility(View.GONE);
            favAdd.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(TvDetailActivity.this, failed, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.favorite_add_tv :
                insertDB();
                break;
            case R.id.favorite_delete_tv:
                deleteDB();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tvHelper.close();
    }
}
