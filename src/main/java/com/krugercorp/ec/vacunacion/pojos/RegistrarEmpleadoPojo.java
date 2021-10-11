package com.krugercorp.ec.vacunacion.pojos;

import com.krugercorp.ec.vacunacion.otros.ValidadorStrategy;
import com.krugercorp.ec.vacunacion.utils.Utils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * validacion de pojo con JSR 380
 */
public class RegistrarEmpleadoPojo implements ValidadorStrategy {

    @NotNull(message = "Cedula no puede ser null")
    @Size(min = 10, max = 10, message = "cedula debe tener 10 Digitos")
    private String cedula;

    @NotNull(message = "El Nombre no puede ser null")
    private String nombres;

    @NotNull(message = "El primer apellido no puede ser null")
    private String primerApellido;

    private String segundoApellido;

    private String tercerApellido;

    @Email(message = "El Email debe ser válido")
    private String correoElectronico;

    private int rol;


    public RegistrarEmpleadoPojo() {
    }

    public RegistrarEmpleadoPojo(String cedula, String nombres, String primerApellido, String segundoApellido, String tercerApellido, String correoElectronico) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.tercerApellido = tercerApellido;
        this.correoElectronico = correoElectronico;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getTercerApellido() {
        return tercerApellido;
    }

    public void setTercerApellido(String tercerApellido) {
        this.tercerApellido = tercerApellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    @Override
    public boolean validarCampo(String valor, String regex) {
        return Utils.validaFormato(valor, regex);
    }

    @Override
    public String toString() {
        return "RegistrarEmpleadoPojo{" +
                "cedula='" + cedula + '\'' +
                ", nombres='" + nombres + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", tercerApellido='" + tercerApellido + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                '}';
    }
}
