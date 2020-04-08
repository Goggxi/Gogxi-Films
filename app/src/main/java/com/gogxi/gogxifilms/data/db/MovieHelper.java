package com.gogxi.gogxifilms.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.gogxi.gogxifilms.data.model.Movie;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_BACKRDOP;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_DATE;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_ID;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_LANGUAGE;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_OVERVIEW;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_POSTER;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_RATE;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_TABLE_NAME;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_TITLE;

public class MovieHelper {

    private static final String DATABASE_TABLE = MOVIE_TABLE_NAME;
    private static DatabaseHelper databaseHelper;
    private static MovieHelper INSTANCE;
    private static SQLiteDatabase database;

    public MovieHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static MovieHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();

        if (database.isOpen())
            database.close();
    }

    public ArrayList<Movie> queryAll() {
        ArrayList<Movie> movieArrayList = new ArrayList<>();
        Cursor cursor = database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC");
        cursor.moveToFirst();
        Movie movie;
        if (cursor.getCount() > 0) {
            do {
                movie = new Movie();
                movie.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MOVIE_ID))));
                movie.setTitle(cursor.getString(cursor.getColumnIndex(MOVIE_TITLE)));
                movie.setReleaseDate(cursor.getString(cursor.getColumnIndex(MOVIE_DATE)));
                movie.setVoteAverage(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MOVIE_RATE))));
                movie.setOriginalLanguage(cursor.getString(cursor.getColumnIndex(MOVIE_LANGUAGE)));
                movie.setOverview(cursor.getString(cursor.getColumnIndex(MOVIE_OVERVIEW)));
                movie.setPosterPath(cursor.getString(cursor.getColumnIndex(MOVIE_POSTER)));
                movie.setBackdropPath(cursor.getString(cursor.getColumnIndex(MOVIE_POSTER)));

                movieArrayList.add(movie);

                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return movieArrayList;
    }

    public Cursor queryById(String id) {
        return database.query(DATABASE_TABLE, null
                , _ID + " = ?"
                , new String[]{id}
                , null
                , null
                , null
                , null);
    }

//    public long insert(ContentValues values) {
//        return database.insert(DATABASE_TABLE, null, values);
//    }

    public long insert(Movie movies) {
        ContentValues conts = new ContentValues();
        conts.put(MOVIE_ID, movies.getId());
        conts.put(MOVIE_TITLE, movies.getTitle());
        conts.put(MOVIE_RATE, movies.getVoteAverage());
        conts.put(MOVIE_OVERVIEW, movies.getOverview());
        conts.put(MOVIE_DATE, movies.getReleaseDate());
        conts.put(MOVIE_LANGUAGE, movies.getOriginalLanguage());
        conts.put(MOVIE_POSTER, movies.getPosterPath());
        conts.put(MOVIE_BACKRDOP, movies.getBackdropPath());
        return database.insert(DATABASE_TABLE, null, conts);
    }

    public int deleteById(String id) {
        return database.delete(DATABASE_TABLE, MOVIE_ID + " = ?", new String[]{id});
    }

    public int deleteMovie(int id) {
        return database.delete(DATABASE_TABLE, MOVIE_ID + " = '" + id + "'", null);
    }

    public boolean checkMovie(String id) {
        database = databaseHelper.getWritableDatabase();
        String select = "SELECT * FROM " + DATABASE_TABLE + " WHERE " + MOVIE_ID + " =?";
        Cursor cursor = database.rawQuery(select, new String[]{id});
        boolean checkMovie = false;
        if (cursor.moveToFirst()) {
            checkMovie = true;
            int count = 0;
            while (cursor.moveToNext()) {
                count++;
            }
            Log.d(TAG, String.format("%d records found", count));
        }
        cursor.close();
        return checkMovie;
    }
}
