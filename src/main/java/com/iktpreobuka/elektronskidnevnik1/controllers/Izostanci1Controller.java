package com.iktpreobuka.elektronskidnevnik1.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

import com.iktpreobuka.elektronskidnevnik1.entity.Izostanci1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.repositories.Izostanci1Repository;
import com.iktpreobuka.elektronskidnevnik1.service.Izostanci1Service;

@RestController
@RequestMapping("/absence")
public class Izostanci1Controller {

	private static final Logger logger = LoggerFactory.getLogger(Izostanci1Controller.class);

	@Autowired
	private Izostanci1Service izostanci1Service;

	// private Izostanci1Repository izostanci1Repository;

	public Izostanci1Controller(Izostanci1Service izostanci1Service) {
		this.izostanci1Service = izostanci1Service;
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK','ADMIN')")
	@GetMapping("/all")
	public ResponseEntity<Iterable<Izostanci1Entity>> getAllAbsences() {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		Iterable<Izostanci1Entity> izostanci = izostanci1Service.getAllAbsences();
		logger.info("[{}] Prikazani su svi izostanci.", formattedTime);
		return new ResponseEntity<>(izostanci, HttpStatus.OK);
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'RODITELJ', 'UCENIK', 'ADMIN')")
	@GetMapping("/get/{id}")
	public ResponseEntity<Izostanci1Entity> getAbsenceById(@PathVariable int id) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Prikazan je izostanak sa id-jem: {}", formattedTime, id);
		try {
			Izostanci1Entity izostanak = izostanci1Service.getAbsenceById(id);
			return new ResponseEntity<>(izostanak, HttpStatus.OK);
		} catch (NotFoundException e) {
			logger.error("Izostanak sa id-jem {} nije pronadjen: {}", id, e.getMessage());
			return new ResponseEntity<Izostanci1Entity>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<Izostanci1Entity> createAbsence(@RequestParam String opravdani,
			@RequestParam String neopravdani, @RequestParam String datumIzostanka,@RequestParam Ucenik1Entity ucenik) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Kreiran je novi izostanak sa id-jem: id={}, opravdani={}, neopravdani={}, datumIzostanka={},ucenik={}.", formattedTime);
		try {
			Izostanci1Entity noviIzostanak = izostanci1Service.createAbsence(opravdani, neopravdani, datumIzostanka, ucenik);
			return new ResponseEntity<>(noviIzostanak, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("[{}] Greska kod kreiranja izostanka: {}", formattedTime, e.getMessage());
			return new ResponseEntity<Izostanci1Entity>(HttpStatus.BAD_REQUEST);
		}
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<Izostanci1Entity> updateAbsence(@PathVariable int id, @RequestParam String noviOpravdani,
			@RequestParam String noviNeopravdani, @RequestParam String novDatumIzostanka, @RequestParam Ucenik1Entity ucenik) throws NotFoundException {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Izostanak sa id-jem {} je uspesno azuriran.", formattedTime, id);
		try {
			Izostanci1Entity updatedAbsence = izostanci1Service.updateAbsence(id, noviOpravdani, noviNeopravdani,
					novDatumIzostanka);
			return new ResponseEntity<>(updatedAbsence, HttpStatus.OK);
		} catch (NotFoundException e) {
			logger.error("[{}] Izostanak sa id-jem {} nije pronadjen: {}", formattedTime, id, e.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PreAuthorize("hasAnyRole('NASTAVNIK', 'ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Izostanci1Entity> deleteAbsence(@PathVariable int id) {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);
		logger.info("[{}] Izostanak sa id-jem {} je uspesno obrisan.", formattedTime, id);
		try {
			izostanci1Service.deleteAbsence(id);
			return new ResponseEntity<Izostanci1Entity>(HttpStatus.OK);
		} catch (NotFoundException e) {
			logger.error("[{}] Izostanak sa id-jem {} nije pronadjen: {}", formattedTime, id, e.getMessage());
			return new ResponseEntity<Izostanci1Entity>(HttpStatus.NOT_FOUND);
		}
	}

}

//-------------------------------------------------------------------------------

//	@GetMapping("/allabsences")
//	public Iterable<Izostanci1Entity> getAllAbsences() {
//	    return izostanci1Repository.findAll();
//	}
//	
//	@GetMapping
//	public String getIzostanakById(@PathVariable int idIzostanka) {
//		Izostanci1Entity izostanak = izostanci1Repository.findById(idIzostanka);
//		if(izostanak != null) {
//			return " Izostanak sa id-jem " + idIzostanka + " je uspesno pronadjen.";
//		}else {
//			return " Izostanak sa id-jem " + idIzostanka + " nije pronadjen.";
//
//		}
//	}
//	
//	
//	
//	@PostMapping
//	public String addNewAbsence(@RequestParam String opravdani, @RequestParam String neopravdani,
//			@RequestParam String datumIzostanka) {
//		Izostanci1Entity noviIzostanak = new Izostanci1Entity();
//		noviIzostanak.setOpravdani(opravdani);
//		noviIzostanak.setNeopravdani(neopravdani);
//		noviIzostanak.setDatumIzostanka(datumIzostanka);
//		izostanci1Repository.save(noviIzostanak);
//
//		if (izostanci1Repository.existsById(noviIzostanak.getId())) {
//			return "Izostanak " + noviIzostanak + "' je uspesno dodat.";
//		} else {
//			return "Izostanak " + noviIzostanak + " nije dodat.";
//		}
//	}
//
//	@PutMapping
//	public String updateAbsence(@PathVariable int idIzostanka, @RequestParam String opravdani, String neopravdani,
//			String datumIzostanka, @RequestParam String noviOpravdani, @RequestParam String noviNeopravdani,
//			@RequestParam String novDatumIzostanka,
//			@RequestParam String izmenjenOpravdani, String izmenjenNeopravdani, String izmenjenDatumIzostanka) {
//		Izostanci1Entity izostanak = izostanci1Repository.findById(idIzostanka);
//		if (izostanak != null) {
//			izostanak.setOpravdani(izmenjenOpravdani);
//			izostanak.setNeopravdani(izmenjenNeopravdani);
//			izostanak.setDatumIzostanka(izmenjenDatumIzostanka);
//			izostanci1Repository.save(izostanak);
//				return " Izostanak sa id-jem " + idIzostanka + " je uspesno izmenjen. ";
//			} else {
//				return "Izostanak sa id-jem " + idIzostanka + " nije izmenjen.";
//			}
//		
//
//	}
//
//	@DeleteMapping
//	public String deleteAbsenceById(@PathVariable int idIzostanka) {
//		Izostanci1Entity izostanak = izostanci1Repository.findById(idIzostanka);
//		if (izostanak != null) {
//			izostanci1Repository.delete(izostanak);
//			if (!izostanci1Repository.existsById(idIzostanka)) {
//				return "Izostanak sa ID-jem " + idIzostanka + " je uspesno obrisan";
//			} else {
//				return "Izostanak sa ID-jem " + idIzostanka + " nije obrisan";
//			}
//		} else {
//			return "Izostanak sa ID-jem " + idIzostanka + " nije pronadjen";
//
//		}
//	}
