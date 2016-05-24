/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.converter;

/**
 *
 * @author s.robayo222
 */
import com.ingesoft.itinerapp.dtos.EventoDTO;
import com.ingesoft.itinerapp.entities.EventoEntity;
import java.util.ArrayList;
import java.util.List;

public class EventoConverter
{
    public static EventoDTO basicEntity2DTO(EventoEntity entity) {
        if (entity != null) {
            EventoDTO dto = new EventoDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());

            return dto;
        } else {
            return null;
        }
    }

    public static EventoEntity basicDTO2Entity(EventoDTO dto) {
        if (dto != null) {
            EventoEntity entity = new EventoEntity();
            entity.setId(dto.getId());
            entity.setName(dto.getName());

            return entity;
        } else {
            return null;
        }
    }

    public static List<EventoDTO> listEntity2DTO(List<EventoEntity> entities) {
        List<EventoDTO> dtos = new ArrayList<EventoDTO>();
        if (entities != null) {
            for (EventoEntity entity : entities) {
                dtos.add(basicEntity2DTO(entity));
            }
        }
        return dtos;
    }

    public static List<EventoEntity> listDTO2Entity(List<EventoDTO> dtos) {
        List<EventoEntity> entities = new ArrayList<EventoEntity>();
        if (dtos != null) {
            for (EventoDTO dto : dtos) {
                entities.add(basicDTO2Entity(dto));
            }
        }
        return entities;
    }
}
