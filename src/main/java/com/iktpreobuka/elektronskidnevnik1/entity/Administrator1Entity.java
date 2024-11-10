package com.iktpreobuka.elektronskidnevnik1.entity;

//import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.elektronskidnevnik1.security.Views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "administrator")
public class Administrator1Entity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonView(Views.Administrator.class)
    private int id;
    
    @Column(name = "Ime_administratora", nullable = false)
    @JsonView(Views.Administrator.class)
    private String ime;
    
    @Column(name = "Prezime_administratora", nullable = false)
    @JsonView(Views.Administrator.class)
    private String prezime;

    @OneToOne
    @JoinColumn(name = "korisnikId")
    @JsonView(Views.Administrator.class)
    private Korisnik1Entity korisnik;

	

	public Administrator1Entity(int id, String ime, String prezime, Korisnik1Entity korisnik) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.korisnik = korisnik;
	}

	public Administrator1Entity() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Korisnik1Entity getKorisnik() {
		return korisnik;
	}

	public void setKorisnik(Korisnik1Entity korisnik) {
		this.korisnik = korisnik;
	}

	
}
