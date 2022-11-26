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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
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

    public void getAll() {
        database.addChildEventListener(dataChangeEventListener);
    }

    ChildEventListener dataChangeEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            handleNewData(dataSnapshot);
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            handleNewData(dataSnapshot);
        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            if (!dataSnapshot.exists()) return;

            try {
                Post post = dataSnapshot.getValue(Post.class);
                responseHandler.postRemoved(post);
            } catch (Exception e) {}
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {}

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {}
    };

    private void handleNewData(DataSnapshot dataSnapshot) {
        if (!dataSnapshot.exists()) return;

        try {
            Post post = dataSnapshot.getValue(Post.class);
            responseHandler.updatePosts(post);
        } catch (Exception e) {}
    }

    public void deletePost(Post post) {
        Query query = database.orderByChild("id").equalTo(post.getId());
        query.addListenerForSingleValueEvent(deleteEventListener);
    }

    ValueEventListener deleteEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                appleSnapshot.getRef().removeValue();
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            responseHandler.postRemoveFailed();
        }
    };
}
