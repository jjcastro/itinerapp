package com.ingesoft.itinerapp.ejbs;

import com.ingesoft.itinerapp.api.IUsuarioLogic;
import com.ingesoft.itinerapp.entities.UsuarioEntity;
import com.ingesoft.itinerapp.persistence.UsuarioPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UsuarioLogic implements IUsuarioLogic {

    private static final Logger logger = Logger.getLogger(UsuarioLogic.class.getName());

    @Inject private UsuarioPersistence persistence;

    @Override
    public List<UsuarioEntity> getUsuarios() {
        logger.info("Inicia proceso de consultar todos los usuarios");
        List<UsuarioEntity> usuarios = persistence.findAll();
        logger.info("Termina proceso de consultar todos los usuarios");
        return usuarios;
    }

    @Override
    public UsuarioEntity getUsuario(Long id) {
        logger.log(Level.INFO, "Inicia proceso de consultar el usuario con el id dado", id);
        UsuarioEntity usuario = persistence.find(id);
        if (usuario == null) {
            logger.log(Level.SEVERE, "El usuario con id: " +id+ " no existe", id);
            throw new IllegalArgumentException("El usuario solicitado no existe");
        }
        logger.log(Level.INFO, "Termina proceso de consultar usuario con id "+id, id);
        return usuario;
    }

    @Override
    public UsuarioEntity createUsuario(UsuarioEntity entity) {
        logger.info("Inicia proceso de creación de usuario");
        persistence.create(entity);
        logger.info("Termina proceso de creación de usuario");
        return entity;
    }

    @Override
    public UsuarioEntity updateUsuario(UsuarioEntity pEntity) {
        logger.log(Level.INFO, "Inicia proceso de actualizar usuario con id "+pEntity.getId(), pEntity.getId());
        UsuarioEntity newEntity = persistence.update(pEntity);
        logger.log(Level.INFO, "Termina proceso de actualizar usuario con id "+pEntity.getId(), pEntity.getId());
        return newEntity;
    }

    @Override
    public void deleteUsuario(Long id) {
        logger.log(Level.INFO, "Inicia proceso de borrar usuario con id "+id, id);
        persistence.delete(id);
        logger.log(Level.INFO, "Termina proceso de borrar usuario con id "+id, id);
    }

    @Override
    public boolean login(UsuarioEntity entity) {
        logger.log(Level.INFO, "Inicia proceso de autenticar el usuario con username "+entity.getCorreo(), entity.getCorreo());
        UsuarioEntity newEntity = persistence.login(entity);
        logger.log(Level.INFO, "Termina proceso de autenticar usuario con id "+entity.getCorreo(), entity.getCorreo());
        return newEntity != null;
    }
}
