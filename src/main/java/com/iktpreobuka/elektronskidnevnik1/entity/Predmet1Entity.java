package com.iktpreobuka.elektronskidnevnik1.entity;

//import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.iktpreobuka.elektronskidnevnik1.security.Views;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "predmet")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Predmet1Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	@JsonView(Views.Administrator.class)
	private int id;

	@Column(name = "Ime_Predmeta", nullable = false)
	@JsonView(Views.Public.class)
	private String imePredmeta;

	@Column(name = "Nedeljni_Fond_Casova", nullable = false)
	@JsonView(Views.Public.class)
	private int nedeljniFondCasova;

	@ManyToOne
    @JoinColumn(name = "nastavnik_id")
    @JsonView(Views.Private.class)
    private Nastavnik1Entity nastavnik;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "razred_id")
	@JsonView(Views.Private.class) 
	private Razred1Entity razred;
	

	public Predmet1Entity() {

	}

	public Predmet1Entity(int id, String imePredmeta, int nedeljniFondCasova, Nastavnik1Entity nastavnik,
			Razred1Entity razred) {
		super();
		this.id = id;
		this.imePredmeta = imePredmeta;
		this.nedeljniFondCasova = nedeljniFondCasova;
		this.nastavnik = nastavnik;
		this.razred = razred;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImePredmeta() {
		return imePredmeta;
	}

	public void setImePredmeta(String imePredmeta) {
		this.imePredmeta = imePredmeta;
	}

	public int getNedeljniFondCasova() {
		return nedeljniFondCasova;
	}

	public void setNedeljniFondCasova(int nedeljniFondCasova) {
		this.nedeljniFondCasova = nedeljniFondCasova;
	}

	public Nastavnik1Entity getNastavnik() {
		return nastavnik;
	}

	public void setNastavnik(Nastavnik1Entity nastavnik) {
		this.nastavnik = nastavnik;
	}

	public Razred1Entity getRazred() {
		return razred;
	}

	public void setRazred(Razred1Entity razred) {
		this.razred = razred;
	}


	

}
