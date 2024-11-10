package com.iktpreobuka.elektronskidnevnik1.service;

import java.nio.file.AccessDeniedException;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronskidnevnik1.entity.Izostanci1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Korisnik1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Ocena1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Predmet1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Roditelj1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;
import com.iktpreobuka.elektronskidnevnik1.repositories.Izostanci1Repository;
import com.iktpreobuka.elektronskidnevnik1.repositories.Korisnik1Repository;
import com.iktpreobuka.elektronskidnevnik1.repositories.Nastavnik1Repository;
import com.iktpreobuka.elektronskidnevnik1.repositories.Ocena1Repository;
import com.iktpreobuka.elektronskidnevnik1.repositories.Predmet1Repository;
import com.iktpreobuka.elektronskidnevnik1.repositories.Razred1Repository;
import com.iktpreobuka.elektronskidnevnik1.repositories.Roditelj1Repository;
import com.iktpreobuka.elektronskidnevnik1.repositories.Ucenik1Repository;

import jakarta.validation.Valid;

@Service
public class Administrator1Impl implements Administrator1Service {
	
	
	@Autowired
	private final Predmet1Repository predmet1Repository;
	@Autowired
	private final Izostanci1Repository izostanci1Repository;
	@Autowired
	private final Razred1Repository razred1Repository;
	@Autowired
	private final Nastavnik1Repository nastavnik1Repository;
	@Autowired
	private final Ucenik1Repository ucenik1Repository;
	@Autowired
	private final Roditelj1Repository roditelj1Repository;
	@Autowired
	private final Ocena1Repository ocena1Repository;
	@Autowired
	private Korisnik1Repository korisnik1Repository;

	public Administrator1Impl(Predmet1Repository predmet1Repository, Nastavnik1Repository nastavnik1Repository,
			Ucenik1Repository ucenik1Repository, Roditelj1Repository roditelj1Repository,
			Ocena1Repository ocena1Repository, Razred1Repository razred1Repository,
			Izostanci1Repository izostanci1Repository) {
		this.predmet1Repository = predmet1Repository;
		this.izostanci1Repository = izostanci1Repository;
		this.razred1Repository = razred1Repository;
		this.nastavnik1Repository = nastavnik1Repository;
		this.ucenik1Repository = ucenik1Repository;
		this.roditelj1Repository = roditelj1Repository;
		this.ocena1Repository = ocena1Repository;
	}

//metoda za korisnike
	
	@Override
	public Iterable<Korisnik1Entity> getAllUsers() throws AccessDeniedException {
		if (!isCurrentUserAdmin()) {
			throw new AccessDeniedException("Samo administrator moze pristupiti ovim podacima.");
		}

		return korisnik1Repository.findAll();
	}
	
