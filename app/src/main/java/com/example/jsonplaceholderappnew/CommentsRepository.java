package com.example.jsonplaceholderappnew;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsRepository
{
    List<Comment> commentList;

    public CommentsRepository(List<Comment> commentList)
    {
        this.commentList = commentList;
    }

    public MutableLiveData<List<Comment>> getCommentsList(int postId)
    {
        MutableLiveData<List<Comment>> mutableLiveData = new MutableLiveData<>();

        RetrofitBuilder.getPostsService().getCommentsList(postId).enqueue(new Callback<List<Comment>>()
        {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response)
            {
                commentList = response.body();
                mutableLiveData.setValue(commentList);
                Log.i("INFO", "Pozyskano dane z repository comments");
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t)
            {
                Log.e("ERROR", Objects.requireNonNull(t.getMessage()));
            }
        });

        return mutableLiveData;
    }
}
