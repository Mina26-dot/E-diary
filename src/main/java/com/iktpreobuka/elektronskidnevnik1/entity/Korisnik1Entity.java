package com.iktpreobuka.elektronskidnevnik1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.elektronskidnevnik1.security.Views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;


@Entity
@Table(name = "Korisnik")
public class Korisnik1Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	@JsonView(Views.Administrator.class)
	private int id;

	@Column(name = "Korisnicko_Ime", nullable = false)
	@JsonView(Views.Administrator.class)
	private String korisnickoIme;

	@Column(name = "Sifra", nullable = false)
	@JsonIgnore
	private String sifra;

	@Column(name = "Uloga", nullable = true)
	@JsonView(Views.Private.class)
	private String uloga;
	

	public Korisnik1Entity() {
	}

	public Korisnik1Entity(int id, String korisnickoIme, String sifra, String uloga) {
		super();
		this.id = id;
		this.korisnickoIme = korisnickoIme;
		this.sifra = sifra;
		this.uloga = uloga;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getSifra() {
		return sifra;
	}

	public void setSifra(String sifra) {
		this.sifra = sifra;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	
}
