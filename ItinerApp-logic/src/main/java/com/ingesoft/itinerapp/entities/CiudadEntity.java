package com.ingesoft.itinerapp.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 * @generated
 */
@Entity
public class CiudadEntity implements Serializable {

  @Id
  @GeneratedValue(generator = "Ciudad")
  private Long id;
  private String nombre;
  private String pais;
  private String descripcion;
  private String subtext;
  private String fotoBig;
  private String fotoSmall;
  @Temporal(javax.persistence.TemporalType.DATE)
  private Date fechaI;
  @Temporal(javax.persistence.TemporalType.DATE)
  private Date fechaF;

  public CiudadEntity()
  {

  }

  public CiudadEntity( Long id, String nombre, String pais, String descripcion, String subtext, String fotoBig, String fotoSmall, Date fechaI, Date fechaF ){
      this.id = id;
      this.nombre = nombre;
      this.pais = pais;
      this.descripcion = descripcion;
      this.subtext = subtext;
      this.fotoBig = fotoBig;
      this.fotoSmall = fotoSmall;
      this.fechaI = fechaI;
      this.fechaF = fechaF;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

  public void setPais(String pais) {
    this.pais = pais;
  }

  public String getPais() {
    return pais;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setSubtext(String subtext) {
    this.subtext = subtext;
  }

  public String getSubtext() {
    return subtext;
  }

  public void setFotoBig(String fotoBig) {
    this.fotoBig = fotoBig;
  }

  public String getFotoBig() {
    return fotoBig;
  }

  public void setFotoSmall(String fotoSmall) {
    this.fotoSmall = fotoSmall;
  }

  public String getFotoSmall() {
    return fotoSmall;
  }

  public void setFechaI(Date fechaI) {
    this.fechaI = fechaI;
  }

  public Date getFechaI() {
    return fechaI;
  }

  public void setFechaF(Date fechaF) {
    this.fechaF = fechaF;
  }

  public Date getFechaF() {
    return fechaF;
  }


}
