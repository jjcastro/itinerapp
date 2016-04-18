package com.ingesoft.itinerapp.resources;

import com.ingesoft.itinerapp.api.IRecuerdoLogic;
import com.ingesoft.itinerapp.dtos.PerfilDtos;
import com.ingesoft.itinerapp.dtos.RecuerdoDTO;
import com.ingesoft.itinerapp.exceptions.PerfilLogicException;
import com.ingesoft.itinerapp.mocks.PerfilLogicMock;

        

import java.util.List;
import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;



@Path("recuerdos")
@Produces("application/json")
//@Consume("application/json") intentar esto problema desc.
@RequestScoped
public class PerfilResource {

    //El codigo esta comentado mientras se arreglan ciertos problemas, por ahora se usa el Mock.
    
    /*
    @Inject
    private IRecuerdoLogic perfilLogic;

	
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
    public RecuerdoDTO updateRecuerdo(RecuerdoDTO pRecuerdo) throws PerfilLogicException {
        return perfilLogic.updateRecuerdo(pRecuerdo);
    }

   
    @DELETE
    @Path("{id: \\d+}")
    public void deleteRecuerdo(@PathParam("id") Long id) throws PerfilLogicException {
    	perfilLogic.deleteRecuerdo(id);
    }
    */
     @Inject
    private PerfilLogicMock perfilLogic;

	
    @GET
    public List<PerfilDtos> getRecuerdos() throws PerfilLogicException {
        return perfilLogic.getRecuerdos();
    }

  
    @GET
    @Path("{id: \\d+}")
    public PerfilDtos getRecuerdo(@PathParam("id") Long id) throws PerfilLogicException {
        return perfilLogic.getRecuerdo(id);
    }

    
    @POST
    public PerfilDtos createRecuerdo(PerfilDtos precuerdo) throws PerfilLogicException {
        return perfilLogic.createRecuerdo(precuerdo);
    }

    
    @PUT
    @Path("{id: \\d+}")
    public PerfilDtos updateRecuerdo(@PathParam("id") Long id, PerfilDtos pRecuerdo) throws PerfilLogicException {
        return perfilLogic.updateRecuerdo(id,pRecuerdo);
    }

   
    @DELETE
    @Path("{id: \\d+}")
    public void deleteRecuerdo(@PathParam("id") Long id) throws PerfilLogicException {
    	perfilLogic.deleteRecuerdo(id);
    }
    

}
