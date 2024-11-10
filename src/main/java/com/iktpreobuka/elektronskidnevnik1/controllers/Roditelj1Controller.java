package com.iktpreobuka.elektronskidnevnik1.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.iktpreobuka.elektronskidnevnik1.entity.Korisnik1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Roditelj1Entity;
//import com.iktpreobuka.elektronskidnevnik1.repositories.Roditelj1Repository;
import com.iktpreobuka.elektronskidnevnik1.service.Roditelj1Service;

@RestController
@RequestMapping("/parent")
public class Roditelj1Controller {

	private static final Logger logger = LoggerFactory.getLogger(Roditelj1Controller.class);

	
	@Autowired
	private final Roditelj1Service roditelj1Service;

	public Roditelj1Controller(Roditelj1Service roditelj1Service) {
		this.roditelj1Service = roditelj1Service;
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK','ADMIN')")

	@GetMapping("/all")
	public Iterable<Roditelj1Entity> getAllParents() {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
	    logger.info("[{}] Pozvana metoda getAllParents", formattedTime);

		return roditelj1Service.getAllParents();
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'RODITELJ', 'UCENIK', 'ADMIN')")
	@GetMapping("/get/{id}")
	public String getParentById(@PathVariable int id) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
	    logger.info("[{}] Pozvana metoda getParentById sa parametrom: id={}",formattedTime, id);

		try {
			return roditelj1Service.getParentById(id);
		} catch (NotFoundException e) {
	        logger.error("[{}] Roditelj sa id-jem {} nije pronadjen.",formattedTime, id);

			return e.getMessage();
		}
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@PostMapping("/create")
	public String addNewParent(@RequestParam String ime, @RequestParam String prezime, @RequestParam String email) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
	    logger.info("[{}] Pozvana metoda addNewParent sa parametrima: ime={}, prezime={}, email={}",formattedTime, ime, prezime, email);
		return roditelj1Service.createParent(ime, prezime, email);
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@PutMapping("/update/{id}")
	public String updateParent(@PathVariable int id, @RequestParam String ime, @RequestParam String prezime,
			@RequestParam String email) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
	    logger.info("[{}] Pozvana metoda updateParent sa parametrima: id={}, ime={}, prezime={}, email={}",formattedTime, id, ime, prezime, email);

		try {
			return roditelj1Service.updateParent(id, ime, prezime, email);
		} catch (NotFoundException e) {
	        logger.error("[{}] Roditelj sa id-jem {} nije pronadjen.",formattedTime, id);

			return e.getMessage();
		}
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@DeleteMapping("/delete/{id}")
	public String deleteParentById(@PathVariable int id) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
	    logger.info("[{}] Pozvana metoda deleteParentById sa parametrom: id={}",formattedTime, id);

		try {
			return roditelj1Service.deleteParentById(id);
		} catch (NotFoundException e) {
	        logger.error("[{}] Roditelj sa id-jem {} nije pronadjen.", formattedTime, id);

			return e.getMessage();
		}
	}
//---------------------------------------------------------------------------------------------------------------
//	@Autowired
//	private Roditelj1Repository roditelj1Repository;
//
//	@GetMapping("/allparents")
//	public Iterable<Roditelj1Entity> getAllParents() {
//	    return roditelj1Repository.findAll();
//	}
//	
//	@GetMapping
//	public String getParent(@PathVariable int idRoditelja) {
//		Roditelj1Entity roditelj = roditelj1Repository.findById(idRoditelja);
//		if (roditelj != null) {
//			return " Roditelj sa id-jem " + idRoditelja + " je uspesno pronadjen.";
//		} else {
//			return " Roditelj sa id-jem " + idRoditelja + " nije pronadjen.";
//
//		}
//	}
//
//	@PostMapping
//	public String addNewParent(@RequestParam String imeRoditelja, @RequestParam String prezimeRoditelja,
//			@RequestParam String emailRoditelja) {
//
//		Roditelj1Entity roditelj = new Roditelj1Entity();
//		roditelj.setImeRoditelja(imeRoditelja);
//		roditelj.setPrezimeRoditelja(prezimeRoditelja);
//		roditelj.setEmailRoditelja(emailRoditelja);
//		// roditelj.setDeca(deca);
//		roditelj1Repository.save(roditelj);
//
//		return "Novi roditelj " + imeRoditelja + " " + prezimeRoditelja + " je uspesno dodat";
//
//	}
//
//	@PutMapping
//	public String updateParent(@PathVariable int idRoditelja, @RequestParam String imeRoditelja,
//			@RequestParam String prezimeRoditelja, @RequestParam String emailRoditelja,
//			@RequestParam String izmenjenoImeRoditelja, @RequestParam String izmenjenoPrezimeRoditelja,
//			@RequestParam String izmenjnEmailRoditelja) {
//
//		Roditelj1Entity roditelj = roditelj1Repository.findById(idRoditelja);
//		if (roditelj != null) {
//			roditelj.setImeRoditelja(izmenjenoPrezimeRoditelja);
//			roditelj.setPrezimeRoditelja(izmenjenoPrezimeRoditelja);
//			roditelj.setEmailRoditelja(izmenjnEmailRoditelja);
//			//roditelj.setDeca(deca);
//			roditelj1Repository.save(roditelj);
//
//			return "Roditelj sa id-jem " + idRoditelja + " je uspesno azuriran.";
//		} else {
//			return "Roditelj sa id-jem " + idRoditelja + " nije pronadjen.";
//
//		}
//	}
//
//	@DeleteMapping
//	public String deleteParentById(@PathVariable int idRoditelja) {
//		Roditelj1Entity roditelj = roditelj1Repository.findById(idRoditelja);
//		if (roditelj != null) {
//			roditelj1Repository.delete(roditelj);
//			if (!roditelj1Repository.existsById(idRoditelja)) {
//				return "Roditelj sa id-jem " + idRoditelja + " je uspesno obrisan.";
//			} else {
//				return "Roditelj sa id-jem " + idRoditelja + " nije obrisan.";
//
//			}
//		} else {
//			return "Roditelj sa id-jem " + idRoditelja + " nije pronadjen.";
//
//		}
//	}
}
