package com.pruebasan.android_cesde_social_network;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.pruebasan.android_cesde_social_network.activities.AppActivity;
import com.pruebasan.android_cesde_social_network.activities.WelcomeActivity;

public class MainActivity extends AppActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigate(WelcomeActivity.class);
    }
}