package com.iktpreobuka.elektronskidnevnik1.service;

//import java.util.List;

//import java.util.List;

//import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

//import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ocena1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ocena1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Predmet1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;

public interface Predmet1Service {

	Iterable<Predmet1Entity> getAllSubjects();

	String getSubjectById(int idPredmeta) throws NotFoundException;

	String deleteSubjectById(int idPredmeta) throws NotFoundException;

//	String createSubject(String imePredmeta, int nedeljniFondCasova, Razred1Entity razred, Nastavnik1Entity nastavnik,
//			List<Ucenik1Entity> ucenici, List<Ocena1Entity> ocene);

//	String updateSubject(int id, String novoImePredmeta, int noviNedeljniFondCasova)
//			throws NotFoundException;

//	String createSubject(String imePredmeta, int nedeljniFondCasova);

	//String createSubject(String imePredmeta, int nedeljniFondCasova, int nastavnik, List<Ocena1Entity> ocene, List<Ucenik1Entity> ucenici, int razred);

//	String createSubject(String imePredmeta, int nedeljniFondCasova, Nastavnik1Entity nastavnik,
//			List<Ocena1Entity> ocene, List<Ucenik1Entity> ucenici, int razred);

	//String createSubject(String imePredmeta, int nedeljniFondCasova);

	//String createSubject(String imePredmeta, int nedeljniFondCasova, Razred1Entity razred, Nastavnik1Entity nastavnik, List<Ucenik1Entity> ucenici, List<Ocena1Entity> ocene);
//
//	String updateSubject(int id,String novoImePredmeta, int noviNedeljniFondCasova, Nastavnik1Entity noviNastavnik,
//			List<Ucenik1Entity> noviUcenici, Razred1Entity razred, List<Ocena1Entity> noveOcene) throws NotFoundException;

	//String createSubject(String imePredmeta, int nedeljniFondCasova, Razred1Entity razred, Nastavnik1Entity nastavnik, List<Ucenik1Entity> ucenici, List<Ocena1Entity> ocene);

	//String updateSubject(int id, String novoImePredmeta, int noviNedeljniFondCasova, Nastavnik1Entity noviNastavnik, List<Ucenik1Entity> noviUcenici, Razred1Entity razred, List<Ocena1Entity> noveOcene) throws NotFoundException;

	String updateSubject(int id, String novoImePredmeta, int noviNedeljniFondCasova) throws NotFoundException;

	//String createSubject(String imePredmeta, int nedeljniFondCasova, Nastavnik1Entity nastavnik, Razred1Entity razred);

//	String createSubject(String imePredmeta, int nastavnikId, int nedeljniFondCasova, Nastavnik1Entity nastavnik,
//			Razred1Entity razred);

	String createSubject(String imePredmeta, int nastavnikId, int nedeljniFondCasova, Razred1Entity razred);

	//String createSubject(String imePredmeta, int nedeljniFondCasova, int nastavnik_id, int razred_id);

//	String createSubject(String imePredmeta, int nedeljniFondCasova, String imePredmeta2, Razred1Entity razred,
//			int nedeljniFondCasova2, Nastavnik1Entity nastavnik, List<Ucenik1Entity> ucenici, List<Ocena1Entity> ocene);

	//String createSubject(String imePredmeta, int nedeljniFondCasova, int razred);
	
	// String createSubject(String imePredmeta, int nedeljniFondCasova);

//	String updateSubject(int idPredmeta, String imePredmeta, int nedeljniFondCasova, String novoImePredmeta,
	// int noviNedeljniFondCasova) throws NotFoundException;

	// String updateSubject(int idPredmeta, String novoImePredmeta, int
	// noviNedeljniFondCasova,String nastavnik, String ucenici, int ocene) throws
	// NotFoundException;

	// String createSubject(String imePredmeta, int nedeljniFondCasova, String
	// nastavnik, String ucenici, int ocene);

//	String createSubject(String imePredmeta, int nedeljniFondCasova, Nastavnik1Entity nastavnik,
//			List<Ucenik1Entity> ucenici, List<Ocena1Entity> ocene);

//	String updateSubject(int idPredmeta, String novoImePredmeta, int noviNedeljniFondCasova, Nastavnik1Entity nastavnik,
//			List<Ucenik1Entity> ucenici, List<Ocena1Entity> ocene) throws NotFoundException;

//	String updateSubject(int id, String novoImePredmeta, int noviNedeljniFondCasova, Razred1Entity razred,
//			Nastavnik1Entity nastavnik, List<Ucenik1Entity> ucenici, List<Ocena1Entity> ocene);

//	String updateSubject(int idPredmeta, String novoImePredmeta, int noviNedeljniFondCasova,
//			Nastavnik1Entity noviNastavnik, List<Ucenik1Entity> noviUcenici, List<Ocena1Entity> noveOcene)
//			throws NotFoundException;

//	String updateSubject(int id, String novoImePredmeta, int noviNedeljniFondCasova, Razred1Entity razred,
//			String nastavnik, String ucenici, int ocene);

//	String updateSubject(int id, String novoImePredmeta, int noviNedeljniFondCasova, Razred1Entity razred,
//			Nastavnik1Entity noviNastavnik, List<Ucenik1Entity> noviUcenici, List<Ocena1Entity> noveOcene)throws NotFoundException;
//
//	String updateSubject(int id, String novoImePredmeta, int noviNedeljniFondCasova, Nastavnik1Entity noviNastavnik,
//			List<Ucenik1Entity> noviUcenici, Razred1Entity razred, List<Ocena1Entity> noveOcene)
//			throws NotFoundException;

//	String updateSubject(int id, String novoImePredmeta, int noviNedeljniFondCasova, Razred1Entity razred,
//			Nastavnik1Entity noviNastavnik, List<Ucenik1Entity> noviUcenici, List<Ocena1Entity> noveOcene);

//	String updateSubject(int id, String novoImePredmeta, int noviNedeljniFondCasova, Nastavnik1Entity noviNastavnik,
//			List<Ucenik1Entity> noviUcenici, List<Ocena1Entity> noveOcene) throws NotFoundException;

}
