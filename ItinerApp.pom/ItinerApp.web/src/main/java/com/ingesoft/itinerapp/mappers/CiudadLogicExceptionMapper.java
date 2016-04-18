package com.ingesoft.itinerapp.mappers;

import com.ingesoft.itinerapp.exceptions.CiudadLogicException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;




@Provider
public class CiudadLogicExceptionMapper implements ExceptionMapper<CiudadLogicException> {


	@Override
	public Response toResponse(CiudadLogicException ex) {
		// retorna una respuesta
		return Response
				.status(Response.Status.NOT_FOUND)	// estado HTTP 404
				.entity(ex.getMessage())			// mensaje adicional
				.type("text/plain")
				.build();
	}

}
