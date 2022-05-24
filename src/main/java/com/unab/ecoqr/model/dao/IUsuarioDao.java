package com.unab.ecoqr.model.dao;

import com.unab.ecoqr.model.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioDao  extends JpaRepository<Usuario, Long>{
    
}
