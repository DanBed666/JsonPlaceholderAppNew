package com.example.jsonplaceholderappnew;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository
{
    public MutableLiveData<User> getUser(int userId)
    {
        MutableLiveData<User> mutableLiveData = new MutableLiveData<>();

        RetrofitBuilder.getPostsService().getUser(userId).enqueue(new Callback<User>()
        {
            @Override
            public void onResponse(Call<User> call, Response<User> response)
            {
                mutableLiveData.setValue(response.body());
                Log.i("INFO", "Pozyskano dane z repository comments");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t)
            {
                Log.e("ERROR", Objects.requireNonNull(t.getMessage()));
            }
        });

        return mutableLiveData;
    }
}
