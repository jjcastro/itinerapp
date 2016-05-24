package com.ingesoft.itinerapp.api;


import com.ingesoft.itinerapp.entities.RecuerdoEntity;
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
    
    public List<RecuerdoEntity> getRecuerdos(Long usuarioId);

    public void deleteUsuario(Long id);

    public UsuarioEntity login(UsuarioEntity entity);
    
    public UsuarioEntity addRecuerdo(Long usuarioID, Long recuerdoId);
}
