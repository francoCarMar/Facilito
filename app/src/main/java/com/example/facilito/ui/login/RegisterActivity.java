package com.example.facilito.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.facilito.BaseActivity;
import com.example.facilito.MainActivity;
import com.example.facilito.R;
import com.example.facilito.service.AuthService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends BaseActivity {
    private EditText correo,dni, nombre, apellidos, password, passwordConfirm;
    private TextView result;
    private AuthService authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        correo = findViewById(R.id.etCorreo);
        dni = findViewById(R.id.etDni);
        nombre = findViewById(R.id.etNombre);
        apellidos = findViewById(R.id.etApellidos);
        password = findViewById(R.id.etPassword);
        passwordConfirm = findViewById(R.id.etPasswordConfirm);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        authService = retrofit.create(AuthService.class);
        result = findViewById(R.id.tvResult);
    }

    public void register(View v){
        String c = correo.getText().toString().trim();
        Long d = Long.parseLong(dni.getText().toString().trim());
        String n = nombre.getText().toString().trim();
        String a = apellidos.getText().toString().trim();
        String p = password.getText().toString().trim();
        if(c.isEmpty() || n.isEmpty() || a.isEmpty() || p.isEmpty()){
            result.setText("Complete los campos");
            return;
        }
        AuthService.RegisterRequest registerRequest = new AuthService.RegisterRequest(c,d,n,a,p);

        Call<Void> call = authService.register(registerRequest);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(RegisterActivity.this, "Register Successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);

                } else {
                    result.setText("Error al registrar");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }
    public void goToLogin(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}