/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.dtos;

/**
 *
 * @author s.robayo222
 */
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EventoDTO {

    private Long id;
    private String descripcion;
    private String name;
    private String ciudad;

    public EventoDTO()
    {

    }

    public EventoDTO(Long id, String descripcion, String name, String ciudad)
    {
        this.id = id;
        this.descripcion = descripcion;
        this.name = name;
        this.ciudad = ciudad;
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
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @generated
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

}
