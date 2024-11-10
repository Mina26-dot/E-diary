package com.iktpreobuka.elektronskidnevnik1.service;

//import java.util.ArrayList;
//import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

//import java.util.List;

import org.springframework.stereotype.Service;

import com.iktpreobuka.elektronskidnevnik1.entity.Korisnik1Entity;
import com.iktpreobuka.elektronskidnevnik1.repositories.Korisnik1Repository;

import jakarta.validation.Valid;

@Service
public class Korisnik1Impl implements Korisnik1Service {

	private Korisnik1Repository korisnik1Repository;

	public Korisnik1Impl(Korisnik1Repository korisnik1Repository) {
		this.korisnik1Repository = korisnik1Repository;
	}

	@Override
	public Iterable<Korisnik1Entity> getAllUsers() {
		return korisnik1Repository.findAll();
	}

	@Override
	public String getUserById(int idKorisnika) throws NotFoundException {
		Korisnik1Entity korisnik = korisnik1Repository.findById(idKorisnika);
		if (korisnik != null) {
			return "Pribavljanje korisnika " + korisnik.getKorisnickoIme() + " sa id-jem " + idKorisnika
					+ " je uspesno.";
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public String createUser(@Valid String korisnickoIme, String sifraKorisnika, String uloga) {
		Korisnik1Entity korisnik = new Korisnik1Entity();
		korisnik.setKorisnickoIme(korisnickoIme);
		korisnik.setSifra(sifraKorisnika);
		korisnik.setUloga(uloga);
		korisnik1Repository.save(korisnik);
		return "Korisnik " + korisnickoIme + " je uspesno dodat.";
	}

	@Override
	public String updateUser(int idKorisnika,
			@Valid String izmenjenoKorisnickoIme, String izmenjenaSifraKorisnika, String uloga)
			throws NotFoundException {
		Korisnik1Entity korisnik = korisnik1Repository.findById(idKorisnika);
		if (korisnik != null) {
			korisnik.setKorisnickoIme(izmenjenoKorisnickoIme);
			korisnik.setSifra(izmenjenaSifraKorisnika);
			korisnik.setUloga(uloga);
			korisnik1Repository.save(korisnik);
			return "Korisnik sa id-jem " + idKorisnika + " je uspesno azuriran.";
		} else {
			throw new NotFoundException();
		}
	}

	@Override
	public String deleteUserById(int idKorisnika) throws NotFoundException {
		Korisnik1Entity korisnik = korisnik1Repository.findById(idKorisnika);
		if (korisnik != null) {
			korisnik1Repository.delete(korisnik);
			if (!korisnik1Repository.existsById(idKorisnika)) {
				return "Korisnik sa id-jem " + idKorisnika + " je uspesno obrisan.";
			} else {
				throw new RuntimeException("Korisnik sa id-jem " + idKorisnika + " nije obrisan.");
			}
		} else {
			throw new NotFoundException();
		}
	}

}
