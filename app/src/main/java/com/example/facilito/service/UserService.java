package com.example.facilito.service;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {
    @GET("/correo/{correo}")
    Call<UserResponse> correo(@Path("correo") String correo);
    @PUT("/update/{correo}")
    Call<UserResponse> correo(@Body PutUserRequest request, @Path("correo") String correo );


    class GetUserRequest {
        private String correo;

        public GetUserRequest(String correo) {
            this.correo = correo;
        }
    }

    class PutUserRequest {
        private String correo;
        private String nombre;
        private String apellido;
        private Long dni;

        public PutUserRequest(String correo, String nombre, String apellido, Long dni){
            this.correo = correo;
            this.nombre = nombre;
            this.apellido = apellido;
            this.dni = dni;
        }
    }

}
