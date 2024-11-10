package com.iktpreobuka.elektronskidnevnik1.service;



import org.springframework.beans.factory.annotation.Autowired;

//import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Ocena1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Predmet1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;
import com.iktpreobuka.elektronskidnevnik1.repositories.Nastavnik1Repository;
import com.iktpreobuka.elektronskidnevnik1.repositories.Ocena1Repository;
import com.iktpreobuka.elektronskidnevnik1.repositories.Predmet1Repository;
import com.iktpreobuka.elektronskidnevnik1.repositories.Ucenik1Repository;

@Service
public class Ocena1Impl implements Ocena1Service {

	@Autowired
	private final Ocena1Repository ocena1Repository;
	
	 @Autowired
	    private Nastavnik1Repository nastavnik1Repository;
	 
	 @Autowired
	    private Predmet1Repository predmet1Repository;
	 
	 @Autowired
	    private Ucenik1Repository ucenik1Repository;

	public Ocena1Impl(Ocena1Repository ocena1Repository) {
		this.ocena1Repository = ocena1Repository;
	}

	@Override
	public Iterable<Ocena1Entity> getAllGrades() {
		return ocena1Repository.findAll();
	}

	@Override
	public String getGradeById(int ocenaId) throws NotFoundException {
		Ocena1Entity ocena = ocena1Repository.findById(ocenaId);
		if (ocena != null) {
			return "Ocena sa id-jem " + ocenaId + " je pronadjena.";
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public String createGrade(int novaOcena,int novaZakljucnaOcena, int prvoPolugodiste, int drugoPolugodiste,int pismeni, int usmeni, int vladanje,int predmetId, int nastavnikId, int ucenikId) {
		if (novaOcena < 1 || novaOcena > 5) {
			return "Ocena mora biti u opsegu od 1 do 5.";
		}
        Nastavnik1Entity nastavnik = nastavnik1Repository.findById(nastavnikId);
        Predmet1Entity predmet = predmet1Repository.findById(predmetId);
       Ucenik1Entity ucenik = ucenik1Repository.findById(ucenikId);

		Ocena1Entity ocena = new Ocena1Entity();
		ocena.setOcena(novaOcena);
		ocena.setZakljucnaOcena(novaZakljucnaOcena);
		ocena.setPrvoPolugodiste(prvoPolugodiste);
		ocena.setDrugoPolugodiste(drugoPolugodiste);
		ocena.setUsmeni(usmeni);
		ocena.setPismeni(pismeni);
		ocena.setVladanje(vladanje);
		ocena.setPredmet(predmet);
	//	ocena.setNastavnik(nastavnik);
		 ocena.setNastavnik(nastavnik);
		ocena.setUcenik(ucenik);
		ocena1Repository.save(ocena);
		return "Nova ocena sa id-jem " + ocena.getIdOcene() + " je uspesno dodata.";
	}


	@Override
	public String updateGrade(int idOcene, int izmenjenaOcena,int izmenjenoPrvoPolugodiste,int izmenjenoDrugoPolugodiste, int izmenjenUsmeni, int izmenjenPismeni, int zakljucnaOcena, int vladanje) throws NotFoundException {
		Ocena1Entity ocena = ocena1Repository.findById(idOcene);
		if (ocena != null) {
			ocena.setOcena(izmenjenaOcena);
			ocena.setPrvoPolugodiste(izmenjenoPrvoPolugodiste);
			ocena.setDrugoPolugodiste(izmenjenoDrugoPolugodiste);
			ocena.setPismeni(izmenjenPismeni);
			ocena.setUsmeni(izmenjenUsmeni);
			ocena.setZakljucnaOcena(zakljucnaOcena);
			ocena.setVladanje(vladanje);
			ocena1Repository.save(ocena);
			return "Ocena sa id-jem " + idOcene + " je uspesno azurirana.";
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public String deleteGradeById(int ocenaId) throws NotFoundException {
		Ocena1Entity ocena = ocena1Repository.findById(ocenaId);
		if (ocena != null) {
			ocena1Repository.delete(ocena);
			return "Ocena sa id-jem " + ocenaId + " je uspesno obrisana.";
		} else {
			throw new NotFoundException();
		}
	}
//------------------------------------------------------------------------------------------------------
//	@Override
//	public Ocena1Entity createOcena(Ocena1Entity ocena) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Ocena1Entity updateOcena(Integer id, Ocena1Entity ocena) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void deleteOcena(Integer id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<Ocena1Entity> getAllOcene() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Ocena1Entity getOcenaById(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public double izracunajProsekOcena(Ucenik1Entity ucenik) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	

}
