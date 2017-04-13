package com.cimsolutions.DAO;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cimsolutions.entities.Reparatiemethoden;

//CRUD operations
@Repository
@Transactional
public interface MethodeDAO {

	// Read
	public Reparatiemethoden getMethodeById(int id);

	// Get All
	public List<Reparatiemethoden> listMethode();

}
