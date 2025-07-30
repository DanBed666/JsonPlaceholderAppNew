package com.example.jsonplaceholderappnew;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PostsService
{
    String BASE_URL = "https://jsonplaceholder.typicode.com/";
    @GET("posts")
    Call<List<Post>> getPostsList();

    @GET("posts/{postId}/comments")
    Call<List<Comment>> getCommentsList(@Path("postId") int postId);

    @GET("users/{userId}")
    Call<User> getUser(@Path("userId") int userId);

    @GET("albums")
    Call<List<Album>> getAlbumsList();

    @GET("photos")
    Call<List<Photo>> getPhotosList();
}
