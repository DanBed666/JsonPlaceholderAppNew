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
import java.util.Random;

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
                Random r = new Random();

                String t = title.getText().toString();
                String b = body.getText().toString();
                //PostsViewModel postsViewModel = new PostsViewModel();
                //postsViewModel.createNewPost(new Post(r.nextInt(100) + 101, r.nextInt(100) + 101, t, b));

                int id = r.nextInt(100) + 101;

                Post post  = new Post(r.nextInt(100) + 101, id, t, b);

                DatabaseManager dm = new DatabaseManager();
                dm.addItem("posts", id, post);

                finish();
            }
        });
    }
}