package com.ingesoft.itinerapp.resources;

/**
 * @author jc.martha10
 */

import com.ingesoft.itinerapp.api.IUsuarioLogic;
import com.ingesoft.itinerapp.converter.UsuarioConverter;
import com.ingesoft.itinerapp.converter.UsuarioLoginConverter;
import com.ingesoft.itinerapp.dtos.UsuarioDTO;
import com.ingesoft.itinerapp.dtos.UsuarioLoginDTO;
import com.ingesoft.itinerapp.entities.UsuarioEntity;
import com.ingesoft.itinerapp.entities.UsuarioLoginEntity;
import com.ingesoft.itinerapp.exceptions.UsuarioException;
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
    IUsuarioLogic usuarioLogic;

    @GET
    public List<UsuarioDTO> getUsuarios() throws UsuarioException {
        System.out.println("Esta pidiendo usuarios");
        List<UsuarioEntity> usuarios = usuarioLogic.getUsuarios();
        return UsuarioConverter.listEntity2DTO(usuarios);
    }


    @GET
    @Path("{id: \\d+}")
    public UsuarioDTO getUsuario(@PathParam("id") Long id) throws UsuarioException {
        UsuarioEntity ent = usuarioLogic.getUsuario(id);
        return UsuarioConverter.basicEntity2DTO(ent);
    }

    @POST
    public UsuarioDTO createUsuario(UsuarioDTO usuario) throws UsuarioException{
        System.out.println("id: " + usuario.getId());
        System.out.println("Nombre; " + usuario.getNombre());
        System.out.println("Apellido: " + usuario.getApellido());
        System.out.println("Username: " + usuario.getUsername());
        System.out.println("Email: " + usuario.getEmail());
        System.out.println("Cédula: " + usuario.getCedula());
        UsuarioEntity entity = UsuarioConverter.basicDTO2Entity(usuario);
        UsuarioEntity newEntity = usuarioLogic.createUsuario(entity);
        return UsuarioConverter.basicEntity2DTO(newEntity);
    }

     @PUT
    @Path("{id: \\d+}")
    public UsuarioDTO updateUsuario(@PathParam("id") Long id, UsuarioDTO usuario) throws UsuarioException {

        UsuarioEntity entity = UsuarioConverter.basicDTO2Entity(usuario);
        entity.setId(id);
        UsuarioEntity oldEntity = usuarioLogic.getUsuario(id);
        UsuarioEntity savedUsuario = usuarioLogic.updateUsuario(entity);
        return UsuarioConverter.basicEntity2DTO(savedUsuario);

    }


      @DELETE
    @Path("{id: \\d+}")
    public void deleteUsuario(@PathParam("id") Long id) throws UsuarioException {
    	usuarioLogic.deleteUsuario(id);
    }

    @POST
    @Path("login")
    public UsuarioLoginDTO login(UsuarioLoginDTO usuario) throws UsuarioException{
        System.out.println("Logueando a " + usuario.getUsername() + "...");
        UsuarioLoginEntity entity = UsuarioLoginConverter.basicDTO2Entity(usuario);
        if (usuarioLogic.login(entity)){
            System.out.println("Logueado confirmado");
            return usuario;
        }

        System.out.println("No fue logueado");
        return null;
    }

}
