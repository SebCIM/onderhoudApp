package com.cimsolutions.DAO;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
		System.out.println("add user");
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		Transaction testTransaction = null;

		try {
			// open session to work with database
			testTransaction = session.beginTransaction();
			System.out.println(u.getPassword());
			System.out.println(u.getUsername());
			System.out.println(u.getToken());
			System.out.println(u.getIsAdmin());
			session.save(u);
			testTransaction.commit();
			// session.persist(u);
			logger.info("User saved successfully, User Details=" + u);

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			if (!testTransaction.wasCommitted()) {
				testTransaction.rollback();
			}
		}
	}

	@Override
	public void updateUser(Apuser p) {
		System.out.println("removing user");
		System.out.println(p);
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		Transaction testTransaction = null;

		try {
			// open session to work with database
			testTransaction = session.beginTransaction();
			if (p != null) {
				session.update(p);
				testTransaction.commit();
				logger.info("User updated successfully, User details=" + p);
			}

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Apuser> listUsers() {
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
			Object userResult = query.uniqueResult();
			
			if (userResult != null) {
				// close and commit transaction of current session
				session.getTransaction().commit();
				Apuser user = (Apuser) userResult;
				return user;
			}

		} catch (Exception e) {
			// TODO: handle exception
			// if something go wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void removeUser(int id) {
		System.out.println("removing user");
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		Transaction testTransaction = null;

		try {
			// open session to work with database
			testTransaction = session.beginTransaction();

			Apuser u = (Apuser) session.load(Apuser.class, new Integer(id));

			if (u != null) {
				session.delete(u);
				testTransaction.commit();
				logger.info("User deleted successfully, User details=" + u);
			}

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}
}
