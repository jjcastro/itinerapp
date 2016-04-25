/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.converter;

import com.ingesoft.itinerapp.dtos.ItinerarioDTO;
import com.ingesoft.itinerapp.entities.ItinerarioEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author johnycsc
 */
public abstract class ItinerarioConverter {
     public static ItinerarioDTO basicEntity2DTO(ItinerarioEntity entity) {
        if (entity != null) {
            ItinerarioDTO dto = new ItinerarioDTO();
            dto.setImagen(entity.getImagen());
            dto.setFechaEntrada(entity.getFechaEntrada());
            dto.setFechaSalida(entity.getFechaSalida());
            dto.setImagen(entity.getImagen());
            

            return dto;
        } else {
            return null;
        }
    }

    public static ItinerarioEntity basicDTO2Entity(ItinerarioDTO dto) {
        if (dto != null) {
            ItinerarioEntity entity = new ItinerarioEntity();
            entity.setImagen(dto.getImagen());
            entity.setFechaEntrada(dto.getFechaEntrada());
            entity.setFechaSalida(dto.getFechaSalida());
            return entity;
        } else {
            return null;
        }
    }

    public static List<ItinerarioDTO> listEntity2DTO(List<ItinerarioEntity> entities) {
        List<ItinerarioDTO> dtos = new ArrayList<ItinerarioDTO>();
        if (entities != null) {
            for (ItinerarioEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    public static List<ItinerarioEntity> listDTO2Entity(List<ItinerarioDTO> dtos) {
        List<ItinerarioEntity> entities = new ArrayList<ItinerarioEntity>();
        if (dtos != null) {
            for (ItinerarioDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
    
}
