package com.valentin.negozio.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.valentin.negozio.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String>{
	@Query(value = "select * from admin where username = ?1", nativeQuery = true)
	Optional<Admin> findByUsername(String username); // ogni volta che una query ritorna un oggetto singolo bisogna utilizzare Optional!

	@Query(value = "select * from admin where username = ?1 and password = ?2", nativeQuery = true)
	Optional<Admin> controlloLogin(String username, String password);
	
	
}
