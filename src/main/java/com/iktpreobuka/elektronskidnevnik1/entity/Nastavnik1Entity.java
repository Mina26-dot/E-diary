package com.iktpreobuka.elektronskidnevnik1.entity;

//import java.util.List;

//import java.util.ArrayList;
//import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.iktpreobuka.elektronskidnevnik1.security.Views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name = "Nastavnik")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Nastavnik1Entity {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id", nullable = false)
	    @JsonView(Views.Administrator.class)
	    private int id;

	    @Column(name = "ime_nastavnika", nullable = false)
	    @JsonView(Views.Private.class)
	    private String imeNastavnika;

	    @Column(name = "prezime_nastavnika", nullable = false)
	    @JsonView(Views.Private.class)
	    private String prezimeNastavnika;

	    
	public Nastavnik1Entity() {

	}

	public Nastavnik1Entity(int id, String imeNastavnika, String prezimeNastavnika) {
		super();
		this.id = id;
		this.imeNastavnika = imeNastavnika;
		this.prezimeNastavnika = prezimeNastavnika;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImeNastavnika() {
		return imeNastavnika;
	}

	public void setImeNastavnika(String imeNastavnika) {
		this.imeNastavnika = imeNastavnika;
	}

	public String getPrezimeNastavnika() {
		return prezimeNastavnika;
	}

	public void setPrezimeNastavnika(String prezimeNastavnika) {
		this.prezimeNastavnika = prezimeNastavnika;
	}


	
}
