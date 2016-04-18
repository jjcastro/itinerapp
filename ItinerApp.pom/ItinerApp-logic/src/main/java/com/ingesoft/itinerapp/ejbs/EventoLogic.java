/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.ejbs;

/**
 *
 * @author s.robayo222
 */
import com.ingesoft.itinerapp.api.IEventoLogic;
import com.ingesoft.itinerapp.converter.EventoConverter;
import com.ingesoft.itinerapp.dtos.EventoDTO;
import com.ingesoft.itinerapp.entities.EventoEntity;
import com.ingesoft.itinerapp.persistence.EventoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EventoLogic implements IEventoLogic
{

    @Inject private EventoPersistence persistence;

    public List<EventoDTO> getEvento() {
        return EventoConverter.listEntity2DTO(persistence.findAll());
    }

    public EventoDTO getEvento(Long id) {
        return EventoConverter.basicEntity2DTO(persistence.find(id));
    }

    public EventoDTO createEvento(EventoDTO dto) {
        EventoEntity entity = EventoConverter.basicDTO2Entity(dto);
        persistence.create(entity);
        return EventoConverter.basicEntity2DTO(entity);
    }

    public EventoDTO updateEvento(EventoDTO dto) {
        EventoEntity entity = persistence.update(EventoConverter.basicDTO2Entity(dto));
        return EventoConverter.basicEntity2DTO(entity);
    }

    public void deleteEvento(Long id) {
        persistence.delete(id);
    }
}
