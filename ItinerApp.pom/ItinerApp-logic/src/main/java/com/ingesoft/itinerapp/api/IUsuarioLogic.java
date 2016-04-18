package com.ingesoft.itinerapp.api;

import com.ingesoft.itinerapp.dtos.UsuarioDTO;
import java.util.List;

/**
 * @author jc.martha10
 */
public interface IUsuarioLogic {

    public List<UsuarioDTO> getUsuarios();

    public UsuarioDTO getUsuario(Long id);

    public UsuarioDTO createUsuario(UsuarioDTO dto);

    public UsuarioDTO updateUsuario(UsuarioDTO dto);

    public void deleteUsuario(Long id);
}
