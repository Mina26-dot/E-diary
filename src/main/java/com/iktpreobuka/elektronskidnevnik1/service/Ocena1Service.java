package com.iktpreobuka.elektronskidnevnik1.service;

//import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

//import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Ocena1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Predmet1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;

public interface Ocena1Service {


	//String createGrade(int novaOcena, String novoPrvoPolugodiste, String novoDrugoPolugodiste);

//	String updateGrade(int idOcene, int izmenjenaOcena, String izmenjenoPrvoPolugodiste,
//			String izmenjenoDrugoPolugodiste) throws NotFoundException;
	
	String getGradeById(int ocenaId) throws NotFoundException;

	String deleteGradeById(int ocenaId) throws NotFoundException;

	Iterable<Ocena1Entity> getAllGrades();

	//String createGrade(int novaOcena, int novaZakljucnaOcena, int prvoPolugodiste, int drugoPolugodiste,int pismeni, int usmeni, int vladanje, Predmet1Entity predmet, Nastavnik1Entity nastavnik, Ucenik1Entity ucenik);

	String updateGrade(int idOcene, int izmenjenaOcena, int izmenjenoPrvoPolugodiste, int izmenjenoDrugoPolugodiste,
			int izmenjenUsmeni, int izmenjenPismeni, int izmenjenaZakljucnaOcena, int izmenjenoVladanje) throws NotFoundException;

//	String createGrade(int novaOcena, int novaZakljucnaOcena, int prvoPolugodiste, int drugoPolugodiste, int pismeni,
//			int usmeni, int vladanje, Integer predmetId, int nastavnikId, Integer ucenikId);

	String createGrade(int novaOcena, int novaZakljucnaOcena, int prvoPolugodiste, int drugoPolugodiste, int pismeni,
			int usmeni, int vladanje, int predmetId, int nastavnikId, int ucenikId);

//	String createGrade(int novaOcena, int novaZakljucnaOcena, int prvoPolugodiste, int drugoPolugodiste, int pismeni,
//			int usmeni, int vladanje, int ucenikId);
}
	
	
	//	String createGrade(int novaOcena);

	//String updateGrade(int idOcene, int izmenjenaOcena) throws NotFoundException;

//	String createGrade(int novaOcena, int novaZakljucnaOcena, String prvoPolugodiste, String drugoPolugodiste);


	
