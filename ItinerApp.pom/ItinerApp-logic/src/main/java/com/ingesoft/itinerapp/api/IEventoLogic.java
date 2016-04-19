/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.api;

/**
 *
 * @author s.robayo222
 */

import com.ingesoft.itinerapp.entities.EventoEntity;
import java.util.List;

public interface IEventoLogic
{
    public List<EventoEntity> getEvento();
    public EventoEntity getEvento(Long id);
    public EventoEntity createEvento(EventoEntity dto);
    public EventoEntity updateEvento(EventoEntity dto);
    public void deleteEvento(Long id);
}
