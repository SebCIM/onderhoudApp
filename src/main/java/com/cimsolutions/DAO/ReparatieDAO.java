package com.cimsolutions.DAO;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;  
import org.springframework.transaction.annotation.Transactional;

import com.cimsolutions.entities.Reparatie;
  
//CRUD operations
@Repository  
@Transactional  
public interface ReparatieDAO {

	//Read
	public Reparatie getReparatieById(int id);

	//Get All
	public List<Reparatie> listReparatie();
	
	// Remove
	public void removeRepair(int id);
}