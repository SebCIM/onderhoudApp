package com.cimsolutions.DAO;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cimsolutions.entities.Apuser;
import com.cimsolutions.entities.Reparatie;

public class ReparatieDAOImpl implements ReparatieDAO {
	private static final Logger logger = LoggerFactory.getLogger(ReparatieDAOImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
	
	//Create
	public void addReparatie(Reparatie r) {
	}
	//Read
	public Reparatie getReparatieById(int id) {
		return null;
	}
	public Reparatie getReparatieByUser(Apuser u) {
		return null;
	}
	//Update
	public void updateUser(Reparatie r) {
	}
	//Delete
	public void removeUser(int id) {
	}
	//Get All
	public List<Reparatie> listReparaties() {
		return null;
	}
}