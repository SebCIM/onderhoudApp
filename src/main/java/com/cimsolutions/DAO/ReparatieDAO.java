package com.cimsolutions.DAO;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;  
import org.springframework.transaction.annotation.Transactional;

import com.cimsolutions.entities.Apuser;  
import com.cimsolutions.entities.Reparatie;  
  
//CRUD operations
@Repository  
@Transactional  
public interface ReparatieDAO {
	
	//Create
	public void addReparatie(Reparatie r);
	//Read
	public Reparatie getReparatieById(int id);
	public Reparatie getReparatieByUser(Apuser u);
	//Update
	public void updateUser(Reparatie r);
	//Delete
	public void removeUser(int id);
	//Get All
	public List<Reparatie> listReparaties();
}