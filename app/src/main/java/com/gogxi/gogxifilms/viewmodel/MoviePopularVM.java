package com.gogxi.gogxifilms.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.gogxi.gogxifilms.data.model.Movie;
import com.gogxi.gogxifilms.data.network.Api;
import com.gogxi.gogxifilms.data.response.MovieResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePopularVM extends ViewModel {
    private Api api;

    private MutableLiveData<ArrayList<Movie>> data = new MutableLiveData<>();

    public void setMoviePopular(){
        if (this.api == null){
            api = new Api();
        }
        api.getUrl().getMoviePopulars().enqueue(new Callback<MovieResponse>() {

            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                if (movieResponse != null && movieResponse.getResults() != null){
                    ArrayList<Movie> movie = movieResponse.getResults();
                    data.postValue(movie);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<ArrayList<Movie>> getMoviePopular(){
        return data;
    }
}