	@Override
	public String getUserById(int userId) throws NotFoundException {
		Korisnik1Entity korisnik = korisnik1Repository.findById(userId);
		if (korisnik != null) {
			return "Korisnik sa id-jem " + userId + " je uspesno pronadjen.";
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public String createUser(String korisnickoIme, String sifra) {
		Korisnik1Entity korisnik = new Korisnik1Entity();
		korisnik.setKorisnickoIme(korisnickoIme);
		korisnik.setSifra(sifra);
		korisnik1Repository.save(korisnik);
		return "Nov korisnik " + korisnickoIme + " " + " je uspesno dodat.";
	}

	@Override
	public String updateUser(int idKorisnika, String korisnickoIme, String sifra, @Valid String izmenjenoKorisnickoIme,String izmenjenaSifra) throws NotFoundException {
		Korisnik1Entity korisnik = korisnik1Repository.findById(idKorisnika);
		if (korisnik != null) {
			korisnik.setKorisnickoIme(izmenjenoKorisnickoIme);
			korisnik.setSifra(izmenjenaSifra);
			ucenik1Repository.save(korisnik);
			return "Korisnik " + korisnickoIme +  " je uspesno azuriran na " + izmenjenoKorisnickoIme + ".";
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public String deleteUserById(int idKorisnika) throws NotFoundException {
		Korisnik1Entity korisnik = korisnik1Repository.findById(idKorisnika);
		if (korisnik != null) {
			korisnik1Repository.delete(korisnik);
			if (!ucenik1Repository.existsById(idKorisnika)) {
				return "Korisnik sa id-jem " + idKorisnika + " je uspesno obrisan.";
			} else {
				throw new RuntimeException("Korisnik sa id-jem " + idKorisnika + " nije obrisan.");
			}
		} else {
			throw new NotFoundException();
		}
	}

	

	// Metode za predmete

	@Override
	public Iterable<Predmet1Entity> getAllSubjects() throws AccessDeniedException {
		if (!isCurrentUserAdmin()) {
			throw new AccessDeniedException("Samo administrator moze pristupiti ovim podacima.");
		}
		return predmet1Repository.findAll();
	}

	@Override
	public String getSubjectById(int idPredmeta) throws NotFoundException {
		Predmet1Entity predmet = predmet1Repository.findById(idPredmeta);
		if (predmet != null) {
			return "Predmet sa id-jem " + idPredmeta + " je pronajen.";
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public String createSubject(String imePredmeta, int nedeljniFondCasova) {
		Predmet1Entity predmet = new Predmet1Entity();
		predmet.setImePredmeta(imePredmeta);
		predmet.setNedeljniFondCasova(nedeljniFondCasova);
		predmet1Repository.save(predmet);
		return "Novi predmet " + imePredmeta + " je uspesno dodat.";
	}

	@Override
	public String updateSubject(int idPredmeta, String novoImePredmeta, int noviNedeljniFondCasova)
			throws NotFoundException {
		Predmet1Entity predmet = predmet1Repository.findById(idPredmeta);
		if (predmet != null) {
			predmet.setImePredmeta(novoImePredmeta);
			predmet.setNedeljniFondCasova(noviNedeljniFondCasova);
			predmet1Repository.save(predmet);
			return "Predmet sa id-jem " + idPredmeta + " je uspesno azuriran.";
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public String deleteSubjectById(int idPredmeta) throws NotFoundException {
		Predmet1Entity predmet = predmet1Repository.findById(idPredmeta);
		if (predmet != null) {
			predmet1Repository.delete(predmet);
			if (!predmet1Repository.existsById(idPredmeta)) {
				return "Predmet sa id-jem " + idPredmeta + " je uspesno obrisan.";
			} else {
				throw new RuntimeException("Predmet sa id-jem " + idPredmeta + " nije obrisan.");
			}
		} else {
			throw new NotFoundException();
		}
	}

//Metode za nastavnika

	@Override
	public Iterable<Nastavnik1Entity> getAllTeachers() throws AccessDeniedException {
		if (!isCurrentUserAdmin()) {
			throw new AccessDeniedException("Samo administrator moze pristupiti ovim podacima.");
		}
		return nastavnik1Repository.findAll();
	}

	@Override
	public String getTeacherById(int idNastavnika) throws NotFoundException {
		Nastavnik1Entity nastavnik = nastavnik1Repository.findById(idNastavnika);
		if (nastavnik != null) {
			return "Nastavnik sa id-jem " + idNastavnika + " je uspesno pronadjen.";
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public String createTeacher(String imeNastavnika, String prezimeNastavnika) {
		Nastavnik1Entity nastavnik = new Nastavnik1Entity();
		nastavnik.setImeNastavnika(imeNastavnika);
		nastavnik.setPrezimeNastavnika(prezimeNastavnika);
		nastavnik1Repository.save(nastavnik);
		return "Nastavnik " + imeNastavnika + " " + prezimeNastavnika + " je uspesno dodat.";
	}

	@Override
	public String updateTeacher(int idNastavnika, String novoImeNastavnika, String novoPrezimeNastavnika)
			throws NotFoundException {
		Nastavnik1Entity nastavnik = nastavnik1Repository.findById(idNastavnika);
		if (nastavnik != null) {
			nastavnik.setImeNastavnika(novoImeNastavnika);
			nastavnik.setPrezimeNastavnika(novoPrezimeNastavnika);
			nastavnik1Repository.save(nastavnik);
			return "Nastavnik sa id-jem " + idNastavnika + " je uspesno azuriran.";
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public String deleteTeacherById(int idNastavnika) throws NotFoundException {
		Nastavnik1Entity nastavnik = nastavnik1Repository.findById(idNastavnika);
		if (nastavnik != null) {
			nastavnik1Repository.deleteById(idNastavnika);
			if (!nastavnik1Repository.existsById(idNastavnika)) {
				return "Nastavnik sa id-jem " + idNastavnika + " je uspesno obrisan.";
			} else {
				throw new RuntimeException("Nastavnik sa id-jem " + idNastavnika + " nije obrisan.");
			}
		} else {
			throw new NotFoundException();
		}
	}
// Metode za ucenika

	@Override
	    public Iterable<Ucenik1Entity> getAllStudents() throws AccessDeniedException {
	        if (!isCurrentUserAdmin()) {
	            throw new AccessDeniedException("Samo administrator moze pristupiti ovim podacima.");
	        }
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
	public String createStudent(String imeUcenika, String prezimeUcenika) {
		Ucenik1Entity ucenik = new Ucenik1Entity();
		ucenik.setImeUcenika(imeUcenika);
		ucenik.setPrezimeUcenika(prezimeUcenika);
		ucenik1Repository.save(ucenik);
		return "Nov ucenik " + imeUcenika + " " + prezimeUcenika + " je uspesno dodat.";
	}

	@Override
	public String updateStudent(int idUcenika, String imeUcenika, String prezimeUcenika, String izmenjenoImeUcenika,
			String izmenjenoPrezimeUcenika) throws NotFoundException {
		Ucenik1Entity ucenik = ucenik1Repository.findById(idUcenika);
		if (ucenik != null) {
			ucenik.setImeUcenika(izmenjenoImeUcenika);
			ucenik.setPrezimeUcenika(izmenjenoPrezimeUcenika);
			ucenik1Repository.save(ucenik);
			return "Ucenik " + imeUcenika + " " + prezimeUcenika + " je uspesno azuriran na " + izmenjenoImeUcenika
					+ " " + izmenjenoPrezimeUcenika + ".";
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
//Metode za roditelja

	@Override
	public Iterable<Roditelj1Entity> getAllParents() throws AccessDeniedException {
		if (!isCurrentUserAdmin()) {
			throw new AccessDeniedException("Samo administrator moze pristupiti ovim podacima.");
		}
		return roditelj1Repository.findAll();
	}

	@Override
	public String getParentById(int idRoditelja) throws NotFoundException {
		Roditelj1Entity roditelj = roditelj1Repository.findById(idRoditelja);
		if (roditelj != null) {
			return "Roditelj sa id-jem " + idRoditelja + " je uspesno pronadjen.";
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public String createParent(String imeRoditelja, String prezimeRoditelja, String emailRoditelja) {
		Roditelj1Entity roditelj = new Roditelj1Entity();
		roditelj.setImeRoditelja(imeRoditelja);
		roditelj.setPrezimeRoditelja(prezimeRoditelja);
		roditelj.setEmailRoditelja(emailRoditelja);
		roditelj1Repository.save(roditelj);
		return "Novi roditelj " + imeRoditelja + " " + prezimeRoditelja + " je uspesno dodat.";
	}

	@Override
	public String updateParent(int idRoditelja, String izmenjenoImeRoditelja, String izmenjenoPrezimeRoditelja,
			String izmenjenoEmailRoditelja) throws NotFoundException {
		Roditelj1Entity roditelj = roditelj1Repository.findById(idRoditelja);
		if (roditelj != null) {
			roditelj.setImeRoditelja(izmenjenoImeRoditelja);
			roditelj.setPrezimeRoditelja(izmenjenoPrezimeRoditelja);
			roditelj.setEmailRoditelja(izmenjenoEmailRoditelja);
			roditelj1Repository.save(roditelj);
			return "Roditelj sa id-jem " + idRoditelja + " je uspesno azuriran.";
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public String deleteParentById(int idRoditelja) throws NotFoundException {
		Roditelj1Entity roditelj = roditelj1Repository.findById(idRoditelja);
		if (roditelj != null) {
			roditelj1Repository.delete(roditelj);
			if (!roditelj1Repository.existsById(idRoditelja)) {
				return "Roditelj sa id-jem " + idRoditelja + " je uspesno obrisan.";
			} else {
				throw new RuntimeException("Roditelj sa id-jem " + idRoditelja + " nije obrisan.");
			}
		} else {
			throw new NotFoundException();
		}
	}
	// Metode za ocene

	@Override
	public Iterable<Ocena1Entity> getAllGrades() throws AccessDeniedException {
		if (!isCurrentUserAdmin()) {
			throw new AccessDeniedException("Samo administrator moze pristupiti ovim podacima.");
		}
		return ocena1Repository.findAll();
	}

	@Override
	public String getGradeById(int ocenaId) throws NotFoundException {
		Ocena1Entity ocena = ocena1Repository.findById(ocenaId);
		if (ocena != null) {
			return "Ocena sa id-jem " + ocenaId + " je pronadjena.";
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public String createGrade(int novaOcena, int novoPrvoPolugodiste, int novoDrugoPolugodiste, int vladanje) {
		if (novaOcena < 1 || novaOcena > 5) {
			return "Ocena mora biti u opsegu od 1 do 5.";
		}
		Ocena1Entity ocena = new Ocena1Entity();
		ocena.setOcena(novaOcena);
		ocena.setPrvoPolugodiste(novoPrvoPolugodiste);
		ocena.setDrugoPolugodiste(novoDrugoPolugodiste);
		ocena.setVladanje(vladanje);
		ocena1Repository.save(ocena);
		return "Nova ocena sa id-jem " + ocena.getIdOcene() + " je uspesno dodata.";
	}

	@Override
	public String updateGrade(int idOcene, int izmenjenaOcena, int izmenjenoPrvoPolugodiste,
			int izmenjenoDrugoPolugodiste,int izmenjenoVladanje) throws NotFoundException {
		Ocena1Entity ocena = ocena1Repository.findById(idOcene);
		if (ocena != null) {
			ocena.setOcena(izmenjenaOcena);
			ocena.setPrvoPolugodiste(izmenjenoPrvoPolugodiste);
			ocena.setDrugoPolugodiste(izmenjenoDrugoPolugodiste);
			ocena.setVladanje(izmenjenoVladanje);
			ocena1Repository.save(ocena);
			return "Ocena sa id-jem " + idOcene + " je uspesno azurirana.";
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public String deleteGradeById(int ocenaId) throws NotFoundException {
		Ocena1Entity ocena = ocena1Repository.findById(ocenaId);
		if (ocena != null) {
			ocena1Repository.delete(ocena);
			return "Ocena sa id-jem " + ocenaId + " je uspesno obrisana.";
		} else {
			throw new NotFoundException();
		}
	}

// metoda za izostanke
//
	@Override
    public Iterable<Izostanci1Entity> getAllAbsences() throws AccessDeniedException {
        if (!isCurrentUserAdmin()) {
            throw new AccessDeniedException("Samo administrator moze pristupiti ovim podacima.");
        }
        return izostanci1Repository.findAll();
    }
	
	@Override
    public Izostanci1Entity createAbsence(String opravdani, String neopravdani, String datumIzostanka) {
        Izostanci1Entity izostanak = new Izostanci1Entity();
        izostanak.setOpravdani(opravdani);
        izostanak.setNeopravdani(neopravdani);
        izostanak.setDatumIzostanka(datumIzostanka);
        return izostanci1Repository.save(izostanak);
    }

    @Override
    public Izostanci1Entity updateAbsence(int id, String noviOpravdani, String noviNeopravdani, String novDatumIzostanka) throws NotFoundException {
        Izostanci1Entity izostanak = null;
        izostanak = izostanci1Repository.findById(id);
        izostanak.setOpravdani(noviOpravdani);
        izostanak.setNeopravdani(noviNeopravdani);
        izostanak.setDatumIzostanka(novDatumIzostanka);
        return izostanci1Repository.save(izostanak);
    }

    @Override
    public void deleteAbsence(int id) throws NotFoundException {
        if (!izostanci1Repository.existsById(id)) {
            throw new NotFoundException();
        }
        izostanci1Repository.deleteById(id);
    }

    @Override
    public Izostanci1Entity getAbsenceById(int id) throws NotFoundException {
        Izostanci1Entity izostanak = izostanci1Repository.findById(id);
        if (izostanak == null) {
            throw new NotFoundException();
        }
        return izostanak;
    }

	

//metoda za razred

	@Override
	public Iterable<Razred1Entity> getAllClasses() throws AccessDeniedException {
		if (!isCurrentUserAdmin()) {
			throw new AccessDeniedException("Samo administrator moze pristupiti ovim podacima.");
		}
		return razred1Repository.findAll();
	}
    
    
    
	
	private boolean isCurrentUserAdmin() {
		// TODO Auto-generated method stub
		return false;
	}
//------------------------------------------------------------------------------------------------------
//	@Override
//	public Predmet1Entity createPredmet(Predmet1Entity predmet) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Predmet1Entity updatePredmet(Integer id, Predmet1Entity predmet) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void deletePredmet(Integer id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<Predmet1Entity> getAllPredmet() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Predmet1Entity getPredmetById(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Nastavnik1Entity createNastavnik(Nastavnik1Entity nastavnik) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Nastavnik1Entity updateNastavnik(Integer id, Nastavnik1Entity nastavnik) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void deleteNastavnik(Integer id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<Nastavnik1Entity> getAllNastavnik() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Nastavnik1Entity getNastavnikById(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
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
//
//	@Override
//	public Roditelj1Entity createRoditelj(Roditelj1Entity roditelj) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Roditelj1Entity updateRoditelj(Integer id, Roditelj1Entity roditelj) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void deleteRoditelj(Integer id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<Roditelj1Entity> getAllRoditelj() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Roditelj1Entity getRoditeljById(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Ocena1Entity createOcena(Ocena1Entity ocena) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Ocena1Entity updateOcena(Integer id, Ocena1Entity ocena) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void deleteOcena(Integer id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<Ocena1Entity> getAllOcene() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Ocena1Entity getOcenaById(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
//
//	

	

	


}
