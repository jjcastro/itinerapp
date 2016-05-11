package com.ingesoft.itinerapp.converter;

import com.ingesoft.itinerapp.dtos.UsuarioLoginDTO;
import com.ingesoft.itinerapp.entities.UsuarioLoginEntity;

public abstract class UsuarioLoginConverter {

    public static UsuarioLoginDTO basicEntity2DTO(UsuarioLoginEntity entity) {
        if (entity != null) {
            UsuarioLoginDTO dto = new UsuarioLoginDTO();
            dto.setUsername(entity.getUsername());
            dto.setPass(entity.getPass());

            return dto;
        } else {
            return null;
        }
    }

    public static UsuarioLoginEntity basicDTO2Entity(UsuarioLoginDTO dto) {
        if (dto != null) {
            UsuarioLoginEntity entity = new UsuarioLoginEntity(dto.getUsername(), dto.getPass());
            return entity;
        } else {
            return null;
        }
    }
}
