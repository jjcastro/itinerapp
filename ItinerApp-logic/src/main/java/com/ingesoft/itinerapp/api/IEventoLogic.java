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
import com.ingesoft.itinerapp.dtos.EventoDTO;
import java.util.List;

public interface IEventoLogic
{
    public List<EventoDTO> getEvento();
    public EventoDTO getEvento(Long id);
    public EventoDTO createEvento(EventoDTO dto);
    public EventoDTO updateEvento(EventoDTO dto);
    public void deleteEvento(Long id);
}
