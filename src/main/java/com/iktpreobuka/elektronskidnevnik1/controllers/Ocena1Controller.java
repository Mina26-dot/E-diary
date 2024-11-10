package com.iktpreobuka.elektronskidnevnik1.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Ocena1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Predmet1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.repositories.Ocena1Repository;
import com.iktpreobuka.elektronskidnevnik1.service.EmailService;
//import com.iktpreobuka.elektronskidnevnik1.repositories.Ocena1Repository;
import com.iktpreobuka.elektronskidnevnik1.service.Ocena1Service;

@RestController
@RequestMapping("/grades")
public class Ocena1Controller {

	private static final Logger logger = LoggerFactory.getLogger(Ocena1Controller.class);

	@Autowired
	private final Ocena1Service ocena1Service;
	
	@Autowired
	private EmailService emailService;

	public Ocena1Controller(Ocena1Service ocena1Service) {
		this.ocena1Service = ocena1Service;
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@GetMapping("/all")
	public ResponseEntity<Iterable<Ocena1Entity>> getAllGrades() {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Pozvana metoda getAllGrades", formattedTime);
		Iterable<Ocena1Entity> ocene = ocena1Service.getAllGrades();
		return new ResponseEntity<>(ocene, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK','RODITELJ','UCENIK','ADMIN')")
	@GetMapping("/{id}")
	public ResponseEntity<String> getGradeById(@PathVariable int id) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Pozvana metoda getGradeById sa parametrom: id={}", formattedTime, id);
		
		try {
			String response = ocena1Service.getGradeById(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NotFoundException e) {
			logger.error("[{}] Ocenu sa id={} nije pronadjena.", formattedTime, id, e.getMessage());

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<String> createGrade(@RequestParam int novaOcena, @RequestParam int novaZakljucnaOcena, @RequestParam int prvoPolugodiste, @RequestParam int drugoPolugodiste,@RequestParam int usmeni, @RequestParam int pismeni, @RequestParam int vladanje,Integer predmetId, Integer nastavnikId, Integer ucenikId) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Pozvana metoda createGrade sa parametrima: novaOcena={}, novaZakljucnaOcena={},prvoPolugodiste={}, drugoPolugodiste={},  usmeni={}, pismeni={} , vladanje={}, predmetId={}, nastavnikId={}, ucenikId={}",
				formattedTime, novaOcena, novaZakljucnaOcena,prvoPolugodiste, 
				drugoPolugodiste, usmeni, pismeni, vladanje,predmetId, nastavnikId, ucenikId);

		try {
	        String response = ocena1Service.createGrade(novaOcena, novaZakljucnaOcena,prvoPolugodiste,drugoPolugodiste, pismeni, usmeni, vladanje, predmetId, nastavnikId, ucenikId);
	        String studentName = "Mina Vasiljevic"; 
	        String parentEmail = "milosstojke30@gmail.com";
	        String subject = "Nova ocena je dodata";
	        String message = "Nastavnik -ime- je dodao novu ocenu za ucenika Mina Vasiljevic, iz predmeta -naziv-. Nova ocena: " + novaOcena + ", Nova zakljucna ocena: " + novaZakljucnaOcena;
	        emailService.sendEmailToParent(studentName, parentEmail, subject, message);
	        
	        return new ResponseEntity<>(response, HttpStatus.CREATED);
	    } catch (Exception e) {
	        return new ResponseEntity<>("Greska pri dodavanju ocene: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK','ADMIN')")
	@PutMapping("/update/{idOcene}")
	public ResponseEntity<String> updateGrade(@PathVariable int idOcene, @RequestParam int izmenjenaOcena,
		@RequestParam int izmenjenoPrvoPolugodiste, @RequestParam int izmenjenoDrugoPolugodiste,@RequestParam int izmenjenUsmeni, @RequestParam int izmenjenPismeni,@RequestParam int izmenjenaZakljucnaOcena, @RequestParam int izmenjenoVladanje) {
		
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info(
				"[{}] Pozvana metoda updateGrade sa parametrima: idOcene={}, izmenjenaOcena={}, izmenjenoPrvoPolugodiste={}, izmenjenoDrugoPolugodiste={},izmenjenUsmeni={}, izmenjenPismeni={},izmenjenaZakljucnaOcena={}, izmenjenoVladanje ={}",
				formattedTime, idOcene, izmenjenaOcena, izmenjenoPrvoPolugodiste,
				izmenjenoDrugoPolugodiste,izmenjenUsmeni, izmenjenPismeni, izmenjenaZakljucnaOcena, izmenjenoVladanje);
		
		try {
			String response = ocena1Service.updateGrade( idOcene,  izmenjenaOcena, izmenjenoPrvoPolugodiste, izmenjenoDrugoPolugodiste,  izmenjenUsmeni,  izmenjenPismeni,  izmenjenaZakljucnaOcena,  izmenjenoVladanje);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NotFoundException e) {
			logger.error("[{}] Ocena sa id={} nije azurirana.", formattedTime, idOcene, e.getMessage());

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK','ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteGradeById(@PathVariable int id) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Pozvana metoda deleteGradeById sa parametrom: id={}", formattedTime, id);

		try {
			String response = ocena1Service.deleteGradeById(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NotFoundException e) {
			logger.error("[{}] Nije moguce obrisati ocenu sa id={}.", formattedTime, id, e.getMessage());

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
//----------------------------------------------------------------------------------
//	private Ocena1Repository ocena1Repository;
//
//	@GetMapping("/allgrades")
//	public Iterable<Ocena1Entity> getAllOcene() {
//	    return ocena1Repository.findAll();
//	}
//	
//	@GetMapping
//	public String getGrade(@PathVariable int ocenaId) {
//		Ocena1Entity ocena = ocena1Repository.findById(ocenaId);
//		if (ocena != null) {
//			return "Ocena sa id-jem " + ocenaId + " je pronadjena.";
//		} else {
//			return "Ocena sa id-jem " + ocenaId + "  nije pronadjena.";
//
//		}
//	}
//
//	@PostMapping
//	public String addNewGrade(@RequestParam int ocenaUcenika, @RequestParam String prvoPolugodiste,
//			@RequestParam String drugoPolugodiste,
//
//			@RequestParam int novaOcena, @RequestParam String novoPrvoPolugodiste,
//			@RequestParam String novoDrugoPolugodiste) {
//		if (novaOcena < 1 || novaOcena > 5) {
//			return "Ocena mora biti u opsegu od 1 do 5.";
//		}
//		Ocena1Entity ocena = new Ocena1Entity();
//		ocena.setOcena(novaOcena);
//		ocena.setPrvoPolugodiste(novoPrvoPolugodiste);
//		ocena.setDrugoPolugodiste(novoDrugoPolugodiste);
//		ocena1Repository.save(ocena);
//		return "Nova ocena " + ocena + " je uspesno dodata.";
//	}
//
//	@PutMapping
//	public String updateGrade(@PathVariable int idOcene, @RequestParam int ocenaUcenika,
//			@RequestParam String prvoPolugodiste, @RequestParam String drugoPolugodiste,
//
//			@RequestParam int izmenjenaOcena, @RequestParam String izmenjenoPrvoPolugodiste,
//			@RequestParam String izmenjenoDrugoPolugodiste) {
//		Ocena1Entity ocena = ocena1Repository.findById(idOcene);
//		if (ocena != null) {
//			ocena.setOcena(izmenjenaOcena);
//			ocena.setPrvoPolugodiste(izmenjenoPrvoPolugodiste);
//			ocena.setDrugoPolugodiste(izmenjenoDrugoPolugodiste);
//
//			ocena1Repository.save(ocena);
//			return " Ocena sa id-jem" + idOcene + " je uspesno azurirana.";
//		} else {
//			return " Ocena id-jem" + idOcene + " nije pronadjena.";
//
//		}
//
//	}
//
//	@DeleteMapping
//	public String deleteGrade(@PathVariable int ocenaId) {
//		Ocena1Entity ocena = ocena1Repository.findById(ocenaId);
//		if (ocena != null) {
//			ocena1Repository.delete(ocena);
//			return "Ocena " + ocena + " je uspesno obrisana.";
//		} else {
//			return "Ocena " + ocena + " nije pronadjena.";
//
//		}
//	}
}
