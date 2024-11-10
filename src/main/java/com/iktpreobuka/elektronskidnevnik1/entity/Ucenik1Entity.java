package com.iktpreobuka.elektronskidnevnik1.entity;


import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.elektronskidnevnik1.security.Views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

@Entity
@Table(name = "Ucenik")
public class Ucenik1Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	@JsonView(Views.Administrator.class)
	private int id;

	@Column(name = "Ime_Ucenika", nullable = false)
	@JsonView(Views.Private.class)
	private String imeUcenika;

	@Column(name = "Prezime_Ucenika", nullable = false)
	@JsonView(Views.Private.class)
	private String prezimeUcenika;

	@ManyToOne
	@JoinColumn(name = "roditelj_id")
	@JsonView(Views.Private.class)
	private Roditelj1Entity roditelj;

	@ManyToOne
	@JoinColumn(name = "razred_id")
	@JsonView(Views.Private.class)
	private Razred1Entity razred;

	public Ucenik1Entity() {

	}

	public Ucenik1Entity(int id, String imeUcenika, String prezimeUcenika, Roditelj1Entity roditelj,
			Razred1Entity razred) {
		super();
		this.id = id;
		this.imeUcenika = imeUcenika;
		this.prezimeUcenika = prezimeUcenika;
		this.roditelj = roditelj;

		this.razred = razred;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImeUcenika() {
		return imeUcenika;
	}

	public void setImeUcenika(String imeUcenika) {
		this.imeUcenika = imeUcenika;
	}

	public String getPrezimeUcenika() {
		return prezimeUcenika;
	}

	public void setPrezimeUcenika(String prezimeUcenika) {
		this.prezimeUcenika = prezimeUcenika;
	}

	public Roditelj1Entity getRoditelj() {
		return roditelj;
	}

	public void setRoditelj(Roditelj1Entity roditelj) {
		this.roditelj = roditelj;
	}

	public Razred1Entity getRazred() {
		return razred;
	}

	public void setRazred(Razred1Entity razred) {
		this.razred = razred;
	}

}
