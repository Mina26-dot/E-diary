package com.iktpreobuka.elektronskidnevnik1.service;

import org.springframework.beans.factory.annotation.Autowired;

//import java.util.List;

//import java.util.List;

//import java.util.List;

//import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestParam;

import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ocena1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Predmet1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;
import com.iktpreobuka.elektronskidnevnik1.repositories.Nastavnik1Repository;
//import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ocena1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ocena1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Predmet1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;
import com.iktpreobuka.elektronskidnevnik1.repositories.Predmet1Repository;

@Service
public class Predmet1Impl implements Predmet1Service {

	@Autowired
	private final Predmet1Repository predmet1Repository;
	
	@Autowired
    private Nastavnik1Repository nastavnik1Repository;

	public Predmet1Impl(Predmet1Repository predmet1Repository) {
		this.predmet1Repository = predmet1Repository;
	}

	@Override
	public Iterable<Predmet1Entity> getAllSubjects() {
		return predmet1Repository.findAll();
	}

	@Override
	public String getSubjectById(int idPredmeta) throws NotFoundException {
		Predmet1Entity predmet = predmet1Repository.findById(idPredmeta);
		if (predmet != null) {
			return "Predmet sa id-jem " + idPredmeta + " je pronadjen.";
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public String createSubject(String imePredmeta,int nastavnikId, int nedeljniFondCasova, Razred1Entity razred) {
       
		Nastavnik1Entity nastavnik = nastavnik1Repository.findById(nastavnikId);
		Predmet1Entity predmet = new Predmet1Entity();
        predmet.setImePredmeta(imePredmeta);
        predmet.setNedeljniFondCasova(nedeljniFondCasova);
        predmet.setNastavnik(nastavnik);
        predmet.setRazred(razred);
        
       // nastavnik.getPredmeti().add(predmet);
        
        predmet1Repository.save(predmet);
        nastavnik1Repository.save(nastavnik);
        return "Novi predmet " + imePredmeta + " je uspeno dodat nastavniku " + nastavnik.getImeNastavnika() + " " + nastavnik.getPrezimeNastavnika();
    }
	

	@Override
	public String updateSubject(int id, String novoImePredmeta, int noviNedeljniFondCasova) throws NotFoundException {
	    try {
	        Predmet1Entity predmet = predmet1Repository.findById(id);
	        
	        predmet.setImePredmeta(novoImePredmeta);
	        predmet.setNedeljniFondCasova(noviNedeljniFondCasova);
	        
	        predmet1Repository.save(predmet);
	        return "Predmet sa id-jem " + id + " je uspesno azuriran.";
	    } catch (Exception e) {
	        throw new NotFoundException();
	    }
	}

	@Override
	public String deleteSubjectById(int idPredmeta) throws NotFoundException {
		Predmet1Entity predmet = predmet1Repository.findById(idPredmeta);
		if (predmet != null) {
			predmet1Repository.delete(predmet);
			if (!predmet1Repository.existsById(idPredmeta)) {
				return "Predmet sa id-jem " + idPredmeta + " je uspesno obrisan.";
			} else {
				throw new RuntimeException("Predmet sa id-jem " + idPredmeta + " nije obrisan.");
			}
		} else {
			throw new NotFoundException();
		}
	}
//----------------------------------------------------------------------------------------------------------
//	@Override
//	public Predmet1Entity createPredmet(Predmet1Entity predmet) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Predmet1Entity updatePredmet(Integer id, Predmet1Entity predmet) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void deletePredmet(Integer id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<Predmet1Entity> getAllPredmet() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Predmet1Entity getPredmetById(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
