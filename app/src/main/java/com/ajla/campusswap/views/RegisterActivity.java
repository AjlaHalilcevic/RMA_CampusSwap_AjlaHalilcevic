package com.ajla.campusswap.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.ajla.campusswap.R;
import com.ajla.campusswap.viewmodels.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {
    private EditText emailInput, passwordInput;
    private Button registerButton;
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailInput = findViewById(R.id.emailRegister);
        passwordInput = findViewById(R.id.passwordRegister);
        registerButton = findViewById(R.id.registerButton);
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        registerButton.setOnClickListener(v -> {
            String email = emailInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();
            if (!email.isEmpty() && password.length() >= 6) {
                registerViewModel.register(email, password);
            } else {
                Toast.makeText(this, "Enter valid email and 6+ char password", Toast.LENGTH_SHORT).show();
            }
        });

        registerViewModel.getUserLiveData().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
