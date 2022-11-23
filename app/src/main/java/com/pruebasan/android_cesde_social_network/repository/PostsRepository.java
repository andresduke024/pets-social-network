package com.pruebasan.android_cesde_social_network.repository;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostsRepository {
    private static PostsRepository instance;

    private static PostsRepository getInstance() {
        if (instance == null) {
            instance = new PostsRepository();
        }

        return instance;
    }

    private DatabaseReference database;

    private PostsRepository() {
        this.database = FirebaseDatabase.getInstance().getReference("Posts");
    }
}
