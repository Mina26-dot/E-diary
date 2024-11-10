package com.iktpreobuka.elektronskidnevnik1.service;


//import java.util.List;

//import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

//import com.iktpreobuka.elektronskidnevnik1.entity.Izostanci1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Korisnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ocena1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Predmet1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Roditelj1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;

public interface Ucenik1Service {

	Iterable<Ucenik1Entity> getAllStudents();

	String getStudentById(int idUcenika) throws NotFoundException, NotFoundException;

//	String createStudent(String imeUcenika, String prezimeUcenika);

	String deleteStudentById(int idUcenika) throws NotFoundException;

//	String updateStudent(int id, String novoIme, String novoPrezime) throws NotFoundException;

//	String createStudent(String ime, String prezime, Korisnik1Entity korisnik, Roditelj1Entity roditelj,
//			Ocena1Entity ocena);

	//String updateStudent(int id, String izmenjenoIme, String izmenjenoPrezime, Korisnik1Entity izmenjenKorisnik, Roditelj1Entity izmenjenRoditelj, List<Ocena1Entity> izmenjenaOcena, Predmet1Entity izmenjeniPredmet, Nastavnik1Entity izmenjeniNastavnik, Razred1Entity izmenjeniRazred, List<Izostanci1Entity> izmenjeniIzostanci) throws NotFoundException;
//
//	boolean isStudentBelongsToParent(int id, String roditelj);
//
//	boolean isParentAuthorizedForStudent(String roditelj, int id);

	//String createStudent(String ime, String prezime, Korisnik1Entity korisnik, Roditelj1Entity roditelj, List<Ocena1Entity> ocene, Predmet1Entity predmet, Nastavnik1Entity nastavnik, Razred1Entity razred, List<Izostanci1Entity> izostanci);

//	String createStudent(String ime, String prezime, Korisnik1Entity korisnik, Roditelj1Entity roditelj,
//			Nastavnik1Entity nastavnik, Razred1Entity razred);

//	String updateStudent(int id, String izmenjenoIme, String izmenjenoPrezime, Korisnik1Entity izmenjenKorisnik,
//			Roditelj1Entity izmenjenRoditelj, List<Ocena1Entity> izmenjenaOcena, Nastavnik1Entity izmenjeniNastavnik,
//			Razred1Entity izmenjeniRazred) throws NotFoundException;

	String createStudent(String ime, String prezime, Roditelj1Entity roditelj, Razred1Entity razred);

//	String updateStudent(int id, String izmenjenoIme, String izmenjenoPrezime, Roditelj1Entity izmenjenRoditelj,
//			List<Ocena1Entity> izmenjenaOcena, Nastavnik1Entity izmenjeniNastavnik, Razred1Entity izmenjeniRazred)
//			throws NotFoundException;

	String updateStudent(int id, String izmenjenoIme, String izmenjenoPrezime, Roditelj1Entity izmenjenRoditelj,
			Razred1Entity izmenjeniRazred) throws NotFoundException;

//	boolean isParentAuthorizedForStudent(String korisnickoImeRoditelja, int idUcenika);

	//boolean isParentAuthorizedForStudent(Long parentId, int id);

//	String isParentAuthorizedForStudent(String korisnickoIme, int id) throws NotFoundException;

	//boolean isParentAuthorizedForStudent(int parentId, int id);

	//List<Ucenik1Entity> pronadjiUcenikeIzOdeljenja(Odeljenje1Entity odeljenje);

	//List<Ucenik1Entity> pronadjiUcenikeIzOdeljenja(String odeljenje);


	//List<Ucenik1Entity> pronadjiUcenikeIzOdeljenja(int odeljenjeId);

//	List<Ucenik1Entity> pronadjiUcenikeIzOdeljenja(Odeljenje1Entity odeljenje);

//	String updateStudent(int id, String novoImeUcenika, String novoPrezimeUcenika);

}
