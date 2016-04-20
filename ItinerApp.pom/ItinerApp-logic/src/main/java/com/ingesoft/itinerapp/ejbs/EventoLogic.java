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
import com.ingesoft.itinerapp.entities.EventoEntity;
import com.ingesoft.itinerapp.persistence.EventoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EventoLogic implements IEventoLogic
{

    @Inject private EventoPersistence persistence;

    public List<EventoEntity> getEvento() {
       //Faltan Validaciones.
        List<EventoEntity> eventos = persistence.findAll();
        return eventos;
    }

    public EventoEntity getEvento(Long id) {
        //Faltan Validaciones.
        return persistence.find(id);
    }

    public EventoEntity createEvento(EventoEntity entity) {
        //Faltan Validaciones.
        persistence.create(entity);
        return entity;
    }

    public EventoEntity updateEvento(EventoEntity pEntity) {
        //Faltan Validaciones.
        EventoEntity entity = persistence.update(pEntity);
        return entity;
    }

    public void deleteEvento(Long id) {
        //Faltan Validaciones.
        persistence.delete(id);
    }

    public List<EventoEntity> getEventos()
    {
       List<EventoEntity> eventos = persistence.findAll();
       return eventos;
    }
}
