package com.cimsolutions.DAO;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;  
import org.springframework.transaction.annotation.Transactional;

import com.cimsolutions.entities.Wegenlijst;
  
//CRUD operations
@Repository  
@Transactional  
public interface WegenDAO {

	//Read
	public Wegenlijst getWegById(int id);

	//Get All
	public List<Wegenlijst> listWegen();
}