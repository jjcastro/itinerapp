package com.ingesoft.itinerapp.entities;

import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;

/**
 * @generated
 */
@Entity
public class RecuerdoEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "Recuerdo")
    private Long id;
    
    private String descripcion;
    private String imagen;


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
