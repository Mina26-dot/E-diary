package com.iktpreobuka.elektronskidnevnik1.repositories;

import org.springframework.data.repository.CrudRepository;
import com.iktpreobuka.elektronskidnevnik1.entity.Korisnik1Entity;


public interface Korisnik1Repository extends CrudRepository<Korisnik1Entity,Integer> {



	Korisnik1Entity findById(int idKorisnika);

    Iterable<Korisnik1Entity> findByUloga(String uloga);

	Iterable<Korisnik1Entity> findAll();

}
