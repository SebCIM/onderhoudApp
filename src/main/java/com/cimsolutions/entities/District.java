package com.cimsolutions.entities;
// Generated 31-Mar-2017 10:55:48 by Hibernate Tools 4.3.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * District generated by hbm2java
 */
@Entity
@Table(name = "district", catalog = "onderhouddb")
public class District implements java.io.Serializable {

	private int id;
	private String districtGebiedAfkorting;
	private int districtCode;
	private String districtGebied;
	private String districtNaam;
	private Set<Reparatie> reparaties = new HashSet<Reparatie>(0);

	public District() {
	}

	public District(int id, int districtCode, String districtGebied, String districtNaam) {
		this.id = id;
		this.districtCode = districtCode;
		this.districtGebied = districtGebied;
		this.districtNaam = districtNaam;
	}

	public District(int id, String districtGebiedAfkorting, int districtCode, String districtGebied,
			String districtNaam, Set<Reparatie> reparaties) {
		this.id = id;
		this.districtGebiedAfkorting = districtGebiedAfkorting;
		this.districtCode = districtCode;
		this.districtGebied = districtGebied;
		this.districtNaam = districtNaam;
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

	@Column(name = "DistrictGebiedAfkorting", length = 5)
	public String getDistrictGebiedAfkorting() {
		return this.districtGebiedAfkorting;
	}

	public void setDistrictGebiedAfkorting(String districtGebiedAfkorting) {
		this.districtGebiedAfkorting = districtGebiedAfkorting;
	}

	@Column(name = "DistrictCode", nullable = false)
	public int getDistrictCode() {
		return this.districtCode;
	}

	public void setDistrictCode(int districtCode) {
		this.districtCode = districtCode;
	}

	@Column(name = "DistrictGebied", nullable = false, length = 20)
	public String getDistrictGebied() {
		return this.districtGebied;
	}

	public void setDistrictGebied(String districtGebied) {
		this.districtGebied = districtGebied;
	}

	@Column(name = "DistrictNaam", nullable = false, length = 30)
	public String getDistrictNaam() {
		return this.districtNaam;
	}

	public void setDistrictNaam(String districtNaam) {
		this.districtNaam = districtNaam;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "district")
	public Set<Reparatie> getReparaties() {
		return this.reparaties;
	}

	public void setReparaties(Set<Reparatie> reparaties) {
		this.reparaties = reparaties;
	}

}
