package com.krugercorp.ec.vacunacion.pojos.reporte;

import java.sql.Date;

public class InfoVacunacionPojo {

    private String tipoVacuna;
    private Date fechaVacunacion;
    private int dosis;

    public InfoVacunacionPojo() {
    }

    public InfoVacunacionPojo(String tipoVacuna, Date fechaVacunacion, int dosis) {
        this.tipoVacuna = tipoVacuna;
        this.fechaVacunacion = fechaVacunacion;
        this.dosis = dosis;
    }

    public String getTipoVacuna() {
        return tipoVacuna;
    }

    public void setTipoVacuna(String tipoVacuna) {
        this.tipoVacuna = tipoVacuna;
    }

    public Date getFechaVacunacion() {
        return fechaVacunacion;
    }

    public void setFechaVacunacion(Date fechaVacunacion) {
        this.fechaVacunacion = fechaVacunacion;
    }

    public int getDosis() {
        return dosis;
    }

    public void setDosis(int dosis) {
        this.dosis = dosis;
    }
}
