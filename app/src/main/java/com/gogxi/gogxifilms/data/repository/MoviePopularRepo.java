package com.gogxi.gogxifilms.data.repository;

import com.gogxi.gogxifilms.ui.model.MoviePopular;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MoviePopularRepo {
    @GET("3/movie/popular?api_key=d8e957a69af1be921c9c4466f5661e87&language=en-US&page=1")
    Call<MoviePopular> getMovieDiscover();
}
