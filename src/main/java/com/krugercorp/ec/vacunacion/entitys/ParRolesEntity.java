package com.krugercorp.ec.vacunacion.entitys;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "par_roles", schema = "registro_vacunacion", catalog = "inventario_vacunacion")
@SequenceGenerator(name = "PAR_ROLES",sequenceName = "registro_vacunacion.sec_par_roles",allocationSize = 1)
public class ParRolesEntity {
    private Integer id;
    private String nombreRol;
    private String descripcion;
    private String estado;
    private List<CoreRolAsignadoEntity> coreRolAsignadosById;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "PAR_ROLES")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nombre_rol")
    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @Basic
    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Basic
    @Column(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParRolesEntity that = (ParRolesEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nombreRol, that.nombreRol) &&
                Objects.equals(descripcion, that.descripcion) &&
                Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreRol, descripcion, estado);
    }

    @OneToMany(mappedBy = "parRolesByIdRol")
    public List<CoreRolAsignadoEntity> getCoreRolAsignadosById() {
        return coreRolAsignadosById;
    }

    public void setCoreRolAsignadosById(List<CoreRolAsignadoEntity> coreRolAsignadosById) {
        this.coreRolAsignadosById = coreRolAsignadosById;
    }
}
