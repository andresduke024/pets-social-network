package com.pruebasan.android_cesde_social_network.repository;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pruebasan.android_cesde_social_network.models.Post;
import com.pruebasan.android_cesde_social_network.models.User;
import com.pruebasan.android_cesde_social_network.repository.response.PostsResponseHandler;

import java.util.ArrayList;

public class PostsRepository {
    private DatabaseReference database;
    private PostsResponseHandler responseHandler;

    public PostsRepository(PostsResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
        this.database = FirebaseDatabase.getInstance().getReference("Posts");
    }

    public void add(Post post) {
        String id = database.push().getKey();
        post.setId(id);
        database.child(id).setValue(post).addOnCompleteListener(newPostCompletionListener);
    }

    private OnCompleteListener<Void> newPostCompletionListener = new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful()) {

            } else {

            }
        }
    };

    public void getAll() {
        database.addChildEventListener(dataChangeEventListener);
    }

    ChildEventListener dataChangeEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            if (!dataSnapshot.exists()) return;

            try {
                Post post = dataSnapshot.getValue(Post.class);
                responseHandler.updatePosts(post);
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }

        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            if (!dataSnapshot.exists()) return;

            try {
                Post post = dataSnapshot.getValue(Post.class);
                responseHandler.updatePosts(post);
            } catch (Exception e) {
                System.out.println(e.getStackTrace());
            }
        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };
}
