/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.ejbs;

import com.ingesoft.itinerapp.api.IItinerarioLogic;
import com.ingesoft.itinerapp.entities.CiudadEntity;
import com.ingesoft.itinerapp.entities.ItinerarioEntity;
import com.ingesoft.itinerapp.exceptions.BusinessLogicException;
import com.ingesoft.itinerapp.persistence.CiudadPersistence;
import com.ingesoft.itinerapp.persistence.ItinerarioPersistence;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author johnycsc
 */
public class ItinerarioLogic implements  IItinerarioLogic{
    
    private static final Logger logger = Logger.getLogger(ItinerarioLogic.class.getName());
    
    @Inject
    private ItinerarioPersistence persistence;

    @Inject
    private CiudadPersistence ciudadPersistence;

    @Override
    public List<ItinerarioEntity> getItinerarios() {
        logger.info("Inicia proceso de consultar todos los itinerarios");
        List<ItinerarioEntity> itinerarios = persistence.findAll();
        logger.info("Termina proceso de consultar todos los itinerarios");
        return itinerarios;
        
    }

    @Override
    public ItinerarioEntity getItinerario(Long id) {
        logger.log(Level.INFO, "Inicia proceso de consultar itinerario con id={0}", id);
        ItinerarioEntity itinerario = persistence.find(id);
        if (itinerario == null) {
            logger.log(Level.SEVERE, "El itinerario con el id {0} no existe", id);
            throw new IllegalArgumentException("El itinerario solicitado no existe");
        }
        logger.log(Level.INFO, "Termina proceso de consultar itinerario con id={0}", id);
        return itinerario;
    }

    @Override
    public ItinerarioEntity createItinerario(ItinerarioEntity entity) throws BusinessLogicException{
        logger.info("Inicia proceso de creación de itinerario");
        if (!validateDates(entity.getFechaSalida(), entity.getFechaEntrada())) {
      
          throw new BusinessLogicException("Las fechas no son válidas");
        }
        persistence.create(entity);
        logger.info("Termina proceso de creación de itinerario");
        return entity;
    }

    @Override
    public ItinerarioEntity updateItinerario(ItinerarioEntity entity) {
       logger.log(Level.INFO, "Inicia proceso de actualizar autor con id={0}", entity.getId());
       ItinerarioEntity newEntity = persistence.update(entity);
       logger.log(Level.INFO, "Termina proceso de actualizar autor con id={0}", entity.getId());
       return newEntity;      
    }

    @Override
    public void deleteItinerario(Long id) {
        logger.log(Level.INFO, "Inicia proceso de borrar autor con id={0}", id);
        persistence.delete(id);
        logger.log(Level.INFO, "Termina proceso de borrar autor con id={0}", id);
    }

    @Override
    public List<CiudadEntity> getCiudades(Long itinerarioId) {
        return getItinerario(itinerarioId).getCiudades();
    }

    @Override
    public CiudadEntity getCiudad(Long idCiudad, Long idItinerario) {
        List<CiudadEntity> authors = getItinerario(idItinerario).getCiudades();
        CiudadEntity ciudadEntity = ciudadPersistence.find(idCiudad);
        if (ciudadEntity == null) {
            throw new IllegalArgumentException("La ciudad no existe");
        }
        int index = authors.indexOf(ciudadEntity);
        if (index >= 0) {
            return authors.get(index);
        }
        throw new IllegalArgumentException("La ciudad no está asociada al itinerario");
    }

    @Override
    public CiudadEntity agregarCiudad(Long idCiudad, Long idItinerario) throws BusinessLogicException {
        ItinerarioEntity itinerarioEntity = getItinerario(idItinerario);
        CiudadEntity ciudadEntity = ciudadPersistence.find(idCiudad);
        if (ciudadEntity == null) {
            throw new IllegalArgumentException("La ciudad no existe");
        }
        if (!validateDates(ciudadEntity.getFechaF(), itinerarioEntity.getFechaSalida())) {
            throw new BusinessLogicException("La fecha de publicación no puede ser anterior a la fecha de nacimiento del autor");
        }
        itinerarioEntity.getCiudades().add(ciudadEntity);
        return ciudadEntity;
    }

    @Override
    public void borrarCiudad(Long idCiudad, Long idItinerario) {
        ItinerarioEntity bookEntity = getItinerario(idItinerario);
        CiudadEntity authorEntity = ciudadPersistence.find(idCiudad);
        if (authorEntity == null) {
            throw new IllegalArgumentException("El autor no existe");
        }
        bookEntity.getCiudades().remove(authorEntity);
        
    }
    
    
    
    
    
    
    
    private boolean validateDates(Date fechaSalida, Date fechaLlegada) {
        if (fechaSalida == null || fechaLlegada.before(fechaSalida) || fechaLlegada == null) {
            return false;
        }
        return true;
    }
    
}
