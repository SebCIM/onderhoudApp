package com.cimsolutions.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cimsolutions.DAO.UserDaoImpl;
import com.cimsolutions.entities.Apuser;
import com.cimsolutions.entities.Reparatie;
import com.cimsolutions.entities.Userreparatie;
import com.cimsolutions.utils.HibernateUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class ReparatieDAOImpl implements ReparatieDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Userreparatie> listReparaties() {
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		try {
			// open session to work with database
			session.getTransaction().begin();
			List<Userreparatie> reparatieList = session.createQuery("from Userreparatie").list();
			if (reparatieList != null) {
				for (Userreparatie s : reparatieList) {
					logger.info("Reparatie List:" + s);
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
	public Userreparatie getReparatieById(int id) {
		// open session from class HibernateUtils
		SessionFactory factory = HibernateUtils.getSessionFactory();
		// get session to connect with database
		Session session = factory.getCurrentSession();

		try {
			// open session to work with database
			session.getTransaction().begin();
			Userreparatie s = (Userreparatie) session.load(Userreparatie.class, new Integer(id));
			if (s != null) {
				logger.info("Userreparatie loaded successfully, Userreparatie details=" + s);
				// close and commit transaction of current session
				session.getTransaction().commit();
				return s;
			}

		} catch (Exception e) {
			// if something goes wrong, we will rollback
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addReparatie(Reparatie r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Reparatie getReparatieByUser(Apuser u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(Reparatie r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeUser(int id) {
		// TODO Auto-generated method stub
		
	}

}