package com.gogxi.gogxifilms.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gogxi.gogxifilms.data.model.MoviePlayNow;
import com.gogxi.gogxifilms.data.model.MoviePopular;
import com.gogxi.gogxifilms.data.network.Api;
import com.gogxi.gogxifilms.data.response.MoviePlayNowResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieNowVM extends ViewModel {
    private Api api;

    private MutableLiveData<ArrayList<MoviePlayNow>> data = new MutableLiveData<>();

    public void setMovieNow(){
        if (this.api == null){
            api = new Api();
        }
        api.getUrl().gedMoviePlayNow().enqueue(new Callback<MoviePlayNowResponse>() {

            @Override
            public void onResponse(Call<MoviePlayNowResponse> call, Response<MoviePlayNowResponse> response) {
                MoviePlayNowResponse moviePlayNowResponse = response.body();
                if (moviePlayNowResponse != null && moviePlayNowResponse.getResults() != null){
                    ArrayList<MoviePlayNow> moviePlayNow = moviePlayNowResponse.getResults();
                    data.postValue(moviePlayNow);
                }
            }

            @Override
            public void onFailure(Call<MoviePlayNowResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<ArrayList<MoviePlayNow>> getMovieNow(){
        return data;
    }
}
