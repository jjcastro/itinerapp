package com.ingesoft.itinerapp.api;


import com.ingesoft.itinerapp.entities.CiudadEntity;
import java.util.List;

public interface ICiudadLogic {
    public List<CiudadEntity> getCiudades();
    public CiudadEntity getCiudad(Long id);
    public CiudadEntity createCiudad(CiudadEntity dto);
    public CiudadEntity updateCiudad(CiudadEntity dto);
    public void deleteCiudad(Long id);
}
