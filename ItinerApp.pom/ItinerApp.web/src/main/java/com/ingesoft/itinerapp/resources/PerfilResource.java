package com.ingesoft.itinerapp.resources;

import com.ingesoft.itinerapp.api.IRecuerdoLogic;
import com.ingesoft.itinerapp.converter.RecuerdoConverter;
import com.ingesoft.itinerapp.dtos.PerfilDtos;
import com.ingesoft.itinerapp.dtos.RecuerdoDTO;
import com.ingesoft.itinerapp.entities.RecuerdoEntity;
import com.ingesoft.itinerapp.exceptions.PerfilLogicException;
import com.ingesoft.itinerapp.mocks.PerfilLogicMock;

        

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;



@Path("recuerdos")
@Produces("application/json")
@Consumes("application/json")     
@RequestScoped
public class PerfilResource {

    //El codigo esta comentado mientras se arreglan ciertos problemas, por ahora se usa el Mock.
    private static final Logger logger = Logger.getLogger(PerfilResource.class.getName());
    
    @Inject
    private IRecuerdoLogic perfilLogic;

	
    @GET
    public List<RecuerdoDTO> getRecuerdos() throws PerfilLogicException {
        logger.info("Se ejecuta método getRecuerdos");
        List<RecuerdoEntity> recuerdos = perfilLogic.getRecuerdos();
        return RecuerdoConverter.listEntity2DTO(recuerdos);
        
    }

  
    @GET
    @Path("{id: \\d+}")
    public RecuerdoDTO getRecuerdo(@PathParam("id") Long id) throws PerfilLogicException {
        logger.log(Level.INFO, "Se ejecuta método getRecuerdo con id={0}", id);
        RecuerdoEntity recuerdo = perfilLogic.getRecuerdo(id);
        return RecuerdoConverter.basicEntity2DTO(recuerdo);
    }

    
    @POST
    public RecuerdoDTO createRecuerdo(RecuerdoDTO precuerdo) throws PerfilLogicException {
        logger.info("Se ejecuta método createRecuerdo");
        RecuerdoEntity entity = RecuerdoConverter.basicDTO2Entity(precuerdo);
        RecuerdoEntity newEntity;
        try {
            newEntity = perfilLogic.createRecuerdo(entity);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }
        return RecuerdoConverter.basicEntity2DTO(newEntity);
    }

    
    @PUT
    @Path("{id: \\d+}")
    public RecuerdoDTO updateRecuerdo(@PathParam("id") Long id, RecuerdoDTO pRecuerdo) throws PerfilLogicException {
        logger.log(Level.INFO, "Se ejecuta método updateRecuerdocon id={0}", id);
        RecuerdoEntity entity = RecuerdoConverter.basicDTO2Entity(pRecuerdo);
        entity.setId(id);
        RecuerdoEntity oldEntity = perfilLogic.getRecuerdo(id);
        try {
            RecuerdoEntity savedRecuerdo = perfilLogic.updateRecuerdo(entity);
            return RecuerdoConverter.basicEntity2DTO(savedRecuerdo);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getLocalizedMessage(), ex);
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }
    }

   
    @DELETE
    @Path("{id: \\d+}")
    public void deleteRecuerdo(@PathParam("id") Long id) throws PerfilLogicException {
    	logger.log(Level.INFO, "Se ejecuta método deleteRecuerdo con id={0}", id);
        perfilLogic.deleteRecuerdo(id);
    }
    /*
     @Inject
    private PerfilLogicMock perfilLogic;

	
    @GET
    public List<RecuerdoDTO> getRecuerdos() throws PerfilLogicException {
        return perfilLogic.getRecuerdos();
    }

  
    @GET
    @Path("{id: \\d+}")
    public RecuerdoDTO getRecuerdo(@PathParam("id") Long id) throws PerfilLogicException {
        return perfilLogic.getRecuerdo(id);
    }

    
    @POST
    public RecuerdoDTO createRecuerdo(RecuerdoDTO precuerdo) throws PerfilLogicException {
        return perfilLogic.createRecuerdo(precuerdo);
    }

    
    @PUT
    @Path("{id: \\d+}")
    public RecuerdoDTO updateRecuerdo(@PathParam("id") Long id, RecuerdoDTO pRecuerdo) throws PerfilLogicException {
        return perfilLogic.updateRecuerdo(id,pRecuerdo);
    }

   
    @DELETE
    @Path("{id: \\d+}")
    public void deleteRecuerdo(@PathParam("id") Long id) throws PerfilLogicException {
    	perfilLogic.deleteRecuerdo(id);
    }
    */
        
}
