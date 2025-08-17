package com.example.jsonplaceholderappnew;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosRepository
{
    public MutableLiveData<List<Photo>> getPhotosList(int albumId)
    {
        MutableLiveData<List<Photo>> mutableLiveData = new MutableLiveData<>();

        RetrofitBuilder.getPostsService().getPhotosList(albumId).enqueue(new Callback<List<Photo>>()
        {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response)
            {
                mutableLiveData.setValue(response.body());
                Log.i("REPO", "working");
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t)
            {
                Log.e("REPOERR", Objects.requireNonNull(t.getMessage()));
            }
        });

        return mutableLiveData;
    }
}
