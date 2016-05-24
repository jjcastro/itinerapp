package com.ingesoft.itinerapp.converter;


import com.ingesoft.itinerapp.dtos.UsuarioDTO;
import com.ingesoft.itinerapp.entities.UsuarioEntity;
import java.util.ArrayList;
import java.util.List;

public abstract class UsuarioConverter {

    public static UsuarioDTO basicEntity2DTO(UsuarioEntity entity) {
        if (entity != null) {
            UsuarioDTO dto = new UsuarioDTO();
            dto.setId(entity.getId());
            dto.setLugarNacimiento(entity.getLugarNacimiento());
            dto.setNombre(entity.getNombre());
            dto.setCorreo(entity.getCorreo());
            dto.setPassword(entity.getPassword());
            dto.setFechaNacimiento(entity.getFechaNacimiento());
            dto.setFoto(entity.getFoto());
            dto.setAdministrador(entity.getAdministrador());

            return dto;
        } else {
            return null;
        }
    }

    public static UsuarioEntity basicDTO2Entity(UsuarioDTO dto) {
        if (dto != null) {
            UsuarioEntity entity = new UsuarioEntity(dto.getId(),dto.getLugarNacimiento(), dto.getNombre(), dto.getCorreo(), dto.getPassword(), dto.getFechaNacimiento(), dto.getFoto(), dto.getAdministrador());
            return entity;
        } else {
            return null;
        }
    }

    public static List<UsuarioDTO> listEntity2DTO(List<UsuarioEntity> entities) {
        List<UsuarioDTO> dtos = new ArrayList<UsuarioDTO>();
        if (entities != null) {
            for (UsuarioEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    public static List<UsuarioEntity> listDTO2Entity(List<UsuarioDTO> dtos) {
        List<UsuarioEntity> entities = new ArrayList<UsuarioEntity>();
        if (dtos != null) {
            for (UsuarioDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
