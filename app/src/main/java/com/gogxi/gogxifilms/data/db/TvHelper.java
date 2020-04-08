package com.gogxi.gogxifilms.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.gogxi.gogxifilms.data.model.TVShow;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_BACKRDOP;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_DATE;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_ID;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_LANGUAGE;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_OVERVIEW;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_POSTER;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_RATE;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_TABLE_NAME;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_TITLE;

public class TvHelper {

    private static final String DATABASE_TABLE = TV_TABLE_NAME;
    private static DatabaseHelper databaseHelper;
    private static TvHelper INSTANCE;
    private static SQLiteDatabase database;

    private TvHelper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static TvHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TvHelper(context);
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

    public ArrayList<TVShow> queryAll() {
        ArrayList<TVShow> tvShowArrayList = new ArrayList<>();
        Cursor tvShowCursor = database.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC");
        tvShowCursor.moveToFirst();
        TVShow tvShow;
        if (tvShowCursor.getCount() > 0) {
            do {
                tvShow = new TVShow();
                tvShow.setId(Integer.parseInt(tvShowCursor.getString(tvShowCursor.getColumnIndex(TV_ID))));
                tvShow.setName(tvShowCursor.getString(tvShowCursor.getColumnIndex(TV_TITLE)));
                tvShow.setFirstAirDate(tvShowCursor.getString(tvShowCursor.getColumnIndex(TV_DATE)));
                tvShow.setVoteAverage(Integer.parseInt(tvShowCursor.getString(tvShowCursor.getColumnIndex(TV_RATE))));
                tvShow.setOriginalLanguage(tvShowCursor.getString(tvShowCursor.getColumnIndex(TV_LANGUAGE)));
                tvShow.setOverview(tvShowCursor.getString(tvShowCursor.getColumnIndex(TV_OVERVIEW)));
                tvShow.setPosterPath(tvShowCursor.getString(tvShowCursor.getColumnIndex(TV_POSTER)));
                tvShow.setBackdropPath(tvShowCursor.getString(tvShowCursor.getColumnIndex(TV_BACKRDOP)));

                tvShowArrayList.add(tvShow);

                tvShowCursor.moveToNext();
            } while (!tvShowCursor.isAfterLast());
        }
        tvShowCursor.close();
        return tvShowArrayList;
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

    public long insert(ContentValues values) {
        return database.insert(DATABASE_TABLE, null, values);
    }

    public int deleteById(String id) {
        return database.delete(DATABASE_TABLE, TV_ID + " = ?", new String[]{id});
    }

    public boolean checkTvShow(String id) {
        database = databaseHelper.getWritableDatabase();
        String select = "SELECT * FROM " + TV_TABLE_NAME + " WHERE " + TV_ID + " =?";
        Cursor cursor = database.rawQuery(select, new String[]{id});
        boolean checkTvShow = false;
        if (cursor.moveToFirst()) {
            checkTvShow = true;
            int count = 0;
            while (cursor.moveToNext()) {
                count++;
            }
            Log.d(TAG, String.format("%d records found", count));
        }
        cursor.close();
        return checkTvShow;
    }
}
