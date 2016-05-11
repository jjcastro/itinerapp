package com.ingesoft.itinerapp.dtos;

/**
 * @author jc.martha10
 */

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsuarioLoginDTO {

    /**
     * Atributos de la clase UsuarioLogin
     */

    private String username;
    private String pass;

    /**
     * Metodos GET y SET de la clase UsuarioLogin de todos sus atributos
     */
    public UsuarioLoginDTO() {}

    public UsuarioLoginDTO(String pUsername, String pPass) {
        username = pUsername;
        pass = pPass;
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