package com.gogxi.gogxifilms.data.db;

import android.provider.BaseColumns;

public class DatabaseContract {

    public static final class FavMovieColumns implements BaseColumns {
        static final String MOVIE_TABLE_NAME = "favorite_movie";
        public static final String MOVIE_ID = "movie_id";
        public static final String MOVIE_TITLE = "title";
        public static final String MOVIE_DATE = "date";
        public static final String MOVIE_RATE = "rate";
        public static final String MOVIE_LANGUAGE = "language";
        public static final String MOVIE_OVERVIEW = "overview";
        public static final String MOVIE_POSTER = "poster";
        public static final String MOVIE_BACKRDOP = "backdrop";
    }

    public static final class FavTvShowColumns implements BaseColumns {
        static final String TV_TABLE_NAME = "favorite_tvshow";
        public static final String TV_ID = "tvshow_id";
        public static final String TV_TITLE = "title";
        public static final String TV_DATE = "date";
        public static final String TV_RATE = "rate";
        public static final String TV_LANGUAGE = "language";
        public static final String TV_OVERVIEW = "overview";
        public static final String TV_POSTER = "poster";
        public static final String TV_BACKRDOP = "backdrop";
    }
}
