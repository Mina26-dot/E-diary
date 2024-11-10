package com.iktpreobuka.elektronskidnevnik1.service;

//import java.util.List;

//import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

//import com.iktpreobuka.elektronskidnevnik1.entity.Korisnik1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Predmet1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;

public interface Nastavnik1Service {

	

	Iterable<Nastavnik1Entity> getAllTeachers();
	

	//String createTeacher(String imeNastavnika, String prezimeNastavnika, List<Predmet1Entity> predmeti, Razred1Entity razred);

	String updateTeacher(int idNastavnika, String novoImeNastavnika, String novoPrezimeNastavnika) throws NotFoundException;

	String deleteTeacherById(int idNastavnika) throws NotFoundException;

	String getTeacherById(int idNastavnika) throws NotFoundException;


	String createTeacher(String imeNastavnika, String prezimeNastavnika);


	




}
