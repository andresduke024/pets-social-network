package com.pruebasan.android_cesde_social_network.repository.response;

import com.pruebasan.android_cesde_social_network.models.User;

public interface LoginResponseHandler {
    void userAuthenticated(User user);
    void userAuthenticationFailed(String errorMessage);
}
