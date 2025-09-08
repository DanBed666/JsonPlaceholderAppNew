package com.example.jsonplaceholderappnew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class PostsActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    List<Comment> commentList = new ArrayList<>();
    PostsViewModel postsViewModel;
    Button new_post;
    DatabaseManager dm = new DatabaseManager();
    CommentsViewModel commentsViewModel = new CommentsViewModel();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_posts);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        new_post = findViewById(R.id.new_post);
        postsViewModel = new PostsViewModel();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        addPostsToDb();
        getPostsListFromDb();

        new_post.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getApplicationContext(), NewPostActivity.class));
            }
        });
    }
    public void addPostsToDb()
    {
        postsViewModel.getPostsList().observeForever(new Observer<List<Post>>()
        {
            @Override
            public void onChanged(List<Post> posts)
            {
                for (Post p : posts)
                {
                    dm.addItem("posts", p.getId(), p);
                    addCommentToDb(p.getId());
                }
            }
        });
    }

    public void getPostsListFromDb()
    {
        DatabaseManager databaseManager = new DatabaseManager();

        databaseManager.getAllItems("posts", new OnDataGetListener()
        {
            @Override
            public void setOnDataGetListener(List<DocumentSnapshot> documentSnapshotList)
            {
                recyclerView.setAdapter(new PostsAdapter(documentSnapshotList, getApplicationContext()));
            }
        });
    }

    public void addCommentToDb(int postId)
    {
        commentsViewModel.getCommentsList(postId).observeForever(new Observer<List<Comment>>()
        {
            @Override
            public void onChanged(List<Comment> comments)
            {
                for (Comment c : comments)
                {
                    dm.addItem("comments", c.getId(), c);
                }
            }
        });
    }
}