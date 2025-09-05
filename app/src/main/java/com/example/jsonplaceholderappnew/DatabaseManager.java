package com.example.jsonplaceholderappnew;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class DatabaseManager
{
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    OnDataGetListener onDataGetListener;
    public void addItem(Post post)
    {
        db.collection("posts").document(String.valueOf(post.getId())).set(post)
                .addOnSuccessListener(new OnSuccessListener<Void>()
                {
                    @Override
                    public void onSuccess(Void unused)
                    {
                        Log.d("FIREBASE", "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Log.e("FIREBASE", "Error writing document: " + e.getMessage(), e);
                    }
                });
    }

    public void getAllItems(String collectionName, OnDataGetListener onDataGetListener)
    {
        db.collection(collectionName).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if (task.isSuccessful())
                {
                    onDataGetListener.setOnDataGetListener(task.getResult().getDocuments());
                }
                else
                {
                    Log.d("GET", "Error getting documents: ", task.getException());
                }
            }
        });
    }
}
