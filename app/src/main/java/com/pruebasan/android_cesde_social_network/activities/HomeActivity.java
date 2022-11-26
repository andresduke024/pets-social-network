package com.pruebasan.android_cesde_social_network.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pruebasan.android_cesde_social_network.R;
import com.pruebasan.android_cesde_social_network.activities.components.PostsListAdapter;
import com.pruebasan.android_cesde_social_network.models.Post;
import com.pruebasan.android_cesde_social_network.repository.PostsRepository;
import com.pruebasan.android_cesde_social_network.repository.response.PostsResponseHandler;

import java.util.ArrayList;
import java.util.Collections;

public class HomeActivity extends AppActivity implements PostsResponseHandler {

    FloatingActionButton btnAddPost;
    ListView listView;
    ProgressBar progressBar;

    ArrayList<Post> posts = new ArrayList<>();
    ArrayAdapter adapter;
    PostsRepository repository;

    Post selectedPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setViewComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updatePosts();
    }

    private void setViewComponents() {
        listView = findViewById(R.id.postsListView);
        btnAddPost = findViewById(R.id.btnAddPost);
        progressBar = findViewById(R.id.progressBar);

        setOnClickListeners();
        setupListView();
    }

    private void setOnClickListeners() {
        btnAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigate(AddPostActivity.class);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Post post = posts.get(position);

                if(!post.isEditable(getApplicationContext())) return;

                selectedPost = post;
                showPostOptionsDialog(view);
            }
        });
    }

    public void showPostOptionsDialog(View view) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.post_options_title);
        builder.setMessage(R.string.post_options_description);

        builder.setPositiveButton(R.string.edit_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                editPost();
            }
        });

        builder.setNeutralButton(R.string.delete_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                deletePost();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setupListView() {
        adapter = new PostsListAdapter(HomeActivity.this, posts);

        listView.setAdapter(adapter);
        listView.setClickable(true);
    }

    private void updatePosts() {
        if (repository == null)
            repository = new PostsRepository(this);

        progressBar.setVisibility(View.VISIBLE);
        repository.getAll();
    }

    private void deletePost() {
        progressBar.setVisibility(View.VISIBLE);
        repository.deletePost(selectedPost);
        selectedPost = null;
    }

    private void editPost() {

    }

    /// Response Handler implementation

    @Override
    public void updatePosts(Post post) {
        progressBar.setVisibility(View.GONE);
        removePostFromList(post);
        posts.add(post);
        Collections.sort(posts);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void postRemoved(Post post) {
        removePostFromList(post);
        Collections.sort(posts);
        adapter.notifyDataSetChanged();
        finishPostRemove(R.string.post_deleted);
    }

    @Override
    public void postRemoveFailed() { finishPostRemove(R.string.post_delete_fail); }

    private void finishPostRemove(int messageId) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, messageId, Toast.LENGTH_LONG).show();
    }

    private void removePostFromList(Post post) {
        for (Post item: posts) {
            if (item.getId().equals(post.getId())){
                posts.remove(item);
                break;
            }
        }
    }
}