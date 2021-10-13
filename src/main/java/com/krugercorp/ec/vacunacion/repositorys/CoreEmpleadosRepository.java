package com.krugercorp.ec.vacunacion.repositorys;

import com.krugercorp.ec.vacunacion.entitys.CoreEmpleadosEntity;
import com.krugercorp.ec.vacunacion.entitys.CoreVacunacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.sql.Date;

import java.util.List;

public interface CoreEmpleadosRepository extends JpaRepository<CoreEmpleadosEntity, Integer> {

    CoreEmpleadosEntity findByCedulaAndEstado(String cedula, String estado);

    CoreEmpleadosEntity findByCedula(String cedula);

    @Query("select distinct e from CoreEmpleadosEntity e left join CoreVacunacionEntity v on e.id = v.coreEmpleadosByIdEmpleado.id where v.coreEmpleadosByIdEmpleado is not null")
    List<CoreEmpleadosEntity> findAllVacunados();

    @Query("select e from CoreEmpleadosEntity e left join CoreVacunacionEntity v on e.id = v.coreEmpleadosByIdEmpleado.id where v.coreEmpleadosByIdEmpleado is null")
    List<CoreEmpleadosEntity> findAllNoVacunados();

    @Query("select distinct e from CoreEmpleadosEntity e join CoreVacunacionEntity v on e.id = v.coreEmpleadosByIdEmpleado.id where v.parTipoVacunaByIdTipoVacuna.id = :idTipoVacuna")
    List<CoreEmpleadosEntity> findAllByTipoVacuna(@Param("idTipoVacuna") int idTipoVacuna);

    @Query("select distinct e from CoreEmpleadosEntity e join CoreVacunacionEntity v on e.id = v.coreEmpleadosByIdEmpleado.id where v.fechaDosis between :fechaDesde and :fechaHasta")
    List<CoreEmpleadosEntity> findAllByVacunadosEntre(@Param("fechaDesde") Date fechaDesde,@Param("fechaHasta") Date fechaHasta);

    List<CoreEmpleadosEntity> findAllByCoreVacunacionsById(CoreVacunacionEntity coreVacunacionsById);


}
