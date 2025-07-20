package com.example.jsonplaceholderappnew;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder>
{
    List<Post> posts = new ArrayList<>();
    Context context;

    public PostsAdapter(List<Post> posts, Context c)
    {
        this.posts = posts;
        context = c;
    }

    @NonNull
    @Override
    public PostsAdapter.PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_post, parent, false);
        return new PostsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.PostsViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        holder.title.setText(posts.get(position).getTitle());
        holder.body.setText(posts.get(position).getBody());

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                context.startActivity(new Intent(context, CommentsActivity.class));
                Log.i("IDPOSTA", String.format("Numer posta to %d", posts.get(position).getId()));
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return posts.size();
    }

    public static class PostsViewHolder extends RecyclerView.ViewHolder
    {
        TextView title;
        TextView body;
        public PostsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }
    }
}
