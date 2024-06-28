package com.example.facilito.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.facilito.BaseActivity;
import com.example.facilito.R;
import com.example.facilito.service.AuthManager;
import com.example.facilito.service.UserManager;

public class LoginActivity extends BaseActivity{

    private EditText correo, password;
    private TextView result;

    private AuthManager authManager;
    private UserManager userManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        correo = findViewById(R.id.etCorreo);
        password = findViewById(R.id.etPassword);

        authManager = new AuthManager(this);
        result = findViewById(R.id.tvResult);
        userManager = new UserManager(this);
    }
    public void login(View v){
        String c = correo.getText().toString().trim();
        String p = password.getText().toString().trim();
        authManager.login(c,p);
        userManager.getUser(c);
    }
    public void goToRegister(View v) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

}