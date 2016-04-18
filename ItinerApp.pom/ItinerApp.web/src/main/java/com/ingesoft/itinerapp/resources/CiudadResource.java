package com.ingesoft.itinerapp.resources;




import com.ingesoft.itinerapp.dtos.CiudadDTO;
import com.ingesoft.itinerapp.exceptions.CiudadLogicException;
import com.ingesoft.itinerapp.mocks.CiudadLogicMock;

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



@Path("ciudad")
@Produces("application/json")
@RequestScoped
public class CiudadResource {

    @Inject
    CiudadLogicMock ciudadLogic;

    @GET
    public List<CiudadDTO> getCiudades() throws CiudadLogicException {
        return ciudadLogic.getCiudades();
    }


    @GET
    @Path("{id: \\d+}")
    public CiudadDTO getCiudad(@PathParam("id") Long id) throws CiudadLogicException {
        return ciudadLogic.getCiudad(id);
    }


    @POST
    public CiudadDTO createCiudad(CiudadDTO pciudad) throws CiudadLogicException {
        return ciudadLogic.createCiudad(pciudad);
    }


    @PUT
    @Path("{id: \\d+}")
    public CiudadDTO updateCiudad(@PathParam("id") Long id, CiudadDTO pciudad) throws CiudadLogicException {
        return ciudadLogic.updateCiudad(id, pciudad);
    }


    @DELETE
    @Path("{id: \\d+}")
    public void deleteCiudad(@PathParam("id") Long id) throws CiudadLogicException {
      ciudadLogic.deleteCiudad(id);
    }



}
