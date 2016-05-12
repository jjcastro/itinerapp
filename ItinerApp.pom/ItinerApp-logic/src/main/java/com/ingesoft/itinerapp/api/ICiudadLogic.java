package com.ingesoft.itinerapp.api;


import com.ingesoft.itinerapp.entities.CiudadEntity;
import com.ingesoft.itinerapp.entities.EventoEntity;
import java.util.List;

public interface ICiudadLogic {
    public List<CiudadEntity> getCiudades();
    public CiudadEntity getCiudad(Long id);
    public CiudadEntity createCiudad(CiudadEntity dto);
    public CiudadEntity updateCiudad(CiudadEntity dto);
    public void deleteCiudad(Long id);
    public List<EventoEntity> getEventos(Long ciudadId);
}
