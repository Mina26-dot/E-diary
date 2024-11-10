package com.iktpreobuka.elektronskidnevnik1.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import jakarta.persistence.Table;

@Entity
@Table(name = "Izostanci")
public class Izostanci1Entity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JsonView(Views.Administrator.class)
    private int id;
    
    @Column(name = "Opravdani", nullable = true)
    @JsonView(Views.Private.class)
    private String opravdani;
    
    @Column(name = "Neopravdani", nullable = true)
    @JsonView(Views.Private.class)
    private String neopravdani;
    
    @Column(name = "Datum_Izostanka", nullable = true)
    @JsonView(Views.Private.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private String datumIzostanka;
    
    @ManyToOne
    @JoinColumn(name = "ucenik_id")
    @JsonIgnoreProperties({"roditelj", "razred"})
    private Ucenik1Entity ucenik;


	public Izostanci1Entity() {

	}


	public Izostanci1Entity(int id, String opravdani, String neopravdani, String datumIzostanka, Ucenik1Entity ucenik) {
		super();
		this.id = id;
		this.opravdani = opravdani;
		this.neopravdani = neopravdani;
		this.datumIzostanka = datumIzostanka;
		this.ucenik = ucenik;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getOpravdani() {
		return opravdani;
	}


	public void setOpravdani(String opravdani) {
		this.opravdani = opravdani;
	}


	public String getNeopravdani() {
		return neopravdani;
	}


	public void setNeopravdani(String neopravdani) {
		this.neopravdani = neopravdani;
	}


	public String getDatumIzostanka() {
		return datumIzostanka;
	}


	public void setDatumIzostanka(String datumIzostanka) {
		this.datumIzostanka = datumIzostanka;
	}


	public Ucenik1Entity getUcenik() {
		return ucenik;
	}


	public void setUcenik(Ucenik1Entity ucenik) {
		this.ucenik = ucenik;
	}

	
}
