package com.valentin.negozio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.valentin.negozio.model.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, String> {
	@Query(value = "select * from utente where username = ?1", nativeQuery = true)
	Optional<Utente> findByUsername(String username); // ogni volta che una query ritorna un oggetto singolo bisogna
														// utilizzare Optional!

	@Query(value = "select * from utente where username = ?1 and password = ?2", nativeQuery = true)
	Optional<Utente> controlloLogin(String username, String password);

	@Modifying(clearAutomatically = true)
	@Query("Update Utente Set nome = ?2, cognome = ?3, indirizzo = ?4, cap = ?5, password = ?6, email = ?7 Where username = ?1")
	int updateUtente(String user, String nome, String cognome, String indirizzo, String cap, String password,
			String email);

	@Query(value = "select * from utente where upper(username) like concat('%', upper(?1), '%') or upper(nome) like concat('%', upper(?1), '%') or upper(cognome) like concat('%', upper(?1), '%')", nativeQuery = true)
	List<Utente> searchUtente(String keyword);
}