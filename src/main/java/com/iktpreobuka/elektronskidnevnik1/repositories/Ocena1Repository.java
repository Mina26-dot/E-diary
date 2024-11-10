package com.iktpreobuka.elektronskidnevnik1.repositories;

//import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronskidnevnik1.entity.Ocena1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;

public interface Ocena1Repository extends CrudRepository<Ocena1Entity,Integer>{

	Ocena1Entity findById(int idOcene);

	Iterable<Ocena1Entity> findAll();

	//List<Ocena1Entity> findByUcenik(Ucenik1Entity ucenik);

}
