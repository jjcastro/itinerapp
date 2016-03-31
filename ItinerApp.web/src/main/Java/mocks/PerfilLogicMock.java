package mocks;

import exceptions.PerfilLogicException;
import dtos.PerfilDtos;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;


@Named
@ApplicationScoped
public class PerfilLogicMock {
	
	
    private final static Logger logger = Logger.getLogger(PerfilLogicMock.class.getName());
	
	
    private static ArrayList<PerfilDtos> recuerdos;

    
    public PerfilLogicMock() {

    	if (recuerdos == null) {
            recuerdos = new ArrayList<>();
            recuerdos.add(new PerfilDtos(1L, "Paris", "Me gustó mucho esta ciudad."
                    + "Paris es una hermosa ciudad, fue increíble la vista "
                    + "desde la torre Eiffel y la arquitectura de Notre Dame."));
            recuerdos.add(new PerfilDtos(2L, "Florida", "Muy divertido!"));
            recuerdos.add(new PerfilDtos(3L, "Rio De Janeiro", "Su gente es muy amable"));
            
        }
        
    	
    	logger.setLevel(Level.INFO);
	
    	logger.info("Inicializa la lista de recuerdos");
    	logger.info("recuerdos" + recuerdos );
    }    
       
    public List<PerfilDtos> getRecuerdos() throws PerfilLogicException {
    	if (recuerdos == null) {
    		logger.severe("Error interno: lista de recuerdos no existe.");
    		throw new PerfilLogicException("Error interno: lista de recuerdos no existe.");    		
    	}
    	
    	logger.info("retornando todas los recuerdos");
    	return recuerdos;
    }

  
    public PerfilDtos getRecuerdo(Long id) throws PerfilLogicException {
    	logger.info("recibiendo solicitud de recuerdo con id " + id);
    	
    	
        for (PerfilDtos recuerdo : recuerdos) {
            if (Objects.equals(recuerdo.getId(), id)){
            	logger.info("retornando recuerdo " + recuerdo);
                return recuerdo;
            }
        }
        
       
        logger.severe("No existe el recuerdo con ese id");
        throw new PerfilLogicException("No existe recuerdo con ese id");
    }

    
    public PerfilDtos createRecuerdo(PerfilDtos newRecuerdo) throws PerfilLogicException {
    	logger.info("recibiendo solicitud de agregar recuerdo " + newRecuerdo);
    	
    
    	if ( newRecuerdo.getId() != null ) {
	    	
	        for (PerfilDtos recuerdo : recuerdos) {
	        	
	            if (Objects.equals(recuerdo.getId(), newRecuerdo.getId())){
	            	logger.severe("Ya existe un recuerdo con ese id");
	                throw new PerfilLogicException("Ya existe un recuerdo con ese id");
	            }
	        }
	        
	  
    	} else {

    		
    		logger.info("Generando id para el nuevo recuerdo");
    		long newId = 1;
	        for (PerfilDtos recuerdo : recuerdos) {
	            if (newId <= recuerdo.getId()){
	                newId =  recuerdo.getId() + 1;
	            }
	        }
	        newRecuerdo.setId(newId);
    	}
    	
        
    	logger.info("agregando recuerdo " + newRecuerdo);
        recuerdos.add(newRecuerdo);
        return newRecuerdo;
    }

    
    public PerfilDtos updateRecuerdo(Long id, PerfilDtos updatedRecuerdo) throws PerfilLogicException {
    	logger.info("recibiendo solictud de modificar recuerdo " + updatedRecuerdo);
    	
    	
        for (PerfilDtos recuerdo : recuerdos) {
            if (Objects.equals(recuerdo.getId(), id)) {
            	
            	
            	recuerdo.setId(updatedRecuerdo.getId());
                recuerdo.setNombre(updatedRecuerdo.getNombre());
                recuerdo.setDescripcion(updatedRecuerdo.getDescripcion());
                
                
            	logger.info("Modificando Recuerdo " + recuerdo);
                return recuerdo;
            }
        }
        
       
        logger.severe("No existe un recuerdo con ese id");
        throw new PerfilLogicException("No existe un recuerdo con ese id");
    }

   
    public void deleteRecuerdo(Long id) throws PerfilLogicException {
    	logger.info("recibiendo solictud de eliminar recuerdo con id " + id);
    	
    	
        for (PerfilDtos recuerdo : recuerdos) {
            if (Objects.equals(recuerdo.getId(), id)) {
            	
            	
            	logger.info("eliminando recuerdo " + recuerdo);
                recuerdos.remove(recuerdo);
                return;
            }
        }

        
        logger.severe("No existe un recuerdo con ese id");
        throw new PerfilLogicException("No existe un recuerdo con ese id");
    }
   
}
