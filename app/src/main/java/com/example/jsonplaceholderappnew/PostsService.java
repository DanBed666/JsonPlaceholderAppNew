package com.example.jsonplaceholderappnew;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostsService
{
    String BASE_URL = "https://jsonplaceholder.typicode.com/";
    @GET("posts")
    Call<List<Post>> getPostsList();

    @POST("posts")
    Call<Post> createNewPost(@Body Post post);

    @GET("posts/{postId}/comments")
    Call<List<Comment>> getCommentsList(@Path("postId") int postId);

    @GET("users/{userId}")
    Call<User> getUser(@Path("userId") int userId);

    @GET("albums")
    Call<List<Album>> getAlbumsList();

    @GET("albums/{albumId}/photos")
    Call<List<Photo>> getPhotosList(@Path("albumId") int postId);
}
