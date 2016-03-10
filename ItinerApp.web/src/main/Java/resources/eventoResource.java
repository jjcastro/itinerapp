/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;

import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import dtos.eventoDTO;
import exceptions.eventoLogicException;
import mocks.eventoLogicMock;

/**
 *
 * @author s.robayo222
 */
@Path("eventos")
@Produces("application/json")
public class eventoResource
{
    @Inject
	eventoLogicMock eventoLogic;

	/**
	 * Obtiene el listado de ciudades.
	 * @return lista de ciudades
	 * @throws CityLogicException excepción retornada por la lógica
	 */
    @GET
    public List<eventoDTO> getCities() throws eventoLogicException {
        return eventoLogic.getEvento();
    }

    /**
     * Obtiene una ciudad
     * @param id identificador de la ciudad
     * @return ciudad encontrada
     * @throws CityLogicException cuando la ciudad no existe
     */
    @GET
    @Path("{id: \\d+}")
    public eventoDTO getEvento(@PathParam("id") Long id) throws eventoLogicException {
        return eventoLogic.getEvento(id);
    }

    /**
     * Agrega una ciudad
     * @param city ciudad a agregar
     * @return datos de la ciudad a agregar
     * @throws CityLogicException cuando ya existe una ciudad con el id suministrado
     */
    @POST
    public eventoDTO createEvento(eventoDTO evento) throws eventoLogicException {
        return eventoLogic.createEvento(evento);
    }

    /**
     * Actualiza los datos de una ciudad
     * @param id identificador de la ciudad a modificar
     * @param city ciudad a modificar
     * @return datos de la ciudad modificada
     * @throws CityLogicException cuando no existe una ciudad con el id suministrado
     */
    @PUT
    @Path("{id: \\d+}")
    public eventoDTO updateEvento(@PathParam("id") Long id, eventoDTO evento) throws eventoLogicException {
        return eventoLogic.updateEvento(id, evento);
    }

    /**
     * Elimina los datos de una ciudad
     * @param id identificador de la ciudad a eliminar
     * @throws CityLogicException cuando no existe una ciudad con el id suministrado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteEvento(@PathParam("id") Long id) throws eventoLogicException {
    	eventoLogic.deleteEvento(id);
    }
}
