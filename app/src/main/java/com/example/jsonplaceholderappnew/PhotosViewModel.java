package com.example.jsonplaceholderappnew;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class PhotosViewModel
{
    PhotosRepository photosRepository;
    public MutableLiveData<List<Photo>> getAlbumsList(int albumId)
    {
        photosRepository = new PhotosRepository();
        return photosRepository.getPhotosList(albumId);
    }
}
