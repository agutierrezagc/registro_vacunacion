package com.krugercorp.ec.vacunacion.entitys;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "core_credenciales", schema = "registro_vacunacion", catalog = "inventario_vacunacion")
@SequenceGenerator(name = "CORE_CREDENCIALES",sequenceName = "registro_vacunacion.sec_core_credenciales",allocationSize = 1)
public class CoreCredencialesEntity {
    private Integer id;
//    private Integer idEmpleado;
    private String usuario;
    private String clave;
    private String estado;
    private CoreEmpleadosEntity coreEmpleadosByIdEmpleado;

    public CoreCredencialesEntity() {
    }

    public CoreCredencialesEntity(String usuario, String clave, String estado, CoreEmpleadosEntity coreEmpleadosByIdEmpleado) {
        this.usuario = usuario;
        this.clave = clave;
        this.estado = estado;
        this.coreEmpleadosByIdEmpleado = coreEmpleadosByIdEmpleado;
    }

    public CoreCredencialesEntity(CoreCredencialesEntity usersCredentials) {

    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "CORE_CREDENCIALES")
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

    @Basic
    @Column(name = "usuario")
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Basic
    @Column(name = "clave")
    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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
        CoreCredencialesEntity that = (CoreCredencialesEntity) o;
        return Objects.equals(id, that.id) &&
//                Objects.equals(idEmpleado, that.idEmpleado) &&
                Objects.equals(usuario, that.usuario) &&
                Objects.equals(clave, that.clave) &&
                Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, clave, estado);
    }

    @OneToOne
    @JoinColumn(name = "id_empleado", nullable = false)
    public CoreEmpleadosEntity getCoreEmpleadosByIdEmpleado() {
        return coreEmpleadosByIdEmpleado;
    }

    public void setCoreEmpleadosByIdEmpleado(CoreEmpleadosEntity coreEmpleadosByIdEmpleado) {
        this.coreEmpleadosByIdEmpleado = coreEmpleadosByIdEmpleado;
    }
}
