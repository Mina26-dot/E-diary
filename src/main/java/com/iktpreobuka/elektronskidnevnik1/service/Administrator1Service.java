package com.iktpreobuka.elektronskidnevnik1.service;

import java.nio.file.AccessDeniedException;

//import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.iktpreobuka.elektronskidnevnik1.entity.Izostanci1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Korisnik1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Ocena1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Predmet1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Roditelj1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;

import jakarta.validation.Valid;

public interface Administrator1Service {

	// predmeti

	String createSubject(String imePredmeta, int nedeljniFondCasova);

	String updateSubject(int idPredmeta, String novoImePredmeta, int noviNedeljniFondCasova) throws NotFoundException;

	String deleteSubjectById(int idPredmeta) throws NotFoundException;

	Iterable<Predmet1Entity> getAllSubjects() throws AccessDeniedException;

	String getSubjectById(int idPredmeta) throws NotFoundException;
	

	// nastavnik

	String createTeacher(String imeNastavnika, String prezimeNastavnika);

	String updateTeacher(int idNastavnika, String novoImeNastavnika, String novoPrezimeNastavnika)
			throws NotFoundException;

	String deleteTeacherById(int idNastavnika) throws NotFoundException;

	Iterable<Nastavnik1Entity> getAllTeachers() throws AccessDeniedException;

	String getTeacherById(int idNastavnika) throws NotFoundException;

	// ucenik

	String createStudent(String imeUcenika, String prezimeUcenika);

	String updateStudent(int idUcenika, String imeUcenika, String prezimeUcenika, String izmenjenoImeUcenika,
			String izmenjenoPrezimeUcenika) throws NotFoundException;

	String deleteStudentById(int idUcenika) throws NotFoundException;

	Iterable<Ucenik1Entity> getAllStudents() throws AccessDeniedException;

	String getStudentById(int idUcenika) throws NotFoundException;

	// roditelj

	String createParent(String imeRoditelja, String prezimeRoditelja, String emailRoditelja);

	String updateParent(int idRoditelja, String izmenjenoImeRoditelja, String izmenjenoPrezimeRoditelja,
			String izmenjenoEmailRoditelja) throws NotFoundException;

	String deleteParentById(int idRoditelja) throws NotFoundException;

	Iterable<Roditelj1Entity> getAllParents() throws AccessDeniedException;

	String getParentById(int idRoditelja) throws NotFoundException;

	// ocena

	Iterable<Ocena1Entity> getAllGrades() throws AccessDeniedException;

	String getGradeById(int ocenaId) throws NotFoundException;

	String createGrade(int novaOcena, int novoPrvoPolugodiste, int novoDrugoPolugodiste,int vladanje);

	String updateGrade(int idOcene, int izmenjenaOcena, int izmenjenoPrvoPolugodiste,
			int izmenjenoDrugoPolugodiste, int izmenjenoVladanje) throws NotFoundException;

	String deleteGradeById(int ocenaId) throws NotFoundException;

	

	// razredi
	Iterable<Razred1Entity> getAllClasses() throws AccessDeniedException;

	
	// korisnici

	Iterable<Korisnik1Entity> getAllUsers() throws AccessDeniedException;

	String getUserById(int userId) throws NotFoundException;

	String createUser(String korisnickoIme, String sifra);
	
	String updateUser(int idKorisnika, String korisnickoIme, String sifra,
			@Valid String izmenjenoKorisnickoIme, String izmenjenaSifra) throws NotFoundException;

	String deleteUserById(int idKorisnika) throws NotFoundException;

	// izostanci
		
	Izostanci1Entity createAbsence(String opravdani, String neopravdani, String datumIzostanka);

	Izostanci1Entity updateAbsence(int id, String noviOpravdani, String noviNeopravdani, String novDatumIzostanka) throws NotFoundException;

	void deleteAbsence(int id) throws NotFoundException;

	Izostanci1Entity getAbsenceById(int id) throws NotFoundException;

	Iterable<Izostanci1Entity> getAllAbsences() throws AccessDeniedException;





	

	

	





	


}
