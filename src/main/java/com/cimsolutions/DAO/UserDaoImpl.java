package com.cimsolutions.DAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cimsolutions.entities.Apuser;
import com.cimsolutions.utils.HibernateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;

public class UserDaoImpl implements UserDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	public boolean checkLogin(String token) {
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		try {
			// open session to work with database
			session.getTransaction().begin();
			String sql = "Select e from Apuser e where e.token = :token";
			Query query = session.createQuery(sql);
			query.setParameter("token", token);
			Object user = query.uniqueResult();
			if (user != null) {
				// close and commit transaction of current session
				session.getTransaction().commit();
				return true;
			}

		} catch (Exception e) {
			// TODO: handle exception
			// if something go wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void addUser(Apuser u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(u);
		logger.info("User saved successfully, User Details=" + u);
	}

	@Override
	public void updateUser(Apuser u) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(u);
		logger.info("User updated successfully, User Details=" + u);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Apuser> listUsers() {
		System.out.println("test1");
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();
		
		try {
			// open session to work with database
			session.getTransaction().begin();
			List<Apuser> usersList = session.createQuery("from Apuser").list();
			if (usersList != null) {
				for (Apuser u : usersList) {
					logger.info("User List::" + u);
				}
				// close and commit transaction of current session
				session.getTransaction().commit();
				return usersList;
			}

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Apuser getUserById(int id) {
		System.out.println("test1");
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();
		
		try {
			// open session to work with database
			session.getTransaction().begin();
			Apuser u = (Apuser) session.load(Apuser.class, new Integer(id));
			if (u != null) {
				logger.info("User loaded successfully, User details=" + u);
				// close and commit transaction of current session
				session.getTransaction().commit();
				return u;
			}

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Apuser getUserByToken(String token) {
		System.out.println("test1");
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();
		
		try {
			// open session to work with database
			session.getTransaction().begin();
			Apuser u = (Apuser) session.load(Apuser.class, new String(token));
			if (u != null) {
				logger.info("User loaded successfully, User details=" + u);
				// close and commit transaction of current session
				session.getTransaction().commit();
				return u;
			}

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void removeUser(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Apuser u = (Apuser) session.load(Apuser.class, new Integer(id));
		if (null != u) {
			session.delete(u);
		}
		logger.info("User deleted successfully, User details=" + u);
	}
}
