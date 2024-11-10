package com.iktpreobuka.elektronskidnevnik1.controllers;

import java.nio.file.AccessDeniedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iktpreobuka.elektronskidnevnik1.entity.Izostanci1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Izostanci1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Korisnik1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Ocena1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Predmet1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Roditelj1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.repositories.Administrator1Repository;
//import com.iktpreobuka.elektronskidnevnik1.repositories.Nastavnik1Repository;
//import com.iktpreobuka.elektronskidnevnik1.repositories.Ocena1Repository;
//import com.iktpreobuka.elektronskidnevnik1.repositories.Predmet1Repository;
//import com.iktpreobuka.elektronskidnevnik1.repositories.Predmet1Repository;
//import com.iktpreobuka.elektronskidnevnik1.repositories.Roditelj1Repository;
//import com.iktpreobuka.elektronskidnevnik1.repositories.Ucenik1Repository;
import com.iktpreobuka.elektronskidnevnik1.service.Administrator1Service;
//import com.iktpreobuka.elektronskidnevnik1.service.Izostanci1Service;
//import com.iktpreobuka.elektronskidnevnik1.service.Korisnik1Service;
//import com.iktpreobuka.elektronskidnevnik1.service.Nastavnik1Service;
//import com.iktpreobuka.elektronskidnevnik1.service.Ocena1Service;
//import com.iktpreobuka.elektronskidnevnik1.service.Predmet1Service;
//import com.iktpreobuka.elektronskidnevnik1.service.Roditelj1Service;
//import com.iktpreobuka.elektronskidnevnik1.service.Ucenik1Service;

@RestController
@RequestMapping("/admin")
public class Administrator1Controller {

	private static final Logger logger = LoggerFactory.getLogger(Administrator1Controller.class);

	@Autowired
	private Administrator1Service admin1Service;

	//------------------------------------------------ metode za korisnike --------------------------------------------------------------------------------------------------

	@GetMapping("/all")
	public ResponseEntity<Iterable<Korisnik1Entity>> getAllUsers() throws AccessDeniedException {
		logger.info("Pozvana metoda getAllUsers");
		Iterable<Korisnik1Entity> korisnici = admin1Service.getAllUsers();
		return new ResponseEntity<>(korisnici, HttpStatus.OK);
	}

	

