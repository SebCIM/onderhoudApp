package com.cimsolutions.DAO;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;  
import org.springframework.transaction.annotation.Transactional;

import com.cimsolutions.entities.Apuser;  
import com.cimsolutions.entities.Reparatie;
import com.cimsolutions.entities.Userreparatie;  
  
//CRUD operations
@Repository  
@Transactional  
public interface UserReparatieDAO {
	
	//Create
	public void addReparatie(Reparatie r, int id);
	//Read
	public Userreparatie getReparatieById(int id);
	public Reparatie getReparatieByUser(Apuser u);
	//Update
	public void updateUser(Reparatie r);
	//Delete
	public Userreparatie removeUserRepair(int id);
	//Get All
	public List<Userreparatie> listReparaties();
	
	public List<Userreparatie> listReparatiesById(int id);
}