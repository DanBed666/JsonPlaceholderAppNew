package com.example.jsonplaceholderappnew;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder
{
    public static PostsService getPostsService()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PostsService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(PostsService.class);
    }
}
