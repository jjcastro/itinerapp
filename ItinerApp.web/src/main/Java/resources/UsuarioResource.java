package resources;

/**
 * @author jc.martha10
 */

import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import dtos.UsuarioDtos;
import exceptions.UsuarioException;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import mocks.UsuarioLogicMock;

@Path("/usuario")
@Produces("application/json")
@RequestScoped
public class UsuarioResource {
    
     @Inject
	UsuarioLogicMock usuarioLogic;
 
      @GET
    public List<UsuarioDtos> getUsuarios() throws UsuarioException {
          System.out.println("Esta pidiendo usuarios");
        return usuarioLogic.getUsuarios();
    }
    
    
    @GET
    @Path("{id: \\d+}")
    public UsuarioDtos getUsuario(@PathParam("id") Long id) throws UsuarioException {
        return usuarioLogic.getUsuario(id);
    }
    
     @POST
    public UsuarioDtos createUsuario(UsuarioDtos usuario) throws UsuarioException{
        return usuarioLogic.createUsuario(usuario);
    }
    
     @PUT
    @Path("{id: \\d+}")
    public UsuarioDtos updateUsuario(@PathParam("id") Long id, UsuarioDtos usuario) throws UsuarioException {
        return usuarioLogic.updateUsuario(id, usuario);
    }
    
      @DELETE
    @Path("{id: \\d+}")
    public void deleteUsuario(@PathParam("id") Long id) throws UsuarioException {
    	usuarioLogic.deleteUsuario(id);
    }
     
    
    
}
