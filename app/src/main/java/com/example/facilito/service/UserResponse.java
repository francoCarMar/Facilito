package com.example.facilito.service;

public class UserResponse {
    private String nombre;
    private String apellido;
    private String correo;
    private Long dni;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public UserResponse(String correo, String nombre, String apellido, Long dni) {
        this.correo = correo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }
}