package com.cimsolutions.DAO;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;  
import org.springframework.transaction.annotation.Transactional;

import com.cimsolutions.entities.Apuser;
import com.cimsolutions.entities.District;  
  
//CRUD operations
@Repository  
@Transactional  
public interface DistrictDAO {

	//Read
	public District getDistrictById(int id);

	//Get All
	public List<District> listDistrict();
}