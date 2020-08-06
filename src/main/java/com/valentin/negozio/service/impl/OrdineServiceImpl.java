package com.valentin.negozio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.valentin.negozio.model.Ordine;
import com.valentin.negozio.repository.OrdineRepository;
import com.valentin.negozio.service.OrdineService;

@Service
public class OrdineServiceImpl implements OrdineService {
	@Autowired
	OrdineRepository ordineRepository;

	@Override
	public void saveOrdine(Ordine o) {
		ordineRepository.save(o);
	}

	@Override
	public List<Ordine> getAll() {
		return ordineRepository.findAll();
	}

	@Override
	public List<Ordine> findByUsername(String username) {
		return ordineRepository.findByUsername(username);
	}

	@Override
	public Optional<Ordine> findById(Long id) {
		return ordineRepository.findById(id);
	}

	@Override
	public void deleteOrdine(Ordine o) {
		ordineRepository.delete(o);
	}

	@Override
	public void deleteOrdineById(Long id) {
		Optional<Ordine> o = ordineRepository.findById(id);
		if (!o.isEmpty())
			ordineRepository.delete(o.get());
	}
	
	@Override
	public List<Ordine> searchUtente(String keyword) {
		return ordineRepository.searchUtente(keyword);
	}
}
