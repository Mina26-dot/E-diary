package com.iktpreobuka.elektronskidnevnik1.service;

//import java.util.List;

//import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

//import com.iktpreobuka.elektronskidnevnik1.entity.Korisnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Korisnik1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Nastavnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Predmet1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.RoleEntity;
import com.iktpreobuka.elektronskidnevnik1.repositories.Nastavnik1Repository;

@Service
public class Nastavnik1Impl implements Nastavnik1Service {
	
	private final Nastavnik1Repository nastavnik1Repository;

    public Nastavnik1Impl(Nastavnik1Repository nastavnik1Repository) {
        this.nastavnik1Repository = nastavnik1Repository;
    }

    @Override
    public Iterable<Nastavnik1Entity> getAllTeachers() {
        return nastavnik1Repository.findAll();
    }

    @Override
    public String getTeacherById(int idNastavnika) throws NotFoundException {
        Nastavnik1Entity nastavnik = nastavnik1Repository.findById(idNastavnika);
        if (nastavnik != null) {
            return "Nastavnik sa id-jem " + idNastavnika + " je uspesno pronaden.";
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public String createTeacher(String imeNastavnika, String prezimeNastavnika) {
        Nastavnik1Entity nastavnik = new Nastavnik1Entity();
        nastavnik.setImeNastavnika(imeNastavnika);
        nastavnik.setPrezimeNastavnika(prezimeNastavnika);
    //    nastavnik.setRazred(razred);
      //  nastavnik.setKorisnik(korisnik);
        nastavnik1Repository.save(nastavnik);
        return "Nastavnik " + imeNastavnika + " " + prezimeNastavnika + " je uspesno dodat.";
    }

    @Override
    public String updateTeacher(int idNastavnika, String novoImeNastavnika, String novoPrezimeNastavnika) throws NotFoundException {
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
                return "Nastavnik sa id-jem " + idNastavnika + " je uspešno obrisan.";
            } else {
                throw new RuntimeException("Nastavnik sa id-jem " + idNastavnika + " nije obrisan.");
            }
        } else {
            throw new NotFoundException();
        }
    }


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

}
