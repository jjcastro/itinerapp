/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.resources;

import com.ingesoft.itinerapp.api.IEventoLogic;
import com.ingesoft.itinerapp.converter.EventoConverter;
import com.ingesoft.itinerapp.dtos.EventoDTO;
import com.ingesoft.itinerapp.entities.EventoEntity;
import com.ingesoft.itinerapp.exceptions.eventoLogicException;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;


import javax.enterprise.context.RequestScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;


/**
 *
 * @author s.robayo222
 */
@Path("eventos")
@Produces("application/json")
@RequestScoped
public class eventoResource
{
    @Inject
            IEventoLogic eventoLogic;

    @GET
    public List<EventoDTO> getEvento() throws eventoLogicException
    {
        List<EventoEntity> eventos = eventoLogic.getEventos();
        return EventoConverter.listEntity2DTO(eventos);
    }

    @GET
    @Path("{id: \\d+}")
    public EventoDTO getEvento(@PathParam("id") Long id) throws eventoLogicException
    {
        EventoEntity evento = eventoLogic.getEvento(id);
        return EventoConverter.basicEntity2DTO(evento);
    }

    @POST
    public EventoDTO createEvento(EventoDTO evento) throws eventoLogicException
    {
        EventoEntity entity = EventoConverter.basicDTO2Entity(evento);
        EventoEntity newEntity;
        try {
            newEntity = eventoLogic.createEvento(entity);
        } catch (Exception ex) {
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }
        return EventoConverter.basicEntity2DTO(newEntity);
    }

    @PUT
    @Path("{id: \\d+}")
    public EventoDTO updateEvento(@PathParam("id") Long id, EventoDTO evento) throws eventoLogicException
    {
        EventoEntity entity = EventoConverter.basicDTO2Entity(evento);
        entity.setId(id);
        EventoEntity oldEntity = eventoLogic.getEvento(id);
        try {
            EventoEntity savedRecuerdo = eventoLogic.updateEvento(entity);
            return EventoConverter.basicEntity2DTO(savedRecuerdo);
        } catch (Exception ex) {
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteEvento(@PathParam("id") Long id) throws eventoLogicException {
    	eventoLogic.deleteEvento(id);
    }
}
