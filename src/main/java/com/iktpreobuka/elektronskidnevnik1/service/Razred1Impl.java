package com.iktpreobuka.elektronskidnevnik1.service;

//import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronskidnevnik1.entity.Razred1Entity;
import com.iktpreobuka.elektronskidnevnik1.repositories.Razred1Repository;

@Service
public class Razred1Impl implements Razred1Service {

	private final Razred1Repository razred1Repository;

    public Razred1Impl(Razred1Repository razred1Repository) {
        this.razred1Repository = razred1Repository;
    }

    @Override
    public Iterable<Razred1Entity> getAllClasses() {
        return razred1Repository.findAll();
    }

    @Override
    public String getClassById(int idRazreda) throws NotFoundException {
        Razred1Entity razred = razred1Repository.findById(idRazreda);
        if (razred != null) {
            return "Razred sa id-jem " + idRazreda + " je uspesno pronadjen.";
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public String createClass(String noviRazred, String novoOdeljenje) {
        Razred1Entity razred = new Razred1Entity();
        razred.setRazredUcenika(noviRazred);
        razred.setOdeljenje(novoOdeljenje);
        razred1Repository.save(razred);
        return "Novi razred " + noviRazred + novoOdeljenje + " je uspesno dodat.";
    }

    @Override
    public String updateClass(int idRazreda, String izmenjenRazredUcenika, String izmenjenoOdeljenje) throws NotFoundException {
        Razred1Entity razred = razred1Repository.findById(idRazreda);
        if (razred != null) {
            razred.setRazredUcenika(izmenjenRazredUcenika);
            razred.setOdeljenje(izmenjenoOdeljenje);
            razred1Repository.save(razred);
            return "Razred sa id-jem " + idRazreda + " je uspesno azuriran.";
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public String deleteClassById(int idRazreda) throws NotFoundException {
        Razred1Entity razred = razred1Repository.findById(idRazreda);
        if (razred != null) {
            razred1Repository.deleteById(idRazreda);
            if (!razred1Repository.existsById(idRazreda)) {
                return "Razred sa id-jem " + idRazreda + " je uspesno obrisan.";
            } else {
                throw new RuntimeException("Razred sa id-jem " + idRazreda + " nije obrisan.");
            }
        } else {
            throw new NotFoundException();
        }
    }
	
//-----------------------------------------------------------------	
//	@Override
//	public Razred1Entity createRazred(Razred1Entity razred) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Razred1Entity updateRazred(Integer id, Razred1Entity razred) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void deleteRazred(Integer id) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public List<Razred1Entity> getAllRazred() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Razred1Entity getRazredById(Integer id) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
