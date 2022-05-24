package com.unab.ecoqr.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "usuarios")
@EntityListeners(AuditingEntityListener.class)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido_first")
    private String apellidoFirst;

    @Column(name = "apellido_second")
    private String apellidoSecond;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "calle")
    private String calle;

    @Column(name = "numero_calle")
    private String numeroCalle;

    @Column(name = "id_ciudad")
    private String id_ciudad;

    @Column(name = "id_region")
    private String id_region;

    @Column(name = "mail")
    private String mail;

    @Column(name = "pass")
    private String pass;

    @Column(name = "estado")
    private boolean estado;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_registro")
    private Date fechaRegistro;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
   // @Column(name = "contenedores")
    private List<Contenedor> contenedores;

    @PrePersist
    public void prePersist(){
        fechaRegistro = new Date();
    }


    public Usuario() {
        contenedores = new ArrayList<Contenedor>();
    }

    // public Usuario(long id, String nombre, String apellidoFirst, String apellidoSecond, String sexo, String calle,
    //         String numeroCalle, String id_ciudad, String id_region, String mail, String pass, boolean estado) {
    //     this.id = id;
    //     this.nombre = nombre;
    //     this.apellidoFirst = apellidoFirst;
    //     this.apellidoSecond = apellidoSecond;
    //     this.sexo = sexo;
    //     this.calle = calle;
    //     this.numeroCalle = numeroCalle;
    //     this.id_ciudad = id_ciudad;
    //     this.id_region = id_region;
    //     this.mail = mail;
    //     this.pass = pass;
    //     this.estado = estado;

    // }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<Contenedor> getContenedores() {
        return contenedores;
    }

    public void setContenedores(List<Contenedor> contenedores) {
        this.contenedores = contenedores;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoFirst() {
        return apellidoFirst;
    }

    public void setApellidoFirst(String apellidoFirst) {
        this.apellidoFirst = apellidoFirst;
    }

    public String getApellidoSecond() {
        return apellidoSecond;
    }

    public void setApellidoSecond(String apellidoSecond) {
        this.apellidoSecond = apellidoSecond;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroCalle() {
        return numeroCalle;
    }

    public void setNumeroCalle(String numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    public String getId_ciudad() {
        return id_ciudad;
    }

    public void setId_ciudad(String id_ciudad) {
        this.id_ciudad = id_ciudad;
    }

    public String getId_region() {
        return id_region;
    }

    public void setId_region(String id_region) {
        this.id_region = id_region;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    private static final long serialVersionUID = 1l;

    // @Override
    // public int hashCode() {
    //     final int prime = 31;
    //     int result = 1;
    //     result = prime * result + (int) (id ^ (id >>> 32));
    //     return result;
    // }

    // @Override
    // public boolean equals(Object obj) {
    //     if (this == obj)
    //         return true;
    //     if (obj == null)
    //         return false;
    //     if (getClass() != obj.getClass())
    //         return false;
    //     Usuario other = (Usuario) obj;
    //     if (id != other.id)
    //         return false;
    //     return true;
    // }

    /** se almacena un contenedor */
    public void addContenedor(Contenedor contenedor){
        contenedores.add(contenedor);
    }


    @Override
    public String toString() {
        return "{apellidoFirst=" + apellidoFirst + ", apellidoSecond=" + apellidoSecond + ", calle=" + calle
                + ", contenedores=" + contenedores + ", estado=" + estado + ", fechaRegistro=" + fechaRegistro + ", id="
                + id + ", id_ciudad=" + id_ciudad + ", id_region=" + id_region + ", mail=" + mail + ", nombre=" + nombre
                + ", numeroCalle=" + numeroCalle + ", pass=" + pass + ", sexo=" + sexo + "}";
    }

    // @Override
    // public String toString() {
    //     return "{apellidoFirst=" + apellidoFirst + ", apellidoSecond=" + apellidoSecond + ", calle=" + calle
    //             + ", estado=" + estado + ", id=" + id + ", id_ciudad=" + id_ciudad + ", id_region=" + id_region
    //             + ", mail=" + mail + ", nombre=" + nombre + ", numeroCalle=" + numeroCalle + ", pass=" + pass
    //             + ", sexo=" + sexo + ", fechaRegistro=" + fechaRegistro + "}";
    // }
    

}
