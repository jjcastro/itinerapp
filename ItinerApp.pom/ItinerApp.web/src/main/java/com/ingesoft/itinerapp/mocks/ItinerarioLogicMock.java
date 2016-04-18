/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingesoft.itinerapp.mocks;

/**
 *
 * @author johnycsc
 */

import com.ingesoft.itinerapp.dtos.ItinerarioDtos;
import com.ingesoft.itinerapp.exceptions.ItinerarioException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


@Named
@ApplicationScoped
public class ItinerarioLogicMock {
    
        private final static Logger logger = Logger.getLogger(ItinerarioLogicMock.class.getName());
        
        
        private static ArrayList<ItinerarioDtos> itinerarios;
        
        /**
     * Constructor. Crea los datos de ejemplo.
     * @throws java.text.ParseException
     */
    public ItinerarioLogicMock() throws ParseException {
        
        
    	if (itinerarios == null) {
            itinerarios = new ArrayList<>();
            itinerarios.add(new ItinerarioDtos(1L, "Viaje Paris", "https://scdn3.thomascook.com/crop?imageUrl=http://magnolia.production.thomascook.io/wcms/dam/tcuk/city-breaks/paris/1hero1.jpg&maxWidth=1200&maxHeight=0", " 2016-04-18", " 2016-05-18" ));
            itinerarios.add(new ItinerarioDtos(2L, "Viaje EEUU", "http://static.thousandwonders.net/Washington.D.C..original.14.jpg", " 2016-06-20", " 2016-08-2"));
            itinerarios.add(new ItinerarioDtos(3L, "Viaje Asia", "http://www.interfima.org/wp-content/uploads/2015/03/china-information.jpg", " 2016-09-19", " 2016-11-25"));
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de itinerarios");
    	logger.info("itinerarios" + itinerarios );
    }   
    
    
    /**
	 * Obtiene el listado de itinerarios. 
	 * @return lista de itinerarios
         * @throws exceptions.ItinerarioException  
	 */    
    public List<ItinerarioDtos> getItinerarios() throws ItinerarioException {
    	if (itinerarios == null) {
    		logger.severe("Error interno: lista de itinerarios no existe.");
    		throw new ItinerarioException("Error interno: lista de itinerarios no existe.");    		
    	}
    	
    	logger.info("retornando todos los itinerarios");
        logger.info("itinerarios" + itinerarios );
    	return itinerarios;
    }
    
      /**
     * Obtiene un itinerario
     * @param id identificador de la itinerario
     * @return itinerario encontrada
     * @throws CityLogicException cuando la ciudad no existe
     */
    public ItinerarioDtos getItinerario(Long id) throws ItinerarioException {
    	logger.info("recibiendo solicitud de ciudad con id " + id);
    	
    	// busca la ciudad con el id suministrado
        for (ItinerarioDtos itinerario : itinerarios) {
            if (Objects.equals(itinerario.getId(), id)){
            	logger.info("retornando itinerario " + itinerario);
                return itinerario;
            }
        }
        
        // si no encuentra la ciudad
        logger.severe("No existe itinerario con ese id");
        throw new ItinerarioException("No existe itinerario con ese id");
    }
    
    
    /**
     * Agrega un itinerario a la lista.
     * @param newItinerario itinerario a adicionar
     * @throws CityLogicException cuando ya existe una ciudad con el id suministrado
     * @return itinerario agregado
     */
    public ItinerarioDtos createItinerario(ItinerarioDtos newItinerario) throws ItinerarioException {
    	logger.info("recibiendo solicitud de agregar itinerario " + newItinerario);
    	
    	// la nueva ciudad tiene id ?
    	if ( newItinerario.getId() != null ) {
	    	// busca la ciudad con el id suministrado
	        for (ItinerarioDtos city : itinerarios) {
	        	// si existe una ciudad con ese id
	            if (Objects.equals(city.getId(), newItinerario.getId())){
	            	logger.severe("Ya existe un itinerario con ese id");
	                throw new ItinerarioException("Ya existe un itinerario con ese id");
	            }
	        }
	        
	    // la nueva ciudad no tiene id ? 
    	} else {

    		// genera un id para la ciudad
    		logger.info("Generando id para la nueva ciudad");
    		long newId = 1;
	        for (ItinerarioDtos itinerario : itinerarios) {
	            if (newId <= itinerario.getId()){
	                newId =  itinerario.getId() + 1;
	            }
	        }
	        newItinerario.setId(newId);
    	}
    	
        // agrega la ciudad
    	logger.info("agregando itinerario " + newItinerario);
        itinerarios.add(newItinerario);
        return newItinerario;
    }

    
     /**
     * Actualiza los datos de una ciudad
     * @param id identificador de la ciudad a modificar
     * @param city ciudad a modificar
     * @return datos de la ciudad modificada 
     * @throws CityLogicException cuando no existe una ciudad con el id suministrado
     */
    public ItinerarioDtos updateItinerario(Long id, ItinerarioDtos updatedItinerario) throws ItinerarioException {
    	logger.info("recibiendo solictud de modificar itinerario " + updatedItinerario);
    	
    	// busca la ciudad con el id suministrado
        for (ItinerarioDtos itinerario : itinerarios) {
            if (Objects.equals(itinerario.getId(), id)) {
            	
            	// modifica la ciudad
            	itinerario.setId(updatedItinerario.getId());
                itinerario.setNombre(updatedItinerario.getNombre());
                itinerario.setImagen(updatedItinerario.getImagen());
                itinerario.setDateIn(updatedItinerario.getDateIn());
                itinerario.setDateOut(updatedItinerario.getDateOut());
                
                // retorna la ciudad modificada
            	logger.info("Modificando itinerario " + itinerario);
                return itinerario;
            }
        }
        
        // no encontró la ciudad con ese id ?
        logger.severe("No existe un itinerario con ese id");
        throw new ItinerarioException("No existe un itinerario con ese id");
    }
    
    
    
    /**
     * Elimina los datos de una ciudad
     * @param id identificador de la ciudad a eliminar
     * @throws CityLogicException cuando no existe una ciudad con el id suministrado
     */
    public void deleteItinerario(Long id) throws ItinerarioException {
    	logger.info("recibiendo solictud de eliminar itinerario con id " + id);
    	
    	// busca la ciudad con el id suministrado
        for (ItinerarioDtos itinerario : itinerarios) {
            if (Objects.equals(itinerario.getId(), id)) {
            	
            	// elimina la ciudad
            	logger.info("eliminando itinerario " + itinerario);
                itinerarios.remove(itinerario);
                return;
            }
        }

        // no encontró la ciudad con ese id ?
        logger.severe("No existe un itinerario con ese id");
        throw new ItinerarioException("No existe un itinerario con ese id");
    }
    
    
        
    
    
    
    
}
