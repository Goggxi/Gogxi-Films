package com.gogxi.gogxifilms.data.repository;

import com.gogxi.gogxifilms.BuildConfig;
import com.gogxi.gogxifilms.R;
import com.gogxi.gogxifilms.data.response.MoviePopularResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRepo {
    String BASE_URL = "https://api.themoviedb.org/";
    int LANG = R.string.language;

    @GET("3/movie/popular?api_key="+ BuildConfig.API_KEY +"&language="+ LANG +"&page=1")
    Call<MoviePopularResponse> getMoviePopulars();
    @GET("3/movie/now_playing?api_key="+ BuildConfig.API_KEY +"&language="+ LANG +"&page=1")
    Call<MoviePopularResponse> gedMoviePlayNow();
}
