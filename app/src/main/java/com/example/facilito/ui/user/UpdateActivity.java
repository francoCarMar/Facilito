package com.example.facilito.ui.user;

import android.content.SharedPreferences;
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
    private SharedPreferences preferences;

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

        preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        correo = findViewById(R.id.etCorreo);
        correo.setText(preferences.getString("correo", ""));
        dni = findViewById(R.id.etDni);
        Long numberDNI = getSharedPreferences("my_preferences", MODE_PRIVATE).getLong("dni", -1);
        dni.setText(String.valueOf(numberDNI));
        nombre = findViewById(R.id.etNombre);
        nombre.setText(preferences.getString("nombre", ""));
        apellidos = findViewById(R.id.etApellidos);
        apellidos.setText(preferences.getString("apellido", ""));
        userManager = new UserManager(this);
    }

    public void update(View v){

        String c1 = preferences.getString("correo", "");
        String c2 = correo.getText().toString().trim();
        Long d = Long.parseLong(dni.getText().toString().trim());
        String n = nombre.getText().toString().trim();
        String a = apellidos.getText().toString().trim();
        if(c2.isEmpty() || n.isEmpty() || a.isEmpty()){
            result.setText("Complete los campos");
            return;
        }
        userManager.updateUser(c1,c2, n, a, d);

    }
}