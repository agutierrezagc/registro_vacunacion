package com.krugercorp.ec.vacunacion.impl;

import com.google.gson.Gson;
import com.krugercorp.ec.vacunacion.entitys.*;
import com.krugercorp.ec.vacunacion.excepciones.BadRequestException;
import com.krugercorp.ec.vacunacion.excepciones.UnathorizedException;
import com.krugercorp.ec.vacunacion.pojos.ActualizaEmpleadoPojo;
import com.krugercorp.ec.vacunacion.pojos.RegistrarEmpleadoPojo;
import com.krugercorp.ec.vacunacion.pojos.VacunaEmpleadoPojo;
import com.krugercorp.ec.vacunacion.pojos.login.CredencialesPojo;
import com.krugercorp.ec.vacunacion.pojos.login.PayLoadPojo;
import com.krugercorp.ec.vacunacion.pojos.login.User;
import com.krugercorp.ec.vacunacion.repositorys.*;
import com.krugercorp.ec.vacunacion.services.EmpleadoCoreService;
import com.krugercorp.ec.vacunacion.utils.Constantes;
import com.krugercorp.ec.vacunacion.utils.Utils;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmpleadoCoreServiceImpl implements EmpleadoCoreService {

    private CoreEmpleadosRepository empleadosRepository;
    private CoreCredencialesRepository credencialesRepository;
    private ParRolesRepository parRolesRepository;
    private ParTipoVacunaRepository parTipoVacunaRepository;

    @Autowired
    public void setEmpleadosRepository(CoreEmpleadosRepository empleadosRepository) {
        this.empleadosRepository = empleadosRepository;
    }

    @Autowired
    public void setCredencialesRepository(CoreCredencialesRepository credencialesRepository) {
        this.credencialesRepository = credencialesRepository;
    }

    @Autowired
    public void setParRolesRepository(ParRolesRepository parRolesRepository) {
        this.parRolesRepository = parRolesRepository;
    }

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
            //Crear nombre de usuario
            String usuario = Utils.generaUsuario(empleadoNuevo.getNombres(),empleadoNuevo.getCedula());
            //Generar credenciales
            CoreCredencialesEntity credenciales = new CoreCredencialesEntity(usuario, Utils.generateStorngPasswordHash(empleadoNuevo.getCedula()), "I", empleado);
            //Asignar rol
            empleado.setCoreRolAsignadosById(asignarRol(empleadoNuevo.getRol(),empleado));

            empleado.setCoreCredencialesById(credenciales);
            empleado.setFechaRegistro(new Timestamp(System.currentTimeMillis()));
            //almacenar en BD
            persistirEmpleado(empleado);
        }else {
            throw new BadRequestException(mensajeValidacion);
        }
    }

    /**
     * Actualiza datos de empleado
     * @param actualizaEmpleado datos a actualizar
     */
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

    /**
     * Elimina un empleado por número de cedula
     * @param cedula número de cedula de 10 digitos(0000111222)
     */
    @Override
    public void eliminaEmpleado(String cedula) {
        //verifica formato cedula
        verificaCedula(cedula);

        CoreEmpleadosEntity empleadoEliminar = empleadosRepository.findByCedula(cedula);
        empleadosRepository.delete(empleadoEliminar);
    }

    /**
     * Obtener los datos de un empleado por cedula
     * @param cedula número de cedula de 10 digitos(0000111222)
     * @return estructura pojo con los datos del usuario
     */
    @Override
    public ActualizaEmpleadoPojo consultarEmpleado(String cedula) {
        //verifica formato cedula
        verificaCedula(cedula);

        CoreEmpleadosEntity empleadoConsultado = empleadosRepository.findByCedula(cedula);
        return castEmpleadoPojo(empleadoConsultado);
    }

    /**
     * Login para usuario
     * @param credenciales  credenciales de ingreso
     * @return      retorna el estado y token
     */
    @Override
    public User credencialIngreso(CredencialesPojo credenciales) {
        //buscar si usuario esta en la tabla credenciales
        CoreCredencialesEntity storageCred = credencialesRepository.findByUsuario(credenciales.getUsuario());
        //extraer contraseña hash
        if(storageCred != null) {
            try {
                if (Utils.validatePassword(credenciales.getClave(), storageCred.getClave())) {
                    //obtener token
                    User us = new User(getJWTToken(storageCred), storageCred.getEstado().equals("I") ? true : false);
                    return us;
//            return new User(getJWTToken(storageCred),storageCred.getEstado().equals("I")? true:false);
                } else {
                    System.out.println("Else - contraseña incorrecta");
                    throw new UnathorizedException("Contraseña incorecta");
                }
            } catch (NoSuchAlgorithmException e) {
                throw new BadRequestException("Error en el algoritmo");
            } catch (InvalidKeySpecException e) {
                throw new UnathorizedException("Contraseña invalida");
            }
        }else
            throw new UnathorizedException("Usuario invalido");
    }

    //**======================FUNCIONES APOYO========================================**//

    /**
     * verifica que una cedula cumpla lo requerido
     * @param cedula número de cedula de 10 digitos(0000111222)
     */
    private void verificaCedula(String cedula){
        RegistrarEmpleadoPojo empleado = new RegistrarEmpleadoPojo();
        boolean cumpleCedula=cedula.length() !=10?false:true;
        if(cumpleCedula) {
            cumpleCedula = empleado.validarCampo(cedula, Constantes.CtteRegexSoloNumeros);
            if(!cumpleCedula)
                throw new BadRequestException("Cedula no cumple (10 Dígitos - solo numeros)");
        }else
            throw new BadRequestException("Cedula no cumple (10 Dígitos - solo numeros)");
    }

    /**
     * funcion para persistencia de empleado
     * @param empleado entidad empleado a persistir
     */
    private void persistirEmpleado(CoreEmpleadosEntity empleado){
        try{
            empleadosRepository.save(empleado);
        }catch (Exception e){
            throw new BadRequestException("Error, registro no almacenado, posible duplicidad de CI");
        }
    }

    /**
     * Funcion para asignar rol al empleado
     * @param idRol id de rol a aasignar
     * @param empleado entidad empleado para persistencia
     * @return lista de rol
     */
    private List<CoreRolAsignadoEntity> asignarRol(int idRol, CoreEmpleadosEntity empleado){
        List<CoreRolAsignadoEntity> listaRoles = new ArrayList<>();
        // buscar rol
        ParRolesEntity rol = parRolesRepository.getById(idRol);
        // asigna rol
        CoreRolAsignadoEntity rolAsignado = new CoreRolAsignadoEntity("A", empleado, rol);
        listaRoles.add(rolAsignado);
        return listaRoles;
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
     * funcion de cast para empleado pojo
     * @param empleadoEntity datos de empleado a mostrar
     * @return entity con datos basicos
     */
    private ActualizaEmpleadoPojo castEmpleadoPojo(CoreEmpleadosEntity empleadoEntity){
        return new ActualizaEmpleadoPojo(
                empleadoEntity.getCedula(),
                empleadoEntity.getNombres(),
                empleadoEntity.getPrimerApellido(),
                empleadoEntity.getSegundoApellido(),
                empleadoEntity.getTercerApellido(),
                empleadoEntity.getCorreoElectronico(),
                empleadoEntity.getDomicilio(),
                empleadoEntity.getTelefonoMovil(),
                empleadoEntity.getFechaNacimiento(),
                castVacunacion(empleadoEntity.getCoreVacunacionsById()));
    }

    /**
     * Casteo de valores de la informacion de dosis aplicadas a un empleado
     * @param vacunacionesEntity entidad dosis aplicadas
     * @return lista de dosis aplicadas a un empleado
     */
    private List<VacunaEmpleadoPojo> castVacunacion(List<CoreVacunacionEntity> vacunacionesEntity){
        List<VacunaEmpleadoPojo> listaVacunas = new ArrayList<VacunaEmpleadoPojo>();
        for(CoreVacunacionEntity vacuna : vacunacionesEntity){
            listaVacunas.add(new VacunaEmpleadoPojo(vacuna.getId(),vacuna.getDosis(),vacuna.getFechaDosis()));
        }
        return listaVacunas;
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
            listaDosisAplicadasVacuna.add(new CoreVacunacionEntity("A", dosis.getDosis(), dosis.getFechaDosis(),empleado,tipoVacuna,new Timestamp(System.currentTimeMillis())));
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

    /**
     * Funcion para generar el token
     * @param credenciales credenciales ingresados
     * @return      token generado
     */
    private String getJWTToken(CoreCredencialesEntity credenciales) {
        Gson gson = new Gson();
        //obtener roles de usuario
        List<String> roles = convertirListaRoles(rolDeUsuario(credenciales.getUsuario()));
        String[] rolesUsuario = roles.toArray(new String[0]);

        //TODO: REALIZA LA EXPIRACION EN 2 SEMANAS  -> 1209600000 /// 2 dias -> 172800000  /// 1 minuto -> 60000
        PayLoadPojo carga = new PayLoadPojo(credenciales.getCoreEmpleadosByIdEmpleado().getNombres()+" "+
                credenciales.getCoreEmpleadosByIdEmpleado().getPrimerApellido() ,
                rolesUsuario ,credenciales.getCoreEmpleadosByIdEmpleado().getCorreoElectronico(),
                System.currentTimeMillis() + 1209600000 );
        //payload para el token
        String payload = gson.toJson(carga);
//        System.out.println("Carga json "+payload );
        String token = Jwts
                .builder()
                .setPayload(payload)
                .signWith(SignatureAlgorithm.HS512,Constantes.CtteSecretJwt.getBytes())
                .compact();

        return "Bearer " + token;
    }

    /**
     * cast para roles a List String
     * @param roles roles del empleado
     * @return list<string> de roles
     */
    private List<String> convertirListaRoles(List<CoreRolAsignadoEntity> roles){
        List<String> respuestRol = new ArrayList<>();
        for(CoreRolAsignadoEntity rol : roles){
            respuestRol.add(rol.getParRolesByIdRol().getNombreRol());
        }
        return respuestRol;
    }

    /**
     *Obtiene listado de roles del usuario
     * @param usuario entra el correo
     * @return lista de roles de un usuario
     */
    private List<CoreRolAsignadoEntity> rolDeUsuario(String usuario){
        CoreCredencialesEntity credencial = credencialesRepository.findByUsuario(usuario);
        return credencial.getCoreEmpleadosByIdEmpleado().getCoreRolAsignadosById();
    }
}