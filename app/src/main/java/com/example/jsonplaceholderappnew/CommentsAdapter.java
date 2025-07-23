package com.example.jsonplaceholderappnew;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>
{
    List<Comment> commentList;

    public CommentsAdapter(List<Comment> commentList)
    {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentsAdapter.CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_comment, parent, false);
        return new CommentsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.CommentsViewHolder holder, int position)
    {
        holder.email.setText(commentList.get(position).getEmail());
        holder.title.setText(commentList.get(position).getName());
        holder.body.setText(commentList.get(position).getBody());
    }

    @Override
    public int getItemCount()
    {
        return commentList.size();
    }

    public static class CommentsViewHolder extends RecyclerView.ViewHolder
    {
        TextView email;
        TextView title;
        TextView body;
        public CommentsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            email = itemView.findViewById(R.id.email);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }
    }
}
