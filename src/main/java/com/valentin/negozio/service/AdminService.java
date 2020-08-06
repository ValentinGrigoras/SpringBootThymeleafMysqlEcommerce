package com.valentin.negozio.service;

import java.util.Optional;

import com.valentin.negozio.model.Admin;


public interface AdminService {
	Optional<Admin> findByUsername(String username); // optional è un contenitore per l'oggetto ritornato. facendo optional.get ritorna l'oggetto vero.
	Optional<Admin> controlloLogin(String user, String pass);
}
