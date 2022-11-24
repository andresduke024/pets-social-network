package com.pruebasan.android_cesde_social_network.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class NavigationActivity extends AppActivity {

    protected void createNavigationBar(int navigationTitleResourceId) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(navigationTitleResourceId);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
