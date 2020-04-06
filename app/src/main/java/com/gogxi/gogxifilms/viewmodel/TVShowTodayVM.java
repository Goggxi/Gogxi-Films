package com.gogxi.gogxifilms.viewmodel;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gogxi.gogxifilms.data.model.TVShow;
import com.gogxi.gogxifilms.data.network.Api;
import com.gogxi.gogxifilms.data.response.TVShowResponse;
import com.gogxi.gogxifilms.ui.activity.MainActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowTodayVM extends ViewModel {
    private Api api;
    private static final String TAG = MainActivity.class.getSimpleName();
    private MutableLiveData<ArrayList<TVShow>> data = new MutableLiveData<>();

    public void setTvToday(String language){
        if (this.api == null){
            api = new Api();
        }

        api.getUrl().getTVShowToday(language).enqueue(new Callback<TVShowResponse>() {
            @Override
            public void onResponse(Call<TVShowResponse> call, Response<TVShowResponse> response) {
                Log.d(TAG, "onResponse: ");
                TVShowResponse tvShowResponse = response.body();
                try {
                    if (tvShowResponse != null && tvShowResponse.getResults() != null){
                        ArrayList<TVShow> tvShows = tvShowResponse.getResults();
                        data.postValue(tvShows);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }               
            }

            @Override
            public void onFailure(Call<TVShowResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    public LiveData<ArrayList<TVShow>> getTvToday(){
        return data;
    }
}
