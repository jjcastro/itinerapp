/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.dtos;

import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@XmlRootElement 
public class RecuerdoDTO {

    private Long id;
    private String descripcion;
    private String name;
    private String imagen;
    
    @PodamExclude
    private UsuarioDTO usuario;

    public RecuerdoDTO() {
    }

    public RecuerdoDTO(Long id,String name, String descripcion, String imagen,Long idUsuario) {
        this.id = id;
        this.descripcion = descripcion;
        this.name = name;
        this.imagen = imagen;
        
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
    
    /**
     * @generated
     */
    public Long getId() {
        return id;
    }

    /**
     * @generated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @generated
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @generated
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @generated
     */
    public String getName() {
        return name;
    }

    /**
     * @generated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @generated
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @generated
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}