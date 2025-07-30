package com.example.jsonplaceholderappnew;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlbumsViewModel
{
    AlbumsRepository albumsRepository;
    public MutableLiveData<List<Album>> getAlbumsList()
    {
        albumsRepository = new AlbumsRepository();
        return albumsRepository.getAlbumsList();
    }
}
