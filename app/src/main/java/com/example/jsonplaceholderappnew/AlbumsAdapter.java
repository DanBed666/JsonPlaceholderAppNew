package com.example.jsonplaceholderappnew;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>
{
    List<Album> albums;
    UserViewModel userViewModel;
    Context context;

    public AlbumsAdapter(List<Album> albums, Context c)
    {
        this.albums = albums;
        context = c;
    }

    @NonNull
    @Override
    public AlbumsAdapter.AlbumsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_album, parent, false);
        return new AlbumsAdapter.AlbumsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AlbumsAdapter.AlbumsViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        getUserNickname(albums.get(position).getUserId(), holder.user);
        holder.title.setText(albums.get(position).getTitle());

        holder.user.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(context, UserActivity.class);
                i.putExtra("ID_USERA", albums.get(position).getUserId());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(context, PhotosActivity.class);
                i.putExtra("ID_ALBUMU", albums.get(position).getId());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return albums.size();
    }

    public void getUserNickname(int userId, TextView userTv)
    {
        userViewModel = new UserViewModel();

        userViewModel.getUser(userId).observeForever(new Observer<User>()
        {
            @Override
            public void onChanged(User user)
            {
                userTv.setText(user.getUsername());
            }
        });
    }
    public static class AlbumsViewHolder extends RecyclerView.ViewHolder
    {
        TextView user;
        TextView title;
        public AlbumsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            user = itemView.findViewById(R.id.user);
        }
    }
}
