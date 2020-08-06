package com.valentin.negozio.service;

import java.util.List;
import java.util.Optional;

import com.valentin.negozio.model.Utente;

public interface UtenteService {
	void saveUtente(Utente u);

	List<Utente> getAll();

	Optional<Utente> findByUsername(String username);

	Optional<Utente> controlloLogin(String user, String pass);

	int updateUtente(Utente u);

	void deleteUtente(Utente u);

	void deleteUtenteByUsername(String username);

	List<Utente> searchUtente(String keyword);
}
