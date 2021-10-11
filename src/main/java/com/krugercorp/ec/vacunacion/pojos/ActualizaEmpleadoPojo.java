package com.krugercorp.ec.vacunacion.pojos;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class ActualizaEmpleadoPojo {
    private String cedula;
    private String nombres;
    private String primerApellido;
    private String segundoApellido;
    private String tercerApellido;
    private String correoElectronico;
    private String domicilio;
    private String telefonoMovil;
    private Date fechaNacimiento;
    private List<VacunaEmpleadoPojo> dosisVacuna;

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

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<VacunaEmpleadoPojo> getDosisVacuna() {
        return dosisVacuna;
    }

    public void setDosisVacuna(List<VacunaEmpleadoPojo> dosisVacuna) {
        this.dosisVacuna = dosisVacuna;
    }
}
