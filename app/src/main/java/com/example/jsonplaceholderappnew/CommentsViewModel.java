package com.example.jsonplaceholderappnew;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class CommentsViewModel
{
    CommentsRepository commentsRepository;
    public MutableLiveData<List<Comment>> getCommentsList(int postId)
    {
        commentsRepository = new CommentsRepository();
        return commentsRepository.getCommentsList(postId);
    }
}
