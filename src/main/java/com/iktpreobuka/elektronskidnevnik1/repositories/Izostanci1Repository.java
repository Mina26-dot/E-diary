package com.iktpreobuka.elektronskidnevnik1.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronskidnevnik1.entity.Izostanci1Entity;
public interface Izostanci1Repository extends CrudRepository<Izostanci1Entity,Integer> {

	void save(String opravdani);

	Izostanci1Entity findById(int idIzostanka);
	
	void deleteById(int idIzostanka);

	Iterable<Izostanci1Entity> findAll();

	//Iterable<Izostanci1Entity> getAllAbsences();



}
