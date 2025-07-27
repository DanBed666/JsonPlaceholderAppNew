package com.example.jsonplaceholderappnew;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;

public class UserActivity extends AppCompatActivity
{
    UserViewModel userViewModel;
    TextView email;
    TextView name;
    TextView username;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userViewModel = new UserViewModel();
        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);

        userId = getIntent().getIntExtra("ID_USERA", 0);
        getUser(userId);
    }

    public void getUser(int userId)
    {
        userViewModel.getUser(userId).observeForever(new Observer<User>()
        {
            @Override
            public void onChanged(User user)
            {
                email.setText(user.getEmail());
                name.setText(user.getName());
                username.setText(user.getUsername());
            }
        });
    }
}