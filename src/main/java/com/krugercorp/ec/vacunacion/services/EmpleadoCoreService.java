package com.krugercorp.ec.vacunacion.services;

import com.krugercorp.ec.vacunacion.pojos.ActualizaEmpleadoPojo;
import com.krugercorp.ec.vacunacion.pojos.RegistrarEmpleadoPojo;
import com.krugercorp.ec.vacunacion.pojos.login.CredencialesPojo;
import com.krugercorp.ec.vacunacion.pojos.login.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface EmpleadoCoreService {
    public void registroEmpleado(RegistrarEmpleadoPojo empleadoNuevo) throws InvalidKeySpecException, NoSuchAlgorithmException;
    public void actualizaEmpleado(ActualizaEmpleadoPojo actualizaEmpleado);
    public void eliminaEmpleado(String cedula);
    public ActualizaEmpleadoPojo consultarEmpleado(String cedula);
    public User credencialIngreso(CredencialesPojo credenciales);

}
