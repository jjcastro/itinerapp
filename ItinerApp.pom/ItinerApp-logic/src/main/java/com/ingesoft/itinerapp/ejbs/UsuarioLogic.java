package com.ingesoft.itinerapp.ejbs;

import com.ingesoft.itinerapp.api.IUsuarioLogic;
import com.ingesoft.itinerapp.dtos.UsuarioDTO;
import com.ingesoft.itinerapp.entities.UsuarioEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author jjcastro
 */
@Stateless
public class UsuarioLogic implements IUsuarioLogic {

    public List<UsuarioDTO> getUsuarios() {
        // TODO
      return null;
    }

    public UsuarioDTO getUsuario(Long id) {
        // TODO
      return null;
    }

    public UsuarioDTO createUsuario(UsuarioDTO dto) {
        // TODO
      return null;
    }

    public UsuarioDTO updateUsuario(UsuarioDTO dto) {
      // TODO
      return null;
    }

    public void deleteUsuario(Long id) {
      // TODO
    }
}
