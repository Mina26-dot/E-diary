package com.iktpreobuka.elektronskidnevnik1.repositories;

import org.springframework.data.repository.CrudRepository;
import com.iktpreobuka.elektronskidnevnik1.entity.Predmet1Entity;


public interface Predmet1Repository extends CrudRepository<Predmet1Entity,Integer> {


	void save(String nazivPredmeta);


	Predmet1Entity findById(int predmetId);


	Iterable<Predmet1Entity> findAll();


	//boolean existsByNovoImePredmeta(String novoImePredmeta);

}
