/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.dtos;

import java.util.Date;

/**
 *
 * @author johnycsc
 */
public class ItinerarioDTO {
    
    private Long id;
    private Date fechaSalida;
    private Date fechaEntrada;
    private String nombre;
    private String imagen;
    
    
    public ItinerarioDTO(Long id, Date fechaSalida, Date fechaEntrada, String nombre, String imagen){
        this.id = id;
        this.fechaSalida = fechaSalida;
        this.fechaEntrada = fechaEntrada;
        this.nombre = nombre;
        this.imagen = imagen;
        
    }

    public ItinerarioDTO() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
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
