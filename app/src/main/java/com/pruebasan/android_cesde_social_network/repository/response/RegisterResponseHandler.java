package com.pruebasan.android_cesde_social_network.repository.response;

import com.pruebasan.android_cesde_social_network.models.User;

public interface RegisterResponseHandler {
    void userRegistered(User user);
    void registerFailed(String errorMessage);
}
