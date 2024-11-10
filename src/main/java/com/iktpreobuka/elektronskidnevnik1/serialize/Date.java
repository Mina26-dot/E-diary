package com.iktpreobuka.elektronskidnevnik1.serialize;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Date {

	private int id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd")
    private String dan;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM")
    private String mesec;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
    private String godina;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private String sat;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "mm")
    private String minut;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "ss")
    private String sekund;
    
    public Date() {}

	public Date(int id, String dan, String mesec, String godina, String sat, String minut, String sekund) {
		super();
		this.id = id;
		this.dan = dan;
		this.mesec = mesec;
		this.godina = godina;
		this.sat = sat;
		this.minut = minut;
		this.sekund = sekund;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDan() {
		return dan;
	}

	public void setDan(String dan) {
		this.dan = dan;
	}

	public String getMesec() {
		return mesec;
	}

	public void setMesec(String mesec) {
		this.mesec = mesec;
	}

	public String getGodina() {
		return godina;
	}

	public void setGodina(String godina) {
		this.godina = godina;
	}

	public String getSat() {
		return sat;
	}

	public void setSat(String sat) {
		this.sat = sat;
	}

	public String getMinut() {
		return minut;
	}

	public void setMinut(String minut) {
		this.minut = minut;
	}

	public String getSekund() {
		return sekund;
	}

	public void setSekund(String sekund) {
		this.sekund = sekund;
	}
	
    
	
}
