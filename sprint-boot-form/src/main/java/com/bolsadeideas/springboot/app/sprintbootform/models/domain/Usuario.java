package com.bolsadeideas.springboot.app.sprintbootform.models.domain;

import com.bolsadeideas.springboot.app.sprintbootform.validation.IdentificadorRegex;
import com.bolsadeideas.springboot.app.sprintbootform.validation.Requerido;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

public class Usuario {
    /*    @NotEmpty(message = "el nombre no puede ser vacio")*/
    private String nombre;

    //@NotEmpty
    @Requerido
    private String apellido;
    //no va a estar en el formulario, no se valida
    /*    @Pattern(regexp = "[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][A-Z]{1}")*/
    @IdentificadorRegex
    private String identificador;
    /*@NotEmpty*/ //no vacio/required
    @NotBlank
    @Size(min = 3, max = 8)
    private String username;
    @NotEmpty
    @Email(message = "correo con formato pocho")
    private String email;
    @NotEmpty
    private String password;
    @NotNull //para objetos , para int que es primitivo @Min(1)
    @Min(5)
    @Max(5000)
    private Integer cuenta;
    @NotNull
    /*    @Past */
    @Future
    /* @DateTimeFormat(pattern = "yyyy-MM-dd")*/ //formato fecha
    private Date fechaNacimiento;
    @NotNull
    private Pais pais;
    @NotEmpty
    private List<Role> roles;
    private boolean habilitar;
    @NotEmpty
    private String genero;
    private String valorSecreto;

    public String getValorSecreto() {
        return valorSecreto;
    }

    public void setValorSecreto(String valorSecreto) {
        this.valorSecreto = valorSecreto;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean isHabilitar() {
        return habilitar;
    }

    public void setHabilitar(boolean habilitar) {
        this.habilitar = habilitar;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getCuenta() {
        return cuenta;
    }

    public void setCuenta(Integer cuenta) {
        this.cuenta = cuenta;
    }

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
