package com.ingesoft.itinerapp.dtos;

/**
 *
 * @author jc.martha10
 */

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsuarioDTO {

    /**
     * Atributos de la clase Usuario
     */

    private Long id;
    private String nombre;
    private String apellido;
    private String username;
    private String email;
    private String cedula;

    /**
     * Metodos GET y SET de la clase Usuario de todos sus atributos
     */
    public UsuarioDTO() {
    }

    public UsuarioDTO(Long pId, String pNombre, String pApellido, String pUsername, String pEmail, String pCedula) {
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
