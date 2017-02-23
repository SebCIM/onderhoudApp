package com.cimsolutions.service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cimsolutions.DAO.UserDao;
import com.cimsolutions.entities.Apuser;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDao userDAO;

	public void setUserDao(UserDao userDao) {
		this.userDAO = userDao;
	}

	@Override
	@Transactional
	public void addUser(Apuser p) {
		this.userDAO.addUser(p);
	}

	@Override
	@Transactional
	public void updateUser(Apuser p) {
		this.userDAO.updateUser(p);
	}

	@Override
	@Transactional
	public List<Apuser> listUsers() {
		return this.userDAO.listUsers();
	}

	@Override
	@Transactional
	public Apuser getUserById(int id) {
		return this.userDAO.getUserById(id);
	}

	@Override
	@Transactional
	public void removeUser(int id) {
		this.userDAO.removeUser(id);
	}

}