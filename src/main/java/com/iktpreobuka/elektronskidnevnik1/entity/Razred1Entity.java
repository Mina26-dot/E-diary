package com.iktpreobuka.elektronskidnevnik1.entity;

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
@Table(name = "Razred")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Razred1Entity {



	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonView(Views.Administrator.class)
    private int id;

    @Column(name = "razred_ucenika", nullable = false)
    @JsonView(Views.Private.class)
    private String razredUcenika;
    
    @Column(name = "odeljenje")
    @JsonView(Views.Private.class)
    private String odeljenje;


	    
	public Razred1Entity() {

	}

	public Razred1Entity(int id, String razredUcenika, String odeljenje) {
		super();
		this.id = id;
		this.razredUcenika = razredUcenika;
		this.odeljenje = odeljenje;
	
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRazredUcenika() {
		return razredUcenika;
	}

	public void setRazredUcenika(String razredUcenika) {
		this.razredUcenika = razredUcenika;
	}

	public String getOdeljenje() {
		return odeljenje;
	}

	public void setOdeljenje(String odeljenje) {
		this.odeljenje = odeljenje;
	}


	
	
}
