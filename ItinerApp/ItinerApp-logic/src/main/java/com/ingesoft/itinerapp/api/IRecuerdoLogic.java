package com.ingesoft.itinerapp.api;




import com.ingesoft.itinerapp.entities.RecuerdoEntity;
import java.util.List;

public interface IRecuerdoLogic {
    public List<RecuerdoEntity> getRecuerdosUsuario(Long idUsuario);
    public List<RecuerdoEntity> getRecuerdos();
    public RecuerdoEntity getRecuerdo(Long id);
    public RecuerdoEntity createRecuerdo(RecuerdoEntity dto);
    public RecuerdoEntity updateRecuerdo(RecuerdoEntity dto);
    public void deleteRecuerdo(Long id);
}
