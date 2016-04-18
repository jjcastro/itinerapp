package com.ingesoft.itinerapp.mappers;


import com.ingesoft.itinerapp.exceptions.UsuarioException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * @author jc.martha10
 */

@Provider
public class UsuarioMappers implements ExceptionMapper<UsuarioException>{

    @Override
    public Response toResponse(UsuarioException exception) {
        return Response
				.status(Response.Status.NOT_FOUND)	// estado HTTP 404
				.entity(exception.getMessage())			// mensaje adicional
				.type("text/plain")
				.build();
    }
    
}
