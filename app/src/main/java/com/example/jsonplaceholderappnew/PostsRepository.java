package com.example.jsonplaceholderappnew;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsRepository
{
    public MutableLiveData<List<Post>> getPostsList()
    {
        MutableLiveData<List<Post>> mutableLiveData = new MutableLiveData<>();

        RetrofitBuilder.getPostsService().getPostsList().enqueue(new Callback<List<Post>>()
        {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response)
            {
                mutableLiveData.setValue(response.body());
                Log.i("INFO", "Pozyskano dane z repository");
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t)
            {
                Log.e("ERROR", Objects.requireNonNull(t.getMessage()));
            }
        });

        return mutableLiveData;
    }

    public void createNewPost(Post post)
    {
        RetrofitBuilder.getPostsService().createNewPost(post).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response)
            {
                assert response.body() != null;
                Log.i("POSTT", String.valueOf(response.body().getTitle()));
                Log.i("POSTB", String.valueOf(response.body().getBody()));
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t)
            {
                Log.e("ERROR", Objects.requireNonNull(t.getMessage()));
            }
        });
    }
}
