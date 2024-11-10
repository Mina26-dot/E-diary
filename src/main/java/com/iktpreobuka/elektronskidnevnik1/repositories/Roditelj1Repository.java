package com.iktpreobuka.elektronskidnevnik1.repositories;

import org.springframework.data.repository.CrudRepository;
import com.iktpreobuka.elektronskidnevnik1.entity.Roditelj1Entity;


public interface Roditelj1Repository extends CrudRepository<Roditelj1Entity,Integer> {

	Roditelj1Entity findById(int idRoditelja);

	Iterable<Roditelj1Entity> findAll();



}
