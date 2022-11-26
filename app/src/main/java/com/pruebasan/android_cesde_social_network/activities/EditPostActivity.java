package com.pruebasan.android_cesde_social_network.activities;

import androidx.appcompat.app.AppCompatActivity;

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
import com.pruebasan.android_cesde_social_network.repository.EditPostRepository;
import com.pruebasan.android_cesde_social_network.repository.local.LocalStorageRepository;
import com.pruebasan.android_cesde_social_network.repository.response.EditPostResponseHandler;
import com.pruebasan.android_cesde_social_network.utils.Utils;
import com.pruebasan.android_cesde_social_network.utils.ValidationException;

public class EditPostActivity extends NavigationActivity implements EditPostResponseHandler {
    ProgressBar progressBar;
    EditText txtTitle, txtDescription;
    Button btnSend;

    Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);

        createNavigationBar(R.string.edit_post_title);
        setViewComponents();
        getSavedPost();
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
            public void onClick(View view) { tryEditPost(); }
        });
    }

    public void getSavedPost() {
        post = LocalStorageRepository.getSavedPost(this);
        
        if(post == null) {
            closeScreen(R.string.error);
            return;
        }

        txtTitle.setText(post.getTitle());
        txtDescription.setText(post.getMessage());
    }

    private void closeScreen(int messageId) {
        finish();
        Toast.makeText(getApplicationContext(), messageId, Toast.LENGTH_SHORT).show();
    }

    private void tryEditPost() {
        try {
            validateFields();
            sendPost();
        } catch (ValidationException exception) {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
        } catch (Exception exception) {
            Toast.makeText(this, "Ocurrió un error inesperado, asegurese de ingresar todos los campos correctamente", Toast.LENGTH_LONG).show();
        }
    }

    private void validateFields() throws ValidationException {
        if(txtTitle.getText().toString().equals(""))
            throw new ValidationException("El título de la publicación no puede estar vacío");
        if(txtDescription.getText().toString().equals(""))
            throw new ValidationException("La publicación no puede estar vacía");
    }

    private void sendPost() {
        post.setTitle(txtTitle.getText().toString());
        post.setMessage(txtDescription.getText().toString());

        progressBar.setVisibility(View.VISIBLE);
        EditPostRepository repository = new EditPostRepository(this);
        repository.edit(post);
    }

    /// Response Handler implementation

    @Override
    public void postEdited() {
        progressBar.setVisibility(View.GONE);
        closeScreen(R.string.post_edited);
    }
}
