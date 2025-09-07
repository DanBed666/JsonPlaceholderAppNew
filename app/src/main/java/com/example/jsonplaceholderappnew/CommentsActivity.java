package com.example.jsonplaceholderappnew;

import android.os.Bundle;
import android.util.Log;

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
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class CommentsActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    CommentsViewModel commentsViewModel;
    List<Comment> commentList = new ArrayList<>();
    int postId;
    DatabaseManager dm = new DatabaseManager();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_comments);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        commentsViewModel = new CommentsViewModel(commentList);
        recyclerView = findViewById(R.id.recyclerView);
        postId = getIntent().getIntExtra("ID_POSTA", 0);

        Log.i("POSTIDACTIVITY", String.valueOf(postId));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        addCommentsToDb(postId);
        getCommentsListFromDb(postId);
    }

    public void addCommentsToDb(int postId)
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

    public void getCommentsListFromDb(int postId)
    {
        DatabaseManager databaseManager = new DatabaseManager();

        Query query = db.collection("comments").whereEqualTo("postId", postId);

        databaseManager.getItemsWithQuery(query, new OnDataGetListener()
        {
            @Override
            public void setOnDataGetListener(List<DocumentSnapshot> documentSnapshotList)
            {
                recyclerView.setAdapter(new CommentsAdapter(documentSnapshotList));
            }
        });
    }
}