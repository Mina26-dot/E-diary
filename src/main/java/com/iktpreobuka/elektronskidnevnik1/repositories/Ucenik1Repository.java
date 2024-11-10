package com.iktpreobuka.elektronskidnevnik1.repositories;

//import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.elektronskidnevnik1.entity.Korisnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Korisnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Odeljenje1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;


public interface Ucenik1Repository extends CrudRepository<Ucenik1Entity,Integer>{


	

	Ucenik1Entity findById(int idUcenika);

	void deleteById(int idUcenika);

	//void save(Korisnik1Entity korisnik);

	Iterable<Ucenik1Entity> findAll();

	void save(Korisnik1Entity korisnik);

	//boolean existsByIdAndRoditeljKorisnikKorisnickoIme(int studentId, String parentUsername);

//	boolean existsByIdAndRoditeljKorisnickoIme(int idUcenika, String korisnikRoditelj);
//
//	boolean existsByIdAndRoditeljKorisnikRoditelj(int idUcenika, String korisnikRoditelj);

	//boolean isParentAuthorizedForStudent(String korisnickoIme, int id);

	//String getStudentById(int id);



}
