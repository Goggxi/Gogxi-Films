package com.gogxi.gogxifilms.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.gogxi.gogxifilms.data.response.MoviePopularResponse;
import com.gogxi.gogxifilms.data.model.MoviePopular;
import com.gogxi.gogxifilms.data.network.Api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePopularVM extends ViewModel {
    private Api api;

    private MutableLiveData<ArrayList<MoviePopular>> data = new MutableLiveData<>();

    public void setMoviePopular(){
        if (this.api == null){
            api = new Api();
        }
        api.getMovie().getMoviePopulars().enqueue(new Callback<MoviePopularResponse>() {

            @Override
            public void onResponse(Call<MoviePopularResponse> call, Response<MoviePopularResponse> response) {
                MoviePopularResponse moviePopularResponse = response.body();
                if (moviePopularResponse != null && moviePopularResponse.getResults() != null){
                    ArrayList<MoviePopular> moviePopular = moviePopularResponse.getResults();
                    data.postValue(moviePopular);
                }
            }

            @Override
            public void onFailure(Call<MoviePopularResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<ArrayList<MoviePopular>> getMoviePopular(){
        return data;
    }
}
