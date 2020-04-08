package com.gogxi.gogxifilms.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static android.provider.BaseColumns._ID;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_BACKRDOP;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_DATE;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_ID;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_LANGUAGE;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_OVERVIEW;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_POSTER;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_RATE;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_TABLE_NAME;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavMovieColumns.MOVIE_TITLE;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_BACKRDOP;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_DATE;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_ID;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_LANGUAGE;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_OVERVIEW;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_POSTER;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_RATE;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_TABLE_NAME;
import static com.gogxi.gogxifilms.data.db.DatabaseContract.FavTvShowColumns.TV_TITLE;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbfavorite";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_MOVIE_TABLE =
            String.format("CREATE TABLE %s" +
                            " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                            " %s INTEGER," +
                            " %s TEXT NOT NULL," +
                            " %s TEXT NOT NULL," +
                            " %s INTEGER," +
                            " %s TEXT NOT NULL," +
                            " %s TEXT NOT NULL," +
                            " %s TEXT NOT NULL," +
                            " %s TEXT NOT NULL)",
                    MOVIE_TABLE_NAME,
                    _ID,
                    MOVIE_ID,
                    MOVIE_TITLE,
                    MOVIE_DATE,
                    MOVIE_RATE,
                    MOVIE_LANGUAGE,
                    MOVIE_OVERVIEW,
                    MOVIE_POSTER,
                    MOVIE_BACKRDOP
            );

    private static final String SQL_CREATE_TV_TABLE =
            String.format("CREATE TABLE %s" +
                            " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                            " %s INTEGER," +
                            " %s TEXT NOT NULL," +
                            " %s TEXT NOT NULL," +
                            " %s TEXT NOT NULL," +
                            " %s TEXT NOT NULL," +
                            " %s TEXT NOT NULL," +
                            " %s TEXT NOT NULL," +
                            " %s TEXT NOT NULL)",
                    TV_TABLE_NAME,
                    _ID,
                    TV_ID,
                    TV_TITLE,
                    TV_DATE,
                    TV_RATE,
                    TV_LANGUAGE,
                    TV_OVERVIEW,
                    TV_POSTER,
                    TV_BACKRDOP
            );

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_TV_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MOVIE_TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TV_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
