package com.pruebasan.android_cesde_social_network.repository.response;

public interface CreatePostResponseHandler {
    void postCreated();
    void postCreationFailed(String errorMessage);
}
