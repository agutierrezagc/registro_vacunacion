package com.krugercorp.ec.vacunacion.controller;

import com.krugercorp.ec.vacunacion.pojos.ActualizaEmpleadoPojo;
import com.krugercorp.ec.vacunacion.pojos.RegistrarEmpleadoPojo;
import com.krugercorp.ec.vacunacion.pojos.login.CredencialesPojo;
import com.krugercorp.ec.vacunacion.pojos.login.User;
import com.krugercorp.ec.vacunacion.services.EmpleadoCoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("empleado/")
public class EmpleadoController {

    @Autowired
    private EmpleadoCoreService empleadoCore;

    @PostMapping("registro/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public void registraEmpleado(@RequestBody RegistrarEmpleadoPojo empleadoNuevo) throws InvalidKeySpecException, NoSuchAlgorithmException {
        empleadoCore.registroEmpleado(empleadoNuevo);
    }

    @GetMapping("consulta/cedula={cedula}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ActualizaEmpleadoPojo consultaEmpleado(@PathVariable("cedula") String cedula){
        return empleadoCore.consultarEmpleado(cedula);
    }

    @PutMapping("actualizar/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public void actualizaEmpleado(@RequestBody ActualizaEmpleadoPojo actualizaEmpleado){
        empleadoCore.actualizaEmpleado(actualizaEmpleado);
    }

    @DeleteMapping("eliminar/cedula={cedula}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public void eliminaEmpleado(@PathVariable("cedula") String cedula){
        empleadoCore.eliminaEmpleado(cedula);
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public User login(@RequestBody CredencialesPojo credenciales) {
        return empleadoCore.credencialIngreso(credenciales);
    }
}
