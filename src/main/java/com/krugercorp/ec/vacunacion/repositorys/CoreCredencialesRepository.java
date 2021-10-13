package com.krugercorp.ec.vacunacion.repositorys;

import com.krugercorp.ec.vacunacion.entitys.CoreCredencialesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoreCredencialesRepository extends JpaRepository<CoreCredencialesEntity, Integer> {

    CoreCredencialesEntity findByUsuarioAndClave(String usuario, String clave);
    CoreCredencialesEntity findByUsuario(String usuario);

}
