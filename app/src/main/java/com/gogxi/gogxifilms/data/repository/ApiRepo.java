package com.gogxi.gogxifilms.data.repository;

import com.gogxi.gogxifilms.BuildConfig;
import com.gogxi.gogxifilms.R;
import com.gogxi.gogxifilms.data.response.MovieResponse;
import com.gogxi.gogxifilms.data.response.TVShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRepo {
 //   String BASE_URL = "https://api.themoviedb.org/";
    int LANG = R.string.language;

    @GET("3/movie/popular?api_key="+ BuildConfig.API_KEY +"&language="+ LANG)
    Call<MovieResponse> getMoviePopulars();
    @GET("3/movie/now_playing?api_key="+ BuildConfig.API_KEY +"&language="+ LANG)
    Call<MovieResponse> getMoviePlayNow();
    @GET("3/movie/upcoming?api_key="+ BuildConfig.API_KEY +"&language="+ LANG)
    Call<MovieResponse> getMovieUpcoming();
    @GET("3/tv/popular?api_key="+ BuildConfig.API_KEY + "&language="+ LANG)
    Call<TVShowResponse> getTVShowPopular();
    @GET("3/tv/top_rated?api_key="+ BuildConfig.API_KEY + "&language="+ LANG)
    Call<TVShowResponse> getTVShowToprRated();
    @GET("3/tv/airing_today?api_key="+ BuildConfig.API_KEY + "&language="+ LANG)
    Call<TVShowResponse> getTVShowTodayr();
}
