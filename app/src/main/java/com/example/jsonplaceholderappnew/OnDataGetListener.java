package com.example.jsonplaceholderappnew;

import com.google.firebase.firestore.DocumentSnapshot;

import java.util.List;

public interface OnDataGetListener
{
    void setOnDataGetListener(List<DocumentSnapshot> documentSnapshotList);
}
