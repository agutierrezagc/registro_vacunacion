package com.krugercorp.ec.vacunacion.services;

import com.krugercorp.ec.vacunacion.pojos.param.RolPojo;
import com.krugercorp.ec.vacunacion.pojos.param.TipoVacunaPojo;

import java.util.List;

public interface ParametricasService {
    public List<RolPojo> listaRoles();
    public List<TipoVacunaPojo> listaTipoVacuna();

}
