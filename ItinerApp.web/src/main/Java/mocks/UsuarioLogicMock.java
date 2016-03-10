package mocks;

/**
 * @author jc.martha10
 */

import dtos.UsuarioDtos;
import exceptions.UsuarioException;
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
public class UsuarioLogicMock {

        private final static Logger logger = Logger.getLogger(UsuarioLogicMock.class.getName());


        private static ArrayList<UsuarioDtos> usuarios;

        /**
     * Constructor. Crea los datos de ejemplo.
     * @throws java.text.ParseException
     */
    public UsuarioLogicMock() throws ParseException {

		long id1 = 1L;
		String nombre1 = "Juan Camilo";
		String apellido1 = "Marthá";
		String username1 = "jc.martha10";
		String email1 = "jc.martha10@uniandes.edu.co";
		String cedula1 = "12345687";

		long id2 = 2L;
		String nombre2 = "Juan José";
		String apellido2 = "Castro";
		String username2 = "jj.castro";
		String email2 = "jj.castro@uniandes.edu.co";
		String cedula2 = "23456789";

		long id3 = 3L;
		String nombre3 = "Santiago";
		String apellido3 = "Robayo";
		String username3 = "s.robayo";
		String email3 = "s.robayo@uniandes.edu.co";
		String cedula3 = "34567809";

		long id4 = 4L;
		String nombre4 = "Felipe";
		String apellido4 = "Martinez";
		String username4 = "f.martinez";
		String email4 = "f.martinez@uniandes.edu.co";
		String cedula4 = "45678012";

		long id5 = 5L;
		String nombre5 = "Juan Pablo";
		String apellido5 = "Otálora";
		String username5 = "jp.otalora";
		String email5 = "jp.otalora@uniandes.edu.co";
		String cedula5 = "78901234";

    	if (usuarios == null) {
            usuarios = new ArrayList<>();
			usuarios.add(new UsuarioDtos(id1, nombre1, apellido1, username1, email1, cedula1));
			usuarios.add(new UsuarioDtos(id2, nombre2, apellido2, username2, email2, cedula2));
			usuarios.add(new UsuarioDtos(id3, nombre3, apellido3, username3, email3, cedula3));
			usuarios.add(new UsuarioDtos(id4, nombre4, apellido4, username4, email4, cedula4));
			usuarios.add(new UsuarioDtos(id5, nombre5, apellido5, username5, email5, cedula5));
		}

    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);

    	// muestra información
    	logger.info("Inicializa la lista de usuarios");
    	logger.info("usuarios" + usuarios );
    }


    /**
	 * Obtiene el listado de usuarios.
	 * @return lista de usuarios
         * @throws exceptions.UsuarioException
	 */
    public List<UsuarioDtos> getUsuarios() throws UsuarioException {
    	if (usuarios == null) {
    		logger.severe("Error interno: lista de usuarios no existe.");
    		throw new UsuarioException("Error interno: lista de usuarios no existe.");
    	}

    	logger.info("retornando todos los usuarios");
    	return usuarios;
    }

      /**
     * Obtiene un usuario
     * @param id identificador de la usuario
     * @return usuario encontrado
     * @throws UsuarioLogicException cuando el usuario no existe
     */
    public UsuarioDtos getUsuario(Long id) throws UsuarioException {
    	logger.info("recibiendo solicitud de usuario con id " + id);

    	// busca el usuario con el id suministrado
        for (UsuarioDtos usuario : usuarios) {
            if (Objects.equals(usuario.getId(), id)){
            	logger.info("retornando usuario " + usuario);
                return usuario;
            }
        }

        // si no encuentra el usuario
        logger.severe("No existe usuario con ese id");
        throw new UsuarioException("No existe usuario con ese id");
    }


    /**
     * Agrega un usuario a la lista.
     * @param newUsuario usuario a adicionar
     * @throws UsuarioLogicException cuando ya existe una usuario con el id suministrado
     * @return usuario agregado
     */
    public UsuarioDtos createUsuario(UsuarioDtos newUsuario) throws UsuarioException {
    	logger.info("recibiendo solicitud de agregar usuario " + newUsuario);

    	// la nueva usuario tiene id ?
    	if ( newUsuario.getId() != null ) {
	    	// busca el usuario con el id suministrado
	        for (UsuarioDtos city : usuarios) {
	        	// si existe una usuario con ese id
	            if (Objects.equals(city.getId(), newUsuario.getId())){
	            	logger.severe("Ya existe un usuario con ese id");
	                throw new UsuarioException("Ya existe un usuario con ese id");
	            }
	        }

	    // la nueva usuario no tiene id ?
    	} else {

    		// genera un id para el usuario
    		logger.info("Generando id paa la nueva usuario");
    		long newId = 1;
	        for (UsuarioDtos usuario : usuarios) {
	            if (newId <= usuario.getId()){
	                newId =  usuario.getId() + 1;
	            }
	        }
	        newUsuario.setId(newId);
    	}

        // agrega el usuario
    	logger.info("agregando usuario " + newUsuario);
        usuarios.add(newUsuario);
        return newUsuario;
    }


     /**
     * Actualiza los datos de una usuario
     * @param id identificador de el usuario a modificar
     * @param city usuario a modificar
     * @return datos de el usuario modificada
     * @throws UsuarioLogicException cuando no existe una usuario con el id suministrado
     */
    public UsuarioDtos updateUsuario(Long id, UsuarioDtos updatedUsuario) throws UsuarioException {
    	logger.info("recibiendo solictud de modificar usuario " + updatedUsuario);

    	// busca el usuario con el id suministrado
        for (UsuarioDtos usuario : usuarios) {
            if (Objects.equals(usuario.getId(), id)) {

            	// modifica el usuario
            	usuario.setId(updatedUsuario.getId());
                usuario.setNombre(updatedUsuario.getNombre());
                usuario.setApellido(updatedUsuario.getApellido());
                usuario.setUsername(updatedUsuario.getUsername());
                usuario.setEmail(updatedUsuario.getEmail());
                usuario.setCedula(updatedUsuario.getCedula());

                // retorna el usuario modificada
            	logger.info("Modificando usuario " + usuario);
                return usuario;
            }
        }

        // no encontró el usuario con ese id ?
        logger.severe("No existe un usuario con ese id");
        throw new UsuarioException("No existe un usuario con ese id");
    }



    /**
     * Elimina los datos de una usuario
     * @param id identificador de el usuario a eliminar
     * @throws UsuarioLogicException cuando no existe una usuario con el id suministrado
     */
    public void deleteUsuario(Long id) throws UsuarioException {
    	logger.info("recibiendo solictud de eliminar usuario con id " + id);

    	// busca el usuario con el id suministrado
        for (UsuarioDtos usuario : usuarios) {
            if (Objects.equals(usuario.getId(), id)) {

            	// elimina el usuario
            	logger.info("eliminando usuario " + usuario);
                usuarios.remove(usuario);
                return;
            }
        }

        // no encontró el usuario con ese id ?
        logger.severe("No existe un usuario con ese id");
        throw new UsuarioException("No existe un usuario con ese id");
    }







}
