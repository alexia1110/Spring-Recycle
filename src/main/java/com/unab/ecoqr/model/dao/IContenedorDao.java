package com.unab.ecoqr.model.dao;

import java.util.List;

import com.unab.ecoqr.model.entity.Contenedor;
import com.unab.ecoqr.model.entity.Usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContenedorDao extends JpaRepository<Contenedor, Long>{
    public Page<Contenedor> findByEstadoRecicladoAndUsuario_id(Pageable pageable,boolean estado, Long id);
   public  List<Contenedor> findByUsuario(Usuario usuario);
}
