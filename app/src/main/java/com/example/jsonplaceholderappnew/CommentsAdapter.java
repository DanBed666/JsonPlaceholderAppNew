package com.example.jsonplaceholderappnew;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;
import java.util.Objects;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>
{
    List<DocumentSnapshot> commentList;

    public CommentsAdapter(List<DocumentSnapshot> commentList)
    {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentsAdapter.CommentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        Log.i("TAG", "ADAPTER1");
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_comment, parent, false);
        return new CommentsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentsAdapter.CommentsViewHolder holder, int position)
    {
        holder.email.setText(commentList.get(position).getString("email"));
        holder.title.setText(commentList.get(position).getString("name"));
        holder.body.setText(commentList.get(position).getString("body"));

        Log.i("EMAIL", Objects.requireNonNull(commentList.get(position).getString("email")));
        Log.i("NAME", Objects.requireNonNull(commentList.get(position).getString("name")));
        Log.i("BODY", Objects.requireNonNull(commentList.get(position).getString("body")));
    }

    @Override
    public int getItemCount()
    {
        Log.i("TAG", String.valueOf(commentList.size()));
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
