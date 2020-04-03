package com.gogxi.gogxifilms.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gogxi.gogxifilms.data.network.Api;
import com.gogxi.gogxifilms.ui.model.MoviePopular;
import com.gogxi.gogxifilms.ui.model.MoviePopularResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePopularVM extends ViewModel {
    private Api api;

    private MutableLiveData<ArrayList<MoviePopularResult>> data = new MutableLiveData<>();

    public void setMoviePopular(){
        if (this.api == null){
            api = new Api();
        }
        api.getMoviePopular().getMovieDiscover().enqueue(new Callback<MoviePopular>() {

            @Override
            public void onResponse(Call<MoviePopular> call, Response<MoviePopular> response) {
                MoviePopular moviePopular = response.body();
                if (moviePopular != null && moviePopular.getResults() != null){
                    ArrayList<MoviePopularResult> moviePopularResults = moviePopular.getResults();
                    data.postValue(moviePopularResults);
                }
            }

            @Override
            public void onFailure(Call<MoviePopular> call, Throwable t) {

            }
        });
    }

    public LiveData<ArrayList<MoviePopularResult>> getMoviePopular(){
        return data;
    }
}
