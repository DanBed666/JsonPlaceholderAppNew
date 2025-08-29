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

import java.util.ArrayList;
import java.util.List;

public class PostsActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    List<Post> posts = new ArrayList<>();
    PostsViewModel postsViewModel;
    Button new_post;

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
        getPostsList();

        new_post.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getApplicationContext(), NewPostActivity.class));
            }
        });
    }
    public void getPostsList()
    {
        postsViewModel.getPostsList().observeForever(new Observer<List<Post>>()
        {
            @Override
            public void onChanged(List<Post> posts)
            {
                recyclerView.setAdapter(new PostsAdapter(posts, getApplicationContext()));
            }
        });
    }
}