/*
 * eventoDTO
 * Objeto de transferencia de datos de Eventos.
 * Los DTO especifican los mensajes que se envían entre el cliente y el servidor.
 */
package dtos;

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

    /**
     * Constructor con parámetros.
     * @param id identificador de la ciudad
     * @param name nombre dedel evento
     */
    public eventoDTO(Long id, String name, String city, String des) {
		super();
		this.id = id;
		this.nombre = name;
                this.ciudad = city;
                this.descripcion = des;
	}

	/**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param name the name to set
     */
    public void setNombre(String name) {
        this.nombre = name;
    }

    /**
     * @return the city
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param city the city to set
     */
    public void setCiudad(String city) {
        this.ciudad = city;
    }

    /**
     * @return the description
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param des the description to set
     */
    public void setDescripcion(String des) {
        this.descripcion = des;
    }

    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{ id : " + getId() + ", nombre : \"" + getNombre() + ", ciudad : \""+getCiudad()+ ", descripcion : \""+getDescripcion()+"\"}" ;
    }
}