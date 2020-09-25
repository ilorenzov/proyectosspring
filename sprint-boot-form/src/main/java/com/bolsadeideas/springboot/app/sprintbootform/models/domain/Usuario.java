package com.bolsadeideas.springboot.app.sprintbootform.models.domain;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Usuario {
    @NotEmpty(message = "el nombre no puede ser vacio")
    private String nombre;

    @NotEmpty
    private String apellido;

    //no va a estar en el formulario, no se valida
    private String identificador;

    @NotEmpty //no vacio/required
    @Size(min = 3, max = 8)
    private String username;

    @NotEmpty
    @Email(message = "correo con formato pocho")
    private String email;
    @NotEmpty
    private String password;



    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}