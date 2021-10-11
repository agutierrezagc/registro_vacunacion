package com.krugercorp.ec.vacunacion.services;

import com.krugercorp.ec.vacunacion.pojos.ActualizaEmpleadoPojo;
import com.krugercorp.ec.vacunacion.pojos.RegistrarEmpleadoPojo;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface EmpleadoCoreService {
    public void registroEmpleado(RegistrarEmpleadoPojo empleadoNuevo) throws InvalidKeySpecException, NoSuchAlgorithmException;
    public void actualizaEmpleado(ActualizaEmpleadoPojo actualizaEmpleado);
    //visualizar
    //actualizar

}