	@GetMapping("/get/{id}")
	public ResponseEntity<String> getUserById(@PathVariable int id) {
	    try {
	        String response = admin1Service.getUserById(id);
	        logger.info("Prikazan korisnik sa id-jem: {}", id);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (NotFoundException e) {
	        logger.error("Korisnik sa id-jem {} nije pronadjen.", id, e);
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@PostMapping("/createuser")
	public ResponseEntity<String> createUser(@RequestParam String korisnickoIme, @RequestParam String sifra) {

	    String response = admin1Service.createUser(korisnickoIme, sifra);
	    logger.info("Kreiran novi korisnik: {}", korisnickoIme);
	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateUser(@PathVariable int id, @RequestParam String korisnickoIme,
	        @RequestParam String sifra, @RequestParam String izmenjenoKorisnickoIme,
	        @RequestParam String izmenjenaSifra) {
	   

	    try {
	        String response = admin1Service.updateUser(id, korisnickoIme, sifra, izmenjenoKorisnickoIme, izmenjenaSifra);
	        logger.info("Azuriran korisnik sa id-jem: {}", id);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (NotFoundException e) {
	        logger.error("Korisnik sa id-jem {} nije pronadjen prilikom azuriranja.", id, e);
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable int id) {
	    

	    logger.info("Obrisan korisnik sa id-jem: {}", id);
	    try {
	        String response = admin1Service.deleteUserById(id);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (NotFoundException e) {
	        logger.error("Korisnik sa id-jem {} nije pronadjen prilikom brisanja.", id, e);
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
//----------------------------------------------------metode za predmet---------------------------------------------------------------------------------------------	

	@GetMapping("/allsubjects")
	public ResponseEntity<Iterable<Predmet1Entity>> getAllSubjects() throws AccessDeniedException {
	    

	    Iterable<Predmet1Entity> predmeti = admin1Service.getAllSubjects();
	    logger.info("Prikazani su svi predmeti.");
	    return new ResponseEntity<>(predmeti, HttpStatus.OK);
	}

	@GetMapping("/getsubject/{id}")
	public ResponseEntity<String> getSubjectById(@PathVariable int id) {
		
	    try {
	        String response = admin1Service.getSubjectById(id);
	        logger.info("Prikazan predmet sa id-jem: {}", id);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (NotFoundException e) {
	        logger.error("Predmet sa id-jem {} nije pronadjen.", id, e);
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@PostMapping("/createsubject")
	public ResponseEntity<String> createSubject(@RequestParam String imePredmeta,
	        @RequestParam int nedeljniFondCasova) {
	   

	    String response = admin1Service.createSubject(imePredmeta, nedeljniFondCasova);
	    logger.info("Kreiran novi predmet: {}", imePredmeta);
	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/updatesubject/{id}")
	public ResponseEntity<String> updateSubject(@PathVariable int id, @RequestParam String novoImePredmeta,
	        @RequestParam int noviNedeljniFondCasova) {
	    

	    try {
	        String response = admin1Service.updateSubject(id, novoImePredmeta, noviNedeljniFondCasova);
	        logger.info("Azuriran predmet sa id-jem: {}", id);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (NotFoundException e) {
	        logger.error("Predmet sa ID-jem {} nije pronadjen prilikom azuriranja.", id, e);
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@DeleteMapping("/deletesubject/{id}")
	public ResponseEntity<String> deleteSubjectById(@PathVariable int id) {
	   

	    try {
	        String response = admin1Service.deleteSubjectById(id);
	        logger.info("Obrisan predmet sa id-jem: {}", id);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (NotFoundException e) {
	        logger.error("Predmet sa id-jem {} nije pronadjen prilikom brisanja.", id, e);
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
//-----------------------------------------------------metode za nastavnika------------------------------------------------------------------------------------------

	@GetMapping("/allteachers")
	public ResponseEntity<?> getAllTeachers() throws AccessDeniedException {
	   
	    Iterable<Nastavnik1Entity> nastavnici = admin1Service.getAllTeachers();
	    logger.info("Prikazani su svi nastavnici.");
	    return new ResponseEntity<>(nastavnici, HttpStatus.OK);
	}

	@GetMapping("/teacher/{id}")
	public ResponseEntity<String> getTeacherById(@PathVariable int id) {
		
	    try {
	        String response = admin1Service.getTeacherById(id);
	        logger.info("Prikazan nastavnik sa ID-jem: {}", id);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (NotFoundException e) {
	        logger.error("Nastavnik sa id-jem {} nije pronadjen.", id, e);
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@PostMapping("/createteacher")
	public ResponseEntity<String> createTeacher(@RequestParam String imeNastavnika,
	        @RequestParam String prezimeNastavnika) {
		
	    String response = admin1Service.createTeacher(imeNastavnika, prezimeNastavnika);
	    logger.info("Kreiran novi nastavnik: {} {}", imeNastavnika, prezimeNastavnika);
	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/updateteacher/{id}")
	public ResponseEntity<String> updateTeacher(@PathVariable int id, @RequestParam String novoImeNastavnika,
	        @RequestParam String novoPrezimeNastavnika) {
		
	    try {
	        String response = admin1Service.updateTeacher(id, novoImeNastavnika, novoPrezimeNastavnika);
	        logger.info("Azuriran nastavnik sa id-jem: {}", id);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (NotFoundException e) {
	        logger.error("Nastavnik sa id-jem {} nije pronadjen prilikom azuriranja.", id, e);
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@DeleteMapping("/deleteteacher/{id}")
	public ResponseEntity<String> deleteTeacherById(@PathVariable int id) {
		
	    try {
	        String response = admin1Service.deleteTeacherById(id);
	        logger.info("Obrisan nastavnik sa id-jem: {}", id);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (NotFoundException e) {
	        logger.error("Nastavnik sa ID-jem {} nije pronadjen prilikom brisanja.", id, e);
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
//--------------------------------------------------metode za ucenika-------------------------------------------------------------------------------

	@GetMapping("/allstudents")
	public ResponseEntity<Iterable<Ucenik1Entity>> getAllStudents() throws AccessDeniedException {
		
		Iterable<Ucenik1Entity> ucenici = admin1Service.getAllStudents();
		logger.info("Prikazani su svi ucenici.");
		return new ResponseEntity<>(ucenici, HttpStatus.OK);
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<String> getStudentById(@PathVariable int id) {
		
		try {
			String response = admin1Service.getStudentById(id);
			logger.info("Prikazan ucenik sa id-jem: {}", id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NotFoundException e) {
			logger.error("Ucenik sa id-jem {} nije pronadjen.", id, e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/newstudents")
	public ResponseEntity<String> createStudent(@RequestParam String imeUcenika, @RequestParam String prezimeUcenika) {
		
		String response = admin1Service.createStudent(imeUcenika, prezimeUcenika);
		logger.info("Kreiran novi ucenik: {} {}", imeUcenika, prezimeUcenika);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/updatestudents/{id}")
	public ResponseEntity<String> updateStudent(@PathVariable int idUcenika, @RequestParam String imeUcenika,
			@RequestParam String prezimeUcenika, @RequestParam String novoImeUcenika,
			@RequestParam String novoPrezimeUcenika) {
		
		try {
			String response = admin1Service.updateStudent(idUcenika, imeUcenika, prezimeUcenika, novoImeUcenika,
					novoPrezimeUcenika);
			logger.info("Azuriran ucenik sa id-jem: {}", idUcenika);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NotFoundException e) {
			logger.error("Ucenik sa id-jem {} nije pronadjen.", idUcenika, e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/deletestudents/{id}")
	public ResponseEntity<String> deleteStudentById(@PathVariable int id) {
		
		try {
			String response = admin1Service.deleteStudentById(id);
			logger.info("Obrisan ucenik sa id-jem: {}", id);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NotFoundException e) {
			logger.error("Ucenik sa id-jem {} nije pronadjen prilikom brisanja.", id, e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
//--------------------------------------------metode za roditelja----------------------------------------------------------------------------------------

	@GetMapping("/allparents")
	public ResponseEntity<Iterable<Roditelj1Entity>> getAllParents() throws AccessDeniedException {
	    
	    Iterable<Roditelj1Entity> roditelji = admin1Service.getAllParents();
	    logger.info("Prikazani su svi roditelji.");
	    return new ResponseEntity<>(roditelji, HttpStatus.OK);
	}

	@GetMapping("/parent/{id}")
	public ResponseEntity<String> getParentById(@PathVariable int id) {
	    try {
	        
	        String response = admin1Service.getParentById(id);
	        logger.info("Prikazan roditelj sa id-jem: {}", id);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (NotFoundException e) {
	        logger.error("Roditelj sa id-jem {} nije pronadjen.", id, e);
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@PostMapping("/createparent")
	public ResponseEntity<String> createParent(@RequestParam String imeRoditelja, @RequestParam String prezimeRoditelja,
	        @RequestParam String emailRoditelja) {
	   
	    String response = admin1Service.createParent(imeRoditelja, prezimeRoditelja, emailRoditelja);
	    logger.info("Kreiran novi roditelj: {} {}", imeRoditelja, prezimeRoditelja);
	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/updateparent/{id}")
	public ResponseEntity<String> updateParent(@PathVariable int id, @RequestParam String izmenjenoImeRoditelja,
	        @RequestParam String izmenjenoPrezimeRoditelja, @RequestParam String izmenjenoEmailRoditelja) {
	    
	    try {
	        String response = admin1Service.updateParent(id, izmenjenoImeRoditelja, izmenjenoPrezimeRoditelja,
	                izmenjenoEmailRoditelja);
	        logger.info("Azuriran roditelj sa id-jem: {}", id);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (NotFoundException e) {
	        logger.error("Roditelj sa id-jem {} nije pronadjen prilikom azuriranja.", id, e);
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@DeleteMapping("/deleteparent/{id}")
	public ResponseEntity<String> deleteParentById(@PathVariable int id) {
	   
	    try {
	        String response = admin1Service.deleteParentById(id);
	        logger.info("Obrisan roditelj sa id-jem: {}", id);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (NotFoundException e) {
	        logger.error("Roditelj sa id-jem {} nije pronadjen prilikom brisanja.", id, e);
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
//------------------------------------------------metode za ocene--------------------------------------------------------------------------------

	@GetMapping("/allgrades")
	public ResponseEntity<Iterable<Ocena1Entity>> getAllGrades() throws AccessDeniedException {
	    
	    Iterable<Ocena1Entity> ocene = admin1Service.getAllGrades();
	    logger.info("Prikazane su sve ocene.");
	    return new ResponseEntity<>(ocene, HttpStatus.OK);
	}

	@GetMapping("/grade/{id}")
	public ResponseEntity<String> getGradeById(@PathVariable int id) {
	    try {
	        
	        String response = admin1Service.getGradeById(id);
	        logger.info("Prikazana ocena sa id-jem: {}", id);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (NotFoundException e) {
	        logger.error("Ocena sa id-jem {} nije pronadjena.", id, e);
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@PostMapping("/creategrade")
	public ResponseEntity<String> createGrade(@RequestParam int novaOcena, @RequestParam int novoPrvoPolugodiste,
	        @RequestParam int novoDrugoPolugodiste, int vladanje) {
	   
	    String response = admin1Service.createGrade(novaOcena, novoPrvoPolugodiste, novoDrugoPolugodiste,vladanje);
	    logger.info("Kreirana nova ocena.");
	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/updategrade/{id}")
	public ResponseEntity<String> updateGrade(@PathVariable int id, @RequestParam int izmenjenaOcena,
	        @RequestParam int izmenjenoPrvoPolugodiste, @RequestParam int izmenjenoDrugoPolugodiste, int izmenjenoVladanje) {
	    try {
	       
	        String response = admin1Service.updateGrade(id, izmenjenaOcena, izmenjenoPrvoPolugodiste,
	                izmenjenoDrugoPolugodiste, izmenjenoVladanje);
	        logger.info("Azurirana ocena sa id-jem: {}", id);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (NotFoundException e) {
	        logger.error("Ocena sa id-jem {} nije pronadjena prilikom azuriranja.", id, e);
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@DeleteMapping("/deletegrade/{id}")
	public ResponseEntity<String> deleteGradeById(@PathVariable int id) {
	    try {
	       
	        String response = admin1Service.deleteGradeById(id);
	        logger.info("Obrisana ocena sa id-jem: {}", id);
	        return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (NotFoundException e) {
	        logger.error("Ocena sa id-jem {} nije pronadjena prilikom brisanja.", id, e);
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

//--------------------------------------------------metode za izostanke--------------------------------------------------------------------------

	@PostMapping("/createabsence")
	public ResponseEntity<Izostanci1Entity> createAbsence(@RequestParam String opravdani,
	        @RequestParam String neopravdani, @RequestParam String datumIzostanka) {
	    try {
	        
	        Izostanci1Entity noviIzostanak = admin1Service.createAbsence(opravdani, neopravdani, datumIzostanka);
	        logger.info("Kreiran je novi izostanak sa id-jem: {}", noviIzostanak.getId());
	        return new ResponseEntity<>(noviIzostanak, HttpStatus.CREATED);
	    } catch (Exception e) {
	        logger.error("Greska prilikom kreiranja izostanka: {}", e.getMessage());
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	}

	@PutMapping("/updateabsence/{id}")
	public ResponseEntity<Izostanci1Entity> updateAbsence(@PathVariable int id, @RequestParam String noviOpravdani,
	        @RequestParam String noviNeopravdani, @RequestParam String novDatumIzostanka) throws NotFoundException {
	    try {
	       
	        Izostanci1Entity updatedAbsence = admin1Service.updateAbsence(id, noviOpravdani, noviNeopravdani,
	                novDatumIzostanka);
	        logger.info("Izostanak sa id-jem {} je uspesno azuriran.", id);
	        return new ResponseEntity<>(updatedAbsence, HttpStatus.OK);
	    } catch (NotFoundException e) {
	        logger.error("Izostanak sa id-jem {} nije pronadjen prilikom azuriranja.", id, e);
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@DeleteMapping("/deleteabsence/{id}")
	public ResponseEntity<Izostanci1Entity> deleteAbsence(@PathVariable int id) throws NotFoundException {
	    try {
	        
	        admin1Service.deleteAbsence(id);
	        logger.info("Izostanak sa id-jem {} je uspesno obrisan.", id);
	        return new ResponseEntity<>(HttpStatus.OK);
	    } catch (NotFoundException e) {
	        logger.error("Izostanak sa id-jem {} nije pronadjen prilikom brisanja.", id, e);
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}

	@GetMapping("/allabsences")
	public ResponseEntity<Iterable<Izostanci1Entity>> getAllAbsences() throws AccessDeniedException {
	    try {
	       
	        Iterable<Izostanci1Entity> izostanci = admin1Service.getAllAbsences();
	        logger.info("Prikazani su svi izostanci.");
	        return new ResponseEntity<>(izostanci, HttpStatus.OK);
	    } catch (AccessDeniedException e) {
	        logger.error("Pristup zabranjen: {}", e.getMessage());
	        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	    }
	}

	@GetMapping("/absence/{id}")
	public ResponseEntity<Izostanci1Entity> getAbsenceById(@PathVariable int id) throws NotFoundException {
	    try {
	        
	        Izostanci1Entity izostanak = admin1Service.getAbsenceById(id);
	        logger.info("Prikazan je izostanak sa id-jem: {}", id);
	        return new ResponseEntity<>(izostanak, HttpStatus.OK);
	    } catch (NotFoundException e) {
	        logger.error("Izostanak sa id-jem {} nije pronadjen.", id, e);
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}