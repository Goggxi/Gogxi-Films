package com.gogxi.gogxifilms.viewmodel;


import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gogxi.gogxifilms.data.model.Movie;
import com.gogxi.gogxifilms.data.network.Api;
import com.gogxi.gogxifilms.data.response.MovieResponse;
import com.gogxi.gogxifilms.ui.activity.MainActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieNowVM extends ViewModel {
    private Api api;
    private static final String TAG = MainActivity.class.getSimpleName();
    private MutableLiveData<ArrayList<Movie>> data = new MutableLiveData<>();

    public void setMovieNow(String language){
        if (this.api == null){
            api = new Api();
        }
        api.getUrl().getMoviePlayNow(language).enqueue(new Callback<MovieResponse>() {

            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.d(TAG, "onResponse: ");
                MovieResponse movieResponse = response.body();
                try {
                    if (movieResponse != null && movieResponse.getResults() != null){
                        ArrayList<Movie> movie = movieResponse.getResults();
                        data.postValue(movie);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: ");
            }
        });
    }

    public LiveData<ArrayList<Movie>> getMovieNow(){
        return data;
    }
}
