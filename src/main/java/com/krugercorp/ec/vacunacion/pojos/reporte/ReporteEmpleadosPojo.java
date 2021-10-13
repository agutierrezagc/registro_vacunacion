package com.krugercorp.ec.vacunacion.pojos.reporte;

import com.krugercorp.ec.vacunacion.entitys.CoreCredencialesEntity;
import com.krugercorp.ec.vacunacion.entitys.CoreRolAsignadoEntity;
import com.krugercorp.ec.vacunacion.entitys.CoreVacunacionEntity;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class ReporteEmpleadosPojo {
    private String cedula;
    private String nombres;
    private String primerApellido;
    private String segundoApellido;
    private String tercerApellido;
    private String correoElectronico;
    private String domicilio;
    private String telefonoMovil;
    private String tipoVacuna;
    private List<InfoVacunacionPojo> vacunacionInfo;

    public ReporteEmpleadosPojo() {
    }

    public ReporteEmpleadosPojo(String cedula, String nombres, String primerApellido, String segundoApellido, String tercerApellido, String correoElectronico, String domicilio, String telefonoMovil, String tipoVacuna, List<InfoVacunacionPojo> vacunacionInfo) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.tercerApellido = tercerApellido;
        this.correoElectronico = correoElectronico;
        this.domicilio = domicilio;
        this.telefonoMovil = telefonoMovil;
        this.tipoVacuna = tipoVacuna;
        this.vacunacionInfo = vacunacionInfo;
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

    public String getTipoVacuna() {
        return tipoVacuna;
    }

    public void setTipoVacuna(String tipoVacuna) {
        this.tipoVacuna = tipoVacuna;
    }

    public List<InfoVacunacionPojo> getVacunacionInfo() {
        return vacunacionInfo;
    }

    public void setVacunacionInfo(List<InfoVacunacionPojo> vacunacionInfo) {
        this.vacunacionInfo = vacunacionInfo;
    }
}
