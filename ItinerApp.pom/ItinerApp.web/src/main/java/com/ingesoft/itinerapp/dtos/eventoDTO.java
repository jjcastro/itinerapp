/*
 * eventoDTO
 * Objeto de transferencia de datos de Eventos.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package com.ingesoft.itinerapp.dtos;

/**
 * Objeto de transferencia de datos de Ciudades.
 * @author Asistente
 */
public class eventoDTO {
    private Long id;
    private String nombre;
    private String ciudad;
    private String descripcion;

    /**
     * Constructor por defecto
     */
    public eventoDTO()
    {
    }

    public eventoDTO(Long id, String name, String city, String des) {
		super();
		this.id = id;
		this.nombre = name;
                this.ciudad = city;
                this.descripcion = des;
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

    public void setNombre(String name) {
        this.nombre = name;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String city) {
        this.ciudad = city;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String des) {
        this.descripcion = des;
    }

    @Override
    public String toString() {
    	return "{ id : " + getId() + ", nombre : \"" + getNombre() + ", ciudad : \""+getCiudad()+ ", descripcion : \""+getDescripcion()+"\"}" ;
    }
}