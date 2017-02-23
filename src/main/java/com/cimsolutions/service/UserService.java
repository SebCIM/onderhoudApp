package com.cimsolutions.service;

import java.util.List;

import com.cimsolutions.entities.Apuser;

public interface UserService {

	public void addUser(Apuser u);
	public void updateUser(Apuser u);
	public List<Apuser> listUsers();
	public Apuser getUserById(int id);
	public void removeUser(int id);
	
}