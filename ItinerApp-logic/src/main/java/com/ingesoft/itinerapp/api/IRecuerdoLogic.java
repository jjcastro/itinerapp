package com.ingesoft.itinerapp.api;


import java.util.List;

public interface IRecuerdoLogic {
    public List<> getRecuerdos();
    public  getRecuerdo(Long id);
    public  createRecuerdo( dto);
    public  updateRecuerdo( dto);
    public void deleteRecuerdo(Long id);
}
