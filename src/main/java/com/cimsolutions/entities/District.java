package com.cimsolutions.entities;
// Generated 24-Mar-2017 10:12:06 by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * District generated by hbm2java
 */
@Entity
@Table(name = "district", catalog = "onderhouddb")
public class District implements java.io.Serializable {

	private int districtId;
	private String districtGebiedAfkorting;
	private int districtCode;
	private String districtGebied;
	private String districtNaam;

	public District() {
	}

	public District(int districtId, int districtCode, String districtGebied, String districtNaam) {
		this.districtId = districtId;
		this.districtCode = districtCode;
		this.districtGebied = districtGebied;
		this.districtNaam = districtNaam;
	}

	public District(int districtId, String districtGebiedAfkorting, int districtCode, String districtGebied,
			String districtNaam) {
		this.districtId = districtId;
		this.districtGebiedAfkorting = districtGebiedAfkorting;
		this.districtCode = districtCode;
		this.districtGebied = districtGebied;
		this.districtNaam = districtNaam;
	}

	@Id

	@Column(name = "DistrictID", unique = true, nullable = false)
	public int getDistrictId() {
		return this.districtId;
	}

	public void setDistrictId(int districtId) {
		this.districtId = districtId;
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

}
