package com.ingesoft.itinerapp.ejbs;

import com.ingesoft.itinerapp.api.IRecuerdoLogic;
import com.ingesoft.itinerapp.converter.RecuerdoConverter;
import com.ingesoft.itinerapp.dtos.RecuerdoDTO;
import com.ingesoft.itinerapp.entities.RecuerdoEntity;
import com.ingesoft.itinerapp.persistence.RecuerdoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class RecuerdoLogic implements IRecuerdoLogic {

    @Inject private RecuerdoPersistence persistence;

    public List<RecuerdoDTO> getRecuerdos() {
        return RecuerdoConverter.listEntity2DTO(persistence.findAll());
    }

    public RecuerdoDTO getRecuerdo(Long id) {
        return RecuerdoConverter.basicEntity2DTO(persistence.find(id));
    }

    public RecuerdoDTO createRecuerdo(RecuerdoDTO dto) {
        RecuerdoEntity entity = RecuerdoConverter.basicDTO2Entity(dto);
        persistence.create(entity);
        return RecuerdoConverter.basicEntity2DTO(entity);
    }

    public RecuerdoDTO updateRecuerdo(RecuerdoDTO dto) {
        RecuerdoEntity entity = persistence.update(RecuerdoConverter.basicDTO2Entity(dto));
        return RecuerdoConverter.basicEntity2DTO(entity);
    }

    public void deleteRecuerdo(Long id) {
        persistence.delete(id);
    }
}
