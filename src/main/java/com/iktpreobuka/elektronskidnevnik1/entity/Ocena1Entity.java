package com.iktpreobuka.elektronskidnevnik1.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.elektronskidnevnik1.security.Views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Ocena")
public class Ocena1Entity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	@JsonView(Views.Administrator.class)
	private int idOcene;

	@Column(name = "Ocene", nullable = false)
	@JsonView(Views.Private.class)
	@Min(value = 1, message = "Ocena ne moze biti manja od 1")
	@Max(value = 5, message = "Ocena ne moze biti veca od 5")
	private int ocena;

	@Column(name = "prvo_polugodiste", nullable = true)
	@JsonView(Views.Private.class)
	@Min(value = 1, message = "Ocena ne moze biti manja od 1")
	@Max(value = 5, message = "Ocena ne moze biti veca od 5")
	private int prvoPolugodiste;

	@Column(name = "drugo_polugodiste", nullable = true)
	@JsonView(Views.Private.class)
	@Min(value = 1, message = "Ocena ne moze biti manja od 1")
	@Max(value = 5, message = "Ocena ne moze biti veca od 5")
	private int drugoPolugodiste;

	@Column(name = "zakljucne_ocene", nullable = false)
	@JsonView(Views.Private.class)
	@Min(value = 1, message = "Ocena ne moze biti manja od 1")
	@Max(value = 5, message = "Ocena ne moze biti veca od 5")
	private int zakljucnaOcena;

	@Column(name = "vladanje", nullable = false)
	@JsonView(Views.Private.class)
	@Min(value = 1, message = "Ocena ne moze biti manja od 1")
	@Max(value = 5, message = "Ocena ne moze biti veca od 5")
	private int vladanje;

	@Column(name = "pismeni_zadatak", nullable = false)
	@JsonView(Views.Private.class)
	private int pismeni;

	@Column(name = "usmeni_ispit", nullable = false)
	@JsonView(Views.Private.class)
	private int usmeni;

	@ManyToOne
	@JoinColumn(name = "predmet_id")
	@JsonView(Views.Private.class)
	//@NotNull(message = "Predmet ne moze biti null")
	private Predmet1Entity predmet;

	@ManyToOne
	@JoinColumn(name = "nastavnik_id")
	@JsonView(Views.Private.class)
	private Nastavnik1Entity nastavnik;

	@ManyToOne
	@JoinColumn(name = "ucenik_id")
	@JsonView(Views.Private.class)
	@NotNull(message = "Ucenik ne moze biti null")
    @JsonIgnoreProperties({"roditelj","razred","nastavnik"})
	private Ucenik1Entity ucenik;

	public Ocena1Entity() {

	}

	public Ocena1Entity(int idOcene, int ocena, int prvoPolugodiste, int drugoPolugodiste, int zakljucnaOcena,
			int vladanje, int pismeni, int usmeni, Predmet1Entity predmet, Nastavnik1Entity nastavnik,
			Ucenik1Entity ucenik) {
		super();
		this.idOcene = idOcene;
		this.ocena = ocena;
		this.prvoPolugodiste = prvoPolugodiste;
		this.drugoPolugodiste = drugoPolugodiste;
		this.zakljucnaOcena = zakljucnaOcena;
		this.vladanje = vladanje;
		this.pismeni = pismeni;
		this.usmeni = usmeni;
		this.predmet = predmet;
		this.nastavnik = nastavnik;
		this.ucenik = ucenik;
	}

	public int getIdOcene() {
		return idOcene;
	}

	public void setIdOcene(int idOcene) {
		this.idOcene = idOcene;
	}

	public int getOcena() {
		return ocena;
	}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}

	public int getPrvoPolugodiste() {
		return prvoPolugodiste;
	}

	public void setPrvoPolugodiste(int prvoPolugodiste) {
		this.prvoPolugodiste = prvoPolugodiste;
	}

	public int getDrugoPolugodiste() {
		return drugoPolugodiste;
	}

	public void setDrugoPolugodiste(int drugoPolugodiste) {
		this.drugoPolugodiste = drugoPolugodiste;
	}

	public int getZakljucnaOcena() {
		return zakljucnaOcena;
	}

	public void setZakljucnaOcena(int zakljucnaOcena) {
		this.zakljucnaOcena = zakljucnaOcena;
	}

	public int getVladanje() {
		return vladanje;
	}

	public void setVladanje(int vladanje) {
		this.vladanje = vladanje;
	}

	public int getPismeni() {
		return pismeni;
	}

	public void setPismeni(int pismeni) {
		this.pismeni = pismeni;
	}

	public int getUsmeni() {
		return usmeni;
	}

	public void setUsmeni(int usmeni) {
		this.usmeni = usmeni;
	}

	public Predmet1Entity getPredmet() {
		return predmet;
	}

	public void setPredmet(Predmet1Entity predmet) {
		this.predmet = predmet;
	}

	public Nastavnik1Entity getNastavnik() {
		return nastavnik;
	}

	public void setNastavnik(Nastavnik1Entity nastavnik) {
		this.nastavnik = nastavnik;
	}

	public Ucenik1Entity getUcenik() {
		return ucenik;
	}

	public void setUcenik(Ucenik1Entity ucenik) {
		this.ucenik = ucenik;
	}

	
	
}
