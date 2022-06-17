package com.unab.ecoqr.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "residuos")
public class Residuo  implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_empresa")
    private String nombreEmpresa;
    @Column(name = "material")
    private String material;
    @Column(name = "categoria")
    private String categoria;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_elaboracion")
    private Date fechaElaboracion;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_vencimiento")
    private Date fechaVencimiento;
    @Column(name = "id_sucursal")
    private String idSucursal;
    @Column(name = "id_empresa")
    private String idEmpresa;

 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contenedor_id")
  //  @Column(name = "contenedor")
    private Contenedor contenedor;


    public Residuo() {
    }

    // public Residuo(Long id, String nombreEmpresa, String material, String categoria) {
    //     this.id = id;
    //     this.nombreEmpresa = nombreEmpresa;
    //     this.material = material;
    //     this.categoria = categoria;

    // }



    public Contenedor getContenedor() {
        return contenedor;
    }

    public Date getFechaElaboracion() {
        return fechaElaboracion;
    }

    public void setFechaElaboracion(Date fechaElaboracion) {
        this.fechaElaboracion = fechaElaboracion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(String idSucursal) {
        this.idSucursal = idSucursal;
    }

    public void setContenedor(Contenedor contenedor) {
        this.contenedor = contenedor;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    
    
    public String getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(String idEmpresa) {
        this.idEmpresa = idEmpresa;
    }



    private static final long serialVersionUID = 1l;


    @Override
    public String toString() {
        return "{\"categoria\":\"" + categoria + "\", \"id\":\"" + id + "\", \"material\":\"" + material + "\", \"nombreEmpresa\":\""
                + nombreEmpresa + "\", \"fechaElaboracion\":\"" + fechaElaboracion +"\", \"fechaVencimiento\":\"" + fechaVencimiento +"\", \"idSucursal\":\"" + idSucursal +  "\", \"id_empresa\":\"" + idEmpresa +"\"}";
    } 



}
