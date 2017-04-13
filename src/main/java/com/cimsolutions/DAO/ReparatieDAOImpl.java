package com.cimsolutions.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.cimsolutions.entities.Apuser;
import com.cimsolutions.entities.Reparatie;
import com.cimsolutions.entities.Userreparatie;
import com.cimsolutions.utils.HibernateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ReparatieDAOImpl implements ReparatieDAO {
	UserReparatieDAOImpl daoUr = new UserReparatieDAOImpl();
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reparatie> listReparatie() {
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		try {
			// open session to work with database
			session.getTransaction().begin();
			List<Reparatie> reparatieList = session.createQuery("from Reparatie").list();
			if (reparatieList != null) {
				for (Reparatie r : reparatieList) {
					// logger.info("Reparatie List:" + r);
				}
				// close and commit transaction of current session
				session.getTransaction().commit();
				return reparatieList;
			}

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void updateRepair(Reparatie r) {
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();
		
		System.out.println("updaten");
		
		Transaction testTransaction = null;

		try {
			// open session to work with database
			testTransaction = session.beginTransaction();
			if (r != null) {
				session.update(r);
				testTransaction.commit();
				logger.info("Vorstschade is geupdate, Vorstschade details=" + r);
			}

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

	@Override
	public Reparatie getReparatieById(int id) {
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		try {
			// open session to work with database
			session.getTransaction().begin();
			Reparatie r = (Reparatie) session.load(Reparatie.class, new Integer(id));
			if (r != null) {
				// logger.info("Reparatie loaded successfully, Baan details=" + b);
				// close and commit transaction of current session
				session.getTransaction().commit();
				return r;
			}

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void removeRepair(int id) {
		// TODO Auto-generated method stub
		System.out.println("removing repair");
		
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		Transaction testTransaction = null;

		try {
			// open session to work with database
			testTransaction = session.beginTransaction();

			Reparatie r = (Reparatie) session.load(Reparatie.class, new Integer(id));

			if (r != null) {
				session.delete(r);
				testTransaction.commit();
				logger.info("Repair deleted successfully, User Repair=" + r);
			}

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
	}

}
