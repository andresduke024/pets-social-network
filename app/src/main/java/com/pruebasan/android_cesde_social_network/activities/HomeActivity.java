package com.pruebasan.android_cesde_social_network.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pruebasan.android_cesde_social_network.R;
import com.pruebasan.android_cesde_social_network.activities.components.PostsListAdapter;
import com.pruebasan.android_cesde_social_network.models.Post;
import com.pruebasan.android_cesde_social_network.repository.PostsRepository;
import com.pruebasan.android_cesde_social_network.repository.response.PostsResponseHandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class HomeActivity extends AppActivity implements PostsResponseHandler {

    FloatingActionButton btnAddPost;
    ListView listView;
    ProgressBar progressBar;

    ArrayList<Post> posts = new ArrayList<>();
    ArrayAdapter adapter;
    PostsRepository repository;

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

                //TODO: MOSTRAR DIALOG DE EDIT
            }
        });
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

    /// Response Handler implementation

    @Override
    public void updatePosts(Post post) {
        progressBar.setVisibility(View.GONE);
        posts.add(post);
        Collections.sort(posts);
        adapter.notifyDataSetChanged();
    }
}