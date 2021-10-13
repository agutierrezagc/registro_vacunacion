package com.krugercorp.ec.vacunacion.controller;

import com.krugercorp.ec.vacunacion.pojos.reporte.ReporteEmpleadosPojo;
import com.krugercorp.ec.vacunacion.services.ReportesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("reporte/")
public class ReportesController {

    @Autowired
    private ReportesService reportesCore;

    @GetMapping("vacunados/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public List<ReporteEmpleadosPojo> vacunados(){
        return reportesCore.vacunados();
    }

    @GetMapping("no_vacunados/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public List<ReporteEmpleadosPojo> noVacunados(){
        return reportesCore.noVacunados();
    }

    @GetMapping("tipovacuna/idTipoVacuna={idTipoVacuna}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public List<ReporteEmpleadosPojo> tipoVacuna(@PathVariable("idTipoVacuna") int idTipoVacuna) {
        System.out.println("idTipo vacuna "+idTipoVacuna);
        return reportesCore.porTipoVacuna(idTipoVacuna);
    }

    @GetMapping("entre_fechas/desde={desde}/hasta={hasta}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public List<ReporteEmpleadosPojo> entreFechas(@PathVariable("desde") Date desde, @PathVariable("hasta") Date hasta) {
        System.out.println("desde  hasta "+desde+" "+hasta);
        return reportesCore.vacunaEntreFechas(desde, hasta);
    }

}
