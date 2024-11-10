package com.iktpreobuka.elektronskidnevnik1.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.List;
//import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ocena1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ocena1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ocena1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Predmet1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.repositories.Predmet1Repository;
import com.iktpreobuka.elektronskidnevnik1.service.Predmet1Service;


@RestController
@RequestMapping(path = "/subject")
public class Predmet1Controller {

	private static final Logger logger = LoggerFactory.getLogger(Predmet1Controller.class);

	@Autowired
	private final Predmet1Service predmet1Service;

	public Predmet1Controller(Predmet1Service predmet1Service) {
		this.predmet1Service = predmet1Service;
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'RODITELJ', 'UCENIK', 'ADMIN')")
	@GetMapping("/all")
	public ResponseEntity<Iterable<Predmet1Entity>> getAllSubjects() {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Pozvana metoda getAllSubjects", formattedTime);

		Iterable<Predmet1Entity> predmeti = predmet1Service.getAllSubjects();
		return new ResponseEntity<>(predmeti, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'RODITELJ', 'UCENIK', 'ADMIN')")
	@GetMapping("/get/{id}")
	public ResponseEntity<String> getSubjectById(@PathVariable int id) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Pozvana metoda getSubjectById sa parametrom: id={}",formattedTime, id);

		try {
			String response = predmet1Service.getSubjectById(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NotFoundException e) {
			logger.error("[{}] Predmet sa id-jem {} nije pronadjen.",formattedTime, id);

			return new ResponseEntity<>("Predmet sa id-jem " + id + " nije pronadjen.", HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
    @PostMapping("/add")
	 public ResponseEntity<String> createSubject(@RequestParam String imePredmeta,
             @RequestParam int nedeljniFondCasova, @RequestParam int nastavnikId,@RequestParam Razred1Entity razred) {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        logger.info("[{}] Pozvana metoda createSubject sa parametrima: imePredmeta={}, nedeljniFondCasova={},nastavnikId={},razred={}", formattedTime, imePredmeta,
                nedeljniFondCasova,nastavnikId,razred);

        String response = predmet1Service.createSubject( imePredmeta,  nastavnikId,  nedeljniFondCasova,  razred);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
    @PutMapping("/update/{id}")
	 public ResponseEntity<String> updateSubject(@PathVariable int id,
             @RequestParam String novoImePredmeta,
             @RequestParam int noviNedeljniFondCasova) throws NotFoundException {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = currentTime.format(formatter);
        logger.info("[{}] Pozvana metoda updateSubject sa parametrima: id={}, novoImePredmeta={}, noviNedeljniFondCasova={}", formattedTime,
                id, novoImePredmeta, noviNedeljniFondCasova);

        String response = predmet1Service.updateSubject(id, novoImePredmeta, noviNedeljniFondCasova);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteSubjectById(@PathVariable int id) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
	    logger.info("[{}] Pozvana metoda deleteSubjectById sa parametrom: id={}",formattedTime, id);

		try {
			String response = predmet1Service.deleteSubjectById(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NotFoundException e) {
	        logger.error("[{}] Predmet sa id-jem {} nije pronadjen.",formattedTime, id);

			return new ResponseEntity<>("Predmet sa id-jem " + id + " nije pronadjen.", HttpStatus.NOT_FOUND);
		}
	}
//--------------------------------------------------------------------------------------------------------------	
//	private Predmet1Repository predmet1Repository;
//	
//	@GetMapping("/allsubject")
//	public Iterable<Predmet1Entity> getAllSubjets() {
//	    return predmet1Repository.findAll();
//	}
//	
//	@GetMapping
//	public String getSubjectById(@PathVariable int idPredmeta) {
//		Predmet1Entity predmet = predmet1Repository.findById(idPredmeta);
//		if(predmet != null) {
//			return "Predmet sa id-jem " + idPredmeta + " je pronadjen.";
//		}else {
//			return "Predmet sa id-jem " + idPredmeta + " nije pronadjen.";
//
//		}
//	}
//	
//	@PostMapping
//	public String addNewSubject(String imePredmeta, int nedeljniFondCasova) {
//		Predmet1Entity predmet = new Predmet1Entity();
//		predmet.setImePredmeta(imePredmeta);
//		predmet.setNedeljniFondCasova(nedeljniFondCasova);
//		predmet1Repository.save(predmet);
//		return " Novi predmet " + imePredmeta + " je uspesno dodat";	
//	}
//	
//	@PutMapping
//	public String updateSubject(@PathVariable int idPredmeta,String imePredmeta, int nedeljniFondCasova,String novoImePredmeta, int noviNedeljniFondCasova) {
//		Predmet1Entity predmet = predmet1Repository.findById(idPredmeta);
//		if(predmet != null) {
//			predmet.setImePredmeta(novoImePredmeta);
//			predmet.setNedeljniFondCasova(noviNedeljniFondCasova);
//			predmet1Repository.save(predmet);
//			
//				return "Predmet sa id-jem  " + idPredmeta + " je uspesno azuriran. ";
//			}else {
//				return "Predmet sa id-jem " + idPredmeta + " nije pronadjen.";
//			}
//			}
//	
//	@DeleteMapping
//	public String deleteSubjectById(@PathVariable int idPredmeta) {
//		Predmet1Entity predmet = predmet1Repository.findById(idPredmeta);
//		if(predmet != null) {
//			predmet1Repository.delete(predmet);
//			if(!predmet1Repository.existsById(idPredmeta)) {
//				return "Predmet sa id-jm " + idPredmeta + " je uspesno obrisan"; 
//			}else {
//				return "Predmet sa id-jem " + idPredmeta + " nije obrisan"; 
//
//			}
//		}else {
//			return "Predmet sa id-jem " + idPredmeta + " nije pronadjen"; 
//
//		}
//	}
}
