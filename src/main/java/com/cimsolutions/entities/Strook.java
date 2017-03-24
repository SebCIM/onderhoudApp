package com.cimsolutions.entities;
// Generated 24-Mar-2017 10:12:06 by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Strook generated by hbm2java
 */
@Entity
@Table(name = "strook", catalog = "onderhouddb")
public class Strook implements java.io.Serializable {

	private int strookId;
	private String strook;
	private Integer volgorde;

	public Strook() {
	}

	public Strook(int strookId) {
		this.strookId = strookId;
	}

	public Strook(int strookId, String strook, Integer volgorde) {
		this.strookId = strookId;
		this.strook = strook;
		this.volgorde = volgorde;
	}

	@Id

	@Column(name = "StrookID", unique = true, nullable = false)
	public int getStrookId() {
		return this.strookId;
	}

	public void setStrookId(int strookId) {
		this.strookId = strookId;
	}

	@Column(name = "Strook", length = 65535)
	public String getStrook() {
		return this.strook;
	}

	public void setStrook(String strook) {
		this.strook = strook;
	}

	@Column(name = "Volgorde")
	public Integer getVolgorde() {
		return this.volgorde;
	}

	public void setVolgorde(Integer volgorde) {
		this.volgorde = volgorde;
	}

}
