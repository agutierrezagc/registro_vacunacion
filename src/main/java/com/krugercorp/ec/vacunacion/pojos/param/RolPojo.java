package com.krugercorp.ec.vacunacion.pojos.param;

public class RolPojo {
    private int id;
    private String nombreRol;
    private String descripcion;
    private String estado;

    public RolPojo() {
    }

    public RolPojo(int id, String nombreRol, String descripcion, String estado) {
        this.id = id;
        this.nombreRol = nombreRol;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
