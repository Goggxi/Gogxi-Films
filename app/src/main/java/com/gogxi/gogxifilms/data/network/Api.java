package com.gogxi.gogxifilms.data.network;

import com.gogxi.gogxifilms.data.repository.MoviePopularRepo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private Retrofit retrofit;

    public MoviePopularRepo getMoviePopular(){
        String BASE_URL = "https://api.themoviedb.org/";
        if (retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(MoviePopularRepo.class);
    }

    //tv show
//    public MoviePopularRepo getMoviePopular(){
//        String BASE_URL = "https://api.themoviedb.org/";
//        if (retrofit == null){
//            retrofit = new Retrofit
//                    .Builder()
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit.create(MoviePopularRepo.class);
//    }
}
