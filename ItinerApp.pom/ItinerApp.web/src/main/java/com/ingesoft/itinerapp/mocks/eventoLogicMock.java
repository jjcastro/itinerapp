package com.ingesoft.itinerapp.mocks;

import com.ingesoft.itinerapp.dtos.EventoDTO;
import com.ingesoft.itinerapp.exceptions.eventoLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;



/**
 *
 * @author s.robayo222
 */
@Named
@ApplicationScoped
public class eventoLogicMock
{
    private final static Logger logger = Logger.getLogger(eventoLogicMock.class.getName());


    private static ArrayList<EventoDTO> eventos;


    public eventoLogicMock() {

    	if (eventos == null) {
            eventos = new ArrayList<>();
            eventos.add(new EventoDTO(1L, "Estereo Picnic", "Bogota", "Un Mundo Distino"));
            eventos.add(new EventoDTO(2L, "Rock al Parque", "Bogota", "Cultura de Paz"));
            eventos.add(new EventoDTO(3L, "Carnaval de Barranquilla", "Barranquilla", "#Carnaval Somos Todos"));
        }

    	logger.setLevel(Level.INFO);

    	// muestra información
    	logger.info("Inicializa la lista de eventos");
    	logger.info("eventos" + eventos );
    }

    public List<EventoDTO> getEvento() throws eventoLogicException {
    	if (eventos == null) {
    		logger.severe("Error interno: lista de eventos no existe.");
    		throw new eventoLogicException("Error interno: lista de eventos no existe.");
    	}

    	logger.info("retornando todas los eventos");
    	return eventos;
    }

    public EventoDTO getEvento(Long id) throws eventoLogicException {
    	logger.info("recibiendo solicitud de evento con id " + id);

        for (EventoDTO evento : eventos) {
            if (Objects.equals(evento.getId(), id)){
            	logger.info("retornando evento " + evento);
                return evento;
            }
        }

        logger.severe("No existe evento con ese id");
        throw new eventoLogicException("No existe evento con ese id");
    }

    public EventoDTO createEvento(EventoDTO newEvento) throws eventoLogicException {
    	logger.info("recibiendo solicitud de agregar evento " + newEvento);

    	// la nueva ciudad tiene id ?
    	if ( newEvento.getId() != null ) {
	        for (EventoDTO city : eventos) {
	        	// si existe una ciudad con ese id
	            if (Objects.equals(city.getId(), newEvento.getId())){
	            	logger.severe("Ya existe un evento con ese id");
	                throw new eventoLogicException("Ya existe un evento con ese id");
	            }
	        }

    	} else {
    		logger.info("Generando id para el nuevo evento");
    		long newId = 1;
	        for (EventoDTO evento : eventos) {
	            if (newId <= evento.getId()){
	                newId =  evento.getId() + 1;
	            }
	        }
	        newEvento.setId(newId);
    	}

    	logger.info("agregando evento " + newEvento);
        eventos.add(newEvento);
        return newEvento;
    }

    public EventoDTO updateEvento(Long id, EventoDTO updatedEvento) throws eventoLogicException {
    	logger.info("recibiendo solictud de modificar evento " + updatedEvento);

        for (EventoDTO evento : eventos) {
            if (Objects.equals(evento.getId(), id)) {

            	evento.setId(updatedEvento.getId());
                evento.setName(updatedEvento.getName());

            	logger.info("Modificando evento " + evento);
                return evento;
            }
        }
        logger.severe("No existe un evento con ese id");
        throw new eventoLogicException("No existe un evento con ese id");
    }

    public void deleteEvento(Long id) throws eventoLogicException {
    	logger.info("recibiendo solictud de eliminar evento con id " + id);

        for (EventoDTO evento : eventos) {
            if (Objects.equals(evento.getId(), id)) {

            	logger.info("eliminando evento " + evento);
                eventos.remove(evento);
                return;
            }
        }

        logger.severe("No existe un evento con ese id");
        throw new eventoLogicException("No existe un evento con ese id");
    }
}
