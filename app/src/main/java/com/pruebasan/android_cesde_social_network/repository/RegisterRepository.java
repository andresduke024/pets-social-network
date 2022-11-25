package com.pruebasan.android_cesde_social_network.repository;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pruebasan.android_cesde_social_network.models.User;
import com.pruebasan.android_cesde_social_network.repository.response.RegisterResponseHandler;

public class RegisterRepository {
    private DatabaseReference database;
    private RegisterResponseHandler responseHandler;

    private User currentUser;

    public RegisterRepository(RegisterResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
        this.database = FirebaseDatabase.getInstance().getReference("Users");
    }

    public void performRegister(User user) {
        String id = database.push().getKey();
        user.setId(id);
        currentUser = user;

        validateEmail(user.getEmail());
    }

    private void validateEmail(String email) {
        Query query = database.orderByChild("email").equalTo(email);
        query.addListenerForSingleValueEvent(validateEmailEventListener);
    }

    private ValueEventListener validateEmailEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (!dataSnapshot.exists()) {
                database.child(currentUser.getId()).setValue(currentUser).addOnCompleteListener(completionListener);
                return;
            }

            responseHandler.registerFailed("El correo ya se encuentra registrado");
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            responseHandler.registerFailed("Ocurrió un error inesperado, por favor intentalo de nuevo");
        }
    };

    private OnCompleteListener<Void> completionListener = new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {
            if (task.isSuccessful()) {
                responseHandler.userRegistered(currentUser);
            } else {
                responseHandler.registerFailed("Ocurrió un error registrando al usuario");
            }
        }
    };
}
