package com.example.jsonplaceholderappnew;

import android.os.Bundle;

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

        postsViewModel = new PostsViewModel(posts);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        getPostsList();
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