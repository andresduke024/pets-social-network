package com.pruebasan.android_cesde_social_network.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.pruebasan.android_cesde_social_network.R;
import com.pruebasan.android_cesde_social_network.models.User;
import com.pruebasan.android_cesde_social_network.models.enums.AvatarType;
import com.pruebasan.android_cesde_social_network.repository.RegisterRepository;
import com.pruebasan.android_cesde_social_network.repository.local.LocalStorageRepository;
import com.pruebasan.android_cesde_social_network.repository.response.RegisterResponseHandler;
import com.pruebasan.android_cesde_social_network.utils.Utils;
import com.pruebasan.android_cesde_social_network.utils.ValidationException;

public class RegisterActivity extends NavigationActivity implements RegisterResponseHandler {

    Button btnRegister;
    EditText txtUsername, txtEmail, txtPassword, txtPhone, txtPetName, txtPetAge;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        createNavigationBar(R.string.register_title);
        setViewComponents();
    }

    private void setViewComponents() {
        btnRegister = findViewById(R.id.btnRegister);
        txtUsername = findViewById(R.id.txtUsername);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtPhone = findViewById(R.id.txtPhone);
        txtPetName = findViewById(R.id.txtPetName);
        txtPetAge = findViewById(R.id.txtPetAge);
        progressBar = findViewById(R.id.progressBar);

        setListeners();
    }

    private void setListeners() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tryRegisterUser();
            }
        });
    }

    private void tryRegisterUser() {
        try {
            validateFields();
            createUser();
        } catch (ValidationException exception) {
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
        } catch (Exception exception) {
            Toast.makeText(this, "Ocurrió un error inesperado, asegurese de ingresar todos los campos correctamente", Toast.LENGTH_LONG).show();
        }
    }

    private void validateFields() throws ValidationException {
        if(txtUsername.getText().toString().equals(""))
            throw new ValidationException("El nombre no puede estar vacío");
        if(txtEmail.getText().toString().equals(""))
            throw new ValidationException("El correo no puede estar vacío");
        if(txtPassword.getText().toString().equals(""))
            throw new ValidationException("La contraseña no puede estar vacía");
        if(txtPhone.getText().toString().equals(""))
            throw new ValidationException("El teléfono no puede estar vacío");
        if(txtPetName.getText().toString().equals(""))
            throw new ValidationException("El nombre de la mascota no puede estar vacío");
        if(txtPetAge.getText().toString().equals(""))
            throw new ValidationException("La edad de la mascota no puede estar vacía");

        try {
            Integer.parseInt(txtPetAge.getText().toString());
        } catch (Exception e) {
            throw new ValidationException("Asegúrate de ingresar solo números para la edad de la mascota");
        }
    }

    private void createUser() {
        User user = new User();

        user.setUsername(txtUsername.getText().toString());
        user.setEmail(txtEmail.getText().toString());
        user.setPassword(txtPassword.getText().toString());
        user.setPhone(txtPhone.getText().toString());
        user.setPetName(txtPetName.getText().toString());

        int petAge = Integer.parseInt(txtPetAge.getText().toString());
        user.setPetAge(petAge);

        AvatarType avatar = AvatarType.getRandom();
        user.setAvatarType(avatar.toString());

        String date = Utils.getDate();
        user.setCreatedAt(date);

        progressBar.setVisibility(View.VISIBLE);
        RegisterRepository repository = new RegisterRepository(this);
        repository.performRegister(user);
    }

    /// Response Handler implementation

    @Override
    public void userRegistered(User user) {
        progressBar.setVisibility(View.GONE);
        LocalStorageRepository.saveUser(getBaseContext(), user);
        navigate(HomeActivity.class);
    }

    @Override
    public void registerFailed(String errorMessage) {
        progressBar.setVisibility(View.GONE);
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }
}