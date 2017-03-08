package com.cimsolutions.entities;
// Generated 08-Mar-2017 16:38:13 by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Apuser generated by hbm2java
 */
@Entity
@Table(name = "apuser", catalog = "onderhouddb")
public class Apuser implements java.io.Serializable {

	private Integer id;
	private String username;
	private String bedrijf;
	private String password;
	private String token;
	private Boolean isAdmin;
	private String email;
	private String tel;
	private String district;

	public Apuser() {
	}

	public Apuser(String username, String bedrijf, String password, String token, Boolean isAdmin, String email,
			String tel, String district) {
		this.username = username;
		this.bedrijf = bedrijf;
		this.password = password;
		this.token = token;
		this.isAdmin = isAdmin;
		this.email = email;
		this.tel = tel;
		this.district = district;
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

	@Column(name = "username", length = 45)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "bedrijf", length = 45)
	public String getBedrijf() {
		return this.bedrijf;
	}

	public void setBedrijf(String bedrijf) {
		this.bedrijf = bedrijf;
	}

	@Column(name = "password", length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "token", length = 45)
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name = "isAdmin")
	public Boolean getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Column(name = "email", length = 45)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "tel", length = 45)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "district", length = 45)
	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

}
