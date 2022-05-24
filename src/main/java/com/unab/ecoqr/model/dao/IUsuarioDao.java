package com.unab.ecoqr.model.dao;

import java.util.Optional;

import com.unab.ecoqr.model.entity.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;


public interface IUsuarioDao  extends JpaRepository<Usuario, Long>{
    public Optional<Usuario> findByMailLikeIgnoreCase(String mail);
}
