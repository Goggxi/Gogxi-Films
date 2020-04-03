package com.gogxi.gogxifilms.data.network;


import com.gogxi.gogxifilms.data.repository.ApiRepo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    private Retrofit retrofit;

    public ApiRepo getUrl(){
        if (retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(ApiRepo.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiRepo.class);
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
