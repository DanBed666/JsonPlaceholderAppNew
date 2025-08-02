package com.example.jsonplaceholderappnew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class HomepageActivity extends AppCompatActivity
{
    TextView greeting;
    Button logout;
    Button posts;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    Button albums;
    Button settings;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_homepage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        greeting = findViewById(R.id.greeting);
        posts = findViewById(R.id.btn_posts);
        logout = findViewById(R.id.btn_logout);
        albums = findViewById(R.id.btn_albums);
        settings = findViewById(R.id.btn_settings);

        posts.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getApplicationContext(), PostsActivity.class));
            }
        });

        albums.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getApplicationContext(), AlbumsActivity.class));
            }
        });

        logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mAuth.signOut();
                finish();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });

        settings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getApplicationContext(), UserSettingsActivity.class));
            }
        });

        greeting.setText(String.format("Hello %s!", Objects.requireNonNull(mAuth.getCurrentUser()).getEmail()));
    }
}