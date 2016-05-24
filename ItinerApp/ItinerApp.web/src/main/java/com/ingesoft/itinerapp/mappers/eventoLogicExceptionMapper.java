package com.ingesoft.itinerapp.mappers;

import com.ingesoft.itinerapp.exceptions.eventoLogicException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


/**
 *
 * @author s.robayo222
 */
@Provider
public class eventoLogicExceptionMapper implements ExceptionMapper<eventoLogicException> {

	@Override
	public Response toResponse(eventoLogicException ex) {
		// retorna una respuesta
		return Response
				.status(Response.Status.NOT_FOUND)	// estado HTTP 404
				.entity(ex.getMessage())			// mensaje adicional
				.type("text/plain")
				.build();
	}

}
