package com.cimsolutions.DAO;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;  
import org.springframework.transaction.annotation.Transactional;

import com.cimsolutions.entities.Baan;
  
//CRUD operations
@Repository  
@Transactional  
public interface BaanDAO {

	//Read
	public Baan getBaanById(int id);

	//Get All
	public List<Baan> listBaan();
}