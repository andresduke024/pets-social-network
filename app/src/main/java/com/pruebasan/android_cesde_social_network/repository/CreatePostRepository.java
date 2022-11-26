package com.pruebasan.android_cesde_social_network.repository;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pruebasan.android_cesde_social_network.models.Post;
import com.pruebasan.android_cesde_social_network.repository.response.CreatePostResponseHandler;

public class CreatePostRepository {
    private DatabaseReference database;
    private CreatePostResponseHandler responseHandler;

    public CreatePostRepository(CreatePostResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
        this.database = FirebaseDatabase.getInstance().getReference("Posts");
    }

    public void add(Post post) {
        String id = database.push().getKey();
        post.setId(id);

        database.child(id).setValue(post).addOnCompleteListener(completionListener);
    }

    private OnCompleteListener<Void> completionListener = new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful()) {
                responseHandler.postCreated();
            } else {
                responseHandler.postCreationFailed("Ocurrió un error registrando al usuario");
            }
        }
    };

}
