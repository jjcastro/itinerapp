package com.ingesoft.itinerapp.resources;

/**
 * @author jc.martha10
 */

import com.ingesoft.itinerapp.api.IUsuarioLogic;
import com.ingesoft.itinerapp.converter.RecuerdoConverter;
import com.ingesoft.itinerapp.converter.UsuarioConverter;
import com.ingesoft.itinerapp.dtos.RecuerdoDTO;
import com.ingesoft.itinerapp.dtos.UsuarioDTO;
import com.ingesoft.itinerapp.entities.UsuarioEntity;
import com.ingesoft.itinerapp.exceptions.UsuarioException;
import java.util.Iterator;
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
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;


@Path("usuarios")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
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
        UsuarioDTO resp = UsuarioConverter.basicEntity2DTO(ent);
        System.out.println("El id del dto es :     "+resp.getId());
        return UsuarioConverter.basicEntity2DTO(ent);
    }

     
    @GET
    @Path("{usuarioId: \\d+}/recuerdos")
    public List<RecuerdoDTO> listRecuerdos(@PathParam("usuarioId") Long usuarioId) {
        return RecuerdoConverter.listEntity2DTO(usuarioLogic.getRecuerdos(usuarioId));
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
        UsuarioEntity entidadLogueada = usuarioLogic.login(entity);
        System.out.println("Id logueada:   "+entidadLogueada.getId());
        if (entidadLogueada!= null){
            System.out.println("Logueado confirmado");
            UsuarioDTO logueadoDTO = UsuarioConverter.basicEntity2DTO(entidadLogueada);
            System.out.println("Id logueada DTO:   "+logueadoDTO.getId());
            return logueadoDTO;
        }

        System.out.println("No fue logueado");
        return null;
    }

}
