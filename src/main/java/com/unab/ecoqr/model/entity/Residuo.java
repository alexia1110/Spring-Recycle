package com.unab.ecoqr.model.entity;

import java.io.Serializable;


import javax.persistence.*;

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

    
    private static final long serialVersionUID = 1l;


    @Override
    public String toString() {
        return "{\"categoria\":\"" + categoria + "\", \"id\":\"" + id + "\", \"material\":\"" + material + "\", \"nombreEmpresa\":\""
                + nombreEmpresa + "\"}";
    } 



}
