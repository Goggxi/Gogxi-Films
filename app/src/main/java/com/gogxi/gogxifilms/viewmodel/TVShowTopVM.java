package com.gogxi.gogxifilms.viewmodel;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gogxi.gogxifilms.data.model.TVShow;
import com.gogxi.gogxifilms.data.network.Api;
import com.gogxi.gogxifilms.data.response.TVShowResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowTopVM extends ViewModel {
    private Api api;

    private MutableLiveData<ArrayList<TVShow>> data = new MutableLiveData<>();

    public void setTvTop(){
        if (this.api == null){
            api = new Api();
        }

        api.getUrl().getTVShowToprRated().enqueue(new Callback<TVShowResponse>() {
            @Override
            public void onResponse(Call<TVShowResponse> call, Response<TVShowResponse> response) {
                TVShowResponse tvShowResponse = response.body();
                if (tvShowResponse != null && tvShowResponse.getResults() != null){
                    ArrayList<TVShow> tvShows = tvShowResponse.getResults();
                    data.postValue(tvShows);
                }
            }

            @Override
            public void onFailure(Call<TVShowResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<ArrayList<TVShow>> getTvTop(){
        return data;
    }
}
