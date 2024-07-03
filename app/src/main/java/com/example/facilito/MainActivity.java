package com.example.facilito;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.facilito.ui.login.HomeActivity;
import com.example.facilito.ui.login.LoginActivity;
import com.example.facilito.ui.user.ConfigPantallaActivity;
import com.example.facilito.ui.user.FavoritosActivity;
import com.example.facilito.ui.user.PedidosActivity;
import com.example.facilito.ui.user.UpdateActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.facilito.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    private TextView tvUsuario, tvDni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        BottomNavigationView navView = findViewById(R.id.nav_view1);

        // Configura el Toolbar
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Configura el NavController
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_grifo, R.id.navigation_gas, R.id.navigation_tramites, R.id.navigation_balon,
                R.id.navigation_electricidad)
                .setOpenableLayout(drawer)
                .build();
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);
        NavigationUI.setupWithNavController(navigationView, navController);
        NavigationUI.setupWithNavController(navView, navController);

        // Configura el Drawer Toggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // Manejo de elementos del menú de navegación
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.navFavorites) {
                    Toast.makeText(MainActivity.this, "Mis favoritos seleccionados", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, FavoritosActivity.class);
                    startActivity(intent);
                    finish();
                } else if (id == R.id.navOrders) {
                    Toast.makeText(MainActivity.this, "Pedidos seleccionados", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, PedidosActivity.class);
                    startActivity(intent);
                } else if (id == R.id.navHome) {
                    Toast.makeText(MainActivity.this, "Pantalla Inicial seleccionada", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, ConfigPantallaActivity.class);
                    startActivity(intent);
                } else if (id == R.id.navLogin) {
                    Toast.makeText(MainActivity.this, "Iniciar sesión seleccionado", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else if(id == R.id.navLogout){
                    logout();
                    updateTextView();
                } else if(id == R.id.navUpdate){
                    Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    return false;
                }

                drawer.closeDrawers();
                return true;
            }
        });

        // Configura el botón de la casita
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
                updateTextView();
            }
        });

        View headerView = navigationView.getHeaderView(0);
        tvUsuario = headerView.findViewById(R.id.tvUsuario);
        tvDni = headerView.findViewById(R.id.tvDni);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    public void updateTextView() {
        String usuario = getSharedPreferences("my_preferences", MODE_PRIVATE).getString("nombre", "");
        Long dni = getSharedPreferences("my_preferences", MODE_PRIVATE).getLong("dni", 0);
        tvUsuario.setText(usuario);
        if (dni != 0) {
            tvDni.setText("DNI:" + dni);
        }
    }

    public void logout() {
        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(MainActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void activateItem(MenuItem item) {
        if (isLogged()) {
            item.setVisible(true);
        }
    }

    public boolean isLogged() {
        SharedPreferences sharedPreferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        return sharedPreferences.contains("nombre");
    }

}