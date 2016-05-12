package com.ingesoft.itinerapp.resources;




import com.ingesoft.itinerapp.api.ICiudadLogic;
import com.ingesoft.itinerapp.converter.CiudadConverter;
import com.ingesoft.itinerapp.dtos.CiudadDTO;
import com.ingesoft.itinerapp.ejbs.CiudadLogic;
import com.ingesoft.itinerapp.entities.CiudadEntity;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;



@Path("ciudad")
@Produces("application/json")
@RequestScoped
public class CiudadResource {

    @Inject
    ICiudadLogic ciudadLogic;

    @GET
    public List<CiudadDTO> getCiudades() throws CiudadLogicException {
        List<CiudadEntity> ciudades = ciudadLogic.getCiudades();
        return CiudadConverter.listEntity2DTO(ciudades);
    }


    @GET
    @Path("{id: \\d+}")
    public CiudadDTO getCiudad(@PathParam("id") Long id) throws CiudadLogicException {
        CiudadEntity ciudad = ciudadLogic.getCiudad(id);
        return CiudadConverter.basicEntity2DTO(ciudad);
    }


    @POST
    public CiudadDTO createCiudad(CiudadDTO pciudad) throws CiudadLogicException {
        CiudadEntity entity = CiudadConverter.basicDTO2Entity(pciudad);
        CiudadEntity newEntity;
        try {
            newEntity = ciudadLogic.createCiudad(entity);
        } catch (Exception ex) {
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }
        return CiudadConverter.basicEntity2DTO(newEntity);
    }


    @PUT
    @Path("{id: \\d+}")
    public CiudadDTO updateCiudad(@PathParam("id") Long id, CiudadDTO pciudad) throws CiudadLogicException {
        CiudadEntity entity = CiudadConverter.basicDTO2Entity(pciudad);
        entity.setId(id);
        CiudadEntity oldEntity = ciudadLogic.getCiudad(id);
        try {
            CiudadEntity savedRecuerdo = ciudadLogic.updateCiudad(entity);
            return CiudadConverter.basicEntity2DTO(savedRecuerdo);
        } catch (Exception ex) {
            throw new WebApplicationException(ex.getLocalizedMessage(), ex, Response.Status.BAD_REQUEST);
        }
    }


    @DELETE
    @Path("{id: \\d+}")
    public void deleteCiudad(@PathParam("id") Long id) throws CiudadLogicException {
      ciudadLogic.deleteCiudad(id);
    }



}
