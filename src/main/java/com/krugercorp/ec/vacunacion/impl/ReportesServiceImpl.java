package com.krugercorp.ec.vacunacion.impl;

import com.krugercorp.ec.vacunacion.entitys.CoreEmpleadosEntity;
import com.krugercorp.ec.vacunacion.entitys.CoreVacunacionEntity;
import com.krugercorp.ec.vacunacion.pojos.reporte.InfoVacunacionPojo;
import com.krugercorp.ec.vacunacion.pojos.reporte.ReporteEmpleadosPojo;
import com.krugercorp.ec.vacunacion.repositorys.CoreEmpleadosRepository;
import com.krugercorp.ec.vacunacion.services.ReportesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReportesServiceImpl implements ReportesService {

    private CoreEmpleadosRepository empleadosRepository;
    @Autowired
    public void setEmpleadosRepository(CoreEmpleadosRepository empleadosRepository) {
        this.empleadosRepository = empleadosRepository;
    }

    /**
     * Reporte de empleados Vacunados
     * @return lista de empleados vacundos
     */
    @Override
    public List<ReporteEmpleadosPojo> vacunados(){
        return castReportes(empleadosRepository.findAllVacunados());
    }

    /**
     * Reporte empleados No vacunados
     * @return lista de empleados No vacunados
     */
    @Override
    public List<ReporteEmpleadosPojo> noVacunados(){
        return castReportes(empleadosRepository.findAllNoVacunados());
    }

    /**
     * lista de empleados vacunados por Tipo de Vacuna
     * @param idTipoVacuna id tipo vacuna a filtrar
     * @return lista de empleados vacunados con un tipo de vacuna especifico
     */
    @Override
    public List<ReporteEmpleadosPojo> porTipoVacuna(int idTipoVacuna){
        return castReportes(empleadosRepository.findAllByTipoVacuna(idTipoVacuna));
    }

    /**
     * Reporte de empleados que hayan sido vacunados en un rango de fechas
     * @param desde fecha desde donde filtrar
     * @param hasta fechas hasta donde filtrar
     * @return lista de empleados vacunados en un rango de fechas
     */
    @Override
    public List<ReporteEmpleadosPojo> vacunaEntreFechas(Date desde, Date hasta){
        return castReportes(empleadosRepository.findAllByVacunadosEntre(desde, hasta));
    }



    /**
     * Cast para Reportes pojo
     * @param empleados lista de empleados en entity
     * @return lista casteada en pojo
     */
    private List<ReporteEmpleadosPojo> castReportes(List<CoreEmpleadosEntity> empleados){
        List<ReporteEmpleadosPojo> listaEmpleados = new ArrayList<>();
        for(CoreEmpleadosEntity empleado :empleados){
            listaEmpleados.add(new ReporteEmpleadosPojo(empleado.getCedula(),
                                                        empleado.getNombres(),
                                                        empleado.getPrimerApellido(),
                                                        empleado.getSegundoApellido(),
                                                        empleado.getTercerApellido(),
                                                        empleado.getCorreoElectronico(),
                                                        empleado.getDomicilio(),
                                                        empleado.getTelefonoMovil(),
                                                        empleado.getCoreVacunacionsById().size()>0? empleado.getCoreVacunacionsById().get(0).getParTipoVacunaByIdTipoVacuna().getNombreVacuna(): null,
                                                        infoDosisAplicada(empleado.getCoreVacunacionsById())
                                                        ));
        }
        return listaEmpleados;
    }

    /**
     * Informacion de dosis aplicada anidado
     * @param dosisAplicadas numero de dosis que tiene el empleado
     * @return lista con informacion
     */
    private List<InfoVacunacionPojo> infoDosisAplicada(List<CoreVacunacionEntity> dosisAplicadas){
        List<InfoVacunacionPojo> listaDosis = new ArrayList<InfoVacunacionPojo>();
        for(CoreVacunacionEntity dosis: dosisAplicadas){
            listaDosis.add(new InfoVacunacionPojo(dosis.getParTipoVacunaByIdTipoVacuna().getNombreVacuna(), dosis.getFechaDosis(), dosis.getDosis()));
        }
        return listaDosis;
    }
}
