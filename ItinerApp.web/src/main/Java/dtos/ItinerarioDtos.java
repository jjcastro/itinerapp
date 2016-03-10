/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;
/**
 *
 * @author johnycsc
 */

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class ItinerarioDtos {
    /**
     * Atributos de la clase Itinerario
     */
    
    private Long id;
    private String imagen;
    private String nombre;
    private Date dateOut;
    private Date dateIn;
    
    /**
     * Metodos GET y SET de la clase Itinerario de todos sus atributos
     */
    
    public ItinerarioDtos(Long pId, String pNombre, String pImagen, Date pDateIn, Date pDateOut){
        id= pId;
        nombre = pNombre;
        imagen = pImagen;
        dateOut = pDateOut;
        dateIn = pDateIn;
        
    }
    
     public ItinerarioDtos(Long pId, String pNombre, Date pDateIn, Date pDateOut){
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
    
    public Date getDateOut(){
        return dateOut;
    }
    
    public void setDateOut(Date pDateOut){
        this.dateOut = pDateOut;
    }
    
    public Date getDateIn(){
        return dateIn;
    }
    
    public void setDateIn(Date pDateIn){
        this.dateIn = pDateIn;
    }
    
}
