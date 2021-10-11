package com.krugercorp.ec.vacunacion.impl;

import com.krugercorp.ec.vacunacion.entitys.*;
import com.krugercorp.ec.vacunacion.excepciones.BadRequestException;
import com.krugercorp.ec.vacunacion.pojos.ActualizaEmpleadoPojo;
import com.krugercorp.ec.vacunacion.pojos.RegistrarEmpleadoPojo;
import com.krugercorp.ec.vacunacion.pojos.VacunaEmpleadoPojo;
import com.krugercorp.ec.vacunacion.repositorys.CoreEmpleadosRepository;
import com.krugercorp.ec.vacunacion.repositorys.CoreRolAsignadoRepository;
import com.krugercorp.ec.vacunacion.repositorys.ParRolesRepository;
import com.krugercorp.ec.vacunacion.repositorys.ParTipoVacunaRepository;
import com.krugercorp.ec.vacunacion.services.EmpleadoCoreService;
import com.krugercorp.ec.vacunacion.utils.Constantes;
import com.krugercorp.ec.vacunacion.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmpleadoCoreServiceImpl implements EmpleadoCoreService {

    private CoreEmpleadosRepository empleadosRepository;
    @Autowired
    public void setEmpleadosRepository(CoreEmpleadosRepository empleadosRepository) {
        this.empleadosRepository = empleadosRepository;
    }

    private CoreRolAsignadoRepository rolAsignadoRepository;
    @Autowired
    public void setRolAsignadoRepository(CoreRolAsignadoRepository rolAsignadoRepository) {
        this.rolAsignadoRepository = rolAsignadoRepository;
    }

    private ParRolesRepository parRolesRepository;
    @Autowired
    public void setParRolesRepository(ParRolesRepository parRolesRepository) {
        this.parRolesRepository = parRolesRepository;
    }

    private ParTipoVacunaRepository parTipoVacunaRepository;
    @Autowired
    public void setParTipoVacunaRepository(ParTipoVacunaRepository parTipoVacunaRepository) {
        this.parTipoVacunaRepository = parTipoVacunaRepository;
    }

    /**
     * Registra a un pleado nuevo
     * @param empleadoNuevo pojo con la informacion basica del empleado
     */
    @Override
    public void registroEmpleado(RegistrarEmpleadoPojo empleadoNuevo) throws InvalidKeySpecException, NoSuchAlgorithmException {
        System.out.println("adentro "+empleadoNuevo);
        List<CoreRolAsignadoEntity> listaRoles = new ArrayList<>();
        String mensajeValidacion = verificaCamposMensaje(empleadoNuevo);
        if(mensajeValidacion.length() == 0) {
            CoreEmpleadosEntity empleado = castEmpleadoPojo(empleadoNuevo);
            //Crear nombre de usuaio
            String usuario = Utils.generaUsuario(empleadoNuevo.getNombres(),empleadoNuevo.getCedula());
            //Generar credenciales
            CoreCredencialesEntity credenciales = new CoreCredencialesEntity(usuario, Utils.generateStorngPasswordHash(empleadoNuevo.getCedula()), "I", empleado);
            // buscar rol
            ParRolesEntity rol = parRolesRepository.getById(empleadoNuevo.getRol());
            // asigna rol
            CoreRolAsignadoEntity rolAsignado = new CoreRolAsignadoEntity("A", empleado, rol);
            listaRoles.add(rolAsignado);

            empleado.setCoreRolAsignadosById(listaRoles);
            empleado.setCoreCredencialesById(credenciales);
            empleado.setFechaRegistro(new Timestamp(System.currentTimeMillis()));

            persistirEmpleado(empleado);
        }else {
            throw new BadRequestException(mensajeValidacion);
        }
    }

    @Override
    public void actualizaEmpleado(ActualizaEmpleadoPojo actualizaEmpleado){
        CoreEmpleadosEntity empleado = empleadosRepository.findByCedulaAndEstado(actualizaEmpleado.getCedula(),"A");
        if (empleado != null){
            //completa datos de empleado
            castActualizacionEmpleado(actualizaEmpleado, empleado);
            //añade dosis aplicadas
            empleado.setCoreVacunacionsById(adicionaDosis(actualizaEmpleado, empleado));
            // persistir datos
            persistirEmpleado(empleado);
        }else{
            throw new BadRequestException("Empleado no encontrado");
        }

    }

    //**======================FUNCIONES APOYO========================================**//

    /**
     * funcion para persistencia de empleado
     * @param empleado entidad empleado a persistir
     */
    private void persistirEmpleado(CoreEmpleadosEntity empleado){
        try{
            empleadosRepository.save(empleado);
        }catch (Exception e){
            e.printStackTrace();
            throw new BadRequestException("Error, registro no almacenado");
        }
    }

    /**
     * funcion de cast para empleado nuevo
     * @param empleadoNuevo datos de empleado a registrar
     * @return entity con datos basicos
     */
    private CoreEmpleadosEntity castEmpleadoPojo(RegistrarEmpleadoPojo empleadoNuevo){
        return new CoreEmpleadosEntity(
                empleadoNuevo.getCedula(),
                empleadoNuevo.getNombres(),
                empleadoNuevo.getPrimerApellido(),
                empleadoNuevo.getSegundoApellido(),
                empleadoNuevo.getTercerApellido(),
                empleadoNuevo.getCorreoElectronico(),
                "A");
    }

    /**
     * funcion de cast para actualizacion de datos de empleado
     * @param actualizaEmpleado datos actualizados de empleado a registrar
     */
    private void castActualizacionEmpleado(ActualizaEmpleadoPojo actualizaEmpleado, CoreEmpleadosEntity empleado){
        empleado.setDomicilio(actualizaEmpleado.getDomicilio());
        empleado.setTelefonoMovil(actualizaEmpleado.getTelefonoMovil());
        empleado.setFechaNacimiento(actualizaEmpleado.getFechaNacimiento());
    }

    /**
     * funcion para adicionar dosis vacuna de empleado
     * @param actualizaEmpleado datos actualizados de empleado a registrar
     */
    private List<CoreVacunacionEntity> adicionaDosis(ActualizaEmpleadoPojo actualizaEmpleado, CoreEmpleadosEntity empleado){
        List<CoreVacunacionEntity> listaDosisAplicadasVacuna = new ArrayList<CoreVacunacionEntity>();
        for(VacunaEmpleadoPojo dosis : actualizaEmpleado.getDosisVacuna()){
            ParTipoVacunaEntity tipoVacuna = parTipoVacunaRepository.getById(dosis.getIdTipoVacuna());
            listaDosisAplicadasVacuna.add(new CoreVacunacionEntity("A", dosis.getDosis(), dosis.getFechaDosis(),empleado,tipoVacuna));
        }
        return listaDosisAplicadasVacuna;
    }

    /**
     * verifica si la informacion cumple con la validacion
     * @param empleadoNuevo informacion del empleado a registrar
     * @return mensaje de verificacion
     */
    private String verificaCamposMensaje(RegistrarEmpleadoPojo empleadoNuevo){
        String mensaje = "";
        boolean resp = empleadoNuevo.validarCampo(empleadoNuevo.getCorreoElectronico(), Constantes.CttePatronEmail);
        mensaje += !resp? "Formato incorrecto de Email \\n":"";

        mensaje += empleadoNuevo.getCedula().length() !=10 ? "Cédula debe tener 10 dígitos\\n":"";

        resp = empleadoNuevo.validarCampo(empleadoNuevo.getCedula(), Constantes.CtteRegexSoloNumeros);
        mensaje += !resp? "Cédula contiene caracteres distintos a números \\n":"";

        resp = empleadoNuevo.validarCampo(empleadoNuevo.getNombres(), Constantes.CtteRegexSoloLetrasYespacios);
        mensaje += !resp? "Nombre debe tener solo letras \\n":"";

        resp = empleadoNuevo.validarCampo(empleadoNuevo.getPrimerApellido(), Constantes.CtteRegexSoloLetrasYespacios);
        mensaje += !resp? "Primer Apellido debe tener solo letras \\n":"";

        if (empleadoNuevo.getSegundoApellido() != null) {
            resp = empleadoNuevo.validarCampo(empleadoNuevo.getSegundoApellido(), Constantes.CtteRegexSoloLetrasYespacios);
            mensaje += !resp? "Segundo Apellido debe tener solo letras \\n":"";
        }

        if (empleadoNuevo.getTercerApellido() != null){
            resp = empleadoNuevo.validarCampo(empleadoNuevo.getTercerApellido(), Constantes.CtteRegexSoloLetrasYespacios);
            mensaje += !resp? "Tercer Apellido debe tener solo letras \\n":"";
        }
        return mensaje;
    }
}
