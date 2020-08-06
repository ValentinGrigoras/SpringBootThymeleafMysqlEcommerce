package com.valentin.negozio.service;

import java.util.List;
import java.util.Optional;

import com.valentin.negozio.model.Articolo;


public interface ArticoloService {
	void saveArticolo(Articolo a);
	void removeArticoli(Long id);
	List<Articolo> getAll();
	Optional<Articolo> findById(Long id); // optional è un contenitore per l'oggetto ritornato. facendo optional.get ritorna l'oggetto vero.
}
