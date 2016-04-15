/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.entities;

import co.edu.uniandes.csw.crud.spi.entity.BaseEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author johnycsc
 */
@Entity
public class ItinerarioEntity extends BaseEntity implements Serializable{

    private String imagen;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaEntrada;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaSalida;
    private String nombre;
    private int id;
    
    
    @ManyToOne
    private UsuarioEntity usuario;
    
    @ManyToMany
    private List<CiudadEntity> ciudades = new ArrayList<>();
    
    /**
     * retorna el nombre del itinerario
     * @return 
     */
     
    public String getNombre(){
        return nombre;
    }
    
    
    /**
     * Insertar el nombre
     * @param nombre
     */
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    /**
     * retorna la imagen del itinerario
     * @return 
     */
    
    public String getImagen(){
        return imagen;
    }
    

    /**
     * Insertar la imagen
     * @param imagen
     */
    public void setImagen(String imagen){
        this.imagen = imagen;
    }
    
    /**
     * Retorna la fecha de salida del itinerario
     * @return 
     */
    
    public Date getFechaSalida(){
        return fechaSalida;
    }
    
    /**
     * Inserta la fecha de salida 
     * @param fechaSalida
     */
    
    public void setFechaSalida(Date fechaSalida){
        this.fechaSalida = fechaSalida;
    }
    
    
    
    /**
     * Retorna la fecha de entrada del itinerario
     * @return 
     */
    
    public Date getFechaEntrada(){
        return fechaEntrada;
    }
    
    /**
     * Inserta la fecha de entrada
     * @param fechaEntrada
     */
    
    public void setFechaEntrada(Date fechaEntrada){
        this.fechaEntrada = fechaEntrada;
    }
    
    /**
     * Retorna el id del Itinerario
     * @return 
     */

    
    
    
    
    
    
    
    
    
    
    
}

