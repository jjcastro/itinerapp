package com.ingesoft.itinerapp.ejbs;

import com.ingesoft.itinerapp.api.IUsuarioLogic;

import com.ingesoft.itinerapp.entities.UsuarioEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author jjcastro
 */
@Stateless
public class UsuarioLogic implements IUsuarioLogic {

    public List<UsuarioEntity> getUsuarios() {
        // TODO
      return null;
    }

    public UsuarioEntity getUsuario(Long id) {
        // TODO
      return null;
    }

    public UsuarioEntity createUsuario(UsuarioEntity dto) {
        // TODO
      return null;
    }

    public UsuarioEntity updateUsuario(UsuarioEntity dto) {
      // TODO
      return null;
    }

    public void deleteUsuario(Long id) {
      // TODO
    }
}
