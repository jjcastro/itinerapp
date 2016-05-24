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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class EventoLogic implements IEventoLogic
{
    @Inject private EventoPersistence persistence;

    public EventoEntity getEvento(Long id) {
        //Faltan Validaciones.
        EventoEntity evento = persistence.find(id);
        if (evento == null) {
            throw new IllegalArgumentException("El evento solicitado no existe");
        }
        return evento;
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
