package com.iktpreobuka.elektronskidnevnik1.repositories;

import org.springframework.data.repository.CrudRepository;
import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;


public interface Nastavnik1Repository extends CrudRepository<Nastavnik1Entity,Integer>{

	
	
	Nastavnik1Entity findById(int idNastavnika);

	void save(int noviId);

	void deleteById(int idNastavnika);

	Iterable<Nastavnik1Entity> findAll();

	

	
}
