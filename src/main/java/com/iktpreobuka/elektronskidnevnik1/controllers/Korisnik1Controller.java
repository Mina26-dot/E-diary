package com.iktpreobuka.elektronskidnevnik1.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//import java.util.ArrayList;
//import java.util.List;

//import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronskidnevnik1.entity.Korisnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.RoleEntity;
//import com.iktpreobuka.elektronskidnevnik1.repositories.Korisnik1Repository;
import com.iktpreobuka.elektronskidnevnik1.service.Korisnik1Service;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class Korisnik1Controller {

	private static final Logger logger = LoggerFactory.getLogger(Korisnik1Controller.class);

	@Autowired
	private final Korisnik1Service korisnik1Service;

	public Korisnik1Controller(Korisnik1Service korisnik1Service) {
		this.korisnik1Service = korisnik1Service;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/all")
	public ResponseEntity<Iterable<Korisnik1Entity>> getAllUsers() {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Pozvana metoda getAllUsers.", formattedTime);
		Iterable<Korisnik1Entity> korisnici = korisnik1Service.getAllUsers();
		return new ResponseEntity<>(korisnici, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/get/{id}")
	public ResponseEntity<?> getUserById(@PathVariable int id) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Pozvana metoda getUserById sa parametrom: id={}", formattedTime, id);
		try {
			String response = korisnik1Service.getUserById(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/add")
	public ResponseEntity<String> addNewUser(@Valid @RequestBody String korisnickoIme,
			@RequestParam String sifraKorisnika, @RequestParam String uloga) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Pozvana metoda addNewUser sa parametrima: korisnickoIme={}, sifraKorisnika={}, uloga = {}",
				formattedTime, korisnickoIme, sifraKorisnika, uloga);
		String response = korisnik1Service.createUser(korisnickoIme, sifraKorisnika, uloga);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateUser(@PathVariable int id, @RequestParam @Valid String izmenjenoKorisnickoIme,
			@RequestParam String izmenjenaSifraKorisnika, String uloga) throws NotFoundException {

		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info(
				"[{}] Pozvana metoda updateUser sa parametrima: id={}, izmenjenoKorisnickoIme={}, izmenjenaSifraKorisnika={}",
				formattedTime, id, izmenjenoKorisnickoIme, izmenjenaSifraKorisnika, uloga);

		String response = korisnik1Service.updateUser(id, izmenjenoKorisnickoIme, izmenjenaSifraKorisnika, uloga);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable int id) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Pozvana metoda deleteUserById sa parametrom: id={}", formattedTime, id);

		try {
			String response = korisnik1Service.deleteUserById(id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NotFoundException e) {
			logger.error("[{}] Greska prilikom brisanja korisnika sa id-jem: id={}" + formattedTime, id, e);

			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

//---------------------------------------------------------------------------------------------------

//------------------------------------------------------------------------------------------------------	
//	@Autowired
//	private Korisnik1Repository korisnik1Repository;
//
//	@GetMapping("/allusers")
//	public Iterable<Korisnik1Entity> getAllUsers() {
//	    return korisnik1Repository.findAll();
//	}
//	
//	@GetMapping
//	public String getUser(@PathVariable int idKorisnika) {
//		Korisnik1Entity korisnik = korisnik1Repository.findById(idKorisnika);
//		if(korisnik != null) {
//			return "Pribavljanje korisnika  " + korisnik + "sa id-jem " + idKorisnika + " je uspesno.";
//		}else {
//			return "Korisnik " + korisnik + " sa id-jem " + idKorisnika +  " nije pronadjen";
//
//		}
//	}
//	
//	@PostMapping
//	public String addNewUser(@RequestParam String korisnickoIme,@RequestParam String sifraKorisnika) {
//
//		Korisnik1Entity korisnik = new Korisnik1Entity();
//		korisnik.setKorisnickoIme(korisnickoIme);
//		korisnik.setSifra(sifraKorisnika);
//		korisnik1Repository.save(korisnik);
//
//		return "Korisnik " + korisnickoIme + " je uspesno dodat.";
//	}
//
//	@PutMapping("/{id}")
//	public String updateUser(@PathVariable int idKorisnika, @RequestParam String korisnickoIme,
//			@RequestParam String sifraKorisnika,@RequestParam String izmenjenoKorisnickoIme,
//			@RequestParam String izmenjenaSifraKorisnika) {
//		Korisnik1Entity korisnik = korisnik1Repository.findById(idKorisnika);
//		if (korisnik != null) {
//			korisnik.setKorisnickoIme(izmenjenoKorisnickoIme);
//			korisnik.setSifra(izmenjenaSifraKorisnika);
//			korisnik1Repository.save(korisnik);
//
//			return "Korisnik sa id-jem " + idKorisnika + " je uspesno azuriran.";
//		} else {
//			return "Korisnik sa id-jem " + idKorisnika + " nije pronadjen.";
//		}
//	}
//
//	@DeleteMapping
//	public String deleteUserById(@PathVariable int idKorisnika) {
//		Korisnik1Entity korisnik = korisnik1Repository.findById(idKorisnika);
//		if(korisnik != null) {
//			korisnik1Repository.delete(korisnik);
//			if(!korisnik1Repository.existsById(idKorisnika)) {
//				return "Korisnik sa id-jem " + idKorisnika + " je uspesno obrisan.";
//			}else {
//				return "Korisnik sa id-jem " + idKorisnika + " nije obrisan.";
//
//			}
//		}else {
//			return "Korisnik sa id-jem " + idKorisnika + " nije pronadjen.";
//
//		}
//
//	}
}
