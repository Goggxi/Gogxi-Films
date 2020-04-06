package com.gogxi.gogxifilms.data.repository;

import com.gogxi.gogxifilms.BuildConfig;
import com.gogxi.gogxifilms.data.response.MovieResponse;
import com.gogxi.gogxifilms.data.response.TVShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRepo {

    @GET("3/movie/popular?api_key="+ BuildConfig.API_KEY )
    Call<MovieResponse> getMoviePopulars(@Query("language") String language);
    @GET("3/movie/now_playing?api_key="+ BuildConfig.API_KEY )
    Call<MovieResponse> getMoviePlayNow(@Query("language") String language);
    @GET("3/movie/upcoming?api_key="+ BuildConfig.API_KEY )
    Call<MovieResponse> getMovieUpcoming(@Query("language") String language);
    @GET("3/tv/popular?api_key="+ BuildConfig.API_KEY )
    Call<TVShowResponse> getTVShowPopular(@Query("language") String language);
    @GET("3/tv/top_rated?api_key="+ BuildConfig.API_KEY )
    Call<TVShowResponse> getTVShowToprRated(@Query("language") String language);
    @GET("3/tv/airing_today?api_key="+ BuildConfig.API_KEY )
    Call<TVShowResponse> getTVShowToday(@Query("language") String language);
}
