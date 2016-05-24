package com.ingesoft.itinerapp.dtos;

/**
 * @author jc.martha10
 */

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsuarioDTO {

    /**
     * Atributos de la clase Usuario
     */
    @XmlElement(name="id")
    private Long id;
    
    private String lugarNacimiento;
    private String nombre;
    private String correo;
    private String password;
    private String fechaNacimiento;
    private String foto;
    private int administrador;
    private List<RecuerdoDTO> recuerdos;
    private List<ItinerarioDTO> itinerarios;
    

    /**
     * Metodos GET y SET de la clase Usuario de todos sus atributos
     */
    public UsuarioDTO() {}

    public UsuarioDTO(Long pId, String pLugarNacimiento, String pNombre, String pCorreo, String pPassword, String pFechaNacimiento, String pFoto, int pAdministrador) {
        id = pId;
        lugarNacimiento = pLugarNacimiento;
        nombre = pNombre;
        correo = pCorreo;
        password = pPassword;
        fechaNacimiento = pFechaNacimiento;
        foto = pFoto;
        administrador = pAdministrador;
        
        recuerdos = new ArrayList<RecuerdoDTO>();
        itinerarios = new ArrayList<ItinerarioDTO>();
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(long pId) {
        this.id = pId;
    }

    public String getLugarNacimiento() {
        return this.lugarNacimiento;
    }

    public void setLugarNacimiento(String pCedula) {
        this.lugarNacimiento = pCedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String pCorreo) {
        this.correo = pCorreo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String pFechaNacimiento) {
        this.fechaNacimiento = pFechaNacimiento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getAdministrador() {
        return administrador;
    }

    public void setAdministrador(int pAdministrador) {
        this.administrador = pAdministrador;
    }

    public List<RecuerdoDTO> getRecuerdos() {
        return recuerdos;
    }

    public void setRecuerdos(List<RecuerdoDTO> recuerdos) {
        this.recuerdos = recuerdos;
    }

    public List<ItinerarioDTO> getItinerarios() {
        return itinerarios;
    }

    public void setItinerarios(List<ItinerarioDTO> itinerarios) {
        this.itinerarios = itinerarios;
    }
}