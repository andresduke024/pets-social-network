package com.pruebasan.android_cesde_social_network.repository;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.pruebasan.android_cesde_social_network.models.User;
import com.pruebasan.android_cesde_social_network.repository.response.LoginResponseHandler;

public class LoginRepository {

    private DatabaseReference database;
    private LoginResponseHandler responseHandler;

    private User currentUser;

    public LoginRepository(LoginResponseHandler responseHandler) {
        this.responseHandler = responseHandler;
        this.database = FirebaseDatabase.getInstance().getReference("Users");
    }

    public void performLogin(User user) {
        currentUser = user;
        Query query = database.orderByChild("email").equalTo(user.getEmail());
        query.addListenerForSingleValueEvent(loginEventListener);
    }

    private ValueEventListener loginEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (!dataSnapshot.exists()) {
                responseHandler.userAuthenticationFailed("No se encontró ningún usuario con el correo ingresado");
                return;
            }

            for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                if(validateUser(snapshot.getValue(User.class))) break;
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            responseHandler.userAuthenticationFailed("Ocurrió un error inesperado, por favor intentalo de nuevo");
        }
    };

    private boolean validateUser(User user) {
        if (user == null) return false;

        if(user.getPassword().equals(currentUser.getPassword())) {
            responseHandler.userAuthenticated(user);
            return true;
        }

        responseHandler.userAuthenticationFailed("Usuario o contraseña incorrectos");
        return true;
    }
}
