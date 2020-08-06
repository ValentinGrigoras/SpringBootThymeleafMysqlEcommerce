package com.valentin.negozio.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.valentin.negozio.model.Utente;
import com.valentin.negozio.repository.UtenteRepository;
import com.valentin.negozio.service.UtenteService;

@Service
@Transactional
public class UtenteServiceImpl implements UtenteService {
	@Autowired
	UtenteRepository utenteRepository;

	@Override
	public void saveUtente(Utente u) {
		utenteRepository.save(u);
	}

	@Override
	public List<Utente> getAll() {
		return utenteRepository.findAll();
	}

	@Override
	public Optional<Utente> findByUsername(String username) {
		return utenteRepository.findByUsername(username);
	}

	@Override
	public Optional<Utente> controlloLogin(String user, String pass) {
		return utenteRepository.controlloLogin(user, pass);
	}

	@Override
	public int updateUtente(Utente u) {
		return utenteRepository.updateUtente(u.getUsername(), u.getNome(), u.getCognome(), u.getIndirizzo(), u.getCap(),
				u.getPassword(), u.getEmail());
	}

	@Override
	public void deleteUtente(Utente u) {
		utenteRepository.delete(u);
	}

	@Override
	public void deleteUtenteByUsername(String username) {
		Optional<Utente> u = utenteRepository.findByUsername(username);
		if (!u.isEmpty())
			utenteRepository.delete(u.get());
	}

	@Override
	public List<Utente> searchUtente(String keyword) {
		return utenteRepository.searchUtente(keyword);
	}

}
