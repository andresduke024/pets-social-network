package com.pruebasan.android_cesde_social_network.models;

import android.content.Context;

import com.pruebasan.android_cesde_social_network.repository.local.LocalStorageRepository;
import com.pruebasan.android_cesde_social_network.utils.Utils;

import java.text.ParseException;

public class Post implements Comparable<Post> {
    String id;
    User user;
    String title;
    String message;
    String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isEditable(Context context) {
        User currentLoggedUser = LocalStorageRepository.getSavedUser(context);
        return currentLoggedUser.getId().equals(getUser().getId());
    }

    @Override
    public int compareTo(Post post) {
        try {
            return Utils.getDate(post.getCreatedAt()).compareTo(Utils.getDate(getCreatedAt()));
        } catch (ParseException e) {
            return 0;
        }
    }
}
