package com.valentin.negozio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.valentin.negozio.model.Articolo;

@Repository
public interface ArticoloRepository extends JpaRepository<Articolo, Long>{

}
