package com.cimsolutions.DAO;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;  
import org.springframework.transaction.annotation.Transactional;

import com.cimsolutions.entities.Deklagenlijst;
import com.cimsolutions.entities.Wegenlijst;
  
//CRUD operations
@Repository  
@Transactional  
public interface DeklagenDAO {

	//Read
	public Deklagenlijst getDeklaagById(int id);

	//Get All
	public List<Deklagenlijst> listDeklagen();
	
	//Get Filtered Stroken
	public List<Deklagenlijst> listFilteredStroken();
	
	//Get Filtered Banen
	public List<Deklagenlijst> listFilteredBanen();
}