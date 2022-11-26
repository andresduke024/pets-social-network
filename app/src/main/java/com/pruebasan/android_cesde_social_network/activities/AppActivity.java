package com.pruebasan.android_cesde_social_network.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AppActivity extends AppCompatActivity {

    public void navigate(Class<?> destination) {
        try {
            Intent intent = new Intent(this, destination);
            startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this, "Ocurrió un error, por favor inténtalo de nuevo", Toast.LENGTH_SHORT).show();
        }
    }

    public void showLoading() {
        ProgressDialog progress = new ProgressDialog(this);
        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
        progress.dismiss();
    }
}
