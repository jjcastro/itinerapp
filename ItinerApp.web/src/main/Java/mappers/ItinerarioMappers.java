/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import exceptions.ItinerarioException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author johnycsc
 */

@Provider
public class ItinerarioMappers implements ExceptionMapper<ItinerarioException>{

    @Override
    public Response toResponse(ItinerarioException exception) {
        return Response
				.status(Response.Status.NOT_FOUND)	// estado HTTP 404
				.entity(exception.getMessage())			// mensaje adicional
				.type("text/plain")
				.build();
    }
    
}
