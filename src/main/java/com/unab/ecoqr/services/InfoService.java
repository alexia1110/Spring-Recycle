package com.unab.ecoqr.services;

import java.util.List;

import com.unab.ecoqr.model.entity.Info;

public interface InfoService {
   public List<Info> findByCategoria(String categoria);
   public void save(Info info);
   public boolean exist(Long id);
   public void delete(Long id);
}
