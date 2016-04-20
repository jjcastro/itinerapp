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

    private static final Logger logger = Logger.getLogger(RecuerdoLogic.class.getName());

    @Inject private EventoPersistence persistence;

    public EventoEntity getEvento(Long id) {
        //Faltan Validaciones.
        logger.log(Level.INFO, "Inicia proceso de consultar el evento con el id dado", id);
        EventoEntity recuerdo = persistence.find(id);
        if (recuerdo == null) {
            logger.log(Level.SEVERE, "El evento con id: " +id+ " no existe", id);
            throw new IllegalArgumentException("El evento solicitado no existe");
        }
        logger.log(Level.INFO, "Termina proceso de consultar evento con id "+id, id);
        return recuerdo;
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
