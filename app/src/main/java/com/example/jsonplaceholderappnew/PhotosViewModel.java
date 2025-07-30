package com.example.jsonplaceholderappnew;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PhotosViewModel
{
    PhotosRepository photosRepository;
    public MutableLiveData<List<Photo>> getAlbumsList()
    {
        photosRepository = new PhotosRepository();
        return photosRepository.getAlbumsList();
    }
}
