package com.ingesoft.itinerapp.api;


import com.ingesoft.itinerapp.dtos.CiudadDTO;
import java.util.List;

public interface ICiudadLogic {
    public List<CiudadDTO> getCiudads();
    public CiudadDTO getCiudad(Long id);
    public CiudadDTO createCiudad(CiudadDTO dto);
    public CiudadDTO updateCiudad(CiudadDTO dto);
    public void deleteCiudad(Long id);
}
