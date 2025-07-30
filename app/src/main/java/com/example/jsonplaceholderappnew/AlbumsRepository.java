package com.example.jsonplaceholderappnew;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsRepository
{
    public MutableLiveData<List<Album>> getAlbumsList()
    {
        MutableLiveData<List<Album>> mutableLiveData = new MutableLiveData<>();

        RetrofitBuilder.getPostsService().getAlbumsList().enqueue(new Callback<List<Album>>()
        {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response)
            {
                mutableLiveData.setValue(response.body());
                Log.i("REPO", "working");
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t)
            {
                Log.e("REPOERR", Objects.requireNonNull(t.getMessage()));
            }
        });

        return mutableLiveData;
    }
}
