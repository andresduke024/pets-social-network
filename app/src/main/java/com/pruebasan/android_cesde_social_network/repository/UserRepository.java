package com.pruebasan.android_cesde_social_network.repository;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRepository {
    private static UserRepository instance;

    private static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }

        return instance;
    }

    private DatabaseReference database;

    private UserRepository() {
        this.database = FirebaseDatabase.getInstance().getReference("Users");
    }
}
