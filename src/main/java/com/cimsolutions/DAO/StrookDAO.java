package com.cimsolutions.DAO;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;  
import org.springframework.transaction.annotation.Transactional;

import com.cimsolutions.entities.Strook;
  
//CRUD operations
@Repository  
@Transactional  
public interface StrookDAO {

	//Read
	public Strook getStrookById(int id);

	//Get All
	public List<Strook> listStrook();
}