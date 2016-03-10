/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


import dtos.eventoDTO;
import exceptions.eventoLogicException;
/**
 *
 * @author s.robayo222
 */
@Named
@ApplicationScoped
public class eventoLogicMock
{
    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(eventoLogicMock.class.getName());

	// listado de ciudades
    private static ArrayList<eventoDTO> eventos;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public eventoLogicMock() {

    	if (eventos == null) {
            eventos = new ArrayList<>();
            eventos.add(new eventoDTO(1L, "Estereo Picnic", "Bogota", "Un Mundo Distino"));
            eventos.add(new eventoDTO(2L, "Rock al Parque", "Bogota", "Cultura de Paz"));
            eventos.add(new eventoDTO(3L, "Carnaval de Barranquilla", "Barranquilla", "#Carnaval Somos Todos"));
        }

    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);

    	// muestra información
    	logger.info("Inicializa la lista de eventos");
    	logger.info("eventos" + eventos );
    }

	/**
	 * Obtiene el listado de personas.
	 * @return lista de ciudades
	 * @throws CityLogicException cuando no existe la lista en memoria
	 */
    public List<eventoDTO> getEvento() throws eventoLogicException {
    	if (eventos == null) {
    		logger.severe("Error interno: lista de eventos no existe.");
    		throw new eventoLogicException("Error interno: lista de eventos no existe.");
    	}

    	logger.info("retornando todas los eventos");
    	return eventos;
    }

    /**
     * Obtiene una ciudad
     * @param id identificador de la ciudad
     * @return ciudad encontrada
     * @throws CityLogicException cuando la ciudad no existe
     */
    public eventoDTO getEvento(Long id) throws eventoLogicException {
    	logger.info("recibiendo solicitud de evento con id " + id);

    	// busca la ciudad con el id suministrado
        for (eventoDTO evento : eventos) {
            if (Objects.equals(evento.getId(), id)){
            	logger.info("retornando evento " + evento);
                return evento;
            }
        }

        // si no encuentra la ciudad
        logger.severe("No existe evento con ese id");
        throw new eventoLogicException("No existe evento con ese id");
    }

    /**
     * Agrega una ciudad a la lista.
     * @param newCity ciudad a adicionar
     * @throws CityLogicException cuando ya existe una ciudad con el id suministrado
     * @return ciudad agregada
     */
    public eventoDTO createEvento(eventoDTO newEvento) throws eventoLogicException {
    	logger.info("recibiendo solicitud de agregar evento " + newEvento);

    	// la nueva ciudad tiene id ?
    	if ( newEvento.getId() != null ) {
	    	// busca la ciudad con el id suministrado
	        for (eventoDTO city : eventos) {
	        	// si existe una ciudad con ese id
	            if (Objects.equals(city.getId(), newEvento.getId())){
	            	logger.severe("Ya existe un evento con ese id");
	                throw new eventoLogicException("Ya existe un evento con ese id");
	            }
	        }

	    // la nueva ciudad no tiene id ?
    	} else {

    		// genera un id para la ciudad
    		logger.info("Generando id para el nuevo evento");
    		long newId = 1;
	        for (eventoDTO evento : eventos) {
	            if (newId <= evento.getId()){
	                newId =  evento.getId() + 1;
	            }
	        }
	        newEvento.setId(newId);
    	}

        // agrega la ciudad
    	logger.info("agregando evento " + newEvento);
        eventos.add(newEvento);
        return newEvento;
    }

    /**
     * Actualiza los datos de una ciudad
     * @param id identificador de la ciudad a modificar
     * @param city ciudad a modificar
     * @return datos de la ciudad modificada
     * @throws CityLogicException cuando no existe una ciudad con el id suministrado
     */
    public eventoDTO updateEvento(Long id, eventoDTO updatedEvento) throws eventoLogicException {
    	logger.info("recibiendo solictud de modificar evento " + updatedEvento);

    	// busca la ciudad con el id suministrado
        for (eventoDTO evento : eventos) {
            if (Objects.equals(evento.getId(), id)) {

            	// modifica la ciudad
            	evento.setId(updatedEvento.getId());
                evento.setNombre(updatedEvento.getNombre());

                // retorna la ciudad modificada
            	logger.info("Modificando evento " + evento);
                return evento;
            }
        }

        // no encontró la ciudad con ese id ?
        logger.severe("No existe un evento con ese id");
        throw new eventoLogicException("No existe un evento con ese id");
    }

    /**
     * Elimina los datos de una ciudad
     * @param id identificador de la ciudad a eliminar
     * @throws CityLogicException cuando no existe una ciudad con el id suministrado
     */
    public void deleteEvento(Long id) throws eventoLogicException {
    	logger.info("recibiendo solictud de eliminar evento con id " + id);

    	// busca la ciudad con el id suministrado
        for (eventoDTO evento : eventos) {
            if (Objects.equals(evento.getId(), id)) {

            	// elimina la ciudad
            	logger.info("eliminando evento " + evento);
                eventos.remove(evento);
                return;
            }
        }

        // no encontró la ciudad con ese id ?
        logger.severe("No existe un evento con ese id");
        throw new eventoLogicException("No existe un evento con ese id");
    }
}
