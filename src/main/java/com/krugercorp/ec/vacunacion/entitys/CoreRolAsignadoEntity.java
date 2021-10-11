package com.krugercorp.ec.vacunacion.entitys;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "core_rol_asignado", schema = "registro_vacunacion", catalog = "inventario_vacunacion")
@SequenceGenerator(name = "CORE_ROL_ASIGNADO",sequenceName = "registro_vacunacion.sec_core_rol_asignado",allocationSize = 1)
public class CoreRolAsignadoEntity {
    private Integer id;
//    private Integer idEmpleado;
//    private Integer idRol;
    private String estado;
    private CoreEmpleadosEntity coreEmpleadosByIdEmpleado;
    private ParRolesEntity parRolesByIdRol;

    public CoreRolAsignadoEntity() {
    }

    public CoreRolAsignadoEntity(String estado, CoreEmpleadosEntity coreEmpleadosByIdEmpleado, ParRolesEntity parRolesByIdRol) {
        this.estado = estado;
        this.coreEmpleadosByIdEmpleado = coreEmpleadosByIdEmpleado;
        this.parRolesByIdRol = parRolesByIdRol;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "CORE_ROL_ASIGNADO")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    @Basic
//    @Column(name = "id_empleado")
//    public Integer getIdEmpleado() {
//        return idEmpleado;
//    }
//
//    public void setIdEmpleado(Integer idEmpleado) {
//        this.idEmpleado = idEmpleado;
//    }
//
//    @Basic
//    @Column(name = "id_rol")
//    public Integer getIdRol() {
//        return idRol;
//    }
//
//    public void setIdRol(Integer idRol) {
//        this.idRol = idRol;
//    }

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
        CoreRolAsignadoEntity that = (CoreRolAsignadoEntity) o;
        return Objects.equals(id, that.id) &&
//                Objects.equals(idEmpleado, that.idEmpleado) &&
//                Objects.equals(idRol, that.idRol) &&
                Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estado);
    }

    @ManyToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "id")
    public CoreEmpleadosEntity getCoreEmpleadosByIdEmpleado() {
        return coreEmpleadosByIdEmpleado;
    }

    public void setCoreEmpleadosByIdEmpleado(CoreEmpleadosEntity coreEmpleadosByIdEmpleado) {
        this.coreEmpleadosByIdEmpleado = coreEmpleadosByIdEmpleado;
    }

    @ManyToOne
    @JoinColumn(name = "id_rol", referencedColumnName = "id")
    public ParRolesEntity getParRolesByIdRol() {
        return parRolesByIdRol;
    }

    public void setParRolesByIdRol(ParRolesEntity parRolesByIdRol) {
        this.parRolesByIdRol = parRolesByIdRol;
    }
}
