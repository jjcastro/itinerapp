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

@Path("/itinerario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItinerarioResource {
    
     @Context 
    private HttpServletResponse response;
     
    /** Lista que se usara para el manejo de la información del servicio */
    private final static List<ItinerarioDtos> itinerario = new ArrayList<ItinerarioDtos>();
    
    
      /**
     * Metodo POST para la creación de un itinerario
     * @param dto
     * @return 
     */
    @POST
    public ItinerarioDtos createItinerario(ItinerarioDtos dto) {
        itinerario.add(dto);
        return dto;
    }
    
      /**
     * Metodo GET para obtener los itinerarios
     * @return 
     */
    @GET
    public List<ItinerarioDtos> getItinerarios() {
        return itinerario;
    }
    
     /**
     * Metodo PUT utilizado principalmente para actualizar
     * @param id
     * @param dto
     * @return 
     */
    @PUT
    @Path("{id: \\d+}")
    public ItinerarioDtos updateItinerario(@PathParam("id") Long id, ItinerarioDtos dto) {
        dto.setId(id);
        for (int i = 0; i < itinerario.size(); i++) {
            if (itinerario.get(i).getId().equals(dto.getId())){
                itinerario.get(i).setImagen(dto.getImagen());
                itinerario.get(i).setNombre(dto.getNombre());
                itinerario.get(i).setDateOut(dto.getDateOut());
                itinerario.get(i).setDateIn(dto.getDateIn());
            }
        }
        return dto;
    }
    
     /**
     * Metodo DELETE usado para eliminar un elemento
     * @param id
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteItinerario(@PathParam("id") Long id) {
        for (int i = 0; i < itinerario.size(); i++) {
            if (itinerario.get(i).getId().equals(id)){
                itinerario.remove(i);
            }
        }
    }
    

     
}
