package com.iktpreobuka.elektronskidnevnik1.entity;


import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.elektronskidnevnik1.security.Views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table(name = "Roditelj")
public class Roditelj1Entity {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	@JsonView(Views.Administrator.class)
	private int id;

	@Column(name = "Ime_Roditelja", nullable = false)
	@JsonView(Views.Private.class)
	private String imeRoditelja;

	@Column(name = "Prezime_Roditelja", nullable = false)
	@JsonView(Views.Private.class)
	private String prezimeRoditelja;

	@Column(name = "Email_Roditelja", nullable = false)
	@JsonView(Views.Private.class)
	private String emailRoditelja;


	public Roditelj1Entity() {

	}

	public Roditelj1Entity(int id, String imeRoditelja, String prezimeRoditelja, String emailRoditelja
			) {
		super();
		this.id = id;
		this.imeRoditelja = imeRoditelja;
		this.prezimeRoditelja = prezimeRoditelja;
		this.emailRoditelja = emailRoditelja;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImeRoditelja() {
		return imeRoditelja;
	}

	public void setImeRoditelja(String imeRoditelja) {
		this.imeRoditelja = imeRoditelja;
	}

	public String getPrezimeRoditelja() {
		return prezimeRoditelja;
	}

	public void setPrezimeRoditelja(String prezimeRoditelja) {
		this.prezimeRoditelja = prezimeRoditelja;
	}

	public String getEmailRoditelja() {
		return emailRoditelja;
	}

	public void setEmailRoditelja(String emailRoditelja) {
		this.emailRoditelja = emailRoditelja;
	}


	


}
