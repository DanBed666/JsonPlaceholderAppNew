package com.example.jsonplaceholderappnew;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class CommentsViewModel
{
    List<Comment> commentList;
    CommentsRepository commentsRepository;

    public CommentsViewModel(List<Comment> commentList)
    {
        this.commentList = commentList;
    }

    public MutableLiveData<List<Comment>> getCommentsList(int postId)
    {
        commentsRepository = new CommentsRepository(commentList);
        return commentsRepository.getCommentsList(postId);
    }
}
