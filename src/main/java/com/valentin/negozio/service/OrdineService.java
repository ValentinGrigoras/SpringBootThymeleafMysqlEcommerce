package com.valentin.negozio.service;

import java.util.List;
import java.util.Optional;

import com.valentin.negozio.model.Ordine;

public interface OrdineService {
	void saveOrdine(Ordine o);

	List<Ordine> getAll();

	List<Ordine> findByUsername(String username);

	Optional<Ordine> findById(Long id);
	
	void deleteOrdine(Ordine o);

	void deleteOrdineById(Long id);

	List<Ordine> searchUtente(String keyword);
}
