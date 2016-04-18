
package com.ingesoft.itinerapp.dtos;



import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class PerfilDtos {
  
    private Long id;
    private String nombre;
    private String descripcion;
    
    
    public PerfilDtos()
    {
        
    }
    /**
     * Metodos GET y SET de la clase Perfil de todos sus atributos
     */
    
    
    
    public PerfilDtos(Long pId, String pNombre, String pDescripcion ){
        id= pId;
        nombre = pNombre;
        descripcion = pDescripcion;
        
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    
            
    
    

    
}
