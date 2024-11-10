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

//import com.iktpreobuka.elektronskidnevnik1.entity.Korisnik1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;
///import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;
//import com.iktpreobuka.elektronskidnevnik1.repositories.Nastavnik1Repository;
import com.iktpreobuka.elektronskidnevnik1.service.Nastavnik1Service;

@RestController
@RequestMapping("/teacher")
public class Nastavnik1Controller {

	private static final Logger logger = LoggerFactory.getLogger(Nastavnik1Controller.class);

	@Autowired
	private final Nastavnik1Service nastavnik1Service;

	public Nastavnik1Controller(Nastavnik1Service nastavnik1Service) {
		this.nastavnik1Service = nastavnik1Service;
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'RODITELJ', 'UCENIK', 'ADMIN')")
	@GetMapping("/all")
	public ResponseEntity<Iterable<Nastavnik1Entity>> getAllTeachers() {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}]Pozvana metoda getAllTeachers.", formattedTime);
		Iterable<Nastavnik1Entity> nastavnici = nastavnik1Service.getAllTeachers();
		return new ResponseEntity<>(nastavnici, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK','ADMIN')")
	@GetMapping("/get/{id}")
	public ResponseEntity<String> getTeacherById(@PathVariable int id) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Pozvana metoda getTeacherById sa parametrima: id={}", formattedTime, id);
		try {
			String response = nastavnik1Service.getTeacherById(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK','ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<String> createTeacher(@RequestParam String imeNastavnika,
			@RequestParam String prezimeNastavnika) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Pozvana metoda createTeacher sa parametrima: imeNastavnika={}, prezimeNastavnika={}",
				formattedTime, imeNastavnika, prezimeNastavnika);
		String response = nastavnik1Service.createTeacher(imeNastavnika, prezimeNastavnika);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK','ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateTeacher(@PathVariable int id, @RequestParam String novoImeNastavnika,
			@RequestParam String novoPrezimeNastavnika) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info(
				"[{}] Pozvana metoda updateTeacher sa parametrima: id={}, novoImeNastavnika={}, novoPrezimeNastavnika={}",formattedTime,
				id, novoImeNastavnika, novoPrezimeNastavnika);
		try {
			String response = nastavnik1Service.updateTeacher(id, novoImeNastavnika, novoPrezimeNastavnika);
			
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NotFoundException e) {
			logger.error("[{}] Greska prilikom azuriranja nastavnika sa id-jem: {}",formattedTime, id, e);

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK','ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTeacherById(@PathVariable int id) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Pozvana metoda deleteTeacherById sa parametrom: id={}", formattedTime, id);

		try {
			String response = nastavnik1Service.deleteTeacherById(id);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NotFoundException e) {
			logger.error("[{}] Greska prilikom brisanja nastavnika sa id-jem: {}", formattedTime, id, e);

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
//------------------------------------------------------------------------------------------
//	@Autowired
//	private Nastavnik1Repository nastavnik1Repository;
//
//	@GetMapping("/allteachers")
//	public Iterable<Nastavnik1Entity> getAllNastavnik() {
//	    return nastavnik1Repository.findAll();
//	}
//	
//	@GetMapping
//	public String getTeacherById(@PathVariable int idNastavnika) {
//		Nastavnik1Entity nastavnik = nastavnik1Repository.findById(idNastavnika);
//		if (nastavnik != null) {
//			return "Nastavnik sa id-jem " + idNastavnika + " je uspesno pronadjen.";
//		} else {
//			return "Nastavnik sa id-jem " + idNastavnika + " nije pronadjen.";
//
//		}
//	}
//
//	@PostMapping
//	public String addNewTeacher(@RequestParam String imeNastavnika, @RequestParam String prezimeNastavnika) {
//
//		Nastavnik1Entity nastavnik = new Nastavnik1Entity();
//		nastavnik.setImeNastavnika(imeNastavnika);
//		nastavnik.setPrezimeNastavnika(prezimeNastavnika);
//		nastavnik1Repository.save(nastavnik);
//
//		return "Nastavnik " + imeNastavnika + " " + prezimeNastavnika + " je uspesno dodat.";
//	}
//
//	@PutMapping
//	public String updateTeacher(@PathVariable int idNastavnika, @RequestParam String imeNastavnika, @RequestParam String prezimeNastavnika,
//			 @RequestParam String novoImeNastavnika,
//			@RequestParam String novoPrezimeNastavnika) {
//
//		Nastavnik1Entity nastavnik = nastavnik1Repository.findById(idNastavnika);
//		if (nastavnik != null) {
//			nastavnik.setImeNastavnika(novoImeNastavnika);
//			nastavnik.setPrezimeNastavnika(novoPrezimeNastavnika);
//
//			nastavnik1Repository.save(nastavnik);
//			return "Nastavnik sa id-jem" + idNastavnika +  " uspesno azuriran";
//		} else {
//			return "Nastavnik sa id-jem" + idNastavnika +  " nije pronadjen.";
//		}
//	}
//
//	@DeleteMapping
//	public String deleteTeacherById(@PathVariable int idNastavnika) {
//		Nastavnik1Entity nastavnik = nastavnik1Repository.findById(idNastavnika);
//		if (nastavnik != null) {
//			nastavnik1Repository.deleteById(idNastavnika);
//			if (!nastavnik1Repository.existsById(idNastavnika)) {
//				return "Nastavnik sa id-jem " + idNastavnika + " je uspesno obrisan.";
//			} else {
//
//				return "Nastavnik sa id-jem " + idNastavnika + " nije obrisan.";
//			}
//			
//		} else {
//			return "Nastavnik sa id-jem " + idNastavnika + " nije pronadjen.";
//
//		}
//	}
}