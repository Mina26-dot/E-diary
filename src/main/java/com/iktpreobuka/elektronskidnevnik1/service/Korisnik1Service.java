package com.iktpreobuka.elektronskidnevnik1.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import com.iktpreobuka.elektronskidnevnik1.entity.Korisnik1Entity;
//import com.iktpreobuka.elektronskidnevnik1.entity.RoleEntity;

import jakarta.validation.Valid;

public interface Korisnik1Service {

	Iterable<Korisnik1Entity> getAllUsers();

	String getUserById(int idKorisnika) throws NotFoundException;

	String createUser(@Valid String korisnickoIme, String sifraKorisnika, String uloga);

	String deleteUserById(int idKorisnika) throws NotFoundException;

//	String updateUser(int idKorisnika, String korisnickoIme, String sifraKorisnika,
//			@Valid String izmenjenoKorisnickoIme, String izmenjenaSifraKorisnika,String uloga)
//			throws NotFoundException;
	// String updateUser(int id, String korisnickoIme, String sifra, String
	// izmenjenoKorisnickoIme, String izmenjenaSifra) throws NotFoundException;

	String updateUser(int id, @Valid String izmenjenoKorisnickoIme, String izmenjenaSifraKorisnika, String uloga) throws NotFoundException;



	//String createUser(@Valid String korisnickoIme, String sifraKorisnika, RoleEntity uloga);

//	String updateUser(int idKorisnika, Korisnik1Entity korisnickoIme, String sifraKorisnika,
//			String izmenjenoKorisnickoIme, String izmenjenaSifraKorisnika) throws NotFoundException;

//	String updateUser(int idKorisnika, String korisnickoIme, String sifraKorisnika,
//			@Valid String izmenjenoKorisnickoIme, String izmenjenaSifraKorisnika) throws NotFoundException;

//	String updateUser(int id, String korisnickoIme, String sifraKorisnika, @Valid String izmenjenoKorisnickoIme,
//			String izmenjenaSifraKorisnika);

//	String updateUser(int id, String korisnickoIme, String sifraKorisnika, @Valid String izmenjenoKorisnickoIme,
//			String izmenjenaSifraKorisnika);

	// String createUser(String korisnickoIme, String sifraKorisnika);
}
