package com.krugercorp.ec.vacunacion.pojos;

import java.sql.Date;

public class VacunaEmpleadoPojo {

    private Integer idTipoVacuna;
    private Integer dosis;
    private Date fechaDosis;

    public Integer getIdTipoVacuna() {
        return idTipoVacuna;
    }

    public void setIdTipoVacuna(Integer idTipoVacuna) {
        this.idTipoVacuna = idTipoVacuna;
    }

    public Integer getDosis() {
        return dosis;
    }

    public void setDosis(Integer dosis) {
        this.dosis = dosis;
    }

    public Date getFechaDosis() {
        return fechaDosis;
    }

    public void setFechaDosis(Date fechaDosis) {
        this.fechaDosis = fechaDosis;
    }
}
