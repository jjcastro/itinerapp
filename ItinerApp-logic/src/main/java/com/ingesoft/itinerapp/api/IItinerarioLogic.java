/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.api;

import com.ingesoft.itinerapp.entities.ItinerarioEntity;
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
    
    public void deleteItinerario(long id);
 
    
    
}
