package com.unab.ecoqr.model.dao;

import java.util.List;

import com.unab.ecoqr.model.entity.Residuo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IResiduosDao  extends JpaRepository<Residuo, Long>{
    public List<Residuo> findByCategoria(String categoria);
    
}
