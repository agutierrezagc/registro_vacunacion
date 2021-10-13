package com.krugercorp.ec.vacunacion.impl;

import com.krugercorp.ec.vacunacion.entitys.ParRolesEntity;
import com.krugercorp.ec.vacunacion.entitys.ParTipoVacunaEntity;
import com.krugercorp.ec.vacunacion.pojos.param.RolPojo;
import com.krugercorp.ec.vacunacion.pojos.param.TipoVacunaPojo;
import com.krugercorp.ec.vacunacion.repositorys.ParRolesRepository;
import com.krugercorp.ec.vacunacion.repositorys.ParTipoVacunaRepository;
import com.krugercorp.ec.vacunacion.services.ParametricasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParametricasServiceImpl implements ParametricasService {
    private ParTipoVacunaRepository tipoVacunaRepository;
    @Autowired
    public void setTipoVacunaRepository(ParTipoVacunaRepository tipoVacunaRepository) {
        this.tipoVacunaRepository = tipoVacunaRepository;
    }

    private ParRolesRepository rolesRepository;
    @Autowired
    public void setRolesRepository(ParRolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    /**
     * lista parametrica de roles
     * @return Retorna la lista parametrica de roles
     */
    @Override
    public List<RolPojo> listaRoles() {
        return castRol(rolesRepository.findAll());
    }

    /**
     * Lista de tipo vacunas
     * @return Retorna la lista parametrica de tipo vacunas
     */
    @Override
    public List<TipoVacunaPojo> listaTipoVacuna() {
        return castTipoVacunas(tipoVacunaRepository.findAll());
    }

    /**
     * cast roles
     * @param parRoles lista entidad de roles
     * @return roles para presentacion
     */
    private List<RolPojo>castRol(List<ParRolesEntity> parRoles){
        List<RolPojo> listaRoles = new ArrayList<RolPojo>();
        for(ParRolesEntity rol: parRoles){
            listaRoles.add(new RolPojo(rol.getId(),rol.getNombreRol(),rol.getDescripcion(),rol.getEstado()));
        }
        return listaRoles;
    }

    /**
     * cast para tipo vacunas
     * @param parTipoVacunas lista entidad tipos vacunas
     * @return cast para tipos vacunas
     */
    private List<TipoVacunaPojo> castTipoVacunas(List<ParTipoVacunaEntity> parTipoVacunas){
        List<TipoVacunaPojo> listaTipoVacunas = new ArrayList<TipoVacunaPojo>();
        for(ParTipoVacunaEntity tipoVacuna: parTipoVacunas){
            listaTipoVacunas.add(new TipoVacunaPojo(tipoVacuna.getId(),tipoVacuna.getNombreVacuna(),tipoVacuna.getCantidadDosis(),tipoVacuna.getIntervaloDosis(),tipoVacuna.getEstado()));
        }
        return listaTipoVacunas;
    }
}
