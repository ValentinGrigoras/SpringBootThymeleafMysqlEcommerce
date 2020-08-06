package com.valentin.negozio.service;

import java.util.List;

import com.valentin.negozio.model.OrdineArticolo;


public interface OrdineArticoloService {
	void saveOrdineArticolo(OrdineArticolo a);
	List<OrdineArticolo> getAll();
	List<String[]> getArticoli(long id);
	List<String[]> getMiglioriArticoli();
}
