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

import com.ingesoft.itinerapp.api.IItinerarioLogic;
import com.ingesoft.itinerapp.converter.ItinerarioConverter;
import com.ingesoft.itinerapp.dtos.ItinerarioDTO;
import com.ingesoft.itinerapp.dtos.ItinerarioDtosBORRAR;
import com.ingesoft.itinerapp.entities.ItinerarioEntity;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;





@Path("itinerario")
@Produces("application/json")
@Consumes("application/json")   
@RequestScoped
public class ItinerarioResource {
    
    private static final Logger logger = Logger.getLogger(ItinerarioResource.class.getName());

    
    @Inject
    IItinerarioLogic itinerarioLogic;
 
    @GET
    public List<ItinerarioDTO> getItinerarios() throws ItinerarioException {
       logger.info("Se ejecuta método getItinerarios");
       List<ItinerarioEntity> recuerdos = itinerarioLogic.getItinerarios();
       return ItinerarioConverter.listEntity2DTO(recuerdos);
    }
    
    
    @GET
    @Path("{id: \\d+}")
   public ItinerarioDTO getItinerario(@PathParam("id") Long id) throws ItinerarioException {
        logger.log(Level.INFO, "Se ejecuta método getItinerarios con id={0}", id);
        ItinerarioEntity recuerdo = itinerarioLogic.getItinerario(id);
        return ItinerarioConverter.basicEntity2DTO(recuerdo);
    }
    
     @POST
    public ItinerarioDTO createItinerario(ItinerarioDTO itinerario) throws ItinerarioException{
         logger.info("Se ejecuta método createRecuerdo");
        ItinerarioEntity entity = ItinerarioConverter.basicDTO2Entity(itinerario);
        ItinerarioEntity newEntity;
        try {
            newEntity = itinerarioLogic.createItinerario(entity);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }
        return ItinerarioConverter.basicEntity2DTO(newEntity);
    }
    
     @PUT
    @Path("{id: \\d+}")
    public ItinerarioDTO updateItinerario(@PathParam("id") Long id, ItinerarioDTO itinerario) throws ItinerarioException {
           logger.log(Level.INFO, "Se ejecuta método updateRecuerdocon id={0}", id);
        ItinerarioEntity entity = ItinerarioConverter.basicDTO2Entity(itinerario);
        entity.setId(id);
        ItinerarioEntity oldEntity = itinerarioLogic.getItinerario(id);
        try {
            ItinerarioEntity savedRecuerdo = itinerarioLogic.updateItinerario(entity);
            return ItinerarioConverter.basicEntity2DTO(savedRecuerdo);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }
    }
    
      @DELETE
    @Path("{id: \\d+}")
    public void deleteItinerario(@PathParam("id") Long id) throws ItinerarioException {
    	itinerarioLogic.deleteItinerario(id);
    }
     
    
    
}
