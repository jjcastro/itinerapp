package com.ingesoft.itinerapp.ejbs;

import com.ingesoft.itinerapp.api.ICiudadLogic;
import com.ingesoft.itinerapp.dtos.CiudadDTO;
import com.ingesoft.itinerapp.entities.CiudadEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * @author jjcastro
 */
@Stateless
public class CiudadLogic implements ICiudadLogic {

    public List<CiudadDTO> getCiudads() {
        // TODO
      return null;
    }

    public CiudadDTO getCiudad(Long id) {
        // TODO
      return null;
    }

    public CiudadDTO createCiudad(CiudadDTO dto) {
        // TODO
      return null;
    }

    public CiudadDTO updateCiudad(CiudadDTO dto) {
      // TODO
      return null;
    }

    public void deleteCiudad(Long id) {
      // TODO
    }
}
