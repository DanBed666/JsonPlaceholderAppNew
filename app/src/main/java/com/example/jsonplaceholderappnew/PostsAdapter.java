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
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostsViewHolder>
{
    List<DocumentSnapshot> posts = new ArrayList<>();
    Context context;
    UserViewModel userViewModel;
    DatabaseManager dm = new DatabaseManager();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public PostsAdapter(List<DocumentSnapshot> posts, Context c)
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
        if (posts.get(position).get("userId") instanceof Long)
        {
            getUser(Objects.requireNonNull(posts.get(position).getLong("userId")).intValue(), holder.user);
        }
        else
        {
            String userId = posts.get(position).getString("userId");
            Query query = db.collection("users").whereEqualTo("id", userId);
            dm.getItemsWithQuery(query, new OnDataGetListener()
            {
                @Override
                public void setOnDataGetListener(List<DocumentSnapshot> documentSnapshotList)
                {
                    holder.user.setText(documentSnapshotList.get(0).getString("username"));
                }
            });
        }

        holder.title.setText(posts.get(position).getString("title"));
        holder.body.setText(posts.get(position).getString("body"));

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(context, CommentsActivity.class);
                i.putExtra("ID_POSTA", Objects.requireNonNull(posts.get(position).getLong("userId")).intValue());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
                Log.i("IDPOSTA", String.format("Numer posta to %s", posts.get(position).getId()));
            }
        });

        holder.user.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(context, UserActivity.class);
                i.putExtra("ID_USERA", Objects.requireNonNull(posts.get(position).getLong("userId")).intValue());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return posts.size();
    }

    public void getUser(int userId, TextView userTv)
    {
        userViewModel = new UserViewModel();

        userViewModel.getUser(userId).observeForever(new Observer<User>()
        {
            @Override
            public void onChanged(User user)
            {
                if (user != null)
                {
                    userTv.setText(user.getUsername());
                }
                else
                {
                    userTv.setText("Guest");
                }
            }
        });
    }

    public static class PostsViewHolder extends RecyclerView.ViewHolder
    {
        TextView user;
        TextView title;
        TextView body;
        public PostsViewHolder(@NonNull View itemView)
        {
            super(itemView);
            user = itemView.findViewById(R.id.user);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }
    }
}
