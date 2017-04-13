package com.cimsolutions.entities;
// Generated 13-Apr-2017 16:50:56 by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Baan generated by hbm2java
 */
@Entity
@Table(name = "baan", catalog = "onderhouddb")
public class Baan implements java.io.Serializable {

	private int id;
	private String baanNaam;
	private Integer volgorde;

	public Baan() {
	}

	public Baan(int id) {
		this.id = id;
	}

	public Baan(int id, String baanNaam, Integer volgorde) {
		this.id = id;
		this.baanNaam = baanNaam;
		this.volgorde = volgorde;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "BaanNaam", length = 65535)
	public String getBaanNaam() {
		return this.baanNaam;
	}

	public void setBaanNaam(String baanNaam) {
		this.baanNaam = baanNaam;
	}

	@Column(name = "Volgorde")
	public Integer getVolgorde() {
		return this.volgorde;
	}

	public void setVolgorde(Integer volgorde) {
		this.volgorde = volgorde;
	}

}
