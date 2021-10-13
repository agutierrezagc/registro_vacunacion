package com.krugercorp.ec.vacunacion.controller;

import com.krugercorp.ec.vacunacion.pojos.param.RolPojo;
import com.krugercorp.ec.vacunacion.pojos.param.TipoVacunaPojo;
import com.krugercorp.ec.vacunacion.services.ParametricasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("param/")
public class ParametrosController {

    @Autowired
    private ParametricasService parametricasService;


    @GetMapping("lista_roles/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public List<RolPojo> listaRoles(){
        return parametricasService.listaRoles();
    }

    @GetMapping("lista_tipo_vacunas/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public List<TipoVacunaPojo> listaTipoVacunas(){
        return parametricasService.listaTipoVacuna();
    }
}