package com.pruebasan.android_cesde_social_network.activities.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.pruebasan.android_cesde_social_network.R;
import com.pruebasan.android_cesde_social_network.models.Post;
import com.pruebasan.android_cesde_social_network.models.enums.AvatarType;
import com.pruebasan.android_cesde_social_network.utils.AvatarResourcesFactory;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostsListAdapter extends ArrayAdapter<Post> {
    public PostsListAdapter(Context context, ArrayList<Post> list) {
        super(context, R.layout.list_item_post, R.id.textview, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Post post = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_post, parent, false);
        }

        TextView txtTitle = convertView.findViewById(R.id.txtTitle);
        TextView txtDescription = convertView.findViewById(R.id.txtDescription);
        TextView txtUsername = convertView.findViewById(R.id.txtUsername);
        TextView txtDate = convertView.findViewById(R.id.txtDate);
        TextView txtPetInfo = convertView.findViewById(R.id.txtPetInformation);
        ImageView editImage = convertView.findViewById(androidx.appcompat.R.id.image);
        CircleImageView avatarImage = convertView.findViewById(R.id.profile_image);

        txtTitle.setText(post.getTitle());
        txtDescription.setText(post.getMessage());
        txtUsername.setText(post.getUser().getUsername());
        txtDate.setText(post.getCreatedAt());

        String petInfo = post.getUser().getPetName() + " ~ " + post.getUser().getPetAge() + " Años";
        txtPetInfo.setText(petInfo);

        AvatarType avatarType = AvatarType.valueOf(post.getUser().getAvatarType());
        avatarImage.setImageResource(AvatarResourcesFactory.getResourceId(avatarType));

        if(!post.isEditable(convertView.getContext()))
            editImage.setVisibility(View.GONE);

        return super.getView(position, convertView, parent);
    }
}
