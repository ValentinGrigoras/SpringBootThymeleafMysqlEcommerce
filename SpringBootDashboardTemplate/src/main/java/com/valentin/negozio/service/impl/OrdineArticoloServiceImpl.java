package com.valentin.negozio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valentin.negozio.model.OrdineArticolo;
import com.valentin.negozio.repository.OrdineArticoloRepository;
import com.valentin.negozio.service.OrdineArticoloService;

@Service
public class OrdineArticoloServiceImpl implements OrdineArticoloService{

	@Autowired
	OrdineArticoloRepository ordineArticoloRepository;

	@Override
	public void saveOrdineArticolo(OrdineArticolo a) {
		ordineArticoloRepository.save(a);
	}

	@Override
	public List<OrdineArticolo> getAll() {
		return ordineArticoloRepository.findAll();
	}

	@Override
	public List<String[]> getArticoli(long id) {
		return ordineArticoloRepository.getArticoli(id);
	}

	@Override
	public List<String[]> getMiglioriArticoli() {
		return ordineArticoloRepository.getMiglioriArticoli();
	}
}
