package com.example.jsonplaceholderappnew;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class NewPostActivity extends AppCompatActivity
{
    EditText title;
    EditText body;
    Button publish;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_post);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        title = findViewById(R.id.title);
        body = findViewById(R.id.body);
        publish = findViewById(R.id.btn_post);

        publish.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String t = title.getText().toString();
                String b = body.getText().toString();
                PostsViewModel postsViewModel = new PostsViewModel();
                postsViewModel.createNewPost(new Post(66, 666, t, b));
                finish();
            }
        });
    }
}