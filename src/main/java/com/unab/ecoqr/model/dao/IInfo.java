package com.unab.ecoqr.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unab.ecoqr.model.entity.Info;

public interface IInfo  extends JpaRepository<Info, Long>{
    public  List<Info> findByCategoria(String categoria);
}
