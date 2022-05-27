package com.unab.ecoqr.services.impl;
import java.util.List;
import java.util.Optional;

import com.unab.ecoqr.model.dao.IContenedorDao;
import com.unab.ecoqr.model.dao.IUsuarioDao;
import com.unab.ecoqr.model.entity.Contenedor;
import com.unab.ecoqr.model.entity.Usuario;
import com.unab.ecoqr.services.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IUsuarioServiceImpl implements IUsuarioService{
    @Autowired
	private IUsuarioDao usuarioDao;

    @Autowired
	private IContenedorDao contenedorDao;

    // @Autowired
	// private IResiduosDao residuo;
    
	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return (List<Usuario>) usuarioDao.findAll();
	}

    @Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioDao.findAll(pageable);
	}

    @Override
	@Transactional
	public void save(Usuario usuario) {
		usuarioDao.save(usuario);
	}

    @Override
	@Transactional(readOnly = true)
	public Optional<Usuario>  findOne(Long id) {
		// TODO Auto-generated method stub
		return usuarioDao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean exist(Long id) {
		// TODO Auto-generated method stub
		return usuarioDao.existsById(id);
	}

    @Override
	@Transactional
	public void delete(Long id) {
		usuarioDao.deleteById(id);

	}

    @Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findByMail(String mail) {
		return usuarioDao.findByMailLikeIgnoreCase("%" + mail + "%");
	}

    @Override
	@Transactional
	public void saveContenedor(Contenedor contenedor) {
		contenedorDao.save(contenedor);
	}

    @Override
	@Transactional
	public void deleteContenedor(Long id) {
		contenedorDao.deleteById(id);
	}

	// @Override
	// @Transactional
	// public List<Contenedor> findAllContenedor(Long id){
	// Usuario user = 	usuarioDao.findById(id).orElse(null);

	//   List<Contenedor>  contenedores = user.getContenedores();
	//   return contenedores;

	// }




    @Override
	@Transactional(readOnly = true)
	public List<Contenedor> findContenedorByEstado( Long id, boolean estado) {
		return contenedorDao.findByEstadoRecicladoAndUsuario_id(estado, id);
	}



}
