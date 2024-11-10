package com.iktpreobuka.elektronskidnevnik1.service;

//import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;

public interface Razred1Service {

	Iterable<Razred1Entity> getAllClasses();

	String getClassById(int idRazreda) throws NotFoundException;

	//String createClass(String noviRazred);

	//String updateClass(int idRazreda, String izmenjenRazredUcenika) throws NotFoundException;

	String deleteClassById(int idRazreda) throws NotFoundException;

	//String updateClass(int id, int izmenjenRazredUcenika, int izmenjenoOdeljenje) throws NotFoundException;

	String createClass(String noviRazred, String novoOdeljenje);

	String updateClass(int idRazreda, String izmenjenRazredUcenika, String izmenjenoOdeljenje) throws NotFoundException;
}
