package com.ingesoft.itinerapp.ejbs;

import com.ingesoft.itinerapp.api.ICiudadLogic;
import com.ingesoft.itinerapp.converter.CiudadConverter;
import com.ingesoft.itinerapp.dtos.CiudadDTO;
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

     public List<CiudadDTO> getCiudades() {
        return CiudadConverter.listEntity2DTO(persistence.findAll());
    }

    public CiudadDTO getCiudad(Long id) {
        return CiudadConverter.basicEntity2DTO(persistence.find(id));
    }

    public CiudadDTO createCiudad(CiudadDTO dto) {
        CiudadEntity entity = CiudadConverter.basicDTO2Entity(dto);
        persistence.create(entity);
        return CiudadConverter.basicEntity2DTO(entity);
    }

    public CiudadDTO updateCiudad(CiudadDTO dto) {
        CiudadEntity entity = persistence.update(CiudadConverter.basicDTO2Entity(dto));
        return CiudadConverter.basicEntity2DTO(entity);
    }

    public void deleteCiudad(Long id) {
        persistence.delete(id);
    }
}
