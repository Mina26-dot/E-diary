package com.iktpreobuka.elektronskidnevnik1.service;

//import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

//import com.iktpreobuka.elektronskidnevnik1.entity.Korisnik1Entity;
import com.iktpreobuka.elektronskidnevnik1.entity.Roditelj1Entity;

public interface Roditelj1Service {

	Iterable<Roditelj1Entity> getAllParents();

	String getParentById(int idRoditelja) throws NotFoundException;

	String createParent(String imeRoditelja, String prezimeRoditelja, String emailRoditelja);

	String updateParent(int idRoditelja, String izmenjenoImeRoditelja, String izmenjenoPrezimeRoditelja,
			String izmenjenoEmailRoditelja) throws NotFoundException;

	String deleteParentById(int idRoditelja) throws NotFoundException;
}
