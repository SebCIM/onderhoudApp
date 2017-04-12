package com.cimsolutions.entities;
// Generated 12-Apr-2017 14:26:57 by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Wegenlijst generated by hbm2java
 */
@Entity
@Table(name = "wegenlijst", catalog = "onderhouddb")
public class Wegenlijst implements java.io.Serializable {

	private int id;
	private String wegnummer;
	private String aanduiding;
	private String benaming;
	private String regionaleDirectie;
	private String district;
	private String hectometerbordBegin;
	private String hectometerbordEind;
	private String actueel;
	private String toegevoegd;
	private Set<Reparatie> reparaties = new HashSet<Reparatie>(0);

	public Wegenlijst() {
	}

	public Wegenlijst(int id) {
		this.id = id;
	}

	public Wegenlijst(int id, String wegnummer, String aanduiding, String benaming, String regionaleDirectie,
			String district, String hectometerbordBegin, String hectometerbordEind, String actueel, String toegevoegd,
			Set<Reparatie> reparaties) {
		this.id = id;
		this.wegnummer = wegnummer;
		this.aanduiding = aanduiding;
		this.benaming = benaming;
		this.regionaleDirectie = regionaleDirectie;
		this.district = district;
		this.hectometerbordBegin = hectometerbordBegin;
		this.hectometerbordEind = hectometerbordEind;
		this.actueel = actueel;
		this.toegevoegd = toegevoegd;
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

	@Column(name = "Wegnummer", length = 65535)
	public String getWegnummer() {
		return this.wegnummer;
	}

	public void setWegnummer(String wegnummer) {
		this.wegnummer = wegnummer;
	}

	@Column(name = "Aanduiding", length = 65535)
	public String getAanduiding() {
		return this.aanduiding;
	}

	public void setAanduiding(String aanduiding) {
		this.aanduiding = aanduiding;
	}

	@Column(name = "Benaming", length = 65535)
	public String getBenaming() {
		return this.benaming;
	}

	public void setBenaming(String benaming) {
		this.benaming = benaming;
	}

	@Column(name = "RegionaleDirectie", length = 65535)
	public String getRegionaleDirectie() {
		return this.regionaleDirectie;
	}

	public void setRegionaleDirectie(String regionaleDirectie) {
		this.regionaleDirectie = regionaleDirectie;
	}

	@Column(name = "District", length = 65535)
	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Column(name = "HectometerbordBegin", length = 65535)
	public String getHectometerbordBegin() {
		return this.hectometerbordBegin;
	}

	public void setHectometerbordBegin(String hectometerbordBegin) {
		this.hectometerbordBegin = hectometerbordBegin;
	}

	@Column(name = "HectometerbordEind", length = 65535)
	public String getHectometerbordEind() {
		return this.hectometerbordEind;
	}

	public void setHectometerbordEind(String hectometerbordEind) {
		this.hectometerbordEind = hectometerbordEind;
	}

	@Column(name = "Actueel", length = 65535)
	public String getActueel() {
		return this.actueel;
	}

	public void setActueel(String actueel) {
		this.actueel = actueel;
	}

	@Column(name = "Toegevoegd", length = 65535)
	public String getToegevoegd() {
		return this.toegevoegd;
	}

	public void setToegevoegd(String toegevoegd) {
		this.toegevoegd = toegevoegd;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "wegenlijst")
	public Set<Reparatie> getReparaties() {
		return this.reparaties;
	}

	public void setReparaties(Set<Reparatie> reparaties) {
		this.reparaties = reparaties;
	}

}
