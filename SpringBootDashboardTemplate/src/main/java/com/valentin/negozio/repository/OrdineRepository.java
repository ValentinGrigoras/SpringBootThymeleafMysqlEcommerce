package com.valentin.negozio.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.valentin.negozio.model.Ordine;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long>{
	@Query(value="select * from ordine where username = ?1", nativeQuery = true)
	List<Ordine> findByUsername(String user);
	
	@Query(value="select * from ordine where id_ordine = ?1", nativeQuery = true)
	Optional<Ordine> findById(Long id);

	@Query(value = "select * from ordine where upper(username) like concat('%', upper(?1), '%')", nativeQuery = true)
	List<Ordine> searchUtente(String keyword);
}
