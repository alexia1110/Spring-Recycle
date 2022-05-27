package com.unab.ecoqr.services;

import java.util.List;
import java.util.Optional;

import com.unab.ecoqr.model.entity.Contenedor;
import com.unab.ecoqr.model.entity.Residuo;
import com.unab.ecoqr.model.entity.Usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IUsuarioService {
    public List<Usuario> findAll();
    public Page<Usuario> findAll(Pageable pageable);
    public void save(Usuario usuario);
    public Optional<Usuario>  findOne(Long id);
    public boolean exist(Long id);
    public void delete(Long id);
    public void saveContenedor(Contenedor contenedor);
   // public Residuo findResiduoByCategoria(String categoria);
   // public Residuo findResiduoById(Long id);
    public void deleteContenedor(Long id);
    //public List<Contenedor> findContenedorByEstado(Pageable pageable);
    public List<Contenedor> findContenedorByEstado(Long id, boolean estado);
    public Optional<Usuario> findByMail(String mail);
    // public List<Contenedor> findAllContenedor(Long id);
}
