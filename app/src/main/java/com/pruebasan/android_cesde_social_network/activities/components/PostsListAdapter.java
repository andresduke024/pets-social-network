package com.pruebasan.android_cesde_social_network.activities.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pruebasan.android_cesde_social_network.R;
import com.pruebasan.android_cesde_social_network.models.Post;

import java.util.ArrayList;

public class PostsListAdapter extends ArrayAdapter<Post> {
    public PostsListAdapter(Context context, ArrayList<Post> list) {
        super(context, R.layout.list_item_post, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Post post = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_post, parent, false);
        }

        TextView txtUsername = convertView.findViewById(R.id.txtUsername);
        TextView txtDescription = convertView.findViewById(R.id.txtDescription);

        txtUsername.setText(post.getUser().getUsername());
        txtDescription.setText(post.getMessage());

        return super.getView(position, convertView, parent);
    }
}
