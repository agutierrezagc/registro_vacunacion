package com.krugercorp.ec.vacunacion.entitys;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "core_vacunacion", schema = "registro_vacunacion", catalog = "inventario_vacunacion")
@SequenceGenerator(name = "CORE_VACUNACION",sequenceName = "registro_vacunacion.sec_core_vacunacion",allocationSize = 1)
public class CoreVacunacionEntity {
    private Integer id;
//    private Integer idEmpleado;
    private Date fechaRegistro;
    private String estado;
//    private Integer idTipoVacuna;
    private Integer dosis;
    private Date fechaDosis;
    private CoreEmpleadosEntity coreEmpleadosByIdEmpleado;
    private ParTipoVacunaEntity parTipoVacunaByIdTipoVacuna;

    public CoreVacunacionEntity() {
    }

    public CoreVacunacionEntity(String estado, Integer dosis, Date fechaDosis, CoreEmpleadosEntity coreEmpleadosByIdEmpleado, ParTipoVacunaEntity parTipoVacunaByIdTipoVacuna) {
        this.estado = estado;
        this.dosis = dosis;
        this.fechaDosis = fechaDosis;
        this.coreEmpleadosByIdEmpleado = coreEmpleadosByIdEmpleado;
        this.parTipoVacunaByIdTipoVacuna = parTipoVacunaByIdTipoVacuna;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "CORE_VACUNACION")
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
    @Column(name = "fecha_registro")
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    @Basic
    @Column(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

//    @Basic
//    @Column(name = "id_tipo_vacuna")
//    public Integer getIdTipoVacuna() {
//        return idTipoVacuna;
//    }
//
//    public void setIdTipoVacuna(Integer idTipoVacuna) {
//        this.idTipoVacuna = idTipoVacuna;
//    }

    @Basic
    @Column(name = "dosis")
    public Integer getDosis() {
        return dosis;
    }

    public void setDosis(Integer dosis) {
        this.dosis = dosis;
    }

    @Basic
    @Column(name = "fecha_dosis")
    public Date getFechaDosis() {
        return fechaDosis;
    }

    public void setFechaDosis(Date fechaDosis) {
        this.fechaDosis = fechaDosis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoreVacunacionEntity that = (CoreVacunacionEntity) o;
        return Objects.equals(id, that.id) &&
//                Objects.equals(idEmpleado, that.idEmpleado) &&
                Objects.equals(fechaRegistro, that.fechaRegistro) &&
                Objects.equals(estado, that.estado) &&
//                Objects.equals(idTipoVacuna, that.idTipoVacuna) &&
                Objects.equals(dosis, that.dosis) &&
                Objects.equals(fechaDosis, that.fechaDosis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fechaRegistro, estado, dosis, fechaDosis);
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
    @JoinColumn(name = "id_tipo_vacuna", referencedColumnName = "id")
    public ParTipoVacunaEntity getParTipoVacunaByIdTipoVacuna() {
        return parTipoVacunaByIdTipoVacuna;
    }

    public void setParTipoVacunaByIdTipoVacuna(ParTipoVacunaEntity parTipoVacunaByIdTipoVacuna) {
        this.parTipoVacunaByIdTipoVacuna = parTipoVacunaByIdTipoVacuna;
    }
}
