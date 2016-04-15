/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.api;

import com.ingesoft.itinerapp.entities.CiudadEntity;
import com.ingesoft.itinerapp.entities.ItinerarioEntity;
import com.ingesoft.itinerapp.exceptions.BusinessLogicException;
import java.util.List;

/**
 *
 * @author johnycsc
 */
public interface IItinerarioLogic {
    
    public List<ItinerarioEntity> getItinerarios();
    
    public ItinerarioEntity getItinerario(Long id);

    public ItinerarioEntity createItinerario(ItinerarioEntity entity);
    
    public ItinerarioEntity updateItinerario(ItinerarioEntity entity);
    
    public void deleteItinerario(Long id);
    
    public List<CiudadEntity> getCiudades(Long itinerarioId);
    
    public CiudadEntity getCiudad(Long idCiudad, Long idItinerario);
    
    public CiudadEntity agregarCiudad(Long idCiudad, Long idItinerario) throws BusinessLogicException;
    
    public void borrarCiudad(Long idCiudad, Long idItinerario);
    
    
    
    
    
    
 
    
}
