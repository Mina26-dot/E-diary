package com.iktpreobuka.elektronskidnevnik1.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.iktpreobuka.elektronskidnevnik1.entity.Izostanci1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Ucenik1Entity;
import com.iktpreobuka.elektronskidnevnik1.repositories.Izostanci1Repository;

@Service
public class Izostanci1Impl implements Izostanci1Service {

	private Izostanci1Repository izostanci1Repository;

	public Izostanci1Impl(Izostanci1Repository izostanci1Repository) {
		this.izostanci1Repository = izostanci1Repository;
	}

	@Override
	public Izostanci1Entity createAbsence(@RequestParam String opravdani, @RequestParam String neopravdani,
			@RequestParam String datumIzostanka, @RequestParam Ucenik1Entity ucenik) {
		Izostanci1Entity noviIzostanak = new Izostanci1Entity();
		noviIzostanak.setOpravdani(opravdani);
		noviIzostanak.setNeopravdani(neopravdani);
		noviIzostanak.setUcenik(ucenik);
		noviIzostanak.setDatumIzostanka(datumIzostanka);
		
		izostanci1Repository.save(noviIzostanak);

		return izostanci1Repository.save(noviIzostanak);
	}

	@Override
	public Izostanci1Entity updateAbsence(int id, String noviOpravdani, String noviNeopravdani, String novDatumIzostanka) throws NotFoundException {

		Izostanci1Entity izostanak = izostanci1Repository.findById(id);
		if (izostanak != null) {
			izostanak.setOpravdani(noviNeopravdani);
			izostanak.setNeopravdani(noviNeopravdani);
			izostanak.setDatumIzostanka(novDatumIzostanka);
			return izostanci1Repository.save(izostanak);
		} else {
			throw new NotFoundException();

		}
	}

	@Override
	public void deleteAbsence(int idIzostanka) throws NotFoundException {
		Izostanci1Entity izostanak = izostanci1Repository.findById(idIzostanka);
		if (izostanak != null) {
			izostanci1Repository.delete(izostanak);
			if (!izostanci1Repository.existsById(idIzostanka)) {
				 throw new NotFoundException();
	        } else {
	            throw new RuntimeException("Izostanak sa id-jem " + idIzostanka + " nije obrisan");
	        }
	    } else {
	        throw new NotFoundException();
	    }
	}
	

	@Override
	public Iterable<Izostanci1Entity> getAllAbsences() {
	    return izostanci1Repository.findAll();
	}

	@Override
	public Izostanci1Entity getAbsenceById(int idIzostanka) throws NotFoundException {
		Izostanci1Entity izostanak = izostanci1Repository.findById(idIzostanka);
		if (izostanak != null) {
			return izostanak;
		} else {
			throw new NotFoundException();
		}
	}

}
