package com.ingesoft.itinerapp.api;


import com.ingesoft.itinerapp.entities.UsuarioEntity;
import java.util.List;

/**
 * @author jc.martha10
 */
public interface IUsuarioLogic {

    public List<UsuarioEntity> getUsuarios();

    public UsuarioEntity getUsuario(Long id);

    public UsuarioEntity createUsuario(UsuarioEntity dto);

    public UsuarioEntity updateUsuario(UsuarioEntity dto);

    public void deleteUsuario(Long id);

    public boolean login(UsuarioEntity entity);
}
