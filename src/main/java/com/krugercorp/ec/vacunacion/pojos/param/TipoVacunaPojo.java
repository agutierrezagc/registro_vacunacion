package com.krugercorp.ec.vacunacion.pojos.param;

public class TipoVacunaPojo {
    private int id;
    private String nombreVacuna;
    private int dosisPorAplicar;
    private int diasEntreDosis;
    private String estado;

    public TipoVacunaPojo() {
    }

    public TipoVacunaPojo(int id, String nombreVacuna, int dosisPorAplicar, int diasEntreDosis, String estado) {
        this.id = id;
        this.nombreVacuna = nombreVacuna;
        this.dosisPorAplicar = dosisPorAplicar;
        this.diasEntreDosis = diasEntreDosis;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreVacuna() {
        return nombreVacuna;
    }

    public void setNombreVacuna(String nombreVacuna) {
        this.nombreVacuna = nombreVacuna;
    }

    public int getDosisPorAplicar() {
        return dosisPorAplicar;
    }

    public void setDosisPorAplicar(int dosisPorAplicar) {
        this.dosisPorAplicar = dosisPorAplicar;
    }

    public int getDiasEntreDosis() {
        return diasEntreDosis;
    }

    public void setDiasEntreDosis(int diasEntreDosis) {
        this.diasEntreDosis = diasEntreDosis;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
