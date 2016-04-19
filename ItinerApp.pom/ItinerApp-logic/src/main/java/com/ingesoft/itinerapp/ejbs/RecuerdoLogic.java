package com.ingesoft.itinerapp.ejbs;

import com.ingesoft.itinerapp.api.IRecuerdoLogic;
import com.ingesoft.itinerapp.entities.RecuerdoEntity;
import com.ingesoft.itinerapp.persistence.RecuerdoPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class RecuerdoLogic implements IRecuerdoLogic {

    private static final Logger logger = Logger.getLogger(RecuerdoLogic.class.getName());
     
    @Inject private RecuerdoPersistence persistence;

    @Override
    public List<RecuerdoEntity> getRecuerdos() {
        logger.info("Inicia proceso de consultar todos los recuerdos");
        List<RecuerdoEntity> recuerdos = persistence.findAll();
        logger.info("Termina proceso de consultar todos los recuerdos");
        return recuerdos;
    }

    @Override
    public RecuerdoEntity getRecuerdo(Long id) {
        logger.log(Level.INFO, "Inicia proceso de consultar el recuerdo con el id dado", id);
        RecuerdoEntity recuerdo = persistence.find(id);
        if (recuerdo == null) {
            logger.log(Level.SEVERE, "El recuerdo con id: " +id+ " no existe", id);
            throw new IllegalArgumentException("El recuerdo solicitado no existe");
        }
        logger.log(Level.INFO, "Termina proceso de consultar recuerdo con id "+id, id);
        return recuerdo;
    }

    @Override
    public RecuerdoEntity createRecuerdo(RecuerdoEntity entity) {
        logger.info("Inicia proceso de creación de recuerdo");
        persistence.create(entity);
        logger.info("Termina proceso de creación de recuerdo");
        return entity;
    }

    @Override
    public RecuerdoEntity updateRecuerdo(RecuerdoEntity pEntity) {
        logger.log(Level.INFO, "Inicia proceso de actualizar recuerdo con id "+pEntity.getId(), pEntity.getId());
        RecuerdoEntity newEntity = persistence.update(pEntity);
        logger.log(Level.INFO, "Termina proceso de actualizar recuerdo con id "+pEntity.getId(), pEntity.getId());
        return newEntity;
    }

    @Override
    public void deleteRecuerdo(Long id) {
        logger.log(Level.INFO, "Inicia proceso de borrar recuerdo con id "+id, id);
        persistence.delete(id);
        logger.log(Level.INFO, "Termina proceso de borrar recuerdo con id "+id, id);
    }
}
