package com.ingesoft.itinerapp.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

/**
 * @generated
 */
@Entity
public class UsuarioLoginEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "Usuario")
    private Long id;
    private String username;
    private String pass;

    public UsuarioLoginEntity() {}

    public UsuarioLoginEntity(Long pId, String pUsername, String pPass) {
        id = pId;
        username = pUsername;
        pass = pPass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String pUsername) {
        this.username = pUsername;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pPass) {
        this.pass = pPass;
    }
}
