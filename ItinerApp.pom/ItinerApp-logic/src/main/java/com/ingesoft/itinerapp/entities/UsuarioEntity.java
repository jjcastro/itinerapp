package com.ingesoft.itinerapp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 * @generated
 */
@Entity
public class UsuarioEntity implements Serializable {

    @Id
    @GeneratedValue(generator = "Usuario")
    private Long id;
    private String lugarNacimiento;
    private String nombre;
    private String correo;
    private String password;
    private String fechaNacimiento;
    private String foto;
    private int administrador;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @PodamExclude
    private List<RecuerdoEntity> recuerdos;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @PodamExclude
    private List<ItinerarioEntity> itinerarios;
    

    /**
     * Metodos GET y SET de la clase Usuario de todos sus atributos
     */
    public UsuarioEntity() {
        recuerdos = new ArrayList<RecuerdoEntity>();
        itinerarios = new ArrayList<ItinerarioEntity>();
    }

    public UsuarioEntity(Long pId, String pLugarNacimiento, String pNombre, String pCorreo, String pPassword, String pFechaNacimiento, String pFoto, int pAdministrador) {
        id = pId;
        lugarNacimiento = pLugarNacimiento;
        nombre = pNombre;
        correo = pCorreo;
        password = pPassword;
        fechaNacimiento = pFechaNacimiento;
        foto = pFoto;
        administrador = pAdministrador;
        
        recuerdos = new ArrayList<RecuerdoEntity>();
        itinerarios = new ArrayList<ItinerarioEntity>();
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

    public void setLugarNacimiento(String pLugarNacimiento) {
        this.lugarNacimiento = pLugarNacimiento;
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

    public List<RecuerdoEntity> getRecuerdos() {
        return recuerdos;
    }

    public void setRecuerdos(List<RecuerdoEntity> recuerdos) {
        this.recuerdos = recuerdos;
    }

    public List<ItinerarioEntity> getItinerarios() {
        return itinerarios;
    }

    public void setItinerarios(List<ItinerarioEntity> itinerarios) {
        this.itinerarios = itinerarios;
    }
}
