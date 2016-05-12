package com.ingesoft.itinerapp.resources;

/**
 * @author jc.martha10
 */

import com.ingesoft.itinerapp.api.IUsuarioLogic;
import com.ingesoft.itinerapp.converter.UsuarioConverter;
import com.ingesoft.itinerapp.dtos.UsuarioDTO;
import com.ingesoft.itinerapp.entities.UsuarioEntity;
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
        System.out.println("Creando usuario con correo: " + usuario.getCorreo());
        usuario.setAdministrador(0);
        System.out.println("Convirtiendo DTO en Entity...");
        UsuarioEntity entity = UsuarioConverter.basicDTO2Entity(usuario);
        System.out.println("Convertido de DTO a Entity");
        UsuarioEntity newEntity = usuarioLogic.createUsuario(entity);
        System.out.println("Usuario creado.");
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
    public UsuarioDTO login(UsuarioDTO usuario) throws UsuarioException{
        System.out.println("Logueando a " + usuario.getCorreo() + "...");
        UsuarioEntity entity = UsuarioConverter.basicDTO2Entity(usuario);
        if (usuarioLogic.login(entity)){
            System.out.println("Logueado confirmado");
            return usuario;
        }

        System.out.println("No fue logueado");
        return null;
    }

}
