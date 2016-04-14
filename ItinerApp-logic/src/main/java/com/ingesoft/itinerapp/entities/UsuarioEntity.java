package com.ingesoft.itinerapp.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

/**
 * @generated
 */
@Entity
public class UsuarioEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "Usuario")
    private Long id;
    private String nombre;
    private String apellido;
    private String username;
    private String email;
    private String cedula;

    public UsuarioEntity() {}

    public UsuarioEntity(Long pId, String pNombre, String pApellido, String pUsername, String pEmail, String pCedula) {
        id = pId;
        nombre = pNombre;
        apellido = pApellido;
        username = pUsername;
        email = pEmail;
        cedula = pCedula;
    }

    public Long getId() {
        return id;
    }

    public void setId(long pId) {
        this.id = pId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String pApellido) {
        this.apellido = pApellido;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String pUsername) {
        this.username = pUsername;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String pEmail) {
        this.email = pEmail;
    }

    public String getCedula() {
        return this.cedula;
    }

    public void setCedula(String pCedula) {
        this.cedula = pCedula;
    }
}
