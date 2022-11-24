package com.pruebasan.android_cesde_social_network.repository;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pruebasan.android_cesde_social_network.models.User;

public class UserRepository {

    private DatabaseReference database;

    public UserRepository() {
        this.database = FirebaseDatabase.getInstance().getReference("Users");
    }

    public void add(User user) {

    }
}
