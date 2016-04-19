package com.ingesoft.itinerapp.ejbs;

import com.ingesoft.itinerapp.api.ICiudadLogic;
import com.ingesoft.itinerapp.entities.CiudadEntity;
import com.ingesoft.itinerapp.persistence.CiudadPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author jjcastro
 */
@Stateless
public class CiudadLogic implements ICiudadLogic {

    @Inject private CiudadPersistence persistence;

     public List<CiudadEntity> getCiudades() {
         List<CiudadEntity> ciudades = persistence.findAll();
        return ciudades;
    }

    public CiudadEntity getCiudad(Long id) {
        
        //faltan validaciones.
        return persistence.find(id);
    }

    public CiudadEntity createCiudad(CiudadEntity entity) {
        //faltan validaciones.
        
        persistence.create(entity);
        return entity;
    }

    public CiudadEntity updateCiudad(CiudadEntity pEntity) {
        //faltan validaciones.
        CiudadEntity entity = persistence.update(pEntity);
        
        return entity;
    }

    public void deleteCiudad(Long id) {
        //faltan validaciones.
        persistence.delete(id);
    }
}
