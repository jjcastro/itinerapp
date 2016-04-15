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
    private CiudadPersistence authorPersistence;

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
    public ItinerarioEntity createItinerario(ItinerarioEntity entity){
        logger.info("Inicia proceso de creación de itinerario");
        if (!validateDates(entity.getFechaSalida(), entity.getFechaEntrada())) {
          //  throw new BusinessLogicException("Las fechas no son válidas");
        }
        persistence.create(entity);
        logger.info("Termina proceso de creación de itinerario");
        return entity;
    }

    @Override
    public ItinerarioEntity updateItinerario(ItinerarioEntity entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteItinerario(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<CiudadEntity> getCiudades(Long itinerarioId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CiudadEntity getCiudad(Long idCiudad, Long idItinerario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CiudadEntity agregarCiudad(Long idCiudad, Long idItinerario) throws BusinessLogicException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void borrarCiudad(Long idCiudad, Long idItinerario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    private boolean validateDates(Date fechaSalida, Date fechaLlegada) {
        if (fechaSalida == null || fechaLlegada.before(fechaSalida) || fechaLlegada == null) {
            return false;
        }
        return true;
    }
    
}
