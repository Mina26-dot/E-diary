package com.iktpreobuka.elektronskidnevnik1.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//import com.iktpreobuka.elektronskidnevnik1.entity.Izostanci1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Korisnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Ocena1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Predmet1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Roditelj1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.repositories.Ucenik1Repository;
import com.iktpreobuka.elektronskidnevnik1.service.Ucenik1Service;

@RestController
@RequestMapping("/student")
public class Ucenik1Controller {

	private static final Logger logger = LoggerFactory.getLogger(Ucenik1Controller.class);

	@Autowired
	private final Ucenik1Service ucenik1Service;

	public Ucenik1Controller(Ucenik1Service ucenik1Service) {
		this.ucenik1Service = ucenik1Service;

	}

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@GetMapping("/all")
	public Iterable<Ucenik1Entity> getAllStudents() {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Pozvana metoda getAllStudents", formattedTime);
		return ucenik1Service.getAllStudents();
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'RODITELJ', 'ADMIN')")
	@GetMapping("/{id}")
	public String getStudentById(@PathVariable int id) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Pozvana metoda getStudentById sa parametrom: id={}", formattedTime, id);

		try {
			return ucenik1Service.getStudentById(id);
		} catch (NotFoundException e) {
			logger.error("[{}] Ucenik sa id-jem {} nije pronadjen.", formattedTime, id);

			return e.getMessage();
		}

	}

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@PostMapping("/add")
	public String addNewStudent(@RequestParam String ime, @RequestParam String prezime,
			@RequestParam Roditelj1Entity roditelj,
			@RequestParam Razred1Entity razred) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Pozvana metoda addNewStudent sa parametrima: ime={}, prezime={}, roditelj={},razred={}", formattedTime, ime, prezime,
				 roditelj, razred);
		return ucenik1Service.createStudent(ime, prezime, roditelj, razred);
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")

	@PutMapping("/update/{id}")
	public String updateStudent(@PathVariable int id, @RequestParam String izmenjenoIme,
			@RequestParam String izmenjenoPrezime,
			@RequestParam Roditelj1Entity izmenjenRoditelj,
			 @RequestParam Razred1Entity izmenjeniRazred) throws NotFoundException {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info(
				"[{}] Pozvana metoda updateStudent sa parametrima: id={}, izmenjenoIme={}, izmenjenoPrezime={},izmenjeniKorisnik={},izmenjeniRoditelj={},izmenjeniPredmet={},izmenjeniRazred={},izmenjeniIzostanci={}",
				formattedTime, id, izmenjenoIme, izmenjenoPrezime, izmenjenRoditelj,
				 izmenjeniRazred);

		return ucenik1Service.updateStudent(id, izmenjenoIme, izmenjenoPrezime, izmenjenRoditelj,
				 izmenjeniRazred);
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@DeleteMapping("/delete/{id}")
	public String deleteStudentById(@PathVariable int id) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Pozvana metoda deleteStudentById sa parametrom: id={}", formattedTime, id);
		try {
			return ucenik1Service.deleteStudentById(id);
		} catch (NotFoundException e) {
			logger.error("[{}] Ucenik sa id-jem {} nije pronadjen.", formattedTime, id);
			return e.getMessage();
		}
	}
//---------------------------------------------------------------------------------------------------------
//	private Ucenik1Repository ucenik1Repository;
//
//	@GetMapping("/allstudents")
//	public Iterable<Ucenik1Entity> getAllStudents() {
//		return ucenik1Repository.findAll();
//	}
//
//	@GetMapping
//	public String getStudentByid(@PathVariable int idUcenika) {
//		Ucenik1Entity ucenik = ucenik1Repository.findById(idUcenika);
//		if (ucenik != null) {
//			return "Ucenik sa id-jem " + idUcenika + " je uspesno pronadjen.";
//		} else {
//			return "Ucenik sa id-jem " + idUcenika + " nije pronadjen.";
//
//		}
//	}
//
//	@PostMapping
//	public String addNewStudent(@RequestParam String imeUcenika, @RequestParam String prezimeUcenika) {
//		Ucenik1Entity ucenik = new Ucenik1Entity();
//		ucenik.setImeUcenika(imeUcenika);
//		ucenik.setPrezimeUcenika(prezimeUcenika);
//		ucenik1Repository.save(ucenik);
//
//		return " Nov ucenik " + imeUcenika + prezimeUcenika + " je uspesno dodat.";
//
//	}
//
//	@PutMapping
//	public String updateStudent(@PathVariable int idUcenika, @RequestParam String imeUcenika,
//			@RequestParam String prezimeUcenika, @RequestParam String izmenjenoImeUcenika,
//			@RequestParam String izmenjenoPrezimeUcenika) {
//		Ucenik1Entity ucenik = ucenik1Repository.findById(idUcenika);
//		if (ucenik != null) {
//			ucenik.setImeUcenika(izmenjenoImeUcenika);
//			ucenik.setPrezimeUcenika(izmenjenoPrezimeUcenika);
//			ucenik1Repository.save(ucenik);
//
//			return " Ucenik  " + imeUcenika + " " + prezimeUcenika + " je uspresno azuriran na " + izmenjenoImeUcenika
//					+ " " + izmenjenoPrezimeUcenika + " .";
//		} else {
//			return " Ucenik  " + imeUcenika + " " + prezimeUcenika + " nije azuriran.";
//
//		}
//	}
//
//	@DeleteMapping
//	public String deleteStudent(@PathVariable int idUcenika) {
//		Ucenik1Entity ucenik = ucenik1Repository.findById(idUcenika);
//		if (ucenik != null) {
//			ucenik1Repository.delete(ucenik);
//			if (!ucenik1Repository.existsById(idUcenika)) {
//				return " Ucenik sa id-jem " + idUcenika + " je uspesno obrisan.";
//			} else {
//				return " Ucenik sa id-jem " + idUcenika + " nije obrisan.";
//
//			}
//
//		} else {
//			return " Ucenik sa id-jem " + idUcenika + " nije pronadjen.";
//
//		}
//	}

}
