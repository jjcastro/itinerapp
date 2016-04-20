package com.ingesoft.itinerapp.resources;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author johnycsc
 */

import com.ingesoft.itinerapp.dtos.ItinerarioDtosBORRAR;
import com.ingesoft.itinerapp.exceptions.ItinerarioException;
import com.ingesoft.itinerapp.mocks.ItinerarioLogicMock;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;


@Path("itinerario")
@Produces("application/json")
@RequestScoped
public class ItinerarioResource {
    
    @Inject
    ItinerarioLogicMock itinerarioLogic;
 
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public List<ItinerarioDtosBORRAR> getItinerarios() throws ItinerarioException {
       // System.out.println("Esta pidiendo itinerarios");
       List<ItinerarioDtosBORRAR> M = new ArrayList<>();
        ItinerarioDtosBORRAR n = new ItinerarioDtosBORRAR(4L, "juan", "123", "123");
        M.add(n);
        return M;
        //return itinerarioLogic.getItinerarios();
    }
    
    
    @GET
    @Path("{id: \\d+}")
   public ItinerarioDtosBORRAR getItinerario(@PathParam("id") Long id) throws ItinerarioException {
        return itinerarioLogic.getItinerario(id);
    }
    
     @POST
    public ItinerarioDtosBORRAR createItinerario(ItinerarioDtosBORRAR itinerario) throws ItinerarioException{
        return itinerarioLogic.createItinerario(itinerario);
    }
    
     @PUT
    @Path("{id: \\d+}")
    public ItinerarioDtosBORRAR updateItinerario(@PathParam("id") Long id, ItinerarioDtosBORRAR itinerario) throws ItinerarioException {
        return itinerarioLogic.updateItinerario(id, itinerario);
    }
    
      @DELETE
    @Path("{id: \\d+}")
    public void deleteItinerario(@PathParam("id") Long id) throws ItinerarioException {
    	itinerarioLogic.deleteItinerario(id);
    }
     
    
    
}
