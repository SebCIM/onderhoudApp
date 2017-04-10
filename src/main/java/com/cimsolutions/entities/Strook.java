package com.cimsolutions.entities;
// Generated 10-Apr-2017 10:46:59 by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Strook generated by hbm2java
 */
@Entity
@Table(name = "strook", catalog = "onderhouddb")
public class Strook implements java.io.Serializable {

	private int id;
	private String strook;
	private Integer volgorde;
	private Set<Reparatie> reparaties = new HashSet<Reparatie>(0);

	public Strook() {
	}

	public Strook(int id) {
		this.id = id;
	}

	public Strook(int id, String strook, Integer volgorde, Set<Reparatie> reparaties) {
		this.id = id;
		this.strook = strook;
		this.volgorde = volgorde;
		this.reparaties = reparaties;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "strook")
	public Set<Reparatie> getReparaties() {
		return this.reparaties;
	}

	public void setReparaties(Set<Reparatie> reparaties) {
		this.reparaties = reparaties;
	}

}
