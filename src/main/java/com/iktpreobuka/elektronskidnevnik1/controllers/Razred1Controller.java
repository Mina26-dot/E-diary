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

import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;
//import com.iktpreobuka.elektronskidnevnik1.repositories.Razred1Repository;
import com.iktpreobuka.elektronskidnevnik1.service.Razred1Service;

@RestController
@RequestMapping("/class")
public class Razred1Controller {

	private static final Logger logger = LoggerFactory.getLogger(Razred1Controller.class);

	@Autowired
	private final Razred1Service razred1Service;

	public Razred1Controller(Razred1Service razred1Service) {
		this.razred1Service = razred1Service;
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK','ADMIN')")
	@GetMapping("/all")
	public Iterable<Razred1Entity> getAllClasses() {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Pozvana metoda getAllClasses", formattedTime);

		return razred1Service.getAllClasses();
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'RODITELJ', 'UCENIK', 'ADMIN')")
	@GetMapping("/{id}")
	public String getClassById(@PathVariable int id) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Pozvana metoda getClassById sa parametrom: id={}",formattedTime, id);

		try {
			return razred1Service.getClassById(id);
		} catch (NotFoundException e) {
			logger.error("[{}] Razred sa id-jem {} nije pronadjen.", formattedTime, id);

			return e.getMessage();
		}
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@PostMapping("/create")
	public String createClass(@RequestParam String noviRazred,@RequestParam String novoOdeljenje) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Pozvana metoda createClass sa parametrom: noviRazred={}, novoOdeljenje={}",formattedTime, noviRazred, novoOdeljenje);

		return razred1Service.createClass(noviRazred, novoOdeljenje);
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK','ADMIN')")
	@PutMapping("/{id}")
	public String updateClass(@PathVariable int id, @RequestParam String izmenjenRazredUcenika, @RequestParam String izmenjenoOdeljenje) throws NotFoundException {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Pozvana metoda updateClass sa parametrima: id={}, izmenjenRazredUcenika={}, izmenjenoOdeljenje={}",formattedTime, id,
				izmenjenRazredUcenika,izmenjenoOdeljenje);

		return razred1Service.updateClass(id, izmenjenRazredUcenika,izmenjenoOdeljenje);
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@DeleteMapping("/{id}")
	public String deleteClassById(@PathVariable int id) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Pozvana metoda deleteClassById sa parametrom: id={}",formattedTime, id);

		try {
			return razred1Service.deleteClassById(id);
		} catch (NotFoundException e) {
			logger.error("[{}] Razred sa id-jem {} nije pronadjen.",formattedTime, id);

			return e.getMessage();
		}
	}
//----------------------------------------------------------------------------------------------	
//	private Razred1Repository razred1Repository;
//
//	@GetMapping("/allclasses")
//	public Iterable<Razred1Entity> getAllClasses() {
//	    return razred1Repository.findAll();
//	}
//	
//	@GetMapping
//	public String getClass(@PathVariable int idRazreda) {
//		Razred1Entity razred = razred1Repository.findById(idRazreda);
//		if (razred != null) {
//			return " Razred sa id-jem " + idRazreda + " je uspesno pronadjen.";
//		} else {
//			return " Razred sa id-jem " + idRazreda + " nije pronadjen.";
//
//		}
//	}
//
//	@PostMapping
//	public String addNewClass(@RequestParam String noviRazred) {
//		Razred1Entity razred = new Razred1Entity();
//
//		razred.setRazredUcenika(noviRazred);
//		razred1Repository.save(razred);
//
//		return "Novi razred " + noviRazred + " je uspesno dodat.";
//
//	}
//
//	@PutMapping
//	public String updateClass(@PathVariable int idRazreda, @RequestParam String razredUcenika,
//			@RequestParam String izmenjenRazredUcenika) {
//		Razred1Entity razred = razred1Repository.findById(idRazreda);
//		if (razred != null) {
//			razred.setRazredUcenika(izmenjenRazredUcenika);
//			razred1Repository.save(razred);
//			return "Razred " + razredUcenika + " je uspesno azuriran na " + izmenjenRazredUcenika + ".";
//		} else {
//			return "Razred " + razredUcenika + " nije pronadjen.";
//
//		}
//
//	}
//
//	@DeleteMapping
//	public String deleteClassById(@PathVariable int idRazreda) {
//		Razred1Entity razred = razred1Repository.findById(idRazreda);
//		if (razred != null) {
//			razred1Repository.deleteById(idRazreda);
//			if (!razred1Repository.existsById(idRazreda)) {
//				return " Razred sa id-jem " + idRazreda + " je uspesno obrisan.";
//
//			} else {
//				return " Razred sa id-jem " + idRazreda + " nije obrisan.";
//
//			}
//		} else {
//			return " Razred sa id-jem " + idRazreda + " nije pronadjen.";
//
//		}
//
//	}
}
