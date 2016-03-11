/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mocks;

import dtos.CiudadDTO;
import exceptions.CiudadLogicException;
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
public class CiudadLogicMock {


    private final static Logger logger = Logger.getLogger(CiudadLogicMock.class.getName());


    private static ArrayList<CiudadDTO> ciudades;


    public CiudadLogicMock() {

    	if (ciudades == null) {
            ciudades = new ArrayList<>();
            ciudades.add(new CiudadDTO(1L,"Rio de Janeiro","Brasil","Río de Janeiro es una ciudad con mucho encanto, el encanto de sus playas y su mar, el encanto de sus islas, bosques y montañas, el encanto del pueblo bronceado por el sol que brilla durante todo el año. Río es así, una ciudad alegre y extrovertida , fácil de amar. Será un amor a primera vista, como casi siempre pasa. En esta web podrás encontrar informacion turística de Río de Janeiro para ayudarte con los preparativos.","Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odit, itaque, deserunt corporis vero ipsum nisi eius odio natus ullam provident pariatur temporibus.",
"Rio de Janeiro","rio-square.jpg",new Date(), new Date()));
            ciudades.add(new CiudadDTO(2L,"Buenos Aires","Argentina","Río de Janeiro es una ciudad con mucho encanto, el encanto de sus playas y su mar, el encanto de sus islas, bosques y montañas, el encanto del pueblo bronceado por el sol que brilla durante todo el año. Río es así, una ciudad alegre y extrovertida , fácil de amar. Será un amor a primera vista, como casi siempre pasa. En esta web podrás encontrar informacion turística de Río de Janeiro para ayudarte con los preparativos.","Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odit, itaque, deserunt corporis vero ipsum nisi eius odio natus ullam provident pariatur temporibus.","Rio de Janeiro","argentina-square.jpg",new Date(), new Date()));
            ciudades.add(new CiudadDTO(3L,"Santiago de Chile","Chile","Río de Janeiro es una ciudad con mucho encanto, el encanto de sus playas y su mar, el encanto de sus islas, bosques y montañas, el encanto del pueblo bronceado por el sol que brilla durante todo el año. Río es así, una ciudad alegre y extrovertida , fácil de amar. Será un amor a primera vista, como casi siempre pasa. En esta web podrás encontrar informacion turística de Río de Janeiro para ayudarte con los preparativos.","Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odit, itaque, deserunt corporis vero ipsum nisi eius odio natus ullam provident pariatur temporibus.","Rio de Janeiro","chile-square.jpg",new Date(), new Date()));
            ciudades.add(new CiudadDTO(4L,"New York","USA","Río de Janeiro es una ciudad con mucho encanto, el encanto de sus playas y su mar, el encanto de sus islas, bosques y montañas, el encanto del pueblo bronceado por el sol que brilla durante todo el año. Río es así, una ciudad alegre y extrovertida , fácil de amar. Será un amor a primera vista, como casi siempre pasa. En esta web podrás encontrar informacion turística de Río de Janeiro para ayudarte con los preparativos.","Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odit, itaque, deserunt corporis vero ipsum nisi eius odio natus ullam provident pariatur temporibus.","Rio de Janeiro","usa-square.jpg",new Date(), new Date()));
        }


    	logger.setLevel(Level.INFO);

    	logger.info("Inicializa la lista de ciudades");
    	logger.info("ciudades" + ciudades );
    }

    public List<CiudadDTO> getCiudades() throws CiudadLogicException {
    	if (ciudades == null) {
    		logger.severe("Error interno: lista de ciudades no existe.");
    		throw new CiudadLogicException("Error interno: lista de ciudades no existe.");
    	}

    	logger.info("retornando todas los ciudades");
    	return ciudades;
    }


    public CiudadDTO getCiudad(Long id) throws CiudadLogicException {
    	logger.info("recibiendo solicitud de ciudad con id " + id);


        for (CiudadDTO ciudad : ciudades) {
            if (Objects.equals(ciudad.getId(), id)){
            	logger.info("retornando ciudad " + ciudad);
                return ciudad;
            }
        }


        logger.severe("No existe el ciudad con ese id");
        throw new CiudadLogicException("No existe ciudad con ese id");
    }


    public CiudadDTO createCiudad(CiudadDTO newCiudad) throws CiudadLogicException {
    	logger.info("recibiendo solicitud de agregar ciudad " + newCiudad);


    	if ( newCiudad.getId() != null ) {

	        for (CiudadDTO ciudad : ciudades) {

	            if (Objects.equals(ciudad.getId(), newCiudad.getId())){
	            	logger.severe("Ya existe un ciudad con ese id");
	                throw new CiudadLogicException("Ya existe un ciudad con ese id");
	            }
	        }


    	} else {


    		logger.info("Generando id para la nueva ciudad");
    		long newId = 1;
	        for (CiudadDTO ciudad : ciudades) {
	            if (newId <= ciudad.getId()){
	                newId =  ciudad.getId() + 1;
	            }
	        }
	        newCiudad.setId(newId);
    	}


    	logger.info("agregando ciudad " + newCiudad);
        ciudades.add(newCiudad);
        return newCiudad;
    }


    public CiudadDTO updateCiudad(Long id, CiudadDTO updatedciudad) throws CiudadLogicException {
    	logger.info("recibiendo solictud de modificar ciudad " + updatedciudad);


        for (CiudadDTO ciudad : ciudades) {
            if (Objects.equals(ciudad.getId(), id)) {


            	ciudad.setId(updatedciudad.getId());
                ciudad.setNombre(updatedciudad.getNombre());
                ciudad.setDescripcion(updatedciudad.getDescripcion());


            	logger.info("Modificando ciudad " + ciudad);
                return ciudad;
            }
        }


        logger.severe("No existe un ciudad con ese id");
        throw new CiudadLogicException("No existe un ciudad con ese id");
    }


    public void deleteCiudad(Long id) throws CiudadLogicException {
    	logger.info("recibiendo solictud de eliminar ciudad con id " + id);


        for (CiudadDTO ciudad : ciudades) {
            if (Objects.equals(ciudad.getId(), id)) {


            	logger.info("eliminando ciudad " + ciudad);
                ciudades.remove(ciudad);
                return;
            }
        }


        logger.severe("No existe un ciudad con ese id");
        throw new CiudadLogicException("No existe un ciudad con ese id");
    }

}
