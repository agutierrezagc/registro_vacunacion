package com.krugercorp.ec.vacunacion.repositorys;

import com.krugercorp.ec.vacunacion.entitys.CoreEmpleadosEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoreEmpleadosRepository extends JpaRepository<CoreEmpleadosEntity, Integer> {
    CoreEmpleadosEntity findByCedulaAndEstado(String cedula, String estado);
}
