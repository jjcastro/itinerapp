/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.converter;

import com.ingesoft.itinerapp.dtos.CiudadDTO;
import com.ingesoft.itinerapp.entities.CiudadEntity;
import java.util.ArrayList;
import java.util.List;

public class CiudadConverter
{
    public static CiudadDTO basicEntity2DTO(CiudadEntity entity) {
        if (entity != null) {
            CiudadDTO dto = new CiudadDTO();
            dto.setId(entity.getId());
            dto.setNombre(entity.getNombre());
            dto.setPais(entity.getPais());
            dto.setDescripcion(entity.getDescripcion());
            dto.setSubtext(entity.getSubtext());
            dto.setFotoBig(entity.getFotoBig());
            dto.setFotoSmall(entity.getFotoSmall());
            dto.setFechaI(entity.getFechaI());
            dto.setFechaF(entity.getFechaF());
            return dto;
        } else {
            return null;
        }
    }

    public static CiudadEntity basicDTO2Entity(CiudadDTO dto) {
        if (dto != null) {
            CiudadEntity entity = new CiudadEntity();
            entity.setId(dto.getId());
            entity.setNombre(dto.getNombre());
            entity.setPais(dto.getPais());
            entity.setDescripcion(dto.getDescripcion());
            entity.setSubtext(dto.getSubtext());
            entity.setFotoBig(dto.getFotoBig());
            entity.setFotoSmall(dto.getFotoSmall());
            entity.setFechaI(dto.getFechaI());
            entity.setFechaF(dto.getFechaF());
            return entity;
        } else {
            return null;
        }
    }

    public static List<CiudadDTO> listEntity2DTO(List<CiudadEntity> entities) {
        List<CiudadDTO> dtos = new ArrayList<CiudadDTO>();
        if (entities != null) {
            for (CiudadEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    public static List<CiudadEntity> listDTO2Entity(List<CiudadDTO> dtos) {
        List<CiudadEntity> entities = new ArrayList<CiudadEntity>();
        if (dtos != null) {
            for (CiudadDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
