package com.iktpreobuka.elektronskidnevnik1.service;

//import java.util.List;

//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

//import java.util.List;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

//import com.iktpreobuka.elektronskidnevnik1.entity.Izostanci1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Korisnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ocena1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Predmet1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Odeljenje1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Roditelj1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;
import com.iktpreobuka.elektronskidnevnik1.repositories.Ucenik1Repository;

@Service
public class Ucenik1Impl implements Ucenik1Service {

	@Autowired
	private final Ucenik1Repository ucenik1Repository;

	public Ucenik1Impl(Ucenik1Repository ucenik1Repository) {
		this.ucenik1Repository = ucenik1Repository;
	}

	@Override
	public Iterable<Ucenik1Entity> getAllStudents() {
		return ucenik1Repository.findAll();
	}

	
	@Override
	public String getStudentById(int idUcenika) throws NotFoundException {
		
		Ucenik1Entity ucenik = ucenik1Repository.findById(idUcenika);
		if (ucenik != null) {
			return "Ucenik sa id-jem " + idUcenika + " je uspesno pronadjen.";
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public String createStudent(String ime, String prezime, Roditelj1Entity roditelj, Razred1Entity razred) {
		Ucenik1Entity ucenik = new Ucenik1Entity();
		ucenik.setImeUcenika(ime);
		ucenik.setPrezimeUcenika(prezime);
		//ucenik.setKorisnik(korisnik);
		ucenik.setRoditelj(roditelj);
	//	ucenik.setNastavnik(nastavnik);
		ucenik.setRazred(razred);
		ucenik1Repository.save(ucenik);
		return "Nov ucenik " + ime + " " + prezime + " je uspesno dodat.";
	}

	@Override
	public String updateStudent(int id, String izmenjenoIme, String izmenjenoPrezime, Roditelj1Entity izmenjenRoditelj, Razred1Entity izmenjeniRazred) throws NotFoundException {
		Ucenik1Entity ucenik = ucenik1Repository.findById(id);
		if (ucenik != null) {
			ucenik.setImeUcenika(izmenjenoIme);
			ucenik.setPrezimeUcenika(izmenjenoPrezime);
		//	ucenik.setKorisnik(izmenjenKorisnik);
			ucenik.setRoditelj(izmenjenRoditelj);
			//ucenik.setOcene(izmenjenaOcena);
			//ucenik.setPredmet(izmenjeniPredmet);
			//ucenik.setNastavnik(izmenjeniNastavnik);
			ucenik.setRazred(izmenjeniRazred);
			ucenik1Repository.save(ucenik);
			return "Ucenik " + ucenik + " " + " je uspesno azuriran. ";
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public String deleteStudentById(int idUcenika) throws NotFoundException {
		Ucenik1Entity ucenik = ucenik1Repository.findById(idUcenika);
		if (ucenik != null) {
			ucenik1Repository.delete(ucenik);
			if (!ucenik1Repository.existsById(idUcenika)) {
				return "Ucenik sa id-jem " + idUcenika + " je uspesno obrisan.";
			} else {
				throw new RuntimeException("Ucenik sa id-jem " + idUcenika + " nije obrisan.");
			}
		} else {
			throw new NotFoundException();
		}
	}
}
//--------------------------------------------------------------------
//	@Override
//	public Ucenik1Entity createUcenik(Ucenik1Entity ucenik) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Ucenik1Entity updateUcenik(Integer id, Ucenik1Entity ucenik) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void deleteUcenik(Integer id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<Ucenik1Entity> getAllUcenik() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Ucenik1Entity getUcenikById(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}



