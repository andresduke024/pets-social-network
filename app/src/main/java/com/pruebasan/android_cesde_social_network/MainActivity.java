package com.pruebasan.android_cesde_social_network;


import android.os.Bundle;

import com.pruebasan.android_cesde_social_network.activities.AppActivity;
import com.pruebasan.android_cesde_social_network.activities.HomeActivity;
import com.pruebasan.android_cesde_social_network.activities.WelcomeActivity;
import com.pruebasan.android_cesde_social_network.models.User;
import com.pruebasan.android_cesde_social_network.repository.local.LocalStorageRepository;

public class MainActivity extends AppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user = LocalStorageRepository.getSavedUser(getBaseContext());
        Class<?> activity;

        if(user != null){
            activity = HomeActivity.class;
        } else  {
            activity = WelcomeActivity.class;
        }

        navigate(activity);
    }
}