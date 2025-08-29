package com.example.jsonplaceholderappnew;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PostsViewModel
{
    PostsRepository postsRepository;
    public MutableLiveData<List<Post>> getPostsList()
    {
        postsRepository = new PostsRepository();
        return postsRepository.getPostsList();
    }

    public void createNewPost(Post post)
    {
        postsRepository = new PostsRepository();
        postsRepository.createNewPost(post);
    }
}
