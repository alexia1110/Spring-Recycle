package com.unab.ecoqr.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.unab.ecoqr.model.dao.IInfo;
import com.unab.ecoqr.model.entity.Info;
import com.unab.ecoqr.services.InfoService;
import org.springframework.stereotype.Service;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
	private IInfo infoDao;

    @Override
	@Transactional(readOnly = true)
    public List<Info> findByCategoria(String categoria){
        return infoDao.findByCategoria(categoria);
    }

    @Override
	@Transactional
	public void save(Info info) {
		infoDao.save(info);
	}


	@Override
	@Transactional(readOnly = true)
	public boolean exist(Long id) {
		// TODO Auto-generated method stub
		return infoDao.existsById(id);
	}

    @Override
	@Transactional
	public void delete(Long id) {
		infoDao.deleteById(id);

	}
}
