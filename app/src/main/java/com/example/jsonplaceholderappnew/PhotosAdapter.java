package com.example.jsonplaceholderappnew;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>
{
    List<Photo> photos;
    public PhotosAdapter(List<Photo> photos)
    {
        this.photos = photos;
    }

    @NonNull
    @Override
    public PhotosAdapter.PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_photo, parent, false);
        return new PhotosViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosAdapter.PhotosViewHolder holder, int position)
    {
        holder.title.setText(photos.get(position).getTitle());
        Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(holder.url);
        //holder.url.setText(photos.get(position).getUrl());
    }

    @Override
    public int getItemCount()
    {
        return photos.size();
    }

    public static class PhotosViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        ImageView url;
        public PhotosViewHolder(@NonNull View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            url = itemView.findViewById(R.id.url_iv);
        }
    }
}
