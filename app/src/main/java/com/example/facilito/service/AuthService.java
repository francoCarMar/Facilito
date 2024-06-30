package com.example.facilito.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthService {
    @POST("/login")
    Call<UserResponse> login(@Body LoginRequest loginRequest);
    @POST("/registro")
    Call<Void> register(@Body RegisterRequest registerRequest);


    class LoginRequest {
        private String correo;
        private String password;

        public LoginRequest(String correo, String password) {
            this.correo = correo;
            this.password = password;
        }
    }


    class RegisterRequest {
        private String correo;
        private Long dni;
        private String nombre;
        private String apellido;
        private String password;

        public RegisterRequest(String correo, Long dni, String nombre, String apellido, String password){
            this.correo = correo;
            this.dni = dni;
            this.nombre = nombre;
            this.apellido = apellido;
            this.password = password;
        }
    }
}
