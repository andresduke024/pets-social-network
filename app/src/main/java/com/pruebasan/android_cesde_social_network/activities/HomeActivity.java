package com.pruebasan.android_cesde_social_network.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.pruebasan.android_cesde_social_network.R;
import com.pruebasan.android_cesde_social_network.activities.components.PostsListAdapter;
import com.pruebasan.android_cesde_social_network.databinding.ActivityHomeBinding;
import com.pruebasan.android_cesde_social_network.databinding.ActivityMainBinding;
import com.pruebasan.android_cesde_social_network.models.Post;
import com.pruebasan.android_cesde_social_network.repository.PostsRepository;
import com.pruebasan.android_cesde_social_network.repository.local.LocalStorageRepository;
import com.pruebasan.android_cesde_social_network.repository.response.PostsResponseHandler;
import com.pruebasan.android_cesde_social_network.utils.Utils;

import java.util.ArrayList;

public class HomeActivity extends AppActivity implements PostsResponseHandler {

    FloatingActionButton btnAddPost;

    ActivityHomeBinding binding;
    ArrayList<Post> posts = new ArrayList<>();
    PostsRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btnAddPost = findViewById(R.id.btnAddPost);

        ListAdapter adapter = new PostsListAdapter(HomeActivity.this, posts);

        binding.postsListView.setAdapter(adapter);
        binding.postsListView.setClickable(true);

        setOnClickListeners();

        repository = new PostsRepository(this);
        //repository.getAll();
    }

    private void setOnClickListeners() {
        btnAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigate(AddPostActivity.class);
            }
        });
    }

    /// Response Handler implementation

    @Override
    public void updatePosts(Post post) {
        this.posts.add(post);
    }
}