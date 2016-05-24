package com.ingesoft.itinerapp.converter;



import com.ingesoft.itinerapp.dtos.RecuerdoDTO;
import com.ingesoft.itinerapp.entities.RecuerdoEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class RecuerdoConverter {

    public static RecuerdoDTO basicEntity2DTO(RecuerdoEntity entity) {
        if (entity != null) {
            RecuerdoDTO dto = new RecuerdoDTO();
            dto.setId(entity.getId());
            dto.setImagen(entity.getImagen());
            dto.setName(entity.getName());
            dto.setDescripcion(entity.getDescripcion());
            dto.setUsuario(UsuarioConverter.basicEntity2DTO(entity.getUsuario()));
            return dto;
        } else {
            return null;
        }
    }

    public static RecuerdoEntity basicDTO2Entity(RecuerdoDTO dto) {
        if (dto != null) {
            RecuerdoEntity entity = new RecuerdoEntity();
            entity.setId(dto.getId());
            entity.setImagen(dto.getImagen());
            entity.setName(dto.getName());
            entity.setDescripcion(dto.getDescripcion());
            entity.setUsuario(UsuarioConverter.basicDTO2Entity(dto.getUsuario()));
            return entity;
        } else {
            return null;
        }
    }

    public static List<RecuerdoDTO> listEntity2DTO(List<RecuerdoEntity> entities) {
        List<RecuerdoDTO> dtos = new ArrayList<RecuerdoDTO>();
        if (entities != null) {
            for (RecuerdoEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    public static List<RecuerdoEntity> listDTO2Entity(List<RecuerdoDTO> dtos) {
        List<RecuerdoEntity> entities = new ArrayList<RecuerdoEntity>();
        if (dtos != null) {
            for (RecuerdoDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
