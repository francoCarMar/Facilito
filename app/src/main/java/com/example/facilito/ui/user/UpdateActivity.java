package com.example.facilito.ui.user;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.facilito.BaseActivity;
import com.example.facilito.R;
import com.example.facilito.service.AuthService;
import com.example.facilito.service.UserManager;

public class UpdateActivity extends BaseActivity {

    private EditText correo,dni, nombre, apellidos;
    private TextView result;
    private UserManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        correo = findViewById(R.id.etCorreo);
        dni = findViewById(R.id.etDni);
        nombre = findViewById(R.id.etNombre);
        apellidos = findViewById(R.id.etApellidos);
        userManager = new UserManager(this);
    }

    public void update(View v){

        String c1 = getSharedPreferences("my_preferences", MODE_PRIVATE).getString("correo", "");
        String c2 = correo.getText().toString().trim();
        Long d = Long.parseLong(dni.getText().toString().trim());
        String n = nombre.getText().toString().trim();
        String a = apellidos.getText().toString().trim();
        userManager.updateUser(c1,c2, n, a, d);

    }
}