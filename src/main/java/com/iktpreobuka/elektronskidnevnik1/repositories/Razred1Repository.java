package com.iktpreobuka.elektronskidnevnik1.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;

public interface Razred1Repository extends CrudRepository<Razred1Entity, Integer> {

	Razred1Entity findById(int idRazreda);

	

	boolean existsByid(int idRazreda);



	Iterable<Razred1Entity> findAll();

}
