package com.iktpreobuka.elektronskidnevnik1.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.iktpreobuka.elektronskidnevnik1.entity.Izostanci1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;

public interface Izostanci1Service {

	//Izostanci1Entity createAbsence(String opravdani, String neopravdani, String datumIzostanka);

//	Izostanci1Entity updateAbsence(int id, String opravdani, String neopravdani, String datumIzostanka,
//			String noviOpravdani, String noviNeopravdani, String novDatumIzostanka, String izmenjenOpravdani,
//			String izmenjenNeopravdani, String izmenjenDatumIzostanka) throws NotFoundException;

	void deleteAbsence(int idIzostanka) throws NotFoundException;

	Iterable<Izostanci1Entity> getAllAbsences();

	Izostanci1Entity getAbsenceById(int idIzostanka) throws NotFoundException;

	Izostanci1Entity updateAbsence(int id, String noviOpravdani, String noviNeopravdani, String novDatumIzostanka) throws NotFoundException;

	//Izostanci1Entity createAbsence(String opravdani, String neopravdani, String datumIzostanka, int ucenik);

	Izostanci1Entity createAbsence(String opravdani, String neopravdani, String datumIzostanka, Ucenik1Entity ucenik);

	

}
