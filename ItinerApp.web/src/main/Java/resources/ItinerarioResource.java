package resources;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author johnycsc
 */

import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import dtos.ItinerarioDtos;
import exceptions.ItinerarioException;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import mocks.ItinerarioLogicMock;

@Path("itinerario")
@Produces("application/json")
@RequestScoped
public class ItinerarioResource {
    
     @Inject
	ItinerarioLogicMock itinerarioLogic;
 
      @GET
      @Produces(MediaType.TEXT_PLAIN)
    public List<ItinerarioDtos> getItinerarios() throws ItinerarioException {
       // System.out.println("Esta pidiendo itinerarios");
       List<ItinerarioDtos> M = new ArrayList<>();
        ItinerarioDtos n = new ItinerarioDtos(4L, "juan", "123", "123");
        M.add(n);
        return M;
        //return itinerarioLogic.getItinerarios();
    }
    
    
    @GET
    @Path("{id: \\d+}")
   public ItinerarioDtos getItinerario(@PathParam("id") Long id) throws ItinerarioException {
        return itinerarioLogic.getItinerario(id);
    }
    
     @POST
    public ItinerarioDtos createItinerario(ItinerarioDtos itinerario) throws ItinerarioException{
        return itinerarioLogic.createItinerario(itinerario);
    }
    
     @PUT
    @Path("{id: \\d+}")
    public ItinerarioDtos updateItinerario(@PathParam("id") Long id, ItinerarioDtos itinerario) throws ItinerarioException {
        return itinerarioLogic.updateItinerario(id, itinerario);
    }
    
      @DELETE
    @Path("{id: \\d+}")
    public void deleteItinerario(@PathParam("id") Long id) throws ItinerarioException {
    	itinerarioLogic.deleteItinerario(id);
    }
     
    
    
}
