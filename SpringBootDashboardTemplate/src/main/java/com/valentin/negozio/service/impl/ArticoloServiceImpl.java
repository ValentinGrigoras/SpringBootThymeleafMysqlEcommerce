package com.valentin.negozio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valentin.negozio.model.Articolo;
import com.valentin.negozio.repository.ArticoloRepository;
import com.valentin.negozio.service.ArticoloService;

@Service
public class ArticoloServiceImpl implements ArticoloService{
	
	@Autowired
	private ArticoloRepository articoloRepository;
	
	@Override
	public void saveArticolo(Articolo a) {
		articoloRepository.save(a);
	}

	@Override
	public List<Articolo> getAll() {
		
		return articoloRepository.findAll();
	}

	@Override
	public Optional<Articolo> findById(Long id) {
		return articoloRepository.findById(id);
	}

	@Override
	public void removeArticoli(Long id) {
		if(id!=null)
		articoloRepository.deleteById(id);
	}

}
