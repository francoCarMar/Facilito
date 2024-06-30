package com.example.facilito.service;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;

import com.example.facilito.MainActivity;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthManager {

    private AuthService authService;
    private Context context;

    public AuthManager(Context context) {
        this.context = context;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        authService = retrofit.create(AuthService.class);
    }

    public void login(String correo, String password, TextView result) {
        AuthService.LoginRequest loginRequest = new AuthService.LoginRequest(correo, password);
        Call<UserResponse> call = authService.login(loginRequest);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                String fail = "Login failed: ";
                if (response.isSuccessful()) {
                    UserResponse userResponse = response.body();
                    if (userResponse != null) {
                        savedSession(userResponse);
                        Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();
                        context.startActivity(new Intent(context, MainActivity.class));
                    } else {
                        fail += "response body is null";
                        result.setText(fail);
                    }
                } else {
                    try {
                        fail += response.errorBody().string();
                        result.setText(fail);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                String fail = "Login failed: " + t.getMessage();
                result.setText(fail);

            }
        });
    }

    public void savedSession(UserResponse userResponse){
        SharedPreferences sharedPreferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("correo", userResponse.getCorreo());
        editor.putString("nombre", userResponse.getNombre());
        editor.putString("apellido", userResponse.getApellido());
        editor.putLong("dni", userResponse.getDni());
        editor.apply();
    }
}