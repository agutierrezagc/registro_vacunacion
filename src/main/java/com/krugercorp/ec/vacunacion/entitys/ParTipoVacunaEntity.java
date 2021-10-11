package com.krugercorp.ec.vacunacion.entitys;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "par_tipo_vacuna", schema = "registro_vacunacion", catalog = "inventario_vacunacion")
@SequenceGenerator(name = "PAR_TIPO_VACUNA",sequenceName = "registro_vacunacion.sec_par_tipo_vacuna",allocationSize = 1)
public class ParTipoVacunaEntity {
    private Integer id;
    private String nombreVacuna;
    private Integer intervaloDosis;
    private String estado;
    private Integer cantidadDosis;
    private List<CoreVacunacionEntity> coreVacunacionsById;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAR_TIPO_VACUNA")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nombre_vacuna")
    public String getNombreVacuna() {
        return nombreVacuna;
    }

    public void setNombreVacuna(String nombreVacuna) {
        this.nombreVacuna = nombreVacuna;
    }

    @Basic
    @Column(name = "intervalo_dosis")
    public Integer getIntervaloDosis() {
        return intervaloDosis;
    }

    public void setIntervaloDosis(Integer intervaloDosis) {
        this.intervaloDosis = intervaloDosis;
    }

    @Basic
    @Column(name = "estado")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Basic
    @Column(name = "cantidad_dosis")
    public Integer getCantidadDosis() {
        return cantidadDosis;
    }

    public void setCantidadDosis(Integer cantidadDosis) {
        this.cantidadDosis = cantidadDosis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParTipoVacunaEntity that = (ParTipoVacunaEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(nombreVacuna, that.nombreVacuna) &&
                Objects.equals(intervaloDosis, that.intervaloDosis) &&
                Objects.equals(estado, that.estado) &&
                Objects.equals(cantidadDosis, that.cantidadDosis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreVacuna, intervaloDosis, estado, cantidadDosis);
    }

    @OneToMany(mappedBy = "parTipoVacunaByIdTipoVacuna")
    public List<CoreVacunacionEntity> getCoreVacunacionsById() {
        return coreVacunacionsById;
    }

    public void setCoreVacunacionsById(List<CoreVacunacionEntity> coreVacunacionsById) {
        this.coreVacunacionsById = coreVacunacionsById;
    }
}
