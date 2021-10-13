package com.krugercorp.ec.vacunacion.services;

import com.krugercorp.ec.vacunacion.pojos.reporte.ReporteEmpleadosPojo;

import java.sql.Date;
import java.util.List;

public interface ReportesService {
    public List<ReporteEmpleadosPojo> vacunados();
    public List<ReporteEmpleadosPojo> noVacunados();
    public List<ReporteEmpleadosPojo> porTipoVacuna(int idTipoVacuna);
    public List<ReporteEmpleadosPojo> vacunaEntreFechas(Date desde, Date hasta);
}
