package com.pruebasan.android_cesde_social_network.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pruebasan.android_cesde_social_network.R;

public class WelcomeActivity extends AppActivity {

    Button btnLogin, btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        setViewComponents();
    }

    private void setViewComponents() {
        btnRegister = findViewById(R.id.btnRegister);
        btnLogin = findViewById(R.id.btnLogin);

        setListeners();
    }

    private void setListeners() {
        setBtnLoginClickListener();
        setBtnRegisterClickListener();
    }

    private void setBtnRegisterClickListener() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigate(RegisterActivity.class);
            }
        });
    }

    private void setBtnLoginClickListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigate(LoginActivity.class);
            }
        });
    }
}