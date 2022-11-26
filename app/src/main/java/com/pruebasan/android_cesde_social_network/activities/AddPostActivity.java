package com.pruebasan.android_cesde_social_network.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pruebasan.android_cesde_social_network.R;
import com.pruebasan.android_cesde_social_network.models.Post;
import com.pruebasan.android_cesde_social_network.models.User;
import com.pruebasan.android_cesde_social_network.repository.CreatePostRepository;
import com.pruebasan.android_cesde_social_network.repository.local.LocalStorageRepository;
import com.pruebasan.android_cesde_social_network.repository.response.CreatePostResponseHandler;
import com.pruebasan.android_cesde_social_network.utils.Utils;
import com.pruebasan.android_cesde_social_network.utils.ValidationException;

public class AddPostActivity extends NavigationActivity implements CreatePostResponseHandler {

    ProgressBar progressBar;
    EditText txtTitle, txtDescription;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        createNavigationBar(R.string.new_post_title);
        setViewComponents();
    }

    private void setViewComponents() {
        btnSend = findViewById(R.id.btnSend);
        txtTitle = findViewById(R.id.txtTitle);
        txtDescription = findViewById(R.id.txtDescription);
        progressBar = findViewById(R.id.progressBar);

        setListeners();
    }

    private void setListeners() {
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trySendPost();
            }
        });
    }

    private void trySendPost() {
        try {
            validateFields();
            sendPost();
        } catch (ValidationException exception) {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (Exception exception) {
            Toast.makeText(this, "Ocurrió un error inesperado, asegurese de ingresar todos los campos correctamente", Toast.LENGTH_SHORT).show();
        }
    }

    private void validateFields() throws ValidationException {
        if(txtTitle.getText().toString().equals(""))
            throw new ValidationException("El título de la publicación no puede estar vacío");
        if(txtDescription.getText().toString().equals(""))
            throw new ValidationException("La publicación no puede estar vacía");
    }

    private void sendPost() {
        Post post = new Post();

        User currentLoggedUser = LocalStorageRepository.getSavedUser(getApplicationContext());
        post.setUser(currentLoggedUser);

        post.setTitle(txtTitle.getText().toString());
        post.setMessage(txtDescription.getText().toString());

        post.setCreatedAt(Utils.getDate());

        progressBar.setVisibility(View.VISIBLE);
        CreatePostRepository repository = new CreatePostRepository(this);
        repository.add(post);
    }

    /// Response Handler implementation

    @Override
    public void postCreated() {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, "Publicación creada con exito", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void postCreationFailed(String errorMessage) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}