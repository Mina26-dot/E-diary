package com.iktpreobuka.elektronskidnevnik1.service;

//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

//import com.iktpreobuka.elektronskidnevnik1.entity.Korisnik1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Roditelj1Entity;
import com.iktpreobuka.elektronskidnevnik1.repositories.Roditelj1Repository;

@Service
public class Roditelj1Impl implements Roditelj1Service{

	@Autowired
	 private final Roditelj1Repository roditelj1Repository;

	    
	    public Roditelj1Impl(Roditelj1Repository roditelj1Repository) {
	        this.roditelj1Repository = roditelj1Repository;
	    }

	    @Override
	    public Iterable<Roditelj1Entity> getAllParents() {
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
	    public String updateParent(int idRoditelja, String izmenjenoImeRoditelja, String izmenjenoPrezimeRoditelja, String izmenjenoEmailRoditelja) throws NotFoundException {
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
	
//-----------------------------------------------------------------------------------
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

}
