/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.dtos;
/**
 *
 * @author johnycsc
 */

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class ItinerarioDtos {
    /**
     * Atributos de la clase Itinerario
     */
    
    private Long id;
    private String imagen;
    private String nombre;
    private String dateOut;
    private String dateIn;
    
    /**
     * Metodos GET y SET de la clase Itinerario de todos sus atributos
     */
    
    public ItinerarioDtos(Long pId, String pNombre, String pImagen, String pDateIn, String pDateOut){
        super();
        id= pId;
        nombre = pNombre;
        imagen = pImagen;
        dateOut = pDateOut;
        dateIn = pDateIn;
        
    }
    
     public ItinerarioDtos(Long pId, String pNombre, String pDateIn, String pDateOut){
            super();
        id= pId;
        nombre = pNombre;
        dateOut = pDateOut;
        dateIn = pDateIn;
        
    }
            
    
    
    
    
    public Long getId(){
        return id;
    }
    
    public void setId(long pId){
        this.id = pId;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String pNombre){
        this.nombre = pNombre;
    }
    
    public String getImagen(){
        return imagen;
    }
    
    public void setImagen(String pImagen){
        this.imagen = pImagen;
    }
    
    public String getDateOut(){
        return dateOut;
    }
    
    public void setDateOut(String pDateOut){
        this.dateOut = pDateOut;
    }
    
    public String getDateIn(){
        return dateIn;
    }
    
    public void setDateIn(String pDateIn){
        this.dateIn = pDateIn;
    }
    
}
