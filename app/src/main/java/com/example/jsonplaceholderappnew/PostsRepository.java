package com.example.jsonplaceholderappnew;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsRepository
{
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public MutableLiveData<List<Post>> getPostsList()
    {
        MutableLiveData<List<Post>> mutableLiveData = new MutableLiveData<>();

        RetrofitBuilder.getPostsService().getPostsList().enqueue(new Callback<List<Post>>()
        {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response)
            {
                mutableLiveData.setValue(response.body());

                assert response.body() != null;
                for (Post p : response.body())
                {
                    addToDatabase(p);
                }

                getDataFromDb();

                Log.i("INFO", "Pozyskano dane z repository");
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t)
            {
                Log.e("ERROR", Objects.requireNonNull(t.getMessage()));
            }
        });

        return mutableLiveData;
    }

    public void createNewPost(Post post)
    {
        RetrofitBuilder.getPostsService().createNewPost(post).enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response)
            {
                Log.i("POSTT", String.valueOf(response.body().getTitle()));
                Log.i("POSTB", String.valueOf(response.body().getBody()));
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t)
            {
                Log.e("ERROR", Objects.requireNonNull(t.getMessage()));
            }
        });
    }

    public void addToDatabase(Post post)
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

    public void getDataFromDb()
    {
        db.collection("posts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if (task.isSuccessful())
                {
                    for (QueryDocumentSnapshot document : task.getResult())
                    {
                        Log.d("GET", document.getId() + " => " + document.getData());
                    }
                }
                else
                {
                    Log.d("GET", "Error getting documents: ", task.getException());
                }
            }
        });
    }
}
