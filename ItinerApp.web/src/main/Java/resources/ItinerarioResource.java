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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import dtos.ItinerarioDtos;
import exceptions.ItinerarioException;
import javax.inject.Inject;
import mocks.ItinerarioLogicMock;

@Path("/itinerario")
@Produces("application/json")
public class ItinerarioResource {
    
     @Inject
	ItinerarioLogicMock itinerarioLogic;
 
      @GET
    public List<ItinerarioDtos> getItinerarios() throws ItinerarioException {
          System.out.println("Esta pidiendo itinerarios");
        return itinerarioLogic.getItinerarios();
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
