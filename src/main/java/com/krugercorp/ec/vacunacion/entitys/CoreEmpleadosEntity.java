package com.krugercorp.ec.vacunacion.entitys;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "core_empleados", schema = "registro_vacunacion", catalog = "inventario_vacunacion")
@SequenceGenerator(name = "CORE_EMPLEADOS",sequenceName = "registro_vacunacion.sec_core_empleados",allocationSize = 1)
public class CoreEmpleadosEntity {
    private Integer id;
    private String cedula;
    private String nombres;
    private String primerApellido;
    private String segundoApellido;
    private String tercerApellido;
    private String correoElectronico;
    private String domicilio;
    private String telefonoMovil;
    private Date fechaNacimiento;
    private Timestamp fechaRegistro;
    private String estado;
    private CoreCredencialesEntity coreCredencialesById;
    private List<CoreRolAsignadoEntity> coreRolAsignadosById;
    private List<CoreVacunacionEntity> coreVacunacionsById;

    public CoreEmpleadosEntity() {
    }

    public CoreEmpleadosEntity(String cedula, String nombres, String primerApellido, String segundoApellido, String tercerApellido, String correoElectronico, String estado) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.tercerApellido = tercerApellido;
        this.correoElectronico = correoElectronico;
        this.estado = estado;
    }

    public CoreEmpleadosEntity(String domicilio, String telefonoMovil, Date fechaNacimiento) {
        this.domicilio = domicilio;
        this.telefonoMovil = telefonoMovil;
        this.fechaNacimiento = fechaNacimiento;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "CORE_EMPLEADOS")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cedula")
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    @Basic
    @Column(name = "nombres")
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    @Basic
    @Column(name = "primer_apellido")
    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    @Basic
    @Column(name = "segundo_apellido")
    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    @Basic
    @Column(name = "tercer_apellido")
    public String getTercerApellido() {
        return tercerApellido;
    }

    public void setTercerApellido(String tercerApellido) {
        this.tercerApellido = tercerApellido;
    }

    @Basic
    @Column(name = "correo_electronico")
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Basic
    @Column(name = "domicilio")
    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    @Basic
    @Column(name = "telefono_movil")
    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    @Basic
    @Column(name = "fecha_nacimiento")
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Basic
    @Column(name = "fecha_registro")
    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoreEmpleadosEntity that = (CoreEmpleadosEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(cedula, that.cedula) &&
                Objects.equals(nombres, that.nombres) &&
                Objects.equals(primerApellido, that.primerApellido) &&
                Objects.equals(segundoApellido, that.segundoApellido) &&
                Objects.equals(tercerApellido, that.tercerApellido) &&
                Objects.equals(correoElectronico, that.correoElectronico) &&
                Objects.equals(domicilio, that.domicilio) &&
                Objects.equals(telefonoMovil, that.telefonoMovil) &&
                Objects.equals(fechaNacimiento, that.fechaNacimiento) &&
                Objects.equals(fechaRegistro, that.fechaRegistro) &&
                Objects.equals(estado, that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cedula, nombres, primerApellido, segundoApellido, tercerApellido, correoElectronico, domicilio, telefonoMovil, fechaNacimiento, fechaRegistro, estado);
    }

    @OneToOne(mappedBy = "coreEmpleadosByIdEmpleado",cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    public CoreCredencialesEntity getCoreCredencialesById() {
        return coreCredencialesById;
    }

    public void setCoreCredencialesById(CoreCredencialesEntity coreCredencialesById) {
        this.coreCredencialesById = coreCredencialesById;
    }

    @OneToMany(mappedBy = "coreEmpleadosByIdEmpleado")
    public List<CoreRolAsignadoEntity> getCoreRolAsignadosById() {
        return coreRolAsignadosById;
    }

    public void setCoreRolAsignadosById(List<CoreRolAsignadoEntity> coreRolAsignadosById) {
        this.coreRolAsignadosById = coreRolAsignadosById;
    }

    @OneToMany(mappedBy = "coreEmpleadosByIdEmpleado")
    public List<CoreVacunacionEntity> getCoreVacunacionsById() {
        return coreVacunacionsById;
    }

    public void setCoreVacunacionsById(List<CoreVacunacionEntity> coreVacunacionsById) {
        this.coreVacunacionsById = coreVacunacionsById;
    }
}
