package com.ingesoft.itinerapp.api;



import com.ingesoft.itinerapp.dtos.RecuerdoDTO;
import java.util.List;

public interface IRecuerdoLogic {
    public List<RecuerdoDTO> getRecuerdos();
    public RecuerdoDTO getRecuerdo(Long id);
    public RecuerdoDTO createRecuerdo(RecuerdoDTO dto);
    public RecuerdoDTO updateRecuerdo(RecuerdoDTO dto);
    public void deleteRecuerdo(Long id);
}
