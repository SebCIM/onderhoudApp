package com.cimsolutions.DAO;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;  
import org.springframework.transaction.annotation.Transactional;

import com.cimsolutions.entities.Apuser;  
  
//CRUD operations
@Repository  
@Transactional  
public interface UserDao {
	
	//Create
	public void addUser(Apuser p);
	//Read
	public Apuser getUserById(int id);
	public Apuser getUserByToken(String token);
	//Update
	public void updateUser(Apuser p);
	//Delete
	public void removeUser(int id);
	//Get All
	public List<Apuser> listUsers();
}