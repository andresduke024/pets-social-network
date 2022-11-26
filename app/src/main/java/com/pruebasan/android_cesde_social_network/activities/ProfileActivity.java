package com.pruebasan.android_cesde_social_network.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pruebasan.android_cesde_social_network.R;
import com.pruebasan.android_cesde_social_network.models.User;
import com.pruebasan.android_cesde_social_network.models.enums.AvatarType;
import com.pruebasan.android_cesde_social_network.repository.local.LocalStorageRepository;
import com.pruebasan.android_cesde_social_network.utils.AvatarResourcesFactory;

public class ProfileActivity extends NavigationActivity {

    TextView txtUsername, txtEmail, txtDate, txtPetInfo;
    ImageView avatarImage;
    Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        createNavigationBar(R.string.profile_title);
        setViewComponents();
        setupUI();
    }

    private void setViewComponents() {
        txtUsername = findViewById(R.id.txtUsername);
        txtEmail = findViewById(R.id.txtEmail);
        txtDate = findViewById(R.id.txtDate);
        txtPetInfo = findViewById(R.id.txtPetInformation);
        avatarImage = findViewById(R.id.profile_image);
        logoutButton = findViewById(R.id.btnLogout);

        setListeners();
    }

    private void setListeners() {
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LocalStorageRepository.deleteUser(getApplicationContext());
                navigate(WelcomeActivity.class);
            }
        });
    }

    private void setupUI() {
        User user = LocalStorageRepository.getSavedUser(this);

        txtUsername.setText(user.getUsername());
        txtEmail.setText(user.getEmail());
        txtDate.setText(user.getCreatedAt());

        String petInfo = user.getPetName() + " ~ " + user.getPetAge() + " Años";
        txtPetInfo.setText(petInfo);

        AvatarType avatarType = AvatarType.valueOf(user.getAvatarType());
        avatarImage.setImageResource(AvatarResourcesFactory.getResourceId(avatarType));
    }
}