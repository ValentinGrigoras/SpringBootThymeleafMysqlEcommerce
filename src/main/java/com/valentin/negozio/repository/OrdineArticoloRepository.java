package com.valentin.negozio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.valentin.negozio.model.OrdineArticolo;

@Repository
public interface OrdineArticoloRepository extends JpaRepository<OrdineArticolo, Long>{
	@Query(value="Select marca, modello, prezzo, quantita from articolo, ordine_articolo where articolo.id = id_articolo and id_ordine = ?1", nativeQuery = true)
	List<String[]> getArticoli(long id);
	@Query(value="select a.id_articolo, a.marca, a.modello, a.prezzo, sum(o.qta) as totalepezzi " + 
			"from ordine_articolo o " + 
			"inner join articolo a on a.id_articolo = o.id_articolo " + 
			"group by a.id_articolo, a.marca, a.modello, a.prezzo " + 
			"order by totalepezzi desc", nativeQuery = true)
	List<String[]> getMiglioriArticoli();

}
