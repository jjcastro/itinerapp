package mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import exceptions.eventoLogicException;

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
