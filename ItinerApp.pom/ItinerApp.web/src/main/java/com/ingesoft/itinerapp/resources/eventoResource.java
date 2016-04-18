/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.resources;

import com.ingesoft.itinerapp.dtos.eventoDTO;
import com.ingesoft.itinerapp.exceptions.eventoLogicException;
import com.ingesoft.itinerapp.mocks.eventoLogicMock;

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
            eventoLogicMock eventoLogic;

    @GET
    public List<eventoDTO> getEvento() throws eventoLogicException {
        return eventoLogic.getEvento();
    }

    @GET
    @Path("{id: \\d+}")
    public eventoDTO getEvento(@PathParam("id") Long id) throws eventoLogicException {
        return eventoLogic.getEvento(id);
    }

    @POST
    public eventoDTO createEvento(eventoDTO evento) throws eventoLogicException {
        return eventoLogic.createEvento(evento);
    }

    @PUT
    @Path("{id: \\d+}")
    public eventoDTO updateEvento(@PathParam("id") Long id, eventoDTO evento) throws eventoLogicException {
        return eventoLogic.updateEvento(id, evento);
    }

    @DELETE
    @Path("{id: \\d+}")
    public void deleteEvento(@PathParam("id") Long id) throws eventoLogicException {
    	eventoLogic.deleteEvento(id);
    }
}
