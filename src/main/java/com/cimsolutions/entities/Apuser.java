package com.cimsolutions.entities;
// Generated 23-Feb-2017 11:05:33 by Hibernate Tools 4.3.5.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Apuser generated by hbm2java
 */
@Entity
@Table(name = "apuser", catalog = "onderhouddb")
public class Apuser implements java.io.Serializable {

	private String username;
	private String lastname;
	private String password;
	private Boolean isAdmin;
	private String token;

	public Apuser() {
	}

	public Apuser(String username) {
		this.username = username;
	}

	public Apuser(String username, String lastname, String password, Boolean isAdmin, String token) {
		this.username = username;
		this.lastname = lastname;
		this.password = password;
		this.isAdmin = isAdmin;
		this.token = token;
	}

	@Id

	@Column(name = "username", unique = true, nullable = false, length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "lastname", length = 45)
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Column(name = "password", length = 45)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "isAdmin")
	public Boolean getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Column(name = "token", length = 45)
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
