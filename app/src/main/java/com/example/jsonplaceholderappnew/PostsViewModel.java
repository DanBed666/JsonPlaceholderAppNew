package com.example.jsonplaceholderappnew;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PostsViewModel
{
    List<Post> postList;
    PostsRepository postsRepository;

    public PostsViewModel(List<Post> postList)
    {
        this.postList = postList;
    }

    public MutableLiveData<List<Post>> getPostsList()
    {
        postsRepository = new PostsRepository(postList);
        return postsRepository.getPostsList();
    }
}
