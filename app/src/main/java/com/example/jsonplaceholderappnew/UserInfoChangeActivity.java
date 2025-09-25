package com.example.jsonplaceholderappnew;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserInfoChangeActivity extends AppCompatActivity
{
    EditText nameET;
    EditText usernameET;
    EditText cityET;
    Button save;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DatabaseManager dm = new DatabaseManager();
    private final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_info_change);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nameET = findViewById(R.id.name);
        usernameET = findViewById(R.id.username);
        cityET = findViewById(R.id.city);
        save = findViewById(R.id.btn_save);

        setEditTexts();

        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                updateInfo();
                finish();
            }
        });
    }

    public void setEditTexts()
    {
        Query query = db.collection("users").whereEqualTo("id", user.getUid());

        dm.getItemsWithQuery(query, new OnDataGetListener()
        {
            @Override
            public void setOnDataGetListener(List<DocumentSnapshot> documentSnapshotList)
            {
                nameET.setText(documentSnapshotList.get(0).getString("name"));
                usernameET.setText(documentSnapshotList.get(0).getString("username"));
                cityET.setText(documentSnapshotList.get(0).getString("city"));
            }
        });
    }

    public void updateInfo()
    {
        String name = nameET.getText().toString();
        String username = usernameET.getText().toString();
        String city = cityET.getText().toString();

        Map<String, Object> data = new HashMap<>();
        data.put("name", name);
        data.put("username", username);
        data.put("city", city);

        dm.updateItem("users", user.getUid(), data);
    }
}