package com.unab.ecoqr.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "contenedores")
public class Contenedor implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "fecha_reciclado", nullable = true)
    private Date fechaReciclado;

    @Column(name = "estado_reciclado")
    private boolean estadoReciclado;

    @OneToMany(mappedBy = "contenedor", fetch = FetchType.LAZY ,cascade = CascadeType.ALL)
    //@Column(name = "residuos")
    private List<Residuo> residuos;

    @ManyToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name="usuario_id")
  //  @Column(name = "usuario_id")
    private Usuario usuario;

    @PrePersist
    public void prePersist(){
        fechaCreacion = new Date();
    }

    

    public Contenedor() {
       this.residuos = new ArrayList<Residuo>();
    }

    // public Contenedor(Long id, Usuario usuario, boolean estadoReciclado) {
    //     this.id = id;
    //     this.usuario = usuario;
    //     this.estadoReciclado = estadoReciclado;
    // }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaReciclado() {
        return fechaReciclado;
    }

    public void setFechaReciclado(Date fechaReciclado) {
        this.fechaReciclado = fechaReciclado;
    }


    public boolean isEstadoReciclado() {
        return estadoReciclado;
    }



    public void setEstadoReciclado(boolean estadoReciclado) {
      //  this.fechaReciclado = new Date();
        this.estadoReciclado = estadoReciclado;
    }



    public List<Residuo> getResiduos() {
        return residuos;
    }

    public void setResiduos(List<Residuo> residuos) {
        this.residuos = residuos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public void addResiduo(Residuo residuo){
        
        residuos.add(residuo);
    }


    private static final long serialVersionUID = 1l;

    @Override
    public String toString() {
        return "{estadoReciclado=" + isEstadoReciclado() + ", fechaCreacion=" + getFechaCreacion()
                + ", fechaReciclado=" + getFechaReciclado() + ", id=" + getId() + ", residuos=" + getResiduos()  + "}";
    } 

    

    
}
