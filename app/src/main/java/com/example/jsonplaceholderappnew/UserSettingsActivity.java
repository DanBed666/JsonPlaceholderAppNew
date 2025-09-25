package com.example.jsonplaceholderappnew;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.List;

public class UserSettingsActivity extends AppCompatActivity
{
    TextView email;
    TextView name;
    TextView username;
    TextView city;
    Button info;
    Button email_btn;
    Button password;
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    DatabaseManager dm = new DatabaseManager();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ActivityResultLauncher<Intent> someActivityResultLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        city = findViewById(R.id.city);
        info = findViewById(R.id.btn_info_change);
        email_btn = findViewById(R.id.btn_email_change);
        password = findViewById(R.id.btn_password_change);

        assert user != null;
        email.setText(user.getEmail());

        info.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), UserInfoChangeActivity.class);
                someActivityResultLauncher.launch(intent);
            }
        });

        email_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getApplicationContext(), ChangeEmailActivity.class));
            }
        });

        password.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(getApplicationContext(), ChangePasswordActivity.class));
            }
        });

        getUserData();
        getResultFromActivity();
    }

    public void getUserData()
    {
        Query query = db.collection("users").whereEqualTo("id", user.getUid());

        dm.getItemsWithQuery(query, new OnDataGetListener()
        {
            @Override
            public void setOnDataGetListener(List<DocumentSnapshot> documentSnapshotList)
            {
                name.setText(documentSnapshotList.get(0).getString("name"));
                username.setText(documentSnapshotList.get(0).getString("username"));
                city.setText(documentSnapshotList.get(0).getString("city"));
            }
        });
    }

    public void refresh()
    {
        this.recreate();
    }

    public void getResultFromActivity()
    {
        someActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>()
                {
                    @Override
                    public void onActivityResult(ActivityResult result)
                    {
                        if (result.getResultCode() == UserInfoChangeActivity.RESULT_OK)
                        {
                            refresh();
                        }
                    }
                });
    }
}