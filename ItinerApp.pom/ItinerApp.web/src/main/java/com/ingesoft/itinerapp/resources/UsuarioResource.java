package com.ingesoft.itinerapp.resources;

/**
 * @author jc.martha10
 */

import com.ingesoft.itinerapp.dtos.UsuarioDTO;
import com.ingesoft.itinerapp.exceptions.UsuarioException;
import com.ingesoft.itinerapp.mocks.UsuarioLogicMock;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;


@Path("usuarios")
@Produces("application/json")
@RequestScoped
public class UsuarioResource {

    @Inject
    UsuarioLogicMock usuarioLogic;

    @GET
    public List<UsuarioDTO> getUsuarios() throws UsuarioException {
          System.out.println("Esta pidiendo usuarios");
        return usuarioLogic.getUsuarios();
    }


    @GET
    @Path("{id: \\d+}")
    public UsuarioDTO getUsuario(@PathParam("id") Long id) throws UsuarioException {
        return usuarioLogic.getUsuario(id);
    }

     @POST
    public UsuarioDTO createUsuario(UsuarioDTO usuario) throws UsuarioException{
        return usuarioLogic.createUsuario(usuario);
    }

     @PUT
    @Path("{id: \\d+}")
    public UsuarioDTO updateUsuario(@PathParam("id") Long id, UsuarioDTO usuario) throws UsuarioException {
        return usuarioLogic.updateUsuario(id, usuario);
    }

      @DELETE
    @Path("{id: \\d+}")
    public UsuarioDTO deleteUsuario(@PathParam("id") Long id) throws UsuarioException {
    	return usuarioLogic.deleteUsuario(id);
    }
}
