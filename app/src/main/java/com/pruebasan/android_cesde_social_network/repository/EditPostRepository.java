package com.pruebasan.android_cesde_social_network.repository;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pruebasan.android_cesde_social_network.models.Post;
import com.pruebasan.android_cesde_social_network.repository.response.EditPostResponseHandler;

public class EditPostRepository {
    private DatabaseReference database;
    private EditPostResponseHandler responseHandler;

    private Integer editedFields;

    public EditPostRepository(EditPostResponseHandler responseHandler) {
        this.editedFields = 0;
        this.responseHandler = responseHandler;
        this.database = FirebaseDatabase.getInstance().getReference("Posts");
    }

    public void edit(Post post) {
        database.child(post.getId()).child("title").setValue(post.getTitle()).addOnCompleteListener(taskCompletionListener);
        database.child(post.getId()).child("message").setValue(post.getMessage()).addOnCompleteListener(taskCompletionListener);
    }


    private OnCompleteListener<Void> taskCompletionListener = new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            if(editedFields == 1)
                responseHandler.postEdited();
            else editedFields++;
        }
    };
}
