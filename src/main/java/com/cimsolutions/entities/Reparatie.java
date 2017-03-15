package com.cimsolutions.entities;
// Generated 15-Mar-2017 11:59:35 by Hibernate Tools 4.3.5.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Reparatie generated by hbm2java
 */
@Entity
@Table(name = "reparatie", catalog = "onderhouddb")
public class Reparatie implements java.io.Serializable {

	private Integer id;
	private String soort;
	private String district;
	private String rijksweg;
	private String hectometerbord;
	private String baan;
	private String strook;
	private String verhardingssoort;
	private String reparatiemethode;
	private Date datumtijd;
	private String noodofspoed;
	private String aantalgaten;
	private String opmerking;
	private Set<Userreparatie> userreparaties = new HashSet<Userreparatie>(0);

	public Reparatie() {
	}

	public Reparatie(String soort, String district, String rijksweg, String hectometerbord, String baan, String strook,
			String verhardingssoort, String reparatiemethode, Date datumtijd, String noodofspoed, String aantalgaten,
			String opmerking, Set<Userreparatie> userreparaties) {
		this.soort = soort;
		this.district = district;
		this.rijksweg = rijksweg;
		this.hectometerbord = hectometerbord;
		this.baan = baan;
		this.strook = strook;
		this.verhardingssoort = verhardingssoort;
		this.reparatiemethode = reparatiemethode;
		this.datumtijd = datumtijd;
		this.noodofspoed = noodofspoed;
		this.aantalgaten = aantalgaten;
		this.opmerking = opmerking;
		this.userreparaties = userreparaties;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "soort", length = 45)
	public String getSoort() {
		return this.soort;
	}

	public void setSoort(String soort) {
		this.soort = soort;
	}

	@Column(name = "district", length = 45)
	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	@Column(name = "rijksweg", length = 45)
	public String getRijksweg() {
		return this.rijksweg;
	}

	public void setRijksweg(String rijksweg) {
		this.rijksweg = rijksweg;
	}

	@Column(name = "hectometerbord", length = 45)
	public String getHectometerbord() {
		return this.hectometerbord;
	}

	public void setHectometerbord(String hectometerbord) {
		this.hectometerbord = hectometerbord;
	}

	@Column(name = "baan", length = 45)
	public String getBaan() {
		return this.baan;
	}

	public void setBaan(String baan) {
		this.baan = baan;
	}

	@Column(name = "strook", length = 45)
	public String getStrook() {
		return this.strook;
	}

	public void setStrook(String strook) {
		this.strook = strook;
	}

	@Column(name = "verhardingssoort", length = 45)
	public String getVerhardingssoort() {
		return this.verhardingssoort;
	}

	public void setVerhardingssoort(String verhardingssoort) {
		this.verhardingssoort = verhardingssoort;
	}

	@Column(name = "reparatiemethode", length = 45)
	public String getReparatiemethode() {
		return this.reparatiemethode;
	}

	public void setReparatiemethode(String reparatiemethode) {
		this.reparatiemethode = reparatiemethode;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "datumtijd", length = 19)
	public Date getDatumtijd() {
		return this.datumtijd;
	}

	public void setDatumtijd(Date datumtijd) {
		this.datumtijd = datumtijd;
	}

	@Column(name = "noodofspoed", length = 45)
	public String getNoodofspoed() {
		return this.noodofspoed;
	}

	public void setNoodofspoed(String noodofspoed) {
		this.noodofspoed = noodofspoed;
	}

	@Column(name = "aantalgaten", length = 45)
	public String getAantalgaten() {
		return this.aantalgaten;
	}

	public void setAantalgaten(String aantalgaten) {
		this.aantalgaten = aantalgaten;
	}

	@Column(name = "opmerking")
	public String getOpmerking() {
		return this.opmerking;
	}

	public void setOpmerking(String opmerking) {
		this.opmerking = opmerking;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reparatie")
	public Set<Userreparatie> getUserreparaties() {
		return this.userreparaties;
	}

	public void setUserreparaties(Set<Userreparatie> userreparaties) {
		this.userreparaties = userreparaties;
	}

}
