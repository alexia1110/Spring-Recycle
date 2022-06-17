package com.unab.ecoqr.model.entity;
import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "informacion")
public class Info  implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "categoria")
    private String categoria;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Info() {
    }
    private static final long serialVersionUID = 1l;

    @Override
    public String toString() {
        return "{\"id\":\"" + id + "\", \"categoria\":\"" + categoria + "\", \"titulo\":\"" + titulo + "\", \"descripcion\":\""
                + descripcion +"\"}";
    } 
    
    
}
