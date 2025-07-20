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
    List<Post> postList;

    public PostsRepository(List<Post> postList)
    {
        this.postList = postList;
    }

    public MutableLiveData<List<Post>> getPostsList()
    {
        MutableLiveData<List<Post>> mutableLiveData = new MutableLiveData<>();

        RetrofitBuilder.getPostsService().getPostsList().enqueue(new Callback<List<Post>>()
        {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response)
            {
                postList = response.body();
                mutableLiveData.setValue(postList);
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
}
