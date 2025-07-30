package com.example.jsonplaceholderappnew;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>
{
    List<Album> albums;

    public AlbumsAdapter(List<Album> albums)
    {
        this.albums = albums;
    }

    @NonNull
    @Override
    public AlbumsAdapter.AlbumsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_album, parent, false);
        return new AlbumsAdapter.AlbumsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumsAdapter.AlbumsViewHolder holder, int position)
    {
        holder.title.setText(albums.get(position).getTitle());
    }

    @Override
    public int getItemCount()
    {
        return albums.size();
    }

    public static class AlbumsViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        public AlbumsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.title);
        }
    }
}
