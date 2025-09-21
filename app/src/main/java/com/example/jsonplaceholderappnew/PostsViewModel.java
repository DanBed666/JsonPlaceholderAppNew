package com.example.jsonplaceholderappnew;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PostsViewModel
{
    PostsRepository postsRepository;

    public PostsViewModel()
    {
        postsRepository = new PostsRepository();
    }

    public MutableLiveData<List<Post>> getPostsList()
    {
        return postsRepository.getPostsList();
    }

    public void createNewPost(Post post)
    {
        postsRepository.createNewPost(post);
    }
}
