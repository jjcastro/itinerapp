/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.entities;

/**
 *
 * @author s.robayo222
 */
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

@Entity
public class EventoEntity implements Serializable
{
    @Id
    @GeneratedValue(generator = "Evento")
    private Long id;
    private String descripcion;
    @ManyToOne
    @PodamExclude
    private CiudadEntity ciudad;
    private String name;


    /**
     * @generated
     */
    public Long getId(){
        return id;
    }

    /**
     * @generated
     */
    public void setId(Long id){
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
    public CiudadEntity getCiudad() {
        return ciudad;
    }

    /**
     * @generated
     */
    public void setCiudad(CiudadEntity ciudad) {
        this.ciudad = ciudad;
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

}
